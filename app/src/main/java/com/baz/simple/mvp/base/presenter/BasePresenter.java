package com.baz.simple.mvp.base.presenter;

import com.bas.mvp.survive.mvp.screen.Screen;

/**
 * Created by abasofi on 22/04/17.
 */

public class BasePresenter<T extends Screen> implements Presenter<T> {

    protected T mScreen;

    @Override
    public void bindView(T t) {
        this.mScreen = t;
    }

    @Override
    public void unbindView() {
        mScreen = null;
    }
}
