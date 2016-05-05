package com.gospelware.mvpweatherapp.main;

import com.gospelware.mvpweatherapp.base.BasePresenter;
import com.gospelware.mvpweatherapp.base.BaseView;
import com.gospelware.mvpweatherapp.main.model.BaseModelBean;

import java.util.List;


/**
 * Created by ricogao on 05/05/2016.
 */
public class MainContract {

    public interface Presenter extends BasePresenter{
        void getDataById(String cityId);
        void loadDataSuccess(List<BaseModelBean> list);
        void loadDataFailure();
    }

    public interface View extends BaseView{
        void initView();
        void showData(BaseModelBean bean);
        void showError(String msg);
        void showProgressBar();
        void hideProgressBar();
    }
}
