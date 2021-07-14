package com.example.rxjavademo.rxjava.demo;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Predicate;

public class FilterOperatorDemo {

    public static void main(String[] args) {
        System.out.println("==================");
        FilterOperatorDemo demo = new FilterOperatorDemo();
        demo.test1();
        System.out.println("==================");
    }

    private void test1() {
        Observable.range(1, 10)
                .filter(new Predicate<Integer>() {
                    @Override
                    public boolean test(@NonNull Integer integer) throws Exception {
                        return integer < 5;
                    }
                })
                .subscribe(observer);
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

}
