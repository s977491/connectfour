package com.arthur.game.connectfour.viewcontroller.view;

import com.arthur.game.connectfour.model.fsm.GameState;
import com.arthur.game.connectfour.module.SimpleModule;
import com.google.inject.AbstractModule;
import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.name.Names;
import com.google.inject.util.Modules;
import junit.framework.TestCase;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * Created by Kay on 12/24/2017.
 */
public class MainViewTest extends TestCase {
    private Injector injector;
    private ByteArrayOutputStream output;

    public void setUp() throws Exception {
        output = new ByteArrayOutputStream();
        injector = Guice.createInjector(
                Modules.override(new SimpleModule()).with(
                        new AbstractModule() {
                            @Override
                            protected void configure() {
                                bind(InputStream.class)
                                        .annotatedWith(Names.named("ConsoleInput")).toInstance(
                                        new ByteArrayInputStream("-1\n8\n1\nw\n".getBytes()));
                                bind(OutputStream.class)
                                        .annotatedWith(Names.named("ConsoleOutput")).toInstance(output);
                            }
                        }));

    }

    public void testPrintOutputGreen() throws Exception {
        MainView mainView = injector.getInstance(MainView.class);
        mainView.getGameModel().getBoard()[2][3] = 'R';

        mainView.getGameModel().setGameState(GameState.GreenTurn);
        mainView.printOutput();
        String result =
                "| | | | | | | |" + System.lineSeparator() +
                        "| | | | | | | |" + System.lineSeparator() +
                        "| | | | | | | |" + System.lineSeparator() +
                        "| | | |R| | | |" + System.lineSeparator() +
                        "| | | | | | | |" + System.lineSeparator() +
                        "| | | | | | | |" + System.lineSeparator() +
                        "" + System.lineSeparator() +
                        "Player 2 [GREEN] - choose column (1-7): ";
        assertEquals(result, output.toString());

    }

    public void testPrintOutputRed() throws Exception {
        MainView mainView = injector.getInstance(MainView.class);
        mainView.getGameModel().getBoard()[2][3] = 'R';
        mainView.printOutput();
        String result =
                "| | | | | | | |" + System.lineSeparator() +
                        "| | | | | | | |" + System.lineSeparator() +
                        "| | | | | | | |" + System.lineSeparator() +
                        "| | | |R| | | |" + System.lineSeparator() +
                        "| | | | | | | |" + System.lineSeparator() +
                        "| | | | | | | |" + System.lineSeparator() +
                        "" + System.lineSeparator() +
                        "Player 1 [RED] - choose column (1-7): ";
        assertEquals(result, output.toString());
    }

    public void testDoCmd() throws Exception {
        MainView mainView = injector.getInstance(MainView.class);

        mainView.getGameModel().getBoard()[2][3] = 'R';
        mainView.getGameModel().getBoard()[5][0] = 'R';
        mainView.getGameModel().getBoard()[4][0] = 'G';
        mainView.getGameModel().getBoard()[3][0] = 'R';
        mainView.getGameModel().getBoard()[2][0] = 'G';
        mainView.getGameModel().getBoard()[1][0] = 'R';
        mainView.getGameModel().getBoard()[0][0] = 'G';

        mainView.doCmd();
        mainView.doCmd();
        mainView.doCmd();
        mainView.doCmd();
        String result =
                "Invalid move: Input column number too small" + System.lineSeparator() +
                        "Invalid move: Input column number too big" + System.lineSeparator() +
                        "Invalid move: Column already full" + System.lineSeparator() +
                        "Invalid move: Invalid Text[w]" + System.lineSeparator();
        ;

        assertEquals(result, output.toString());
    }

}