package org.example;

public enum GameStates {
    PLAYING,
    MENU;
    public static GameStates gameState = MENU;

    public static void SetGameState(GameStates state){
        gameState = state;
    }
}
