package com.jz.wanandroid.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.jz.base.utils.LogJ
import com.jz.wanandroid.bean.WXArticleBean
import com.jz.wanandroid.bean.base.Result
import com.jz.wanandroid.repository.WXArticleRepository

/**
 * @author zhange
 * @date 2020/8/8.
 * description：公众号的 M 层
 *
 * Repository 通过构造函数传递进来，利用了依赖注入
 */
class ArticleViewModel(private val mWXArticleRepository: WXArticleRepository) : ViewModel() {

    /*公众号列表*/
    val mArticleListLiveData: MutableLiveData<MutableList<WXArticleBean>> = MutableLiveData()

    /*获取公众号列表*/
    suspend fun getArticleList() {
        // 错误在 Repository 进行处理，VM 只需要知道成功，或者失败
        when (val result = mWXArticleRepository.getWXArticle()) {
            is Result.Success<MutableList<WXArticleBean>> -> mArticleListLiveData.postValue(result.data)
            else -> LogJ.d("网络请求出错")
        }
    }
}