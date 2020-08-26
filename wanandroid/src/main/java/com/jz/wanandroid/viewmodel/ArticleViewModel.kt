package com.jz.wanandroid.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.jz.wanandroid.bean.WXArticleBean
import com.jz.base.http.enqueue
import com.jz.base.utils.logd
import com.jz.base.utils.parseJsonString
import com.jz.wanandroid.http.WanAndroidApi

/**
 * @author zhange
 * @date 2020/8/8.
 * description：公众号的 M 层
 */
class ArticleViewModel : ViewModel() {

    /*公众号列表*/
    val mArticleListLiveData: MutableLiveData<List<WXArticleBean>> = MutableLiveData()

    /*获取公众号列表*/
    fun getArticleList() {
        val call = WanAndroidApi.getApi().getWXArticleList()
        call.enqueue {
            onSuccess {
                it.parseJsonString().logd()
                mArticleListLiveData.postValue(it.data)
            }
        }
    }
}