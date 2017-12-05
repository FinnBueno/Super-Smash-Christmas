package me.finnbon.supersmashchristmas.arena;

public enum GameState {

    WAITING(false, true), COUNTDOWN(false, true), PLAYING(true, true), ENDING(false, true);

    private boolean pvpAllowed;
    private boolean movingAllowed;

    GameState(boolean pvp, boolean move) {
        pvpAllowed = pvp;
        movingAllowed = move;
    }

    public boolean isMovingAllowed() {
        return movingAllowed;
    }

    public boolean isPvPAllowed() {
        return pvpAllowed;
    }
}