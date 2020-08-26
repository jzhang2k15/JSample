package com.jz.wanandroid.viewmodel

import android.net.Uri
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

/**
 * @author zhange
 * @date 2020/8/25.
 * description：
 */
class AlbumViewModel : ViewModel() {

    // 相册的图片列表
    var mList: MutableLiveData<MutableList<Uri>> = MutableLiveData()

}