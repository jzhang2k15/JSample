package com.jz.wanandroid.bean

import android.net.Uri

/**
 * @author zhange
 * @date 2020/8/27.
 * description：
 */
class AlbumBean(val name: String, val cover: Uri, val photos: ArrayList<Uri> = ArrayList()) {

    val itemCount: Int
        get() = photos.size

    fun addPhotos(uri: Uri) {
        photos.add(uri)
    }

}
