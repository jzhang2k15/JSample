package com.jz.wanandroid.ui.fragments

import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.jz.base.utils.LogJ
import com.jz.base.adapter.SimpleAdapter
import com.jz.wanandroid.bean.WXArticleBean
import com.jz.base.utils.logd
import com.jz.base.utils.parseJsonString
import com.jz.base.ui.BaseFragment
import com.jz.wanandroid.R
import com.jz.wanandroid.databinding.WanandroidItemArticleBinding
import com.jz.wanandroid.viewmodel.ArticleViewModel
import kotlinx.android.synthetic.main.wanandroid_fragmnet_article.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch

/**
 * @author zhange
 * @date 2020/8/4.
 * description：公众号列表
 */
class ArticleFragments : BaseFragment() {

    private val mArticleViewModel by viewModels<ArticleViewModel>()

    private var mAdapter: SimpleAdapter<WXArticleBean, WanandroidItemArticleBinding>? = null

    override fun getLayoutId(): Int = R.layout.wanandroid_fragmnet_article

    override fun initViews() {
        rvArticle.layoutManager =
                LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        mAdapter = object : SimpleAdapter<WXArticleBean, WanandroidItemArticleBinding>(requireContext(), R.layout.wanandroid_item_article) {
            override fun onBindItem(binding: WanandroidItemArticleBinding, item: WXArticleBean, holder: RecyclerView.ViewHolder) {
                binding.tvName.text = item.name
                holder.itemView.setOnClickListener { }
            }
        }
        rvArticle.adapter = mAdapter
    }

    override fun initData() {
        mArticleViewModel.mArticleListLiveData.observe(viewLifecycleOwner, Observer {
            mAdapter?.mData = it
            mAdapter?.notifyDataSetChanged()
        })
        MainScope().launch {
            mArticleViewModel.getArticleList()
        }
    }


}