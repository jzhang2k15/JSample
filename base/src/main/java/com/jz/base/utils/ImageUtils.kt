package com.jz.base.utils

import android.content.Context
import android.graphics.BitmapFactory
import android.net.Uri
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.bumptech.glide.request.target.Target
import com.jz.base.R
import java.io.File
import java.util.concurrent.ExecutionException
import kotlin.jvm.Throws

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

    // 按照原大小加载图片
    fun loadUriWithOriginSize(context: Context, uri: Uri, imageView: ImageView) {
        Glide.with(context).load(uri).apply(
                RequestOptions()
                        .placeholder(R.drawable.base_ic_launcher)
                        .override(Target.SIZE_ORIGINAL)
        ).into(imageView)
    }

    @Throws(ExecutionException::class, InterruptedException::class)
    fun getFileOrNull(context: Context, uri: Uri): File? {
        return Glide.with(context).downloadOnly().load(uri).submit().get()
    }


}