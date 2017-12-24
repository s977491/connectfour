package com.arthur.game.connectfour;

import com.arthur.game.connectfour.module.SimpleModule;
import com.arthur.game.connectfour.viewcontroller.IGameViewController;
import com.google.inject.Guice;
import com.google.inject.Injector;

/**
 * Hello world!
 */
public class App {
    public static void main(String[] args) {
        Injector injector = Guice.createInjector(new SimpleModule());
        IGameViewController gameViewController = injector.getInstance(IGameViewController.class);
        gameViewController.start();
    }
}
