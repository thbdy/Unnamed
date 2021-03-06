package com.zhangf.unnamed.base;

import android.util.Log;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * 创建人：zhangf
 * 类描述：-
 * 日期：2017/7/13
 * 版权所有：
 */
public  abstract class BaseObserver<T> implements Observer<T>{
    private static final String TAG = "BaseObserver";
    @Override
    public void onSubscribe(Disposable d) {
        Log.d(TAG, "onSubscribe: ");

    }

    @Override
    public void onNext(T t) {
        onNextSuccess(t);
        Log.d(TAG, "onNext: ");

    }

    protected abstract void onNextSuccess(T t);

    @Override
    public void onError(Throwable e) {

        Log.e(TAG, "onError: "+e.getMessage() );


    }

    @Override
    public void onComplete() {
        Log.d(TAG, "onComplete: ");
    }
}
