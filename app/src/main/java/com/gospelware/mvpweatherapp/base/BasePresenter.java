package com.gospelware.mvpweatherapp.base;

/**
 * Created by ricogao on 05/05/2016.
 */
public interface BasePresenter {
    /**
     * for initialization, start service, prepare data etc
     */
    void viewStart();

    /**
     * for termination, release resource etc
     */
    void viewStop();
}
