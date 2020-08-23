package com.jz.wanandroid.bean.base

import com.jz.base.bean.BaseBean

/**
 * @author zhange
 * @date 2020/8/8.
 * description：列表的数据基类
 */
open class BasePageBean<T>(data: PageBean<T>, errorCode: Int, errorMsg: String) :
        BaseBean<PageBean<T>>(data, errorCode, errorMsg)