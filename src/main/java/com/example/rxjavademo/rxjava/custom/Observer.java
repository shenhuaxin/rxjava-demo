package com.example.rxjavademo.rxjava.custom;

public interface Observer<T> {

    /**
     * 建立订阅的回调接口。
     */
    void onSubscribe();

    void onNext(T t);

    void onComplete();

    void onError(Throwable throwable);
}
