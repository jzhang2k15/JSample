package com.jz.wanandroid.viewmodel.factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.jz.wanandroid.repository.WXArticleRepository
import com.jz.wanandroid.viewmodel.ArticleViewModel

/**
 * @author zhange
 * @date 2020/8/31.
 * description：
 */
class ArticleViewModelFactory(private val mWXArticleRepository: WXArticleRepository) : ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return ArticleViewModel(mWXArticleRepository) as T
    }

}