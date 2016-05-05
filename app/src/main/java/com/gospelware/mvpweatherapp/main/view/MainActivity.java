package com.gospelware.mvpweatherapp.main.view;

import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;


import com.gospelware.mvpweatherapp.R;
import com.gospelware.mvpweatherapp.main.MainContract;
import com.gospelware.mvpweatherapp.main.presenter.DaggerMainComponent;
import com.gospelware.mvpweatherapp.main.presenter.MainComponent;
import com.gospelware.mvpweatherapp.main.presenter.MainPresenter;
import com.gospelware.mvpweatherapp.main.model.BaseModelBean;
import com.gospelware.mvpweatherapp.main.presenter.MainPresenterModule;

import javax.inject.Inject;
import javax.inject.Singleton;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class MainActivity extends AppCompatActivity implements MainContract.View {

    @BindView(R.id.textInfo)
    TextView info;
    @BindView(R.id.progress)
    ProgressBar progress;
    @BindView(R.id.cityEdt)
    EditText edt;

    @OnClick(R.id.buttonShow)
    void clickShow() {
        mainPresenter.getDataById(edt.getText().toString());
    }

    @Inject
    @Singleton
    MainPresenter mainPresenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        initPresenter();
//        initView();
    }

    @Override
    public void initPresenter() {
        MainComponent component = DaggerMainComponent
                .builder()
                .mainPresenterModule(new MainPresenterModule(this))
                .build();
        component.inject(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        mainPresenter.onStart();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mainPresenter.onStop();
    }

    @Override
    public void initView() {
        //// TODO: 05/05/2016 some init job for view
    }

    @Override
    public void showData(BaseModelBean bean) {
        if (bean == null) {
            info.setText("Fail");
        } else {
            BaseModelBean.WeatherInfoEntity entity = bean.getWeatherInfo();
            String city = entity.getCity();
            String wd = entity.getWD();
            String ws = entity.getWS();
            String time = entity.getTime();
            String data = "City:" + city + "\nWind Direction:" + wd + "\nWind Speed:" + ws + "\nTime:" + time;
            info.setText(data);
        }
        hideProgressBar();
    }

    @Override
    public void showError(String msg) {
        Toast.makeText(MainActivity.this,msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showProgressBar() {
        progress.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgressBar() {
        progress.setVisibility(View.GONE);
    }



}
