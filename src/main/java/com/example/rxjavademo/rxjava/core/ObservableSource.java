package com.example.rxjavademo.rxjava.core;

/**
 * 被观察者的顶层接口
 */
public interface ObservableSource<T> {

    /**
     * addObserver
     *
     * @param observer
     */
    void subscribe(Observer<T> observer);
}
