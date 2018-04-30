package com.college.utils;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ThreadPoolFactory {
    private static final int nThread = 100;

    private ThreadPoolFactory() {
    }

    private static volatile ExecutorService fixedInstance = null;
    private static volatile ExecutorService singleInstance = null;

    public static ExecutorService getFixedInstance() {
        if (fixedInstance == null) {
            synchronized (ExecutorService.class) {
                if (fixedInstance == null) {
                    fixedInstance = Executors.newFixedThreadPool(nThread);
                }
            }
        }
        return fixedInstance;
    }

    public static ExecutorService getSingleInstance() {
        if (singleInstance == null) {
            synchronized (ExecutorService.class) {
                if (singleInstance == null) {
                    singleInstance = Executors.newSingleThreadExecutor();
                }
            }
        }
        return singleInstance;
    }

}
