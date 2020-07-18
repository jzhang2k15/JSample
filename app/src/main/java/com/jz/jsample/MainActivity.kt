package com.jz.jsample

import android.os.Bundle
import androidx.navigation.Navigation
import com.alibaba.android.arouter.facade.annotation.Route
import com.jz.base.arouter.AppPath
import com.jz.base.ui.BaseARouterActivity

@Route(path = AppPath.MAIN_ACTIVITY)
class MainActivity : BaseARouterActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.app_activity_main)
    }

    override fun onSupportNavigateUp(): Boolean {
        return Navigation.findNavController(this, R.id.container).navigateUp()
    }
}