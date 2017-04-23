package com.baz.simple.mvp.base.presenter;

import com.bas.mvp.survive.mvp.screen.Screen;

/**
 * Created by abasofi on 22/04/17.
 */

public interface Presenter<T extends Screen> {
    void bindView(T t);
    void unbindView();
}
