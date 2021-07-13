package com.example.rxjavademo.rxjava.custom;

public class Main {


    public static void main(String[] args) {
        Observable.create(new ObservableOnSubscribe<Object>() {
            @Override
            public void subscribe(Emitter emitter) {
                emitter.onNext("11");
                emitter.onComplete();
            }
        }).subscribe(new Observer<Object>() {
            @Override
            public void onSubscribe() {
                System.out.println("onSubscribe");
            }

            @Override
            public void onNext(Object o) {
                System.out.println("onNext: " + o);

            }

            @Override
            public void onComplete() {
                System.out.println("onComplete");

            }

            @Override
            public void onError(Throwable throwable) {
                System.out.println("onError");

            }
        });
    }
}
