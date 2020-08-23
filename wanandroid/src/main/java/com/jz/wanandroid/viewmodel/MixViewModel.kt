package com.jz.wanandroid.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.jz.wanandroid.bean.mix.MixBeanParent

/**
 * @author zhange
 * @date 2020/8/21.
 * description：
 */
class MixViewModel : ViewModel() {

    // 图片列表
    val mList: MutableLiveData<MutableList<MixBeanParent>> = MutableLiveData()

}