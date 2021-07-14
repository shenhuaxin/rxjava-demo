package com.example.rxjavademo.rxjava.core;

import com.example.rxjavademo.rxjava.core.scheduler.Scheduler;

import java.util.ArrayDeque;
import java.util.Deque;

public class ObservableObserveOn<T> extends AbstractObservableWithUpStream<T, T> {

    final Scheduler scheduler;

    public ObservableObserveOn(ObservableSource<T> source, Scheduler scheduler) {
        super(source);
        this.scheduler = scheduler;
    }

    @Override
    protected void subscribeActual(Observer<T> observer) {
        Scheduler.Worker worker = scheduler.createWorker();
        source.subscribe(new ObserveOnObserver<>(observer, worker));
    }

    static final class ObserveOnObserver<T> implements Observer<T>, Runnable {

        final Observer<T> downSteam;
        final Scheduler.Worker worker;
        final Deque<T> queue;

        volatile boolean done;
        volatile Throwable error;
        volatile boolean over;

        public ObserveOnObserver(Observer<T> downSteam, Scheduler.Worker worker) {
            this.downSteam = downSteam;
            this.worker = worker;
            queue = new ArrayDeque<>();
        }

        @Override
        public void onSubscribe() {
            downSteam.onSubscribe();
        }

        @Override
        public void onNext(T t) {
            queue.offer(t);// 把事件加入队列，offer不抛异常，只会返回false
            schedule();
        }

        private void schedule() {
            worker.schedule(this);
        }

        @Override
        public void onComplete() {

        }

        @Override
        public void onError(Throwable throwable) {

        }

        @Override
        public void run() {
            drainNormal();
        }

        /**
         * 从队列中排放事件并处理
         */
        private void drainNormal() {
            final Deque<T> q = queue;
            final Observer<T> a = downSteam;

            while (true) {
                boolean d = done;
                T t = q.poll();// 取出数据，没有数据的时候不会抛异常，返回null
                boolean empty = t == null;
                if (checkTerminated(d, empty, a)) {
                    return;
                }
                if (empty) {
                    break;
                }
                a.onNext(t);
            }
        }

        private boolean checkTerminated(boolean d, boolean empty, Observer<T> a) {
            if (over) {
                queue.clear();
                return true;
            }
            if (d) {
                Throwable e = error;
                if (e != null) {
                    over = true;
                    a.onError(error);
                    return true;
                } else if (empty) {
                    over = true;
                    a.onComplete();
                    return true;
                }
            }
            return false;
        }

    }


}
