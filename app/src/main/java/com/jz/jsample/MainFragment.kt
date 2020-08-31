package com.jz.jsample

import com.jz.base.adapter.easy.EasyAdapter
import com.jz.base.addItem
import com.jz.base.arouter.gotoWAHomeActivity
import com.jz.base.ui.BaseFragment
import com.jz.jsample.bean.ModuleBean
import com.jz.jsample.databinding.AppItemModuleMainBinding
import kotlinx.android.synthetic.main.app_fragment_main.*

/**
 * @author zhange
 * @date 2020/7/17.
 * description：
 */
class MainFragment : BaseFragment() {

    private var mAdapter: EasyAdapter<ModuleBean>? = null

    private val mList by lazy {
        ArrayList<ModuleBean>().apply {
            add(ModuleBean(this.size, "Album"))
        }.apply {  }
    }

    override fun getLayoutId(): Int = R.layout.app_fragment_main

    override fun initViews() {
        super.initViews()

        mAdapter = EasyAdapter<ModuleBean>()
                .addItem<ModuleBean, AppItemModuleMainBinding>(R.layout.app_item_module_main) {
                    bindViewHolder { data, items, position, binding ->
                        if (data == null) return@bindViewHolder
                        binding.tvModule.text = data.name
                        binding.tvModule.setOnClickListener {
                            gotoWAHomeActivity()
                        }
                    }
                }
                .attach(rvMain)
                .refreshDataAndNotify(mList)
    }
}