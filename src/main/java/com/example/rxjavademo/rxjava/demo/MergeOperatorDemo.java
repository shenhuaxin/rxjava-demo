package com.example.rxjavademo.rxjava.demo;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;

public class MergeOperatorDemo {

    public static void main(String[] args) {
        System.out.println("==========================");
        MergeOperatorDemo demo = new MergeOperatorDemo();
        demo.test1();
        System.out.println("==========================");
    }

    Observer observer = new Observer<Object>() {
        @Override
        public void onSubscribe(@NonNull Disposable d) {
            System.out.println("onSubscribe...");
        }

        @Override
        public void onNext(@NonNull Object o) {
            System.out.println("onNext..." + o);
        }

        @Override
        public void onError(@NonNull Throwable e) {
            System.out.println("onError..." + e.toString());
        }

        @Override
        public void onComplete() {
            System.out.println("onComplete...");
        }
    };

    private void test1() {
        Observable.concatArray(Observable.just("1111"),
                Observable.just("222"), Observable.just("1111"),
                Observable.just("222"), Observable.just("1111"),
                Observable.just("222"), Observable.just("1111"),
                Observable.just("222"))
                .subscribe(observer);
    }

}
