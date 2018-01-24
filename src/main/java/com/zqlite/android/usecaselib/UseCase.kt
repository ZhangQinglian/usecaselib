package com.zqlite.android.mockaweme.base.usecase

/**
 * 用例基类，每次新增一个用例都需要继承 UseCase，同时也需要继承 RequestValues 和 ResponseValues 两个接口作为对应 UseCase 相关连的请求和响应参数
 */
abstract class UseCase<Q : UseCase.RequestValues, P : UseCase.ResponseValues> {

    /**
     * 用例请求参数
     */
    var requestValues: Q? = null

    /**
     * 用例响应回调
     */
    var useCaseCallback: UseCaseCallback<P>? = null

    internal fun run() {
        executeUseCase(requestValues)
    }

    /**
     * 子类通过继承此方法来进行用例的实际操作
     */
    protected abstract fun executeUseCase(requestValues: Q?)

    interface RequestValues

    interface ResponseValues

    interface UseCaseCallback<in R> {
        fun onSuccess(response: R)
        fun onError()
    }
}
