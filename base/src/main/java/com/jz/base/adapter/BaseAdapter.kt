package com.jz.base.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView

/**
 * @author zhange
 * @date 2020/8/4.
 * description：适配器的基类
 * @param M 数据实体类
 * @param B 布局的 dataBinding
 */
abstract class BaseAdapter<M, B : ViewDataBinding>(private val mContext: Context) :
        RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    var mData: List<M> = ArrayList()

    @LayoutRes
    protected abstract fun getLayoutResId(): Int

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        // 利用 DataBindingUtil 去加载布局，然后跟 holder 绑定起来
        val inflate = DataBindingUtil.inflate<B>(LayoutInflater.from(mContext), getLayoutResId(), parent, false)
        return ViewHolder(inflate.root)
    }

    override fun getItemCount(): Int = mData.size

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        // 利用 DataBindingUtil 拿到加载的布局
        val binding = DataBindingUtil.getBinding<B>(holder.itemView)
        binding?.let {
            onBindItem(it, mData[position], holder)
            it.executePendingBindings()
        }
    }


    // 对外暴露数据 M 和布局 B，在 V 层直接操作 UI
    abstract fun onBindItem(binding: B, item: M, holder: RecyclerView.ViewHolder)

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)
}