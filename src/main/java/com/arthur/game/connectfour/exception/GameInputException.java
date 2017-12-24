package com.arthur.game.connectfour.exception;

public class GameInputException extends Exception {
    public GameInputException(String message, Throwable cause) {
        super(message, cause);
    }

    public GameInputException(String message) {
        super(message);
    }
}
