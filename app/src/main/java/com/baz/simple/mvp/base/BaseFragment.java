package com.baz.simple.mvp.base;

import android.os.Bundle;
import android.support.v4.app.Fragment;

import com.baz.simple.mvp.base.presenter.Presenter;
import com.baz.simple.mvp.base.presenter.PresenterFactory;
import com.baz.simple.mvp.base.presenter.PresenterMgr;

import java.util.HashMap;

/**
 * Created by abasofi on 23/04/17.
 */

public class BaseFragment<T extends Presenter> extends Fragment {
    boolean mIsDestroyedBySystem;
    private PresenterMgr mPresenterMgr = PresenterMgr.getInstance();
    private HashMap<String, PresenterFactory> mPresenterFactoryHashMap = new HashMap<>();

    @Override
    public void onResume() {
        super.onResume();
        mIsDestroyedBySystem = false;
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        mIsDestroyedBySystem = true;
    }

    @Override
    public void onDestroy() {
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
