package com.example.rxjavademo.rxjava.demo;

import androidx.appcompat.app.AppCompatActivity;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        test1();
    }

    private void test1() {
        Observable.create(new ObservableOnSubscribe<Object>() {
            @Override
            public void subscribe(@NonNull ObservableEmitter<Object> emitter) throws Exception {
                // 创建事件发射事件
                System.out.println("subscribe..." + Thread.currentThread());
                emitter.onNext("aaaa");
                emitter.onNext("bbb");
                emitter.onComplete();

            }
        })
                .doOnNext(new Consumer<Object>() {
                    @Override
                    public void accept(Object o) throws Exception {
                        System.out.println("doOnNext accept..." + Thread.currentThread());
                    }
                })
                .subscribeOn(Schedulers.newThread())// 主要来决定我执行subscribe方法所处的线程，也就是产生事件发射事件所在的线程
                .observeOn(AndroidSchedulers.mainThread())// 来决定下游事件被处理时所处的线程
                .map(new Function<Object, Object>() {
                    @Override
                    public Object apply(@NonNull Object o) throws Exception {
                        System.out.println("map apply..." + Thread.currentThread());
                        return "bbb";
                    }
                }).observeOn(Schedulers.io())
                .subscribe(new Observer<Object>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {
                        System.out.println("onSubscribe..." + Thread.currentThread());

                    }

                    @Override
                    public void onNext(@NonNull Object o) {
                        System.out.println("onNext..." + Thread.currentThread());
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        System.out.println("onError..." + Thread.currentThread());
                    }

                    @Override
                    public void onComplete() {
                        System.out.println("onComplete..." + Thread.currentThread());
                    }
                });

//        try {
//            Thread.sleep(20000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
    }
}