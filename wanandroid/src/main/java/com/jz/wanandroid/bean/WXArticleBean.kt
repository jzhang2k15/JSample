package com.jz.wanandroid.bean

import com.google.gson.annotations.SerializedName

/**
 * @author zhange
 * @date 2020/8/4.
 * description：公众号列表 bean
 */
class WXArticleBean {
    @SerializedName("courseId")
    var courseId = 0

    @SerializedName("id")
    var id = 0

    @SerializedName("name")
    var name: String? = null

    @SerializedName("order")
    var order = 0

    @SerializedName("parentChapterId")
    var parentChapterId = 0

    @SerializedName("userControlSetTop")
    var isUserControlSetTop = false

    @SerializedName("visible")
    var visible = 0

    @SerializedName("children")
    var children: List<*>? = null
}