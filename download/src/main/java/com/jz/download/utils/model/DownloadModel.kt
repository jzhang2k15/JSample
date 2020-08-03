package com.jz.download.utils.model

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import java.io.File

/**
 * @author zhange
 * @date 2020/8/3.
 * description：
 */
class DownloadModel : ViewModel() {

    val mLiveData: MutableLiveData<File> = MutableLiveData()

}