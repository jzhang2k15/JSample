package com.jz.base.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.alibaba.android.arouter.launcher.ARouter

/**
 * @author zhange
 * @date 2020/7/18.
 * description：
 */
abstract class BaseARouterActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        ARouter.getInstance().inject(this)
    }

}