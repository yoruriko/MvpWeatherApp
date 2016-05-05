package com.gospelware.mvpweatherapp.main.presenter;

import android.text.TextUtils;

import com.gospelware.mvpweatherapp.main.MainContract;
import com.gospelware.mvpweatherapp.main.model.BaseModel;
import com.gospelware.mvpweatherapp.main.model.BaseModelBean;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import rx.Subscription;

/**
 * Created by ricogao on 28/04/2016.
 */
public class MainPresenter implements MainContract.Presenter {
    private MainContract.View mainView;

    public BaseModel baseModel;

    @Inject
    @Singleton
    public MainPresenter(MainContract.View mainView) {
        this.mainView = mainView;
        baseModel = new BaseModel(this);
    }

    @Override
    public void getDataById(String cityId) {
        if (TextUtils.isEmpty(cityId)) {
            mainView.showError("Please Enter CityId");
        } else {
            mainView.showProgressBar();
            baseModel.getData(cityId);
        }
    }

    @Override
    public void loadDataSuccess(List<BaseModelBean> list) {
        mainView.showData(list.get(0));
    }

    @Override
    public void loadDataFailure() {
        mainView.showData(null);
    }

    @Override
    public void viewStart() {

    }

    @Override
    public void viewStop() {
        //release Resource for Rxjava
        Subscription subscription = baseModel.subscription;
        if (subscription != null && subscription.isUnsubscribed()) {
            subscription.unsubscribe();
        }
    }
}
