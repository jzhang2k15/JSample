package com.jz.jsample

import android.app.Dialog
import android.os.Bundle
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.alibaba.android.arouter.facade.annotation.Route
import com.jz.base.LogJ
import com.jz.base.arouter.AppARouterPath
import com.jz.base.ui.BaseARouterActivity
import com.jz.download.getSimpleDownloadListenerDSL
import com.jz.download.utils.DownloadUtils
import com.jz.download.utils.handle.DownloadListenerDSL
import com.jz.download.utils.model.DownloadModel
import kotlinx.android.synthetic.main.app_activity_test.*
import java.io.File

/**
 * @author zhange
 * @date 2020/7/20.
 * description：
 */
@Route(path = AppARouterPath.TEST_ACTIVITY)
class TestActivity : BaseARouterActivity() {

    val TAG = TestActivity::class.java.simpleName

    private lateinit var file: File

    private lateinit var dialog: Dialog

    private val mDownloadModel by lazy {
        // 这里不能直接新建一个 DownloadModel，不然将和普通的一样，没有对声明周期的感知
        ViewModelProviders.of(this).get(DownloadModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.app_activity_test)


//        dialog = CommonDialog.Builder(this).build()
//        dialog.show()
//
//        file = File(cacheDir, "oldFile.rar")
//        if (!file.exists()) {
//            val mkdir = file.mkdir()
//            LogJ.d("文件创建结果：$mkdir")
//            LogJ.d("文件名为：${file.name}")
//        }


        tvModify.setOnClickListener {

//            finish()

//            val newFile = File(cacheDir, "newFile.zip")
//
//            val renameTo = file.renameTo(newFile)
//            LogJ.d("文件改名结果：${renameTo}")
//            if (!file.exists()) {
//                LogJ.d("文件改名后的文件名为：${file.name}")
//                LogJ.d("文件改名后存在: old = ${file.exists()}, new = ${newFile.exists()}")
//            }


            DownloadUtils.download(
                "",
                File(filesDir, "gift"),
                "car-360dp.dat"
            ) {

                start {
                    LogJ.d(TAG, "开始下载, ${it.filename}")
                }

                completed {
                    LogJ.d(TAG, "下载完成")
                    // 在主线程进行回调
                    mDownloadModel.mLiveData.postValue(it.file)
                    // 在当前线程进行回调
//                    mDownloadModel.mLiveData.setValue(it.file)
                }
            }
        }

        mDownloadModel.mLiveData.observe(this, Observer<File> {
            LogJ.d(TAG, "文件发生改变, ${it.name}")
        })
    }


}