package com.jz.wanandroid.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

/**
 * @author zhange
 * @date 2020/8/6.
 * description：视频 url
 */
class VideoListViewModel : ViewModel() {

    // 视频列表
    val mUrlList : MutableLiveData<MutableList<String>> = MutableLiveData()


}