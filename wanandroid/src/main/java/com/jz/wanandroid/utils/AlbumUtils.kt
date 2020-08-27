package com.jz.wanandroid.utils

import android.content.ContentUris
import android.content.Context
import android.net.Uri
import android.os.Build
import android.provider.MediaStore
import com.jz.base.initAny
import com.jz.base.utils.FileUtils
import com.jz.wanandroid.bean.AlbumBean
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

/**
 * @author zhange
 * @date 2020/8/26.
 * description：相册的工具类
 */
object AlbumUtils {


    private const val ALL = "all"
    private val mAlbumMap by lazy { HashMap<String, AlbumBean>() }

    /**
     * 根据相册名称去访问不同的相册
     */
    suspend fun albumIntent(context: Context, albumName: String = ALL): AlbumBean = withContext(Dispatchers.IO) {
        // 假如缓存里面有的话，则直接拿缓存的列表
        if (mAlbumMap.isNotEmpty() && mAlbumMap.keys.contains(albumName)) {
            mAlbumMap[albumName]!!
        } else {
            // 缓存里面没有，则重新去获取并缓存
            FileUtils.albumIntent(context, sortOrder = "${MediaStore.MediaColumns.DATE_ADDED} desc") {
                while (moveToNext()) {
                    val id = getLong(getColumnIndexOrThrow(MediaStore.MediaColumns._ID))
                    val name = getString(getColumnIndexOrThrow(MediaStore.MediaColumns.DISPLAY_NAME))

                    // 兼容 api 29 之后的分区存储
                    val parentDir = if ((Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q)) {
                        getString(getColumnIndexOrThrow(MediaStore.MediaColumns.RELATIVE_PATH))
                    } else {
                        val absolutePath = getString(getColumnIndexOrThrow(MediaStore.MediaColumns.DATA))
                        absolutePath.replace("/$name", "")
                    }

                    val uri = ContentUris.withAppendedId(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, id)

                    relatedDir(parentDir, uri)
                    relatedDir(ALL, uri)
                }
            }
            mAlbumMap[albumName]!!
        }
    }

    /**
     * 把相册和 uri 关联起来
     */
    private fun relatedDir(parentDir: String, uri: Uri) {
        // 根据相册名拿到对应的实体类
        var album = mAlbumMap[parentDir]
        if (album == null) {
            album = AlbumBean(parentDir, uri).also {
                mAlbumMap[parentDir] = it
            }
        }
        // 相册里面添加 uri
        album.addPhotos(uri)
    }

    /**
     * 获取相册目录
     */
    fun getAlbumDirs(): ArrayList<AlbumBean> = ArrayList(mAlbumMap.values)
}