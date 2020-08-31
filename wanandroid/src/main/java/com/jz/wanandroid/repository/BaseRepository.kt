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
            LogJ.d("发起网络请求")
            call.invoke()
        }
    }

    // 抽象一个网络请求的方法，参数接收一个可以在协程里面运行的方法
    suspend fun <T> apiCallAsync(call: suspend () -> BaseBean<T>): Deferred<BaseBean<T>> {
        return coroutineScope {
            // 在 mJob 协程发起网络请求，获取数据
            async {
                call.invoke()
            }
        }
    }

    fun cancelAllJobs() {
        // 取消 job 时，会把它标记成 completed 的状态。在 completed 的 job 的域中启动的协程，将不会被执行
        mJob.cancel()// 不推荐使用

        // 使用下面两个之一，原理一样，去取消子 Job，但是不改变自身的状态
//        mJob.cancelChildren()
        mScope.cancelChildren()
        LogJ.d("取消网络请求")
    }

}