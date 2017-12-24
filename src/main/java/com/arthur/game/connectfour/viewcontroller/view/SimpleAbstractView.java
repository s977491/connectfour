package com.arthur.game.connectfour.viewcontroller.view;

import com.arthur.game.connectfour.IGameConstant;
import com.arthur.game.connectfour.model.IGameModel;
import com.google.inject.Inject;
import com.google.inject.name.Named;

import java.io.*;

/**
 * Created by Kay on 12/24/2017.
 */
public abstract class SimpleAbstractView implements IView {

    @Inject
    @Named("ConsoleInput")
    protected InputStream consoleInputStream;

    @Inject
    @Named("ConsoleOutput")
    protected OutputStream consoleOutputStream;

    private BufferedReader bufferedReader;
    private PrintWriter printWriter;

    public BufferedReader getBufferedReader() {
        return bufferedReader;
    }

    public PrintWriter getPrintWriter() {
        return printWriter;
    }

    @Inject
    private IGameModel gameModel;

    public IGameModel getGameModel() {
        return gameModel;
    }


    @Inject
    public SimpleAbstractView() {
    }

    @Inject
    public void init() {
        bufferedReader = new BufferedReader(new InputStreamReader(consoleInputStream));
        printWriter = new PrintWriter(consoleOutputStream, true);

    }

    @Override
    public void doCmd() { //default action is move to next state
        gameModel.setGameState(gameModel.getGameState().nextState());
    }

    protected String getBoardViewString() {
        char[][] board = gameModel.getBoard();
        StringBuilder sb = new StringBuilder();
        for (int i = board.length - 1; i >= 0; i--) {
            char[] row = board[i];
            sb.append(IGameConstant.WALL_CHAR);
            for (int j = 0; j < row.length; j++) {
                char piece = row[j];
                sb.append(piece);
                sb.append(IGameConstant.WALL_CHAR);
            }
            sb.append(System.lineSeparator());
        }
        sb.append(System.lineSeparator());
        return sb.toString();
    }
}
