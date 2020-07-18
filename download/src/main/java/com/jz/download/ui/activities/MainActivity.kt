package com.jz.download.ui.activities

import android.os.Bundle
import androidx.navigation.Navigation
import com.alibaba.android.arouter.facade.annotation.Autowired
import com.alibaba.android.arouter.facade.annotation.Route
import com.jz.base.DownloadPath
import com.jz.base.LogJ
import com.jz.base.ui.BaseARouterActivity
import com.jz.download.R

/**
 * @author zhange
 * @date 2020/7/17.
 * description：
 */
@Route(path = DownloadPath.MAIN_ACTIVITY)
class MainActivity : BaseARouterActivity() {


    @JvmField
    @Autowired(name = "stringKey")
    var stringKey: String? = null

    @JvmField
    @Autowired(name = "intKey")
    var intKey: Int? = null

    @JvmField
    @Autowired(name = "booleanKey")
    var booleanKey: Boolean? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.download_activity_main)

        LogJ.d("接收到的参数为: stringKey = $stringKey, intKey = $intKey, booleanKey = $booleanKey")
        val stringExtra = intent.getStringExtra("stringKey")
        val intExtra = intent.getIntExtra("intKey", -1)
        val booleanExtra = intent.getBooleanExtra("booleanKey", false)
        LogJ.d("从 Intent 获取到的参数: stringExtra = $stringExtra, intExtra = $intExtra, booleanExtra = $booleanExtra")
    }

    override fun onSupportNavigateUp(): Boolean {
        return Navigation.findNavController(this, R.id.container).navigateUp()
    }

}