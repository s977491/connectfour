package com.arthur.game.connectfour.model;

import com.arthur.game.connectfour.IGameConstant;
import com.arthur.game.connectfour.exception.GameInputException;
import com.arthur.game.connectfour.model.fsm.GameState;
import com.google.inject.Inject;
import com.google.inject.name.Named;

/**
 * Created by laia on 12/21/2017.
 */
public class SimpleGameModel implements IGameModel {

    @Inject
    @Named("boardColumns")
    private Integer boardColumns;

    @Inject
    @Named("boardRows")
    private Integer boardRows;

    @Inject
    @Named("winThreshold")
    private int winThreshold;


    @Override
    public int getBoardColumns() {
        return boardColumns;
    }

    @Override
    public int getBoardRows() {
        return boardRows;
    }

    @Override
    public GameState getGameState() {
        return gameState;
    }

    @Override
    public void setGameState(GameState gameState) {
        this.gameState = gameState;
    }

    @Override
    public char[][] getBoard() {
        return board;
    }

    @Inject
    public SimpleGameModel() {
    }

    @Inject
    public void init() {
        char[][] initBoard = new char[boardRows][boardColumns];
        for (int i = 0; i < boardRows; i++) {
            for (int j = 0; j < boardColumns; j++) {
                initBoard[i][j] = IGameConstant.EMPTY_PIECE_CHAR;
            }
        }
        init(initBoard);
    }

    public void init(char[][] presetBoard) {
        gameState = GameState.RedTurn;
        this.board = presetBoard;
        assert (this.board[0].length == this.boardColumns);
        assert (this.board.length == this.boardRows);
    }

    private GameState gameState;
    private char[][] board;

    public void process(int inputCol) throws GameInputException {
        if (inputCol < 0)
            throw new GameInputException("Input column number too small");
        if (inputCol >= boardColumns)
            throw new GameInputException("Input column number too big");
        if (board[boardRows - 1][inputCol] != IGameConstant.EMPTY_PIECE_CHAR)
            throw new GameInputException("Column already full");

        int finalRow;
        for (finalRow = 0; finalRow < boardRows; ++finalRow) {
            if (board[finalRow][inputCol] == IGameConstant.EMPTY_PIECE_CHAR) {
                break;
            }
        }
        board[finalRow][inputCol] = gameState.getPlayer().getPiece();
        if (checkWin(finalRow, inputCol)) {
            gameState = gameState.nextStateWin();
        } else if (checkFull()) {
            gameState = gameState.Draw;
        } else {
            gameState = gameState.nextState();
        }
    }

    private boolean checkFull() {
        for (int i = 0; i < boardColumns; ++i)
            if (board[boardRows - 1][i] == IGameConstant.EMPTY_PIECE_CHAR)
                return false;

        return true;
    }

    private boolean checkWin(int row, int col) {
        int cnt;
        cnt = getCnt(row, col, 1, 0) + getCnt(row, col, -1, 0) + 1;
        if (cnt > winThreshold)
            return true;

        cnt = getCnt(row, col, 0, 1) + getCnt(row, col, 0, -1) + 1;
        if (cnt > winThreshold)
            return true;

        cnt = getCnt(row, col, 1, 1) + getCnt(row, col, -1, -1) + 1;
        if (cnt > winThreshold)
            return true;

        cnt = getCnt(row, col, 1, -1) + getCnt(row, col, -1, 1) + 1;
        if (cnt > winThreshold)
            return true;

        return false;
    }

    //get the cnt in certain direction excluding itself
    private int getCnt(int row, int col, int rdir, int cdir) {
        int cnt = 0;
        if (rdir == 0 && cdir == 0) {
            return 0;
        }
        while (true) {
            int newRow = row + rdir;
            int newCol = col + cdir;
            if (newRow < 0 || newRow >= boardRows)
                break;
            if (newCol < 0 || newCol >= boardColumns)
                break;
            if (board[newRow][newCol] != board[row][col])
                break;
            row = newRow;
            col = newCol;
            ++cnt;
        }
        return cnt;
    }

}
