package com.jz.wanandroid.viewmodel

import android.content.Context
import android.net.Uri
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.jz.wanandroid.bean.AlbumBean
import com.jz.wanandroid.utils.AlbumUtils
import kotlinx.coroutines.*

/**
 * @author zhange
 * @date 2020/8/25.
 * description：
 */
class AlbumViewModel : ViewModel() {

    // 相册的图片列表
    var mPhotoList: MutableLiveData<MutableList<Uri>> = MutableLiveData()

    // 相册名字列表
    var mAlbumList: MutableLiveData<MutableList<AlbumBean>> = MutableLiveData()

    // 获取相册
    fun fetchAlbum(context: Context) {
        MainScope().launch {
            AlbumUtils.albumIntent(context)
            mAlbumList.postValue(AlbumUtils.getAlbumDirs())
        }
    }

    // 获取具体相册下面的图片列表
    fun fetchPhotos(context: Context, albumName: String){
        MainScope().launch {
            val album = AlbumUtils.albumIntent(context, albumName)
            mPhotoList.postValue(album.photos)
        }
    }
}