package com.example.rxjavademo.rxjava.demo;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;

public class CreateOperatorDemo {

    public static void main(String[] args) {
        System.out.println("==================");
        CreateOperatorDemo demo = new CreateOperatorDemo();
        demo.test4();
        System.out.println("==================");
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
        Disposable d = Observable.create(new ObservableOnSubscribe<Object>() {
            @Override
            public void subscribe(@NonNull ObservableEmitter<Object> emitter) throws Exception {
//                // 事件产生的地方
//                emitter.onNext("1");
//                emitter.onNext("222");
//                emitter.onNext("aaaa");
//                //emitter.onComplete();
//                emitter.onError(new Throwable("手动丢出异常"));

                // 耗时操作
                // 网络请求
                // 异步操作都放在这里

            }
        }).subscribe(new Consumer<Object>() {
            @Override
            public void accept(Object o) throws Exception {
                System.out.println("accept.." + o);
            }
        }, new Consumer<Throwable>() {
            @Override
            public void accept(Throwable throwable) throws Exception {
                System.out.println("accept.." + throwable);
            }
        });
    }

    private void test2() {
        Observable.just("1", "AAAA", "2")
                .subscribe(observer);
    }

    private void test3() {
        Observable.fromArray("1", "AAAA", "2", "1", "AAAA", "2", "1", "AAAA", "2", "1", "AAAA", "2", "1", "AAAA", "2")
                .subscribe(observer);
    }

    private void test4() {
//        ArrayList<String> list = new ArrayList<>();
//        list.add("111");
//        list.add("222");
//        Observable.fromIterable(list)
//                .subscribe(observer);

//        Observable.fromFuture(new Future<Object>() {
//            @Override
//            public boolean cancel(boolean mayInterruptIfRunning) {
//                return false;
//            }
//
//            @Override
//            public boolean isCancelled() {
//                return false;
//            }
//
//            @Override
//            public boolean isDone() {
//                return false;
//            }
//
//            @Override
//            public Object get() throws ExecutionException, InterruptedException {
//                return "aaaa";
//            }
//
//            @Override
//            public Object get(long timeout, TimeUnit unit) throws ExecutionException, InterruptedException, TimeoutException {
//                return null;
//            }
//        }).subscribe(observer);

//        Observable.fromCallable(new Callable<Object>() {
//            @Override
//            public Object call() throws Exception {
//                return "aaa";
//            }
//        }).subscribe(observer);

    }


}
