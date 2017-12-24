package com.arthur.game.connectfour.model.fsm;

import com.arthur.game.connectfour.IGameConstant;

/**
 * Created by Kay on 12/23/2017.
 */
public enum Player {
    Invalid(0, IGameConstant.PCOLOR_INVALID),
    Red(1, IGameConstant.PCOLOR_RED),
    Green(2, IGameConstant.PCOLOR_GREEN);


    private int id;
    private String color;
    private char piece;

    Player(int i, String color) {
        id = i;
        this.color = color;
        piece = color.charAt(0);
    }

    public int getId() {
        return id;
    }

    public String getColor() {
        return color;
    }

    public char getPiece() {
        return piece;
    }
}