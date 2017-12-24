package com.arthur.game.connectfour.module;

import com.arthur.game.connectfour.model.IGameModel;
import com.arthur.game.connectfour.model.SimpleGameModel;
import com.arthur.game.connectfour.viewcontroller.IGameViewController;
import com.arthur.game.connectfour.viewcontroller.SimpleGameViewController;
import com.google.inject.AbstractModule;
import com.google.inject.name.Names;

import java.io.InputStream;
import java.io.OutputStream;

/**
 * Created by laia on 11/23/2017.
 */
public class SimpleModule extends AbstractModule {

    protected void configure() {
        //basic game properties binding
        bind(Integer.class)
                .annotatedWith(Names.named("boardRows"))
                .toInstance(6);
        bind(Integer.class)
                .annotatedWith(Names.named("boardColumns"))
                .toInstance(7);
        bind(Integer.class)
                .annotatedWith(Names.named("winThreshold"))
                .toInstance(3);

        //input output binding
        bind(InputStream.class)
                .annotatedWith(Names.named("ConsoleInput")).toInstance(System.in);
        bind(OutputStream.class)
                .annotatedWith(Names.named("ConsoleOutput")).toInstance(System.out);

        //class binding
        bind(IGameModel.class).to(SimpleGameModel.class).asEagerSingleton();
        bind(IGameViewController.class).to(SimpleGameViewController.class).asEagerSingleton();

    }
}
