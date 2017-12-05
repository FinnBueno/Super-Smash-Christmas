package me.finnbon.supersmashchristmas.champion;

import me.finnbon.supersmashchristmas.SuperSmashChristmas;
import me.finnbon.supersmashchristmas.ability.CastType;
import me.finnbon.supersmashchristmas.util.Brawler;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerAnimationEvent;
import org.bukkit.event.player.PlayerItemHeldEvent;
import org.bukkit.event.player.PlayerToggleSneakEvent;

public class ChampionManager implements Listener {

    private final SuperSmashChristmas plugin;

    public ChampionManager(SuperSmashChristmas pl) {
        this.plugin = pl;
    }

    @EventHandler
    public void switchSlot(PlayerItemHeldEvent event) {
        int slotOld = event.getPreviousSlot();
        int slotNew = event.getNewSlot();
        if (slotOld == 0 && slotNew == 9)
            event.getPlayer().getInventory().setHeldItemSlot(3);
        else if (slotOld == 3 && slotNew == 4)
            event.getPlayer().getInventory().setHeldItemSlot(0);
        else if (slotNew > 3)
            event.getPlayer().getInventory().setHeldItemSlot(slotOld);
    }

    @EventHandler
    public void click(PlayerAnimationEvent event) {
        Player player = event.getPlayer();

        Brawler brawler = plugin.getArenaManager().getBrawler(player);
        if (brawler == null)
            return;

        if (!brawler.getArena().getState().isPvPAllowed())
            return;

        brawler.getChampion().castAbility(brawler, CastType.CLICK);
    }

    @EventHandler
    public void sneak(PlayerToggleSneakEvent event) {
        Player player = event.getPlayer();
        if (player.isSneaking())
            return;

        Brawler brawler = plugin.getArenaManager().getBrawler(player);
        if (brawler == null)
            return;

        if (!brawler.getArena().getState().isPvPAllowed())
            return;

        brawler.getChampion().castAbility(brawler, CastType.SNEAK);
    }

}