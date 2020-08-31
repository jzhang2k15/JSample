package com.jz.wanandroid.ui.fragments

import android.content.Context
import android.net.Uri
import android.widget.ImageView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.navArgs
import com.jz.base.adapter.easy.EasyAdapter
import com.jz.base.addItem
import com.jz.base.ui.BaseFragment
import com.jz.base.utils.ImageUtils
import com.jz.wanandroid.R
import com.jz.wanandroid.databinding.WanandroidItemAlbumBinding
import com.jz.wanandroid.viewmodel.AlbumViewModel
import com.lxj.xpopup.XPopup
import com.lxj.xpopup.interfaces.XPopupImageLoader
import kotlinx.android.synthetic.main.wanandroid_fragment_album.*
import java.io.File

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
                        if (data == null) return@bindViewHolder
                        ImageUtils.load(requireContext(), data, binding.ivPhoto)
                        binding.root.setOnClickListener {
//                            LogJ.d("data = $data, items = $items")
                            showBigPicture(items, position, binding)
                        }
                    }
                }
                .attach(rvAlbum)
    }

    // 展示大图
    private fun showBigPicture(items: MutableList<Uri>, position: Int, binding: WanandroidItemAlbumBinding) {
        XPopup.Builder(requireContext())
                .asImageViewer(binding.ivPhoto, position, items as List<Any>?, { popupView, position ->
                    // 作用是当Pager切换了图片，需要更新源View
                    popupView.updateSrcView((rvAlbum.getChildAt(position) as ConstraintLayout).getChildAt(0) as ImageView?);
                }, object : XPopupImageLoader {
                    override fun loadImage(position: Int, uri: Any, imageView: ImageView) {
                        //必须指定Target.SIZE_ORIGINAL，否则无法拿到原图，就无法享用天衣无缝的动画
                        ImageUtils.loadUriWithOriginSize(requireContext(), uri as Uri, imageView)
                    }

                    override fun getImageFile(context: Context, uri: Any): File? {
                        try {
                            return ImageUtils.getFileOrNull(context, uri as Uri)
                        } catch (e: Exception) {
                            e.printStackTrace()
                        }
                        return null
                    }
                }).show()
    }

    override fun initData() {
        super.initData()
        mViewModel.mPhotoList.observe(viewLifecycleOwner, Observer {
            mAdapter?.refreshDataAndNotify(it)
        })
        mViewModel.fetchPhotos(requireContext(), mArgs.name)
    }


}