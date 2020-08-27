package com.jz.wanandroid.ui.fragments

import android.net.Uri
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.navArgs
import com.jz.base.adapter.easy.EasyAdapter
import com.jz.base.addItem
import com.jz.base.ui.BaseFragment
import com.jz.base.utils.ImageUtils
import com.jz.wanandroid.R
import com.jz.wanandroid.databinding.WanandroidItemAlbumBinding
import com.jz.wanandroid.utils.AlbumUtils
import com.jz.wanandroid.viewmodel.AlbumViewModel
import kotlinx.android.synthetic.main.wanandroid_fragment_album.*

/**
 * @author zhange
 * @date 2020/8/25.
 * description：相册详情列表
 */
class AlbumFragment : BaseFragment() {

    //    private val mViewModel by viewModels<AlbumViewModel>()
    // 拿到 activity shared 的 viewModel
    private val mViewModel by activityViewModels<AlbumViewModel>()
    private var mAdapter: EasyAdapter<Uri>? = null

    private val mArgs by navArgs<AlbumListFragmentArgs>()

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
    }

    override fun initData() {
        super.initData()
        mViewModel.mPhotoList.observe(viewLifecycleOwner, Observer {
            mAdapter?.refreshDataAndNotify(it)
        })
        mViewModel.fetchPhotos(requireContext(), mArgs.name)
    }


}