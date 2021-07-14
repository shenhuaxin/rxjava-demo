package com.example.rxjavademo.rxjava.core;

public interface Observer<T> {

    void onSubscribe();

    void onNext(T t);

    void onComplete();

    void onError(Throwable throwable);

}
