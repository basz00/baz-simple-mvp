package com.baz.simple.mvp.base.presenter;

import android.support.annotation.NonNull;

/**
 * Created by abasofi on 22/04/17.
 */

public interface PresenterFactory<T extends Presenter> {
    @NonNull
    T createPresenter();
}
