package com.jz.wanandroid.ui.fragments

import android.Manifest
import android.content.ContentUris
import android.net.Uri
import android.os.Build
import android.provider.MediaStore
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.jz.base.adapter.easy.EasyAdapter
import com.jz.base.addItem
import com.jz.base.ui.BaseFragment
import com.jz.base.utils.FileUtils
import com.jz.base.utils.ImageUtils
import com.jz.base.utils.LogJ
import com.jz.wanandroid.R
import com.jz.wanandroid.databinding.WanandroidItemAlbumBinding
import com.jz.wanandroid.utils.AlbumUtils
import com.jz.wanandroid.viewmodel.AlbumViewModel
import com.permissionx.guolindev.PermissionX
import kotlinx.android.synthetic.main.wanandroid_fragment_album.*
import kotlinx.coroutines.*
import java.lang.Thread.sleep

/**
 * @author zhange
 * @date 2020/8/25.
 * description：
 */
class AlbumFragment : BaseFragment() {

    private val mViewModel by viewModels<AlbumViewModel>()
    private var mAdapter: EasyAdapter<Uri>? = null

    override fun getLayoutId(): Int = R.layout.wanandroid_fragment_album

    override fun initViews() {
        super.initViews()
        mAdapter = EasyAdapter<Uri>()
                .addItem<Uri, WanandroidItemAlbumBinding>(R.layout.wanandroid_item_album) {
                    bindViewHolder { data, items, position, binding ->
                        data?.let {
                            ImageUtils.load(requireContext(), data, binding.ivPhoto)
                        }
                    }
                }
                .attach(rvAlbum)
                .refreshDataAndNotify(mutableListOf())
        mViewModel.mList.observe(viewLifecycleOwner, Observer {
            mAdapter?.refreshDataAndNotify(it)
        })

    }

    override fun initData() {
        super.initData()
        PermissionX.init(this)
                .permissions(Manifest.permission.READ_EXTERNAL_STORAGE)
                .onExplainRequestReason { scope, deniedList, beforeRequest ->
                    LogJ.d("需要跟用户解析原因")
                }
                .onForwardToSettings { scope, deniedList ->
                    LogJ.d("需要前往设置页调整")
                }
                .request { allGranted, grantedList, deniedList ->
                    if (allGranted) {
                        getAlbum()
                    } else {
                        LogJ.d("用户拒绝了部分权限")
                    }
                }

        CoroutineScope(Dispatchers.Main).launch {
            val result2 = async { asyncFun(2) }
            val result4 = async { asyncFun(4) }

            LogJ.d("最终结果：${result2.await()} ----- ${result4.await()}")
        }
    }

    private suspend fun asyncFun(param: Long) = withContext(Dispatchers.IO) {
        sleep(param * 1000)
        LogJ.d("开始返回: $param")
        param
    }

    // 获取相册
    private fun getAlbum() {
        CoroutineScope(Dispatchers.Main).launch {
            val list = AlbumUtils.albumIntent(requireContext())
            mViewModel.mList.postValue(list)
        }
    }


}