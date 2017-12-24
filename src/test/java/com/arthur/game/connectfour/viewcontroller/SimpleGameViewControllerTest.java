package com.arthur.game.connectfour.viewcontroller;

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
 * Created by Kay on 12/23/2017.
 */
public class SimpleGameViewControllerTest extends TestCase {
    private ByteArrayOutputStream output;
    private Injector injector;

    public void setUp() throws Exception {
        output = new ByteArrayOutputStream();
        injector = Guice.createInjector(
                Modules.override(new SimpleModule()).with(
                        new AbstractModule() {
                            @Override
                            protected void configure() {
                                bind(InputStream.class)
                                        .annotatedWith(Names.named("ConsoleInput")).toInstance(new ByteArrayInputStream("4\n4\n5\n5\n3\n2\n6\n".getBytes()));
                                bind(OutputStream.class)
                                        .annotatedWith(Names.named("ConsoleOutput")).toInstance(output);
                            }
                        }));

    }

    public void testInput() throws Exception {
        IGameViewController gameViewController = injector.getInstance(IGameViewController.class);
        gameViewController.start();

        String resultString = "| | | | | | | |" + System.lineSeparator() +
                "| | | | | | | |" + System.lineSeparator() +
                "| | | | | | | |" + System.lineSeparator() +
                "| | | | | | | |" + System.lineSeparator() +
                "| | | | | | | |" + System.lineSeparator() +
                "| | | | | | | |" + System.lineSeparator() +
                "" + System.lineSeparator() +
                "Player 1 [RED] - choose column (1-7): " +
                "| | | | | | | |" + System.lineSeparator() +
                "| | | | | | | |" + System.lineSeparator() +
                "| | | | | | | |" + System.lineSeparator() +
                "| | | | | | | |" + System.lineSeparator() +
                "| | | | | | | |" + System.lineSeparator() +
                "| | | |R| | | |" + System.lineSeparator() +
                "" + System.lineSeparator() +
                "Player 2 [GREEN] - choose column (1-7): " +
                "| | | | | | | |" + System.lineSeparator() +
                "| | | | | | | |" + System.lineSeparator() +
                "| | | | | | | |" + System.lineSeparator() +
                "| | | | | | | |" + System.lineSeparator() +
                "| | | |G| | | |" + System.lineSeparator() +
                "| | | |R| | | |" + System.lineSeparator() +
                "" + System.lineSeparator() +
                "Player 1 [RED] - choose column (1-7): " +
                "| | | | | | | |" + System.lineSeparator() +
                "| | | | | | | |" + System.lineSeparator() +
                "| | | | | | | |" + System.lineSeparator() +
                "| | | | | | | |" + System.lineSeparator() +
                "| | | |G| | | |" + System.lineSeparator() +
                "| | | |R|R| | |" + System.lineSeparator() +
                "" + System.lineSeparator() +
                "Player 2 [GREEN] - choose column (1-7): " +
                "| | | | | | | |" + System.lineSeparator() +
                "| | | | | | | |" + System.lineSeparator() +
                "| | | | | | | |" + System.lineSeparator() +
                "| | | | | | | |" + System.lineSeparator() +
                "| | | |G|G| | |" + System.lineSeparator() +
                "| | | |R|R| | |" + System.lineSeparator() +
                "" + System.lineSeparator() +
                "Player 1 [RED] - choose column (1-7): " +
                "| | | | | | | |" + System.lineSeparator() +
                "| | | | | | | |" + System.lineSeparator() +
                "| | | | | | | |" + System.lineSeparator() +
                "| | | | | | | |" + System.lineSeparator() +
                "| | | |G|G| | |" + System.lineSeparator() +
                "| | |R|R|R| | |" + System.lineSeparator() +
                "" + System.lineSeparator() +
                "Player 2 [GREEN] - choose column (1-7): " +
                "| | | | | | | |" + System.lineSeparator() +
                "| | | | | | | |" + System.lineSeparator() +
                "| | | | | | | |" + System.lineSeparator() +
                "| | | | | | | |" + System.lineSeparator() +
                "| | | |G|G| | |" + System.lineSeparator() +
                "| |G|R|R|R| | |" + System.lineSeparator() +
                "" + System.lineSeparator() +
                "Player 1 [RED] - choose column (1-7): " +
                "| | | | | | | |" + System.lineSeparator() +
                "| | | | | | | |" + System.lineSeparator() +
                "| | | | | | | |" + System.lineSeparator() +
                "| | | | | | | |" + System.lineSeparator() +
                "| | | |G|G| | |" + System.lineSeparator() +
                "| |G|R|R|R|R| |" + System.lineSeparator() +
                "" + System.lineSeparator() +
                "Player 1 [RED] wins!";
        assertEquals(resultString, output.toString());
    }
}