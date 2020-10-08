package com.example.rxstudy;

import com.example.rxstudy.log.Log;

import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.ObservableSource;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.core.Scheduler;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.functions.Function;
import io.reactivex.rxjava3.schedulers.Schedulers;

@SuppressWarnings({"WeakerAccess", "ResultOfMethodCallIgnored", "unused"})
public class RxStudy {
    public static void rxFlatMap() {
        Observable<Integer> observable = Observable.fromArray(1, 3, 5, 7)
                .flatMap((Function<Integer, ObservableSource<Integer>>) integer ->
                        Observable.fromArray(integer, integer + 1));
        observable.subscribe(System.out::println);
    }

    public static void rxConcatMap() {
        Observable<Integer> observable = Observable.fromArray(1, 2, 3).concatMap(i -> Observable.fromArray(i*i));
        observable.subscribe(new Observer<Integer>() {
            @Override
            public void onSubscribe(Disposable d) {
                Log.info("onSubsribe, disposalbe: " + d);
            }

            @Override
            public void onNext(Integer integer) {
                Log.info("onNext: " + integer);
            }

            @Override
            public void onError(Throwable e) {
                Log.info("onError: " + e);
            }

            @Override
            public void onComplete() {
                Log.info("onComplete ...");
            }
        });
    }

    public static void rxScheduleSubscribeOn() {
        Observable<Integer> observable = Observable.fromArray(1, 3, 5, 7)
                .flatMap((Function<Integer, ObservableSource<Integer>>) integer -> {
                    Log.info("flatMap, index: " + integer + ", thread: " + Thread.currentThread().getId());
                    return Observable.fromArray(integer, integer + 1);
                })
                .map(integer -> {
                    int result = integer * integer;
                    Log.info(integer + " -> " + result + ", thread: " + Thread.currentThread().getId());
                    return result;
                })
                .observeOn(Schedulers.single())
                .subscribeOn(Schedulers.io());

//        observable.subscribe(integer ->
//                System.out.println("final output: " + integer + ", thread: " + Thread.currentThread().getId()));

        observable.subscribe(new Observer<Integer>() {
            @Override
            public void onSubscribe(Disposable d) {
                Log.info("onSubscribe, disposable: " + d + ", thread: " + Thread.currentThread().getId());
            }

            @Override
            public void onNext(Integer integer) {
                Log.info("onNext, result: " + integer + ", thread: " + Thread.currentThread().getId());
            }

            @Override
            public void onError(Throwable e) {
                Log.info("onError, result: " + e + ", thread: " + Thread.currentThread().getId());
            }

            @Override
            public void onComplete() {
                Log.info("onComplete" + ", thread: " + Thread.currentThread().getId());
            }
        });

        try {
            Thread.sleep(2000L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void rxScheduleObserveOn() {
        Observable<Integer> observable = Observable.fromArray(1, 3, 5, 7)
                .flatMap((Function<Integer, ObservableSource<Integer>>) integer -> {
                    Log.info("flatMap, index: " + integer + ", thread: " + Thread.currentThread().getId());
                    return Observable.fromArray(integer, integer + 1);
                })
                .observeOn(Schedulers.io())
                .map(integer -> {
                    int result = integer * integer;
                    Log.info(integer + " -> " + result + ", thread: " + Thread.currentThread().getId());
                    return result;
                }).observeOn(Schedulers.single());
        observable.subscribe(integer ->
                Log.info("final output: " + integer + ", thread: " + Thread.currentThread().getId()));
        try {
            Thread.sleep(2000L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
