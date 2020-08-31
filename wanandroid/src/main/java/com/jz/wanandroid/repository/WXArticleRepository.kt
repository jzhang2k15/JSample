package com.jz.wanandroid.repository

import com.jz.base.utils.LogJ
import com.jz.base.utils.parseJsonString
import com.jz.wanandroid.bean.WXArticleBean
import com.jz.wanandroid.http.WanAndroidApi

/**
 * @author zhange
 * @date 2020/8/31.
 * description：
 */
class WXArticleRepository : BaseRepository() {

    // 获取公众号列表
    suspend fun getWXArticle(): List<WXArticleBean> {
        return apiCall { WanAndroidApi.getApi().getWXArticleList() }.data
    }

}