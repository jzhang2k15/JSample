package com.jz.wanandroid.ui

import android.Manifest
import android.os.Bundle
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.Navigation
import com.alibaba.android.arouter.facade.annotation.Route
import com.jz.base.arouter.WanAndroidPath
import com.jz.base.ui.BaseARouterActivity
import com.jz.base.utils.LogJ
import com.jz.wanandroid.R
import com.jz.wanandroid.viewmodel.AlbumViewModel
import com.permissionx.guolindev.PermissionX

/**
 * @author zhange
 * @date 2020/8/4.
 * description：首页
 */
@Route(path = WanAndroidPath.HOME_ACTIVITY)
class HomeActivity : BaseARouterActivity() {

    // fragment 之间共享的 viewModel
    private val mSharedViewModel by lazy {
        ViewModelProviders.of(this).get(AlbumViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.wanandroid_activity_home)
        PermissionX.init(this)
            .permissions(Manifest.permission.READ_EXTERNAL_STORAGE)
            .onExplainRequestReason { scope, deniedList, beforeRequest ->
                LogJ.d("需要跟用户解析原因")
            }
            .onForwardToSettings { scope, deniedList ->
                LogJ.d("需要前往设置页手动授权")
            }
            .request { allGranted, grantedList, deniedList ->
                if (allGranted) {
                    mSharedViewModel.fetchAlbum(this)
                } else {
                    LogJ.d("用户拒绝了部分权限")
                }
            }
    }

    override fun onSupportNavigateUp(): Boolean {
        return Navigation.findNavController(this, R.id.container).navigateUp()
    }


}