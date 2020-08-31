package com.jz.wanandroid.repository

import com.jz.wanandroid.bean.WXArticleBean
import com.jz.wanandroid.bean.WXArticleHistoryBean
import com.jz.wanandroid.bean.base.BasePageBean
import com.jz.wanandroid.bean.base.Result
import com.jz.wanandroid.http.CODE_SUCCESS
import com.jz.wanandroid.http.WanAndroidApi
import retrofit2.HttpException

/**
 * @author zhange
 * @date 2020/8/31.
 * description：
 */
class WXArticleRepository : BaseRepository() {

    // 获取公众号列表
    suspend fun getWXArticle(): Result<MutableList<WXArticleBean>> {
        val bean = apiCall { WanAndroidApi.getApi().getWXArticleList() }
        return when (bean.errorCode) {
            CODE_SUCCESS -> Result.Success(bean.data)
            else -> Result.Error(Exception("出现了错误"))
        }
    }

    // 获取公众号文章列表
    suspend fun getArticleHistory(id: Int, page: Int): Result<List<WXArticleHistoryBean>> {
        val bean = apiCall { WanAndroidApi.getApi().getArticleHistory(id, page) }
        return when (bean.errorCode) {
            CODE_SUCCESS -> Result.Success(bean.data.datas)
            else -> Result.Error(Exception("出现了错误"))
        }
    }

}