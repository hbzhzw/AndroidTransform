package com.example.rxstudy;
import com.example.rxstudy.log.Log;

public class RxMain {
    public static void main(String[] args) {
        Log.info("main thread: " + Thread.currentThread().getId());
        Log.info("rx schedule subscribe on ...");
        RxStudy.rxScheduleSubscribeOn();

        Log.info("rx schedule observe on ...");
        RxStudy.rxScheduleObserveOn();

        Log.info("========= concat map ========= ...");
        RxStudy.rxConcatMap();
    }
}
