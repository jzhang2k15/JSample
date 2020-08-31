package com.jz.wanandroid.repository

/**
 * @author zhange
 * @date 2020/8/31.
 * description：对外提供 Repository
 *
 * 管理所有  Repository 的创建
 */
object RepositoryProvider {

    fun getWXArticleRepository(): WXArticleRepository = WXArticleRepository()


}