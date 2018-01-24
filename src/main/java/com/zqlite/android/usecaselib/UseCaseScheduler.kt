package com.zqlite.android.mockaweme.base.usecase

/**
 * 用例调度接口
 */
interface UseCaseScheduler {

    /**
     * 执行用例
     */
    fun execute(runnable: Runnable)

    /**
     * 例成功执行后的响应方法
     */
    fun <V : UseCase.ResponseValues> notifyResponse(response: V, useCaseCallback: UseCase.UseCaseCallback<V>)

    /**
     * 用例执行失败后的响应方法
     */
    fun <V : UseCase.ResponseValues> onError(useCaseCallback: UseCase.UseCaseCallback<V>)
}
