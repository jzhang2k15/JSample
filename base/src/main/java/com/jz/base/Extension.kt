package com.jz.base

import android.content.Context
import android.widget.Toast

/**
 * @author zhange
 * @date 2020/6/14.
 * description：常用的拓展函数
 */

inline fun <reified T> getType() = T::class.java

fun String.toastShort(context: Context) {
    Toast.makeText(context, this, Toast.LENGTH_SHORT).show()
}

fun String.toastLong(context: Context) {
    Toast.makeText(context, this, Toast.LENGTH_LONG).show()
}

/**
 * 创建类型安全的构建器方法
 * init 是 T 类型的拓展函数(无参数，无返回值)
 */
fun <T> initAny(any: T, init: T.() -> Unit): T {
    any.init()
    return any
}