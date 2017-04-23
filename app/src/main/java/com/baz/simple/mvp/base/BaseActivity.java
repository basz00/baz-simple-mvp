package com.baz.simple.mvp.base;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.baz.simple.mvp.base.presenter.Presenter;
import com.baz.simple.mvp.base.presenter.PresenterFactory;
import com.baz.simple.mvp.base.presenter.PresenterMgr;

import java.util.HashMap;

public abstract class BaseActivity extends AppCompatActivity {

    boolean mIsDestroyedBySystem;
    private PresenterMgr mPresenterMgr = PresenterMgr.getInstance();
    private HashMap<String, PresenterFactory> mPresenterFactoryHashMap = new HashMap<>();

    @Override
    protected void onResume() {
        super.onResume();
        mIsDestroyedBySystem = false;
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        mIsDestroyedBySystem = true;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(!mIsDestroyedBySystem){
            for(PresenterFactory presenterFactory : mPresenterFactoryHashMap.values()){
                mPresenterMgr.removePresenter(presenterFactory.getClass().getName());
            }
        }
    }

    protected <T extends Presenter> T createPresenter(PresenterFactory<T> presenterFactory){
        String tag = presenterFactory.getClass().getName();
        mPresenterFactoryHashMap.put(tag, presenterFactory);
        return mPresenterMgr.getPresenter(tag, presenterFactory);
    }
}
