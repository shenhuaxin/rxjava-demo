package com.example.rxjavademo.rxjava.custom;

public interface ObservableOnSubscribe<T> {

    void subscribe(Emitter emitter);

}
