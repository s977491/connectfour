package com.arthur.game.connectfour.viewcontroller.cmd;

import com.arthur.game.connectfour.model.IGameModel;
import com.arthur.game.connectfour.model.fsm.GameState;
import com.google.inject.Inject;

/**
 * Created by Kay on 12/24/2017.
 */
public class CmdQuit implements ICmd {
    @Inject
    private IGameModel gameModel;

    @Override
    public void execute() {
        gameModel.setGameState(GameState.End);
    }
}
