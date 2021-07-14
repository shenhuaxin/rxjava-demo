package com.example.rxjavademo.rxjava.core;

import com.dongnaoedu.rxjava.core.scheduler.Scheduler;

public class ObservableSubscribeOn<T> extends AbstractObservableWithUpStream<T, T> {

    final Scheduler scheduler;

    public ObservableSubscribeOn(ObservableSource<T> source, Scheduler scheduler) {
        super(source);
        this.scheduler = scheduler;
    }

    @Override
    protected void subscribeActual(Observer<T> observer) {
        observer.onSubscribe();
        Scheduler.Worker worker = scheduler.createWorker();
        worker.schedule(new SubscribeTask(new SubscribeOnObserver<>(observer)));
    }

    static class SubscribeOnObserver<T> implements Observer<T> {

        final Observer<T> downStream;

        public SubscribeOnObserver(Observer<T> downStream) {
            this.downStream = downStream;
        }

        @Override
        public void onSubscribe() {

        }

        @Override
        public void onNext(T t) {
            downStream.onNext(t);
        }

        @Override
        public void onComplete() {
            downStream.onComplete();
        }

        @Override
        public void onError(Throwable throwable) {
            downStream.onError(throwable);
        }
    }

    final class SubscribeTask implements Runnable {

        final SubscribeOnObserver<T> parent;

        public SubscribeTask(SubscribeOnObserver<T> parent) {
            this.parent = parent;
        }

        @Override
        public void run() {
            source.subscribe(parent);
        }
    }

}
