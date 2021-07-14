package com.example.rxjavademo.rxjava.demo;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.Observer;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Function;

public class TransOperatorDemo {

    public static void main(String[] args) {
        System.out.println("==========================");
        TransOperatorDemo demo = new TransOperatorDemo();
        demo.test3();
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
        // map: 对被观察者进行处理，把原来发射出来的事件进行处理并且产生新的事件，再次创建新的被观察者，发射事件
        Observable.just("aaa")
                .map(new Function<String, Object>() {
                    @Override
                    public Object apply(@NonNull String s) throws Exception {
                        return "BBBB";
                    }
                }).subscribe(observer);
    }

    private void test2() {
        // 网络请求场景当中常用的操作符
        Observable.just("111", "222", "333", "444", "55")
                .flatMap(new Function<String, ObservableSource<?>>() {
                    @Override
                    public ObservableSource<?> apply(@NonNull String s) throws Exception {
                        return Observable.just(s + "ssss");
                    }
                }).subscribe(observer);
    }


    private void test3() {
        // 网络请求场景当中常用的操作符
        Observable.just("111", "222", "333", "444", "55", "666", "777", "888", "999", "AAAAA")
                .buffer(3)
                .subscribe(observer);
    }


    private void test4() {
        // 网络请求场景当中常用的操作符
        Observable.just("111", "222", "333", "444", "55", "666", "777", "888", "999", "AAAAA")
                .buffer(3)
                .subscribe(observer);
    }

}
