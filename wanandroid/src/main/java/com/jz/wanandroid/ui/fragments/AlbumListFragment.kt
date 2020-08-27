package com.jz.wanandroid.ui.fragments

import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.Navigation
import androidx.navigation.fragment.NavHostFragment
import com.jz.base.adapter.easy.EasyAdapter
import com.jz.base.addItem
import com.jz.base.ui.BaseFragment
import com.jz.base.utils.ImageUtils
import com.jz.wanandroid.R
import com.jz.wanandroid.bean.AlbumBean
import com.jz.wanandroid.databinding.WanandroidFragmentAlbumListBinding
import com.jz.wanandroid.databinding.WanandroidItemAlbumBinding
import com.jz.wanandroid.databinding.WanandroidItemAlbumListBinding
import com.jz.wanandroid.utils.AlbumUtils
import com.jz.wanandroid.viewmodel.AlbumViewModel
import kotlinx.android.synthetic.main.wanandroid_fragment_album.*
import kotlinx.android.synthetic.main.wanandroid_fragment_album_list.*

/**
 * @author zhange
 * @date 2020/8/27.
 * description：相册列表
 */
class AlbumListFragment : BaseFragment() {


    // 拿到 activity shared 的 viewModel
    private val mViewModel by activityViewModels<AlbumViewModel>()
    private var mAdapter: EasyAdapter<AlbumBean>? = null

    override fun getLayoutId(): Int = R.layout.wanandroid_fragment_album_list

    override fun initViews() {
        super.initViews()
        mAdapter = EasyAdapter<AlbumBean>()
                .addItem<AlbumBean, WanandroidItemAlbumListBinding>(R.layout.wanandroid_item_album_list) {
                    bindViewHolder { data, items, position, binding ->
                        if (data == null) return@bindViewHolder
                        ImageUtils.load(requireContext(), data.cover, binding.ivCover)
                        val result = if (data.name.contains("/")) {
                            data.name.substring(data.name.lastIndexOf("/") + 1)
                        } else {
                            data.name
                        }

                        binding.tvName.text = "$result (${data.itemCount})"
                        binding.root.setOnClickListener {
                            val bundle = AlbumListFragmentArgs.Builder(data.name).build().toBundle()
                            nav().navigate(R.id.action_to_album_fragment, bundle)
                        }
                    }
                }
                .attach(rvAlbumList)
    }

    override fun initData() {
        super.initData()
        mViewModel.mAlbumList.observe(viewLifecycleOwner, Observer {
            mAdapter?.refreshDataAndNotify(it)
        })
    }
}