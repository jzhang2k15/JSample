package com.jz.base.bean

/**
 * @author zhange
 * @date 2020/8/4.
 * description：WanAndroid 实体类的基类
 */
open class BaseBean<T>(
        val data: T,
        val errorCode: Int,// 0 是正常返回
        val errorMsg: String
)