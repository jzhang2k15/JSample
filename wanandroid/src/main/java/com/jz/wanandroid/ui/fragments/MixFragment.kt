package com.jz.wanandroid.ui.fragments

import android.Manifest
import android.content.Intent
import android.provider.MediaStore
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.blankj.utilcode.util.PermissionUtils
import com.jz.base.utils.LogJ
import com.jz.base.adapter.easy.EasyAdapter
import com.jz.base.addItem
import com.jz.base.ui.BaseFragment
import com.jz.base.utils.FileUtils
import com.jz.wanandroid.R
import com.jz.wanandroid.bean.mix.MixBean1
import com.jz.wanandroid.bean.mix.MixBean2
import com.jz.wanandroid.bean.mix.MixBeanParent
import com.jz.wanandroid.databinding.WanandroidItemMix1Binding
import com.jz.wanandroid.databinding.WanandroidItemMix2Binding
import com.jz.wanandroid.viewmodel.MixViewModel
import com.permissionx.guolindev.PermissionX
import kotlinx.android.synthetic.main.wanandroid_fragment_mix.*

/**
 * @author zhange
 * @date 2020/8/21.
 * description：
 */
class MixFragment : BaseFragment() {

    private var mAdapter: EasyAdapter<MixBeanParent>? = null
    private val mViewModel by viewModels<MixViewModel>()

    override fun getLayoutId(): Int = R.layout.wanandroid_fragment_mix


    override fun initViews() {
        super.initViews()
        rvMix.layoutManager = LinearLayoutManager(requireContext())
        mAdapter = EasyAdapter<MixBeanParent>()
                .addItem<MixBeanParent, WanandroidItemMix1Binding>(R.layout.wanandroid_item_mix_1) {
                    isForViewType { data, _ -> data is MixBean1 }
                    bindViewHolder { data, _, _, binding ->
                        data?.let {
                            binding.tvContent.text = it.content
                        }
                    }
                }
                .addItem<MixBeanParent, WanandroidItemMix2Binding>(R.layout.wanandroid_item_mix_2) {
                    isForViewType { data, _ -> data is MixBean2 }
                    bindViewHolder { data, _, _, binding ->
                        data?.let {
                            binding.tvContent.text = it.content
                        }
                    }
                }
                .attach(rvMix)
    }

    override fun initData() {
        super.initData()
        mViewModel.mList.observe(viewLifecycleOwner, Observer {
            LogJ.d("接收到新数据 ==========")
            mAdapter?.refreshDataAndNotify(it)
        })

        rvMix.postDelayed({
            val newData = mutableListOf(
                    MixBean1("1", "111"), MixBean1("11", "111"), MixBean1("111", "111"),
                    MixBean2("2", "2"), MixBean2("22", "22"), MixBean2("222", "222")
            )
            mViewModel.mList.postValue(newData)
            LogJ.d("发射新数据 >>>>>>>>>")
        }, 300)

        PermissionX.init(this)
                .permissions(Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE)
                .onExplainRequestReason { scope, deniedList, beforeRequest ->
                    LogJ.d("需要跟用户解析原因")
                }
                .onForwardToSettings { scope, deniedList ->
                    LogJ.d("需要前往设置页调整")
                }
                .request { allGranted, grantedList, deniedList ->
                    if (allGranted) {
                        LogJ.d("已经有权限了")
                        FileUtils.albumIntent(requireContext())
                    } else {
                        LogJ.d("用户拒绝了部分权限")
                    }
                }
    }

}