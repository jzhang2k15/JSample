package com.jz.base.utils

import com.google.gson.Gson
import com.jz.base.getType

/**
 * @author zhange
 * @date 2020/6/14.
 * description：Json 解析工具类
 */

/**
 * String 转换为对象
 */
inline fun <reified T> String.parseJson(gson: Gson? = null): T? {
    gson?.let {
        return it.fromJson(this, getType())
    }
    return Gson().fromJson(this, getType())
}

/**
 * 对象转换为 String
 */
inline fun <reified T> T.parseJsonString(gson: Gson? = null): String {
    gson?.let {
        return it.toJson(this, getType<T>())
    }
    return Gson().toJson(this, getType<T>())
}
