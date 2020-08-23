package com.jz.wanandroid.bean.base

/**
 * @author zhange
 * @date 2020/8/8.
 * description：有页数的数据
 */
class PageBean<T>(
        val curPage: Int,
        val datas: List<T>,
        val offset: Int,
        val over: Int,
        val pageCount: Int,
        val size: Int,
        val total: Int
)