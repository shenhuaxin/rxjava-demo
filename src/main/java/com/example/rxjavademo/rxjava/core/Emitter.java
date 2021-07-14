package com.example.rxjavademo.rxjava.core;

/**
 * 事件发射器
 */
public interface Emitter<T> {

    void onNext(T t);

    void onComplete();

    void onError(Throwable throwable);

}
