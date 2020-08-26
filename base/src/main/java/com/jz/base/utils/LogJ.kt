package com.jz.base.utils

import android.util.Log
import com.jz.base.BuildConfig

/**
 * @author zhange
 * @date 2020/6/26.
 * description：日志工具类
 */
object LogJ {

    var TAG = "JLog"
    var DEBUG = BuildConfig.DEBUG

    fun setGlobalTag(tag: String) {
        // 非空，且不是空格
        if (tag.isNotEmpty() && tag.isNotBlank()) {
            TAG = tag
        }
    }

    fun d(msg: String) {
        if (DEBUG) d(TAG, msg)
    }

    fun w(msg: String) {
        if (DEBUG) Log.w(TAG, msg)
    }

    fun e(msg: String) {
        if (DEBUG) Log.e(TAG, msg)
    }

    fun d(tag: String, msg: String) {
        if (DEBUG) Log.d(tag, msg)
    }

    fun w(tag: String, msg: String) {
        if (DEBUG) Log.w(tag, msg)
    }

    fun e(tag: String, msg: String) {
        if (DEBUG) Log.e(tag, msg)
    }

}

fun String.logd() {
    LogJ.d(this)
}

fun String.logw() {
    LogJ.w(this)
}

fun String.loge() {
    LogJ.e(this)
}
