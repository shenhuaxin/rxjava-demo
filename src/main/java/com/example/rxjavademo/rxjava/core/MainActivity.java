package com.example.rxjavademo.rxjava.core;


import com.example.rxjavademo.rxjava.core.scheduler.Schedulers;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Observable.create(new ObservableOnSubscribe<Object>() {
            @Override
            public void subscribe(Emitter<Object> emitter) {
                Log.i("TAG", "subscribe: thread=" + Thread.currentThread());
                emitter.onNext("aaaa");
                emitter.onNext("bbb");
                emitter.onNext("12312");
                emitter.onComplete();
                //emitter.onError(new Throwable());
            }
        })
                .subscribeOn(Schedulers.newThread())
                .observeOn(Schedulers.mainThread())
                .map(new Function<Object, Object>() {
                    @Override
                    public Object apply(Object o) {
                        Log.i("TAG", "apply: thread=" + Thread.currentThread());
                        return "处理后的" + o;
                    }
                })

//                .flatMap(new Function<Object, ObservableSource<Object>>() {
//                    @Override
//                    public ObservableSource<Object> apply(Object o) {
//                        return Observable.create(new ObservableOnSubscribe<Object>() {
//                            @Override
//                            public void subscribe(Emitter<Object> emitter) {
//                                emitter.onNext("处理后的" + o);
//                            }
//                        });
//                    }
//                })
                .subscribe(new Observer<Object>() {
                    @Override
                    public void onSubscribe() {
                        Log.i("TAG", "onSubscribe: thread=" + Thread.currentThread());
                    }

                    @Override
                    public void onNext(Object o) {
                        Log.i("TAG", "onNext: thread=" + Thread.currentThread());
                    }

                    @Override
                    public void onComplete() {
                        Log.i("TAG", "onComplete: thread=" + Thread.currentThread());
                    }

                    @Override
                    public void onError(Throwable throwable) {
                        Log.i("TAG", "onError: thread=" + Thread.currentThread());
                    }
                });
    }
}