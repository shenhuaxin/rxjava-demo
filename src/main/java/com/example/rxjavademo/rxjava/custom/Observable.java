package com.example.rxjavademo.rxjava.custom;

public abstract class Observable<T> implements ObservableSource<T> {


    @Override
    public void subscribe(Observer<T> observer) {
        subscribeActual(observer);
    }


    protected abstract void subscribeActual(Observer<T> observer);


    public static <T> Observable<T> create(ObservableOnSubscribe<T> source) {
        return new ObservableCreate<>(source);
    }
}
