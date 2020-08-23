package com.jz.wanandroid.bean

import com.google.gson.annotations.SerializedName

/**
 * @author zhange
 * @date 2020/8/8.
 * description：
 */
class WXArticleHistoryBean {

    @SerializedName("apkLink")
    var apkLink: String? = null

    @SerializedName("audit")
    var audit = 0

    @SerializedName("author")
    var author: String? = null

    @SerializedName("canEdit")
    var isCanEdit = false

    @SerializedName("chapterId")
    var chapterId = 0

    @SerializedName("chapterName")
    var chapterName: String? = null

    @SerializedName("collect")
    var isCollect = false

    @SerializedName("courseId")
    var courseId = 0

    @SerializedName("desc")
    var desc: String? = null

    @SerializedName("descMd")
    var descMd: String? = null

    @SerializedName("envelopePic")
    var envelopePic: String? = null

    @SerializedName("fresh")
    var isFresh = false

    @SerializedName("id")
    var id = 0

    @SerializedName("link")
    var link: String? = null

    @SerializedName("niceDate")
    var niceDate: String? = null

    @SerializedName("niceShareDate")
    var niceShareDate: String? = null

    @SerializedName("origin")
    var origin: String? = null

    @SerializedName("prefix")
    var prefix: String? = null

    @SerializedName("projectLink")
    var projectLink: String? = null

    @SerializedName("publishTime")
    var publishTime: Long = 0

    @SerializedName("realSuperChapterId")
    var realSuperChapterId = 0

    @SerializedName("selfVisible")
    var selfVisible = 0

    @SerializedName("shareDate")
    var shareDate: Long = 0

    @SerializedName("shareUser")
    var shareUser: String? = null

    @SerializedName("superChapterId")
    var superChapterId = 0

    @SerializedName("superChapterName")
    var superChapterName: String? = null

    @SerializedName("title")
    var title: String? = null

    @SerializedName("type")
    var type = 0

    @SerializedName("userId")
    var userId = 0

    @SerializedName("visible")
    var visible = 0

    @SerializedName("zan")
    var zan = 0

    @SerializedName("tags")
    var tags: List<TagsBean>? = null

    class TagsBean {
        /**
         * name : 公众号
         * url : /wxarticle/list/408/1
         */
        @SerializedName("name")
        var name: String? = null

        @SerializedName("url")
        var url: String? = null

    }

}