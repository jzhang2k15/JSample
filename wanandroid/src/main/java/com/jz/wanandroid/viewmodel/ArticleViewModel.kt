package com.jz.wanandroid.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.jz.base.utils.LogJ
import com.jz.base.utils.logd
import com.jz.base.utils.parseJsonString
import com.jz.wanandroid.bean.WXArticleBean
import com.jz.wanandroid.repository.WXArticleRepository

/**
 * @author zhange
 * @date 2020/8/8.
 * description：公众号的 M 层
 */
class ArticleViewModel : ViewModel() {

    private val mWXArticleRepository by lazy { WXArticleRepository() }

    /*公众号列表*/
    val mArticleListLiveData: MutableLiveData<List<WXArticleBean>> = MutableLiveData()

    /*获取公众号列表*/
    suspend fun getArticleList() {
        val list = mWXArticleRepository.getWXArticle()
        mArticleListLiveData.postValue(list)
    }
}