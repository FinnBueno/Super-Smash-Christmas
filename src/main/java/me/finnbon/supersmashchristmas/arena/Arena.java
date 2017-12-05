package me.finnbon.supersmashchristmas.arena;

import com.google.common.collect.Sets;
import me.finnbon.supersmashchristmas.util.Brawler;
import me.finnbon.supersmashchristmas.util.MsgUtil;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.*;

import java.util.*;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;

import static me.finnbon.supersmashchristmas.arena.GameState.*;

public class Arena implements Runnable {

    private final Location lobby;
    private final List<Location> spawns;
    private int maxPlayers;
    private String name;

    private transient Map<Brawler, Integer> players;
    private transient GameState state;

    private transient Scoreboard board;

    private transient long timer;
    private transient Objective obj;

    public Arena(Location lobby, Location[] spawns, String name, int maxPlayers) {
        this.name = name;
        this.maxPlayers = maxPlayers;
        this.lobby = lobby;
        this.spawns = Arrays.asList(spawns);
        initScoreboard();
        players = new HashMap<>();
        state = WAITING;
    }

    private void initScoreboard() {
        board = Bukkit.getScoreboardManager().getNewScoreboard();
        obj = board.registerNewObjective("Super Smash Xmas", "dummy");
        obj.setDisplaySlot(DisplaySlot.SIDEBAR);
        obj.setDisplayName("Super Smash Xmas");

        // set lines
        obj.getScore(ChatColor.GOLD + "Players:").setScore(10);
        obj.getScore(ChatColor.RESET.name()).setScore(9);
        Team team;
        for (Brawler br : players.keySet()) {
            team = board.registerNewTeam(br.getPlayer().getName());
            team.addEntry(br.getPlayer().getName());
            int lives = players.get(br);
            ChatColor color = lives == 1 ? ChatColor.RED : lives <= 3 ? ChatColor.GOLD : ChatColor.GREEN;
            team.setPrefix(color + br.getPlayer().getName());
            obj.getScore(br.getPlayer().getName()).setScore(lives);
        }

    }

    public void run() {
        switch (state) {
            case ENDING:
                runEnding();
                break;
            case PLAYING:
                runPlaying();
                break;
            case COUNTDOWN:
                runCountdown();
                break;
            case WAITING:
                runWaiting();
                break;
        }
    }

    private void runEnding() {

    }

    private void runPlaying() {

    }

    private void runCountdown() {

    }

    private void runWaiting() {

    }

    private void setState(GameState newState) {
        state = newState;

        switch (state) {
            case WAITING:
                break;
            case COUNTDOWN:
                timer = 100;
                for (Brawler brawler : players.keySet()) {
                    brawler.teleport(spawns.get(ThreadLocalRandom.current().nextInt(spawns.size())));
                    if (brawler.getChampion() == null)
                        brawler.setChampion(Champions.getRandomChamp().getInstance());
                    brawler.getChampion().preparePlayer(brawler);
                }
                break;
            case PLAYING:
                announce(MsgUtil.ARENA_FIGHT.get());
                break;
            case ENDING:
                timer = 100;
                announce(MsgUtil.ARENA_END.get("%player%", getWinner().getPlayer().getName()));
                break;
        }
    }

    public void join(Player player) {
        // TODO: Check if player is already in arena
        if (state != WAITING) {
            MsgUtil.ARENA_STARTED.send(player);
            return;
        }
        if (players.size() >= maxPlayers) {
            MsgUtil.ARENA_FULL.send(player);
            return;
        }
        player.teleport(lobby);

        // add scoreboard entry
        Team team = board.registerNewTeam(player.getName());
        team.addEntry(player.getName());
        team.setPrefix(ChatColor.GREEN + player.getName());
        obj.getScore(player.getName()).setScore(5);

        players.put(new Brawler(player, this), 5);
        if (players.size() >= maxPlayers) {
            setState(COUNTDOWN);
            return;
        }
        announce(MsgUtil.ARENA_START.get());
    }

    public void leave(Player player) {
        Iterator<Brawler> it = players.keySet().iterator();
        while (it.hasNext()) {
            Brawler br = it.next();
            if (br.getPlayer().getUniqueId() == player.getUniqueId()) {
                br.leave();
                it.remove();

                // remove scoreboard entry
                Team team = board.getTeam(player.getName());
                team.removeEntry(br.getPlayer().getName());
                team.unregister();

                MsgUtil.ARENA_LEAVE.send(br);
                announce(MsgUtil.ARENA_LEAVE_OTHER.get("%player%", br.getPlayer().getName()));
                return;
            }
        }
    }

    public void die(Brawler brawler) {
        int lives = players.get(brawler);
        if (lives >= 0) {
            players.put(brawler, lives - 1);
            Team team = board.getTeam(brawler.getPlayer().getName());
            ChatColor color = lives == 1 ? ChatColor.RED : lives <= 3 ? ChatColor.GOLD : ChatColor.GREEN;
            team.setPrefix(color + brawler.getPlayer().getName());
            obj.getScore(brawler.getPlayer().getName()).setScore(lives);
        } else {
            Team team = board.getTeam(brawler.getPlayer().getName());
            team.removeEntry(brawler.getPlayer().getName());
            team.unregister();
        }
    }

    private Brawler getWinner() {
        int alive = 0;
        Brawler aliveBrawler = null;
        for (Brawler br : players.keySet())
            if (players.get(br) > 0) {
                alive++;
                aliveBrawler = br;
            }
        if (alive != 1)
            return null;
        return aliveBrawler;
    }

    private void announce(String msg) {
        players.keySet().forEach(b -> b.getPlayer().sendMessage(msg));
    }

    public String getName() {
        return name;
    }

    public Brawler getBrawler(Player player) {
        for (Brawler br : players.keySet())
            if (br.getPlayer().getUniqueId() == player.getUniqueId())
                return br;
        return null;
    }

    public GameState getState() {
        return state;
    }
}