package com.arthur.game.connectfour.viewcontroller.cmd;

import com.arthur.game.connectfour.exception.GameInputException;

/**
 * Created by Kay on 12/24/2017.
 */
public interface ICmd {
    void execute() throws GameInputException;
}
