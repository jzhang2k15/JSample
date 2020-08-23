package com.jz.wanandroid.http

import com.jz.base.bean.BaseBean
import com.jz.wanandroid.bean.WXArticleBean
import com.jz.wanandroid.bean.WXArticleHistoryBean
import com.jz.wanandroid.bean.base.BasePageBean
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

/**
 * @author zhange
 * @date 2020/8/3.
 * description：网络请求
 */
interface ApiService {

    // 获取公众号列表
    @GET("wxarticle/chapters/json")
    fun getWXArticleList(): Call<BaseBean<List<WXArticleBean>>>

    // 获取指定 id 公众号的历史数据，page 为页数
    @GET("wxarticle/list/{id}/page/json")
    fun getArticleHistory(@Path("id") id: Int, @Path("page") page: Int): Call<BasePageBean<WXArticleHistoryBean>>





}