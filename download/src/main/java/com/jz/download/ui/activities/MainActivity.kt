package com.jz.download.ui.activities

import android.os.Bundle
import androidx.navigation.Navigation
import com.alibaba.android.arouter.facade.annotation.Autowired
import com.alibaba.android.arouter.facade.annotation.Route
import com.jz.base.arouter.DownloadARouterPath
import com.jz.base.utils.LogJ
import com.jz.base.ui.BaseARouterActivity
import com.jz.download.R

/**
 * @author zhange
 * @date 2020/7/17.
 * description：
 */
@Route(path = DownloadARouterPath.MAIN_ACTIVITY)
class MainActivity : BaseARouterActivity() {

    @JvmField
    @Autowired(name = "stringKey")
    var stringParam: String? = null

    @JvmField
    @Autowired(name = "intKey")
    var intParam: String? = "-1"

    @JvmField
    @Autowired(name = "booleanKey")
    var booleanParam: String? = "false"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.download_activity_main)
        LogJ.d("接收到的参数为: stringParam = $stringParam, intParam = $intParam, booleanParam = $booleanParam")
    }

    override fun onSupportNavigateUp(): Boolean {
        return Navigation.findNavController(this, R.id.container).navigateUp()
    }

}