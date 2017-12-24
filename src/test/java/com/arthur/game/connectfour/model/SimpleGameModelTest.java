package com.arthur.game.connectfour.model;

import com.arthur.game.connectfour.IGameConstant;
import com.arthur.game.connectfour.exception.GameInputException;
import com.arthur.game.connectfour.model.fsm.GameState;
import com.arthur.game.connectfour.module.SimpleModule;
import com.google.inject.Guice;
import com.google.inject.Injector;
import junit.framework.Assert;
import junit.framework.TestCase;
//19870316

/**
 * Created by Kay on 12/23/2017.
 */
public class SimpleGameModelTest extends TestCase {

    private Injector injector;

    @Override
    protected void setUp() throws Exception {
        injector = Guice.createInjector(new SimpleModule());
    }

    public void testInitState() throws Exception {
        SimpleGameModel sgm = injector.getInstance(SimpleGameModel.class);
        assertEquals(sgm.getGameState(), GameState.RedTurn);
        for (int i = 0; i < sgm.getBoardRows(); ++i) {
            for (int j = 0; j < sgm.getBoardColumns(); ++j) {
                assertEquals(sgm.getBoard()[i][j], IGameConstant.EMPTY_PIECE_CHAR);
            }
        }
    }

    public void testProcessStack3() throws Exception {
        SimpleGameModel sgm = injector.getInstance(SimpleGameModel.class);
        for (int row = 0; row < 3; ++row) {
            for (int i = 0; i < sgm.getBoardColumns(); ++i) {
                GameState prevState = sgm.getGameState();
                sgm.process(i);
                assertEquals(sgm.getBoard()[row][i], prevState.getPlayer().getPiece());
                for (int otherRow = row + 1; otherRow < sgm.getBoardRows(); ++otherRow)
                    assertEquals(sgm.getBoard()[otherRow][i], IGameConstant.EMPTY_PIECE_CHAR);
            }
        }
    }

    public void testProcessWinHorizontal() throws Exception {
        SimpleGameModel sgm = injector.getInstance(SimpleGameModel.class);
        char presetBoard[][] = {
                {'R', 'R', 'R', ' ', 'G', 'G', 'G'}
                , {' ', ' ', ' ', ' ', ' ', ' ', ' '}
                , {' ', ' ', ' ', ' ', ' ', ' ', ' '}
                , {' ', ' ', ' ', ' ', ' ', ' ', ' '}
                , {' ', ' ', ' ', ' ', ' ', ' ', ' '}
                , {' ', ' ', ' ', ' ', ' ', ' ', ' '}
        };
        sgm.init(presetBoard);
        assertEquals(sgm.getGameState(), GameState.RedTurn);
        sgm.process(3);
        assertEquals(sgm.getGameState(), GameState.RedWin);
    }

    public void testProcessWinVertical() throws Exception {
        SimpleGameModel sgm = injector.getInstance(SimpleGameModel.class);
        char presetBoard[][] = {
                {'R', 'R', 'R', 'G', ' ', 'G', 'G'}
                , {'R', 'G', ' ', ' ', ' ', ' ', ' '}
                , {'R', 'G', ' ', ' ', ' ', ' ', ' '}
                , {' ', ' ', ' ', ' ', ' ', ' ', ' '}
                , {' ', ' ', ' ', ' ', ' ', ' ', ' '}
                , {' ', ' ', ' ', ' ', ' ', ' ', ' '}
        };
        sgm.init(presetBoard);
        assertEquals(sgm.getGameState(), GameState.RedTurn);
        sgm.process(0);
        assertEquals(sgm.getGameState(), GameState.RedWin);
    }

    public void testProcessWinDiagonal1() throws Exception {
        SimpleGameModel sgm = injector.getInstance(SimpleGameModel.class);
        char presetBoard[][] = {
                {'R', 'R', 'R', 'G', 'R', 'G', 'G'}
                , {'G', 'R', 'G', 'G', 'R', ' ', ' '}
                , {'R', 'G', ' ', ' ', ' ', ' ', ' '}
                , {' ', ' ', ' ', ' ', ' ', ' ', ' '}
                , {' ', ' ', ' ', ' ', ' ', ' ', ' '}
                , {' ', ' ', ' ', ' ', ' ', ' ', ' '}
        };
        sgm.init(presetBoard);
        assertEquals(sgm.getGameState(), GameState.RedTurn);
        sgm.process(2);
        assertEquals(sgm.getGameState(), GameState.GreenTurn);
        sgm.process(3);
        assertEquals(sgm.getGameState(), GameState.RedTurn);
        sgm.process(3);
        assertEquals(sgm.getGameState(), GameState.RedWin);
    }

    public void testProcessWinDiagonal2() throws Exception {
        SimpleGameModel sgm = injector.getInstance(SimpleGameModel.class);
        char presetBoard[][] = {
                {'R', 'G', 'R', 'G', 'R', 'G', 'G'}
                , {'G', 'G', 'G', 'R', 'G', 'R', ' '}
                , {'R', 'R', 'G', 'G', ' ', ' ', ' '}
                , {' ', ' ', ' ', ' ', ' ', ' ', ' '}
                , {' ', ' ', ' ', ' ', ' ', ' ', ' '}
                , {' ', ' ', ' ', ' ', ' ', ' ', ' '}
        };
        sgm.init(presetBoard);
        assertEquals(sgm.getGameState(), GameState.RedTurn);
        sgm.process(4);
        assertEquals(sgm.getGameState(), GameState.GreenTurn);
        sgm.process(4);
        assertEquals(sgm.getGameState(), GameState.GreenWin);
    }

    public void testProcessInvalidMoveAndDraw() throws Exception {
        SimpleGameModel sgm = injector.getInstance(SimpleGameModel.class);
        char presetBoard[][] = {
                {'R', 'R', 'R', 'G', 'R', 'R', 'R'}
                , {'G', 'G', 'G', 'R', 'G', 'G', 'G'}
                , {'R', 'R', 'G', 'G', 'G', 'R', 'R'}
                , {'R', 'R', 'R', 'G', 'R', 'R', 'R'}
                , {'G', 'G', 'R', 'R', 'R', 'G', 'G'}
                , {' ', 'R', 'G', 'G', 'G', 'R', ' '}
        };
        sgm.init(presetBoard);
        for (int i = 1; i <= 5; ++i) {
            try {
                assertEquals(sgm.getGameState(), GameState.RedTurn);
                sgm.process(i);
                Assert.fail("should thrown exception as overflow");
            } catch (GameInputException gie) {
            }
        }

        try {
            assertEquals(sgm.getGameState(), GameState.RedTurn);
            sgm.process(-1);
            Assert.fail("should thrown exception value too small");
        } catch (GameInputException gie) {
        }

        try {
            assertEquals(sgm.getGameState(), GameState.RedTurn);
            sgm.process(sgm.getBoardColumns());
            Assert.fail("should thrown exception value too big");
        } catch (GameInputException gie) {
        }

        sgm.process(0);
        assertEquals(sgm.getGameState(), GameState.GreenTurn);
        sgm.process(6);
        assertEquals(sgm.getGameState(), GameState.Draw);
    }
}