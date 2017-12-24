package com.arthur.game.connectfour.model.fsm;

public enum GameState {
    RedTurn {
        public GameState nextStateWin() {
            return RedWin;
        }

        public GameState nextState() {
            return GreenTurn;
        }

        @Override
        public Player getPlayer() {
            return Player.Red;
        }

    },
    GreenTurn {
        public GameState nextStateWin() {
            return GreenWin;
        }

        public GameState nextState() {
            return RedTurn;
        }

        @Override
        public Player getPlayer() {
            return Player.Green;
        }
    },
    Draw {
        public GameState nextStateWin() {
            return End;
        }

        public GameState nextState() {
            return End;
        }

        @Override
        public Player getPlayer() {
            return Player.Invalid;
        }
    },
    End {
        public GameState nextStateWin() {
            return End;
        }

        public GameState nextState() {
            return End;
        }

        @Override
        public Player getPlayer() {
            return Player.Invalid;
        }
    },
    RedWin {
        public GameState nextStateWin() {
            return End;
        }

        public GameState nextState() {
            return End;
        }

        @Override
        public Player getPlayer() {
            return Player.Red;
        }
    },
    GreenWin {
        public GameState nextStateWin() {
            return End;
        }

        public GameState nextState() {
            return End;
        }

        @Override
        public Player getPlayer() {
            return Player.Green;
        }
    };

    public abstract GameState nextStateWin();

    public abstract GameState nextState();

    public abstract Player getPlayer();

}
