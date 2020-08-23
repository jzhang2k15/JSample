package com.jz.base.http

import retrofit2.Call

/**
 * @author zhange
 * @date 2020/8/8.
 * description：
 */

fun <T> Call<T>.enqueue(dsl: (BaseRetrofitCallback<T>.() -> Unit)) {
    enqueue(BaseRetrofitCallback<T>().apply(dsl))
}