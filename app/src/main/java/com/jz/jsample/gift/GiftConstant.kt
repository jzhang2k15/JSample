package com.jz.jsample.gift

import com.jz.jsample.JApp

/**
 * @author zhange
 * @date 2020/8/2.
 * description：
 */

// 礼物的缓存目录路径
val GIFT_HOME_DIR: String? = "${JApp.sApplication.filesDir.absolutePath}/gift"

// 预览特效的后缀
const val GIFT_PREVIEW_POSTFIX = "_preview"

// lottie 动画文件的名称
const val FILE_GIFT_LOTTIE_JSON = "data.json"

// lottie 动画文件图片所在的文件夹
const val FILE_GIFT_LOTTIE_IMAGE = "images"