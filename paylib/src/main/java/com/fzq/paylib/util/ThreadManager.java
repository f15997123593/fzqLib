package com.fzq.paylib.util;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 简易线程池管理工具
 * 作者: Created by fzq on 2018/4/26 14:28
 * 邮箱: 15997123593@163.com
 */
public class ThreadManager {
    private static ExecutorService mExecutors = Executors.newSingleThreadExecutor();

    public static void execute(Runnable runnable) {
        if (mExecutors == null) {
            mExecutors = Executors.newSingleThreadExecutor();
        }
        mExecutors.execute(runnable);
    }

    public static void shutdown() {
        if (mExecutors == null) return;
        if (mExecutors.isShutdown()) return;
        mExecutors.shutdownNow();
        mExecutors = null;
    }
}
