package com.example.rxstudy.log;

import java.io.IOException;
import java.util.logging.ConsoleHandler;
import java.util.logging.FileHandler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

public class Log {
    private static final String LOG_FILE = "RxStudy.log";
    private static Logger Log = Logger.getLogger("com.example.rxstudy");
    static {
        try {
//            ConsoleHandler consoleHandler = new ConsoleHandler();
//            Log.addHandler(consoleHandler);
            SimpleFormatter formatter = new SimpleFormatter();
            FileHandler fileHandler = new FileHandler(LOG_FILE);
            fileHandler.setFormatter(formatter);
            Log.addHandler(fileHandler);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void info(String msg) {
        Log.info(msg);
    }

//    public static void info(String msg, String fileName) {
//        Log.info(msg);
//    }

    public static void warn(String msg) {
        Log.warning(msg);
    }
}
