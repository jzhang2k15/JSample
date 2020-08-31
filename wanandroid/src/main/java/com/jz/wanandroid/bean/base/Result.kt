package com.jz.wanandroid.bean.base

/**
 * @author zhange
 * @date 2020/8/31.
 * description：官网教程的 result 实类，用来暴露结果
 */
sealed class Result<out R> {
    data class Success<out T>(val data: T): Result<T>()
    data class Error(val exception: Exception): Result<Nothing>()
}