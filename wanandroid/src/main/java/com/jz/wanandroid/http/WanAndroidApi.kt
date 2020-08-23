package com.jz.wanandroid.http

import com.jz.base.http.RetrofitUtils

/**
 * @author zhange
 * @date 2020/8/8.
 * description：WanAndroid API
 */
object WanAndroidApi {

    fun getApi(): ApiService = RetrofitUtils.api()


}