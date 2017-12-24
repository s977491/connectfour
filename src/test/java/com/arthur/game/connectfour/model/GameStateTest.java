package com.arthur.game.connectfour.model;

import com.arthur.game.connectfour.model.fsm.GameState;
import com.arthur.game.connectfour.model.fsm.Player;
import junit.framework.TestCase;

/**
 * Created by Kay on 12/23/2017.
 */
public class GameStateTest extends TestCase {
    public void testStateChange() throws Exception {
        assertEquals(GameState.GreenTurn.nextStateWin(), GameState.GreenWin);
        assertEquals(GameState.RedTurn.nextStateWin(), GameState.RedWin);
        assertEquals(GameState.GreenTurn.nextState(), GameState.RedTurn);
        assertEquals(GameState.RedTurn.nextState(), GameState.GreenTurn);
    }

    public void testStateProperties() throws Exception {
        assertEquals(GameState.GreenTurn.getPlayer(), GameState.GreenWin.getPlayer());
        assertEquals(GameState.GreenTurn.getPlayer(), Player.Green);
        assertEquals(GameState.RedTurn.getPlayer(), GameState.RedWin.getPlayer());
        assertEquals(GameState.RedTurn.getPlayer(), Player.Red);
    }
}