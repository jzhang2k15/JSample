package com.jz.wanandroid.ui.fragments

import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.jz.base.utils.LogJ
import com.jz.base.adapter.easy.EasyAdapter
import com.jz.base.addItem
import com.jz.base.ui.BaseFragment
import com.jz.wanandroid.R
import com.jz.wanandroid.databinding.WanandroidItemVideoBinding
import com.jz.wanandroid.viewmodel.VideoListViewModel
import com.shuyu.gsyvideoplayer.utils.OrientationUtils
import kotlinx.android.synthetic.main.wanandroid_fragment_video_list.*

/**
 * @author zhange
 * @date 2020/8/6.
 * description：
 */
const val LINK_1 = "http://vfx.mtime.cn/Video/2019/06/29/mp4/190629004821240734.mp4"
const val LINK_2 = "http://7xjmzj.com1.z0.glb.clouddn.com/20171026175005_JObCxCE2.mp4"
const val LINK_3 = "https://res.exexm.com/cw_145225549855002"

class VideoListFragment : BaseFragment() {

    private lateinit var oUtils: OrientationUtils

    private val mVideoListViewModel by viewModels<VideoListViewModel>()

    private var mAdapter: EasyAdapter<String>? = null


    override fun getLayoutId(): Int = R.layout.wanandroid_fragment_video_list

    override fun initViews() {
        mVideoListViewModel.mUrlList.observe(this, Observer {
            LogJ.d("有新的数据")
            mAdapter?.refreshDataAndNotify(it)
        })

        rvVideo.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)

        mAdapter = EasyAdapter<String>()
                .addItem<String, WanandroidItemVideoBinding>(R.layout.wanandroid_item_video) {
                    bindViewHolder { data, items, position, binding ->
                        binding.videoPlayer.setUpLazy(data, true, null, null, "这是 ${binding.videoPlayer} 个")
                        binding.videoPlayer.isAutoFullWithSize = true
                        binding.videoPlayer.backButton.visibility = View.GONE
                        binding.videoPlayer.fullscreenButton.setOnClickListener {
                            oUtils = OrientationUtils(requireActivity(), binding.videoPlayer)
                            oUtils.resolveByClick()
                            binding.videoPlayer.startWindowFullscreen(requireContext(), true, true)
                        }
                        binding.videoPlayer.playTag = position.toString()
                        binding.videoPlayer.playPosition = position
                        LogJ.d("binding.videoPlayer.playTag = ${binding.videoPlayer.playTag}")
                        LogJ.d("binding.videoPlayer.playPosition = ${binding.videoPlayer.playPosition}")
                    }
                }
                .attach(rvVideo)
                .refreshDataAndNotify(mutableListOf())

    }

    override fun initData() {
        rvVideo.postDelayed({
            val newData =
                    arrayListOf(LINK_1, LINK_2, LINK_3,
                            LINK_1, LINK_2, LINK_3,
                            LINK_1, LINK_2, LINK_3,
                            LINK_1, LINK_2, LINK_3)
            mVideoListViewModel.mUrlList.postValue(newData)
        }, 300)
    }
}