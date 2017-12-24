package com.arthur.game.connectfour.viewcontroller;

import com.arthur.game.connectfour.model.IGameModel;
import com.arthur.game.connectfour.model.fsm.GameState;
import com.arthur.game.connectfour.viewcontroller.view.*;
import com.google.inject.Inject;

import java.util.EnumMap;

/**
 * Created by laia on 12/21/2017.
 */
public class SimpleGameViewController implements IGameViewController {
    @Inject
    private IGameModel gameModel;


    @Inject
    public SimpleGameViewController() {
    }

    private EnumMap<GameState, IView> stateViewMap = new EnumMap<>(GameState.class);
    @Inject
    private MainView mainView;
    @Inject
    private WinView winView;
    @Inject
    private DrawView drawView;
    @Inject
    private DefaultView defaultView;

    @Inject
    public void init() {

        stateViewMap.put(GameState.RedTurn, mainView);
        stateViewMap.put(GameState.RedWin, winView);
        stateViewMap.put(GameState.GreenTurn, mainView);
        stateViewMap.put(GameState.GreenWin, winView);
        stateViewMap.put(GameState.Draw, drawView);

    }

    public void start() {
        while (gameModel.getGameState() != GameState.End) {
            IView view = stateViewMap.get(gameModel.getGameState());
            if (view == null)
                view = defaultView;
            view.printOutput();
            view.doCmd();
        }
    }
}
