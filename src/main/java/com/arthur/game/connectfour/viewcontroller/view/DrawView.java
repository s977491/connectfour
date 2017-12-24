package com.arthur.game.connectfour.viewcontroller.view;

import com.google.inject.Inject;

/**
 * Created by Kay on 12/24/2017.
 */
public class DrawView extends SimpleAbstractView {

    @Inject
    public DrawView() {
    }

    @Override
    public void printOutput() {
        getPrintWriter().print(getBoardViewString());
        getPrintWriter().print("Game Draw");
        getPrintWriter().flush();
    }

}
