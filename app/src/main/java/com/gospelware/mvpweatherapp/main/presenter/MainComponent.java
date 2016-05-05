package com.gospelware.mvpweatherapp.main.presenter;


import com.gospelware.mvpweatherapp.main.view.MainActivity;

import dagger.Component;

/**
 * Created by ricogao on 28/04/2016.
 */
@Component(modules = {MainPresenterModule.class})
public interface MainComponent {
    void inject(MainActivity mainActivity);
}
