package com.jz.wanandroid.ui.fragments

import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.jz.base.adapter.easy.EasyAdapter
import com.jz.base.addItem
import com.jz.wanandroid.bean.WXArticleBean
import com.jz.base.ui.BaseFragment
import com.jz.wanandroid.R
import com.jz.wanandroid.databinding.WanandroidItemArticleBinding
import com.jz.wanandroid.repository.RepositoryProvider
import com.jz.wanandroid.viewmodel.ArticleViewModel
import com.jz.wanandroid.viewmodel.ViewModelFactoryProvider
import kotlinx.android.synthetic.main.wanandroid_fragmnet_article.*
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch

/**
 * @author zhange
 * @date 2020/8/4.
 * description：公众号列表
 */
class ArticleFragments : BaseFragment() {

    //        private val mArticleViewModel by viewModels<ArticleViewModel>()
//        private val mArticleViewModel by lazy { ViewModelProvider(this).get(ArticleViewModel::class.java) }
    //    private val mArticleViewModel2 by lazy { ViewModelProvider.NewInstanceFactory().create(ArticleViewModel::class.java) }
    private val mArticleViewModel: ArticleViewModel by viewModels { ViewModelFactoryProvider.getArticleViewModelFactory() }

    private var mAdapter: EasyAdapter<WXArticleBean>? = null

    override fun getLayoutId(): Int = R.layout.wanandroid_fragmnet_article

    override fun initViews() {
        mAdapter = EasyAdapter<WXArticleBean>()
                .addItem<WXArticleBean, WanandroidItemArticleBinding>(R.layout.wanandroid_item_article) {
                    bindViewHolder { data, items, position, binding ->
                        if (data == null) return@bindViewHolder
                        binding.tvName.text = data.name
                        binding.root.setOnClickListener { }
                    }
                }
                .attach(rvArticle)
    }

    override fun initData() {
        mArticleViewModel.mArticleListLiveData.observe(viewLifecycleOwner, Observer {
            mAdapter?.refreshDataAndNotify(it)
            mAdapter?.notifyDataSetChanged()
        })
        MainScope().launch {
            mArticleViewModel.getArticleList()
        }
    }


}