package com.gospelware.mvpweatherapp.main.model;

import com.gospelware.mvpweatherapp.main.presenter.MainPresenter;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Path;
import rx.Observable;
import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by ricogao on 28/04/2016.
 */
public class BaseModel {
    String basUrl = "http://www.weather.com.cn/adat/sk/";

    private List<BaseModelBean> list = new ArrayList<>();
    private MainPresenter mainPresenter;
    private Retrofit retrofit;
    public Subscription subscription;

    public BaseModel(MainPresenter presenter) {
        this.mainPresenter = presenter;
    }

    protected void initRetrofit() {
        retrofit = new Retrofit.Builder()
                .baseUrl(basUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();
    }

    protected WeatherService getService() {
        if (retrofit == null) {
            initRetrofit();
        }
        return retrofit.create(WeatherService.class);
    }

    public void getData(String cityId) {

        WeatherService service = getService();

        subscription = service.getModelBean(cityId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new MySubscriber());
    }


    interface WeatherService {
        @GET("{cityId}" + ".html")
        Observable<BaseModelBean> getModelBean(@Path("cityId") String cityId);
    }

    class MySubscriber extends Subscriber<BaseModelBean> {

        @Override
        public void onCompleted() {
            mainPresenter.loadDataSuccess(list);
        }

        @Override
        public void onError(Throwable e) {
            mainPresenter.loadDataFailure();
        }

        @Override
        public void onNext(BaseModelBean baseModelBean) {
            list.add(baseModelBean);
        }
    }

}
