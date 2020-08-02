package com.jz.jsample

import android.app.Dialog
import android.os.Bundle
import com.alibaba.android.arouter.facade.annotation.Route
import com.jz.base.LogJ
import com.jz.base.arouter.AppARouterPath
import com.jz.base.ui.BaseARouterActivity
import kotlinx.android.synthetic.main.app_activity_test.*
import java.io.File

/**
 * @author zhange
 * @date 2020/7/20.
 * description：
 */
@Route(path = AppARouterPath.TEST_ACTIVITY)
class TestActivity : BaseARouterActivity() {

    private lateinit var file: File

    private lateinit var dialog: Dialog

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.app_activity_test)


        dialog = CommonDialog.Builder(this).build()
        dialog.show()

        file = File(cacheDir, "oldFile.rar")
        if (!file.exists()) {
            val mkdir = file.mkdir()
            LogJ.d("文件创建结果：$mkdir")
            LogJ.d("文件名为：${file.name}")
        }




        tvModify.setOnClickListener {

            finish()

//            val newFile = File(cacheDir, "newFile.zip")
//
//            val renameTo = file.renameTo(newFile)
//            LogJ.d("文件改名结果：${renameTo}")
//            if (!file.exists()) {
//                LogJ.d("文件改名后的文件名为：${file.name}")
//                LogJ.d("文件改名后存在: old = ${file.exists()}, new = ${newFile.exists()}")
//            }




        }
    }


}