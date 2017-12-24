package com.arthur.game.connectfour.viewcontroller.view;

import com.arthur.game.connectfour.model.fsm.GameState;
import com.google.inject.Inject;

import java.text.MessageFormat;

/**
 * Created by Kay on 12/24/2017.
 */
public class WinView extends SimpleAbstractView {

    @Inject
    public WinView() {
    }

    private MessageFormat winMsgFormat = new MessageFormat("Player {0} [{1}] wins!");

    protected String getWinText() {
        GameState currentState = getGameModel().getGameState();
        return winMsgFormat.format(
                new Object[]{
                        currentState.getPlayer().getId(),
                        currentState.getPlayer().getColor(),
                });
    }

    @Override
    public void printOutput() {
        getPrintWriter().print(getBoardViewString());
        getPrintWriter().print(getWinText());
        getPrintWriter().flush();
    }


}
