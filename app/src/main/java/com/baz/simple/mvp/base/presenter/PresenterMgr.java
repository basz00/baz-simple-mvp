package com.baz.simple.mvp.base.presenter;

import android.support.v4.util.SimpleArrayMap;
import android.util.Log;

/**
 * Created by abasofi on 22/04/17.
 */

public class PresenterMgr {
    private static PresenterMgr singleton;
    private SimpleArrayMap<String, Presenter> mPresenters;

    public static PresenterMgr getInstance(){
        if(null == singleton){
            singleton = new PresenterMgr();
        }
        return singleton;
    }

    @SuppressWarnings("unchecked")
    public final <T extends Presenter> T getPresenter(String tag,
                                                         PresenterFactory<T> presenterFactory){
        if(mPresenters == null){
            mPresenters = new SimpleArrayMap<>();
        }
        T p = null;
        try {
            p = (T) mPresenters.get(tag);
        }catch (Exception e){
            Log.w("PresenterActivity", "Duplicate Presenter " +
                    "tag identified: " + tag + ". This could " +
                    "cause issues with state.");
        }

        if(p == null){
            p = presenterFactory.createPresenter();
            mPresenters.put(tag, p);
        }

        return p;
    }

    public final void removePresenter(String tag){
        if(mPresenters != null){
            mPresenters.remove(tag);
        }
    }
}
