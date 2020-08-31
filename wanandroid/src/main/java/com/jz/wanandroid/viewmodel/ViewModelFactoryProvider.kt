package com.jz.wanandroid.viewmodel

import com.jz.wanandroid.repository.RepositoryProvider
import com.jz.wanandroid.viewmodel.factory.ArticleViewModelFactory

/**
 * @author zhange
 * @date 2020/8/31.
 * description：提供有参数的 ViewModel Factory，用以生成对应的
 */
object ViewModelFactoryProvider {

    // 获取文章的 viewModelFactory
    fun getArticleViewModelFactory(): ArticleViewModelFactory {
        val repository = RepositoryProvider.getWXArticleRepository()
        return ArticleViewModelFactory(repository)
    }

}