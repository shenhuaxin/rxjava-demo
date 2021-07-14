package com.example.rxjavademo.rxjava.core;

public interface ObservableOnSubscribe<T> {

    void subscribe(Emitter<T> emitter);
}
