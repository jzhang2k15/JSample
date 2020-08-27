package com.jz.base.utils

import android.content.Context
import android.graphics.BitmapFactory
import android.net.Uri
import android.widget.ImageView
import com.bumptech.glide.Glide

/**
 * @author zhange
 * @date 2020/8/25.
 * description：
 */
object ImageUtils {

    // 普通的加载图片
    fun load(context: Context, uri: String?, imageView: ImageView) {
        Glide.with(context)
                .load(uri)
                .into(imageView)
    }

    // 普通的加载图片
    fun load(context: Context, uri: Uri?, imageView: ImageView) {
        Glide.with(context)
                .load(uri)
                .into(imageView)
    }

    fun loadUri(context: Context, uri: Uri, imageView: ImageView) {
        val fd = context.contentResolver.openFileDescriptor(uri, "r")
        fd?.let {
            val bitmap = BitmapFactory.decodeFileDescriptor(it.fileDescriptor)
            it.close()
            imageView.setImageBitmap(bitmap)
        }
    }


}