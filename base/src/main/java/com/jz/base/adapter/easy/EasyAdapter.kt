package com.jz.base.adapter.easy

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.collection.SparseArrayCompat
import androidx.collection.forEach
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView

/**
 * @author zhange
 * @date 2020/8/13.
 * description：
 */
class EasyAdapter<T> : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    // 数据列表
    private val mData: MutableList<T> = mutableListOf()

    // 缓存 viewType，key 是 map 的 size
    private val mViewTypeMap: SparseArrayCompat<EasyHolderCreator<T, ViewDataBinding>> = SparseArrayCompat()

    // 注册 viewType，将 viewType 用 map 的 size 绑定起来
    fun <B : ViewDataBinding> register(creator: EasyHolderCreator<T, B>) = apply {
        // 利用 mViewTypeMap 的 size 作为 viewType
        var viewType = mViewTypeMap.size()
        while (mViewTypeMap.get(viewType) != null) {
            viewType++
        }
        // 假如是没有缓存过的 viewType，则用 size 作为 key，缓存起来
        mViewTypeMap.put(viewType, creator)
    }

    // 绑定到 recyclerview
    fun attach(recyclerView: RecyclerView) = apply { recyclerView.adapter = this }

    // 刷新整个数据列表
    fun refreshDataAndNotify(list: MutableList<T>) = apply {
        mData.clear()
        mData.addAll(list)
        notifyDataSetChanged()
    }

    // 添加一个新的数据到列表最后
    fun addNewData(t: T) = apply {
        mData.add(t)
        notifyItemInserted(mData.lastIndex)
    }

    override fun getItemViewType(position: Int): Int {
        // 遍历 viewType 缓存，假如是合适的数据类型，则将 key 作为 viewType 返回
        mViewTypeMap.forEach { key, creator ->
            val data = mData.getOrNull(position)
            // 利用 creator 提供的 isForViewType 判断是否对应的
            if (creator.isForViewType(data, position)) {
                return key
            }
        }
        throw NullPointerException("No holder add that match position: $position")
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        // 从缓存里面拿出 creator
        val creator = mViewTypeMap.get(viewType)
                ?: throw NullPointerException("No holder add for viewType: $viewType")
        return creator.getHolder(LayoutInflater.from(parent.context), parent, false)
    }

    override fun getItemCount(): Int = mData.size

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        // 从缓存里面拿出 creator
        val creator = mViewTypeMap.get(holder.itemViewType)
                ?: throw NullPointerException("No holder add for viewType: ${holder.itemViewType}")
        // 对外分发
        creator.bindViewHolder(mData.getOrNull(position), mData, position, holder)
    }

}