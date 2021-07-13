package com.example.rxjavademo.rxjava;

import rx.Observable;
import rx.Observer;
import rx.observers.Observers;

public class ObserverDemo {


    public static void main(String[] args) {
//        // 1. 创建被观察者 Observable 对象
//        Observable<Integer> observable = Observable.create(new ObservableOnSubscribe<Integer>() {
//            // create() 是 RxJava 最基本的创造事件序列的方法
//            // 此处传入了一个 OnSubscribe 对象参数
//            // 当 Observable 被订阅时，OnSubscribe 的 call() 方法会自动被调用，即事件序列就会依照设定依次被触发
//            // 即观察者会依次调用对应事件的复写方法从而响应事件
//            // 从而实现被观察者调用了观察者的回调方法 & 由被观察者向观察者的事件传递，即观察者模式
//
//            // 2. 在复写的subscribe（）里定义需要发送的事件
//            @Override
//            public void subscribe(ObservableEmitter<Integer> emitter) throws Exception {
//                // 通过 ObservableEmitter类对象产生事件并通知观察者
//                // ObservableEmitter类介绍
//                // a. 定义：事件发射器
//                // b. 作用：定义需要发送的事件 & 向观察者发送事件
//                emitter.onNext(1);
//                emitter.onNext(2);
//                emitter.onNext(3);
//                emitter.onComplete();
//            }
//        });
    }
}
