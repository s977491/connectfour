package com.arthur.game.connectfour.model;

import com.arthur.game.connectfour.exception.GameInputException;
import com.arthur.game.connectfour.model.fsm.GameState;

/**
 * Created by laia on 12/22/2017.
 */
public interface IGameModel {
    GameState getGameState();

    void setGameState(GameState state);

    char[][] getBoard();

    int getBoardColumns();

    int getBoardRows();

    void process(int inputCol) throws GameInputException;

}
