package com.arthur.game.connectfour.viewcontroller.view;

import com.arthur.game.connectfour.IGameConstant;
import com.arthur.game.connectfour.exception.GameInputException;
import com.arthur.game.connectfour.model.fsm.GameState;
import com.arthur.game.connectfour.viewcontroller.cmd.CmdAddPiece;
import com.arthur.game.connectfour.viewcontroller.cmd.CmdQuit;
import com.arthur.game.connectfour.viewcontroller.cmd.ICmd;
import com.google.inject.Inject;
import com.google.inject.Provider;

import java.text.MessageFormat;

/**
 * Created by Kay on 12/24/2017.
 */
public class MainView extends SimpleAbstractView {

    @Inject
    private Provider<CmdQuit> cmdQuitProvider;
    @Inject
    private Provider<CmdAddPiece> cmdAddPieceProvider;

    @Inject
    public MainView() {
    }

    @Override
    public void printOutput() {
        getPrintWriter().print(getBoardViewString());
        getPrintWriter().print(getInputText());
        getPrintWriter().flush();
    }

    @Override
    public void doCmd() {
        try {
            ICmd cmd = parseCmdFromInput();
            cmd.execute();
        } catch (GameInputException e) {
            getPrintWriter().println(getErrorInputText(e));
        }
    }

    private MessageFormat inputMsgFormat = new MessageFormat("Player {0} [{1}] - choose column (1-{2}): ");

    protected String getInputText() {
        GameState currentState = getGameModel().getGameState();
        return inputMsgFormat.format(
                new Object[]{
                        currentState.getPlayer().getId(),
                        currentState.getPlayer().getColor(),
                        getGameModel().getBoardColumns()
                });
    }

    private MessageFormat inputErrMsgFormat = new MessageFormat("Invalid move: {0}");

    protected String getErrorInputText(GameInputException e) {
        return inputErrMsgFormat.format(new Object[]{e.getMessage()});
    }

    protected ICmd parseCmdFromInput() throws GameInputException {
        String txt = null;
        try {
            txt = getBufferedReader().readLine();
            txt = txt.trim();

            if (IGameConstant.CMD_QUIT.equals(txt))
                return cmdQuitProvider.get();
            int value = Integer.parseInt(txt);
            CmdAddPiece cmd = cmdAddPieceProvider.get();
            cmd.setPieceColumn(value);
            return cmd;
        } catch (Throwable t) {
            throw new GameInputException("Invalid Text[" + txt + "]", t);
        }
    }


}
