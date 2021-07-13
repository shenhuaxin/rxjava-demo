package com.example.rxjavademo.rxjava.custom;

public interface ObservableSource<T> {

    void subscribe(Observer<T> observer);

}
