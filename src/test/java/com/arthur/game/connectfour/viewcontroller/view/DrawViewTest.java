package com.arthur.game.connectfour.viewcontroller.view;

import com.arthur.game.connectfour.module.SimpleModule;
import com.google.inject.AbstractModule;
import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.name.Names;
import com.google.inject.util.Modules;
import junit.framework.TestCase;

import java.io.ByteArrayOutputStream;
import java.io.OutputStream;

/**
 * Created by Kay on 12/24/2017.
 */
public class DrawViewTest extends TestCase {
    private Injector injector;
    private ByteArrayOutputStream output;

    public void setUp() throws Exception {
        output = new ByteArrayOutputStream();
        injector = Guice.createInjector(
                Modules.override(new SimpleModule()).with(
                        new AbstractModule() {
                            @Override
                            protected void configure() {
                                bind(OutputStream.class)
                                        .annotatedWith(Names.named("ConsoleOutput")).toInstance(output);
                            }
                        }));

    }

    public void testPrintOutput() throws Exception {
        DrawView drawView = injector.getInstance(DrawView.class);
        drawView.getGameModel().getBoard()[2][3] = 'R';
        drawView.printOutput();
        String result =
                "| | | | | | | |" + System.lineSeparator() +
                        "| | | | | | | |" + System.lineSeparator() +
                        "| | | | | | | |" + System.lineSeparator() +
                        "| | | |R| | | |" + System.lineSeparator() +
                        "| | | | | | | |" + System.lineSeparator() +
                        "| | | | | | | |" + System.lineSeparator() +
                        "" + System.lineSeparator() +
                        "Game Draw";
        assertEquals(result, output.toString());

    }

}