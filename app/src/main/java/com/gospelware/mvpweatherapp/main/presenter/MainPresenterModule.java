package com.gospelware.mvpweatherapp.main.presenter;

import com.gospelware.mvpweatherapp.main.MainContract;

import dagger.Module;
import dagger.Provides;

/**
 * Created by ricogao on 05/05/2016.
 */
@Module
public class MainPresenterModule {
    private MainContract.View mainView;
    public MainPresenterModule(MainContract.View  mainView){
        this.mainView=mainView;
    }

    @Provides
    public MainContract.View  provideMainView(){
        return mainView;
    }
}
