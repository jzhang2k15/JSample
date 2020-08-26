package com.jz.base.http

import com.jz.base.utils.LogJ
import com.jz.base.getType
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * @author zhange
 * @date 2020/8/3.
 * description：
 */
object RetrofitUtils {

    private const val URL_DEBUG = "https://www.wanandroid.com"
    private const val URL_RELEASE = "https://www.wanandroid.com"

    private val BASE_URL = if (LogJ.DEBUG) URL_DEBUG else URL_RELEASE

    // OkHttpClient 变量，控制网络请求
    private val okHttpClient: OkHttpClient
        get() {
            val builder = OkHttpClient.Builder()
            // 暂时使用默认设置
            return builder.build()
        }

    /*拿到最普通的 retrofit 对象，通用的配置*/
    fun getRetrofit(url: String = BASE_URL, client: OkHttpClient = okHttpClient): Retrofit =
            Retrofit.Builder()
                    .baseUrl(url)
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(client)
                    .build()

    /*通过 retrofit 对象去拿到对应的 interface 接口*/
    inline fun <reified T> api(): T {
        return getRetrofit().create(getType<T>())
    }

}