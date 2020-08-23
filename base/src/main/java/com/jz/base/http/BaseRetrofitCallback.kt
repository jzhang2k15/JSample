package com.jz.base.http

import retrofit2.Call
import retrofit2.Callback
import retrofit2.HttpException
import retrofit2.Response

/**
 * @author zhange
 * @date 2020/8/8.
 * @param T 自定义的返回类型
 * description：自定义的网络请求处理
 */
open class BaseRetrofitCallback<T> : Callback<T> {

    private var onSuccess: ((data: T) -> Unit)? = null
    private var onError: ((call: Call<T>, t: Throwable) -> Unit)? = null

    override fun onResponse(call: Call<T>, response: Response<T>) {
        // 过滤 http 请求不成功的情况，以及返回体为空的情况
        if (!response.isSuccessful || response.body() == null) {
            onError?.invoke(call, HttpException(response))
        } else {
            // http 请求成功，判断后端返回的返回码是否正确
            onSuccess?.invoke(response.body()!!)
        }
    }

    override fun onFailure(call: Call<T>, t: Throwable) {
        onError?.invoke(call, t)
    }

    open fun onSuccess(onSuccess: ((data: T) -> Unit)?) {
        this.onSuccess = onSuccess
    }

    open fun onError(onError: (call: Call<T>, t: Throwable) -> Unit) {
        this.onError = onError
    }
}