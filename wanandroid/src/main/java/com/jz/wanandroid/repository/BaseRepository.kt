package com.jz.wanandroid.repository

import com.jz.base.bean.BaseBean
import com.jz.base.utils.LogJ
import kotlinx.coroutines.*

/**
 * @author zhange
 * @date 2020/8/31.
 * description：数据层，继承该类来提供获取数据的接口
 */
abstract class BaseRepository {

    // 用来记录这里开启的协程
    private val mJob by lazy { SupervisorJob() }

    // 作用域
    private val mScope by lazy { Dispatchers.Default + mJob }

    // 抽象一个网络请求的方法，参数接收一个可以在协程里面运行的方法
    suspend fun <T> apiCall(call: suspend () -> BaseBean<T>): BaseBean<T> {
        return withContext(mScope) {
            // 在 mJob 协程发起网络请求，获取数据
            call.invoke()
        }.apply {
            LogJ.d("errorCode = $errorCode")
        }
    }

    // 抽象一个网络请求的方法，参数接收一个可以在协程里面运行的方法
    suspend fun <T> apiCallAsync(call: suspend () -> BaseBean<T>): Deferred<BaseBean<T>> {
        return coroutineScope {
            // 在 mJob 协程发起网络请求，获取数据
            async {
                call.invoke()
            }
        }.apply {
            val await = await()
            LogJ.d("errorCode = ${await.errorCode}")
        }
    }

    fun cancelAllJobs() {
        mJob.cancel()
    }

}