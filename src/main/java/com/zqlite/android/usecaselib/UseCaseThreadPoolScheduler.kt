package com.zqlite.android.mockaweme.base.usecase

import android.os.Handler
import android.os.Looper

import java.util.concurrent.ArrayBlockingQueue
import java.util.concurrent.ThreadPoolExecutor
import java.util.concurrent.TimeUnit

/**
 * 用例调度实现类，使用线程池来异步执行 UseCase
 */
class UseCaseThreadPoolScheduler : UseCaseScheduler {

    private val mHandler = Handler(Looper.getMainLooper())

    private var mThreadPoolExecutor: ThreadPoolExecutor

    init {
        mThreadPoolExecutor = ThreadPoolExecutor(POOL_SIZE, MAX_POOL_SIZE, TIME_OUT.toLong(), TimeUnit.SECONDS, ArrayBlockingQueue(POOL_SIZE))
    }

    override fun execute(runnable: Runnable) {
        mThreadPoolExecutor.execute(runnable)
    }

    override fun <V : UseCase.ResponseValues> notifyResponse(response: V, useCaseCallback: UseCase.UseCaseCallback<V>) {
        mHandler.post { useCaseCallback.onSuccess(response) }
    }

    override fun <V : UseCase.ResponseValues> onError(useCaseCallback: UseCase.UseCaseCallback<V>) {
        mHandler.post { useCaseCallback.onError() }
    }

    companion object {

        val POOL_SIZE = 20

        val MAX_POOL_SIZE = 40

        val TIME_OUT = 30
    }
}
