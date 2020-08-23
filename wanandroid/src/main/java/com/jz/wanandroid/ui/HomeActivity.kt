package com.jz.wanandroid.ui

import android.os.Bundle
import androidx.navigation.Navigation
import com.alibaba.android.arouter.facade.annotation.Route
import com.jz.base.arouter.WanAndroidPath
import com.jz.base.ui.BaseARouterActivity
import com.jz.wanandroid.R

/**
 * @author zhange
 * @date 2020/8/4.
 * description：首页
 */
@Route(path = WanAndroidPath.HOME_ACTIVITY)
class HomeActivity : BaseARouterActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.wanandroid_activity_home)

    }


    override fun onSupportNavigateUp(): Boolean {
        return Navigation.findNavController(this, R.id.container).navigateUp()
    }


}