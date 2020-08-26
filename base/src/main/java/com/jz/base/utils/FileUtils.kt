package com.jz.base.utils

import android.content.ContentUris
import android.content.Context
import android.content.Intent
import android.database.Cursor
import android.net.Uri
import android.os.Build
import android.os.Environment
import android.provider.MediaStore
import java.io.File


/**
 * @author zhange
 * @date 2020/8/25.
 * description：适配 Android 11 的分区存储
 *
 * 分区存储：
 * 1、私有目录
 * 其中私有目录又分为「内部存储目录」、「外部存储目录」
 * 2、共享目录
 *
 */
object FileUtils {

    // 判断外部存储是否使用
    fun isExternalStorageWritable(): Boolean = Environment.getExternalStorageState() == Environment.MEDIA_MOUNTED

    // 是否外部存储可以读取
    fun isExternalStorageReadable(): Boolean = Environment.getExternalStorageState() in
            setOf(Environment.MEDIA_MOUNTED, Environment.MEDIA_MOUNTED_READ_ONLY)

    /**
     * 访问共享目录的相册
     *
     * 这里只做访问的操作，对外暴露 cursor 给上层代码去写具体操作
     */
    @Throws(IllegalArgumentException::class)
    fun albumIntent(context: Context,
                    projection: Array<String>? = null,
                    selection: String? = null,
                    selectionArgs: Array<String>? = null,
                    sortOrder: String? = null, // 根据创建时间倒序
                    func: (Cursor.() -> Unit)? = null
    ) {
        LogJ.d("版本：${Build.VERSION.SDK_INT}")
        val cursor = context.contentResolver.query(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, projection, selection, selectionArgs, sortOrder)
        if (cursor != null) {
            func?.invoke(cursor)
            cursor.close()
        }
    }

    // 清理应用缓存
    fun clearAppCache() {
        Intent()
    }


}