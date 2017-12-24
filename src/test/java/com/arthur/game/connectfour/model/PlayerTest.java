package com.arthur.game.connectfour.model;

import com.arthur.game.connectfour.model.fsm.Player;
import junit.framework.TestCase;

/**
 * Created by Kay on 12/23/2017.
 */
public class PlayerTest extends TestCase {
    public void testValue() throws Exception {

        assertEquals(Player.Green.getPiece(), 'G');
        assertEquals(Player.Red.getPiece(), 'R');
    }
}