package com.arthur.game.connectfour.viewcontroller.cmd;

import com.arthur.game.connectfour.exception.GameInputException;
import com.arthur.game.connectfour.model.IGameModel;
import com.google.inject.Inject;

/**
 * Created by Kay on 12/24/2017.
 */
public class CmdAddPiece implements ICmd {
    @Inject
    private IGameModel gameModel;
    private int pieceColumn;

    public void setPieceColumn(int pieceColumn) {
        this.pieceColumn = pieceColumn;
    }

    @Override
    public void execute() throws GameInputException {
        gameModel.process(pieceColumn - 1); //convert to 0 index used by model

    }
}
