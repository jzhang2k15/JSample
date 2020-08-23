package com.jz.base.adapter.easy

import androidx.annotation.LayoutRes
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView

/**
 * @author zhange
 * @date 2020/8/13.
 * description：
 */
class EasyHolderCreatorDSL<T, B : ViewDataBinding>(@LayoutRes layoutRes: Int) : EasyHolderCreator<T, B>(layoutRes) {

    private var viewType: ((data: T?, position: Int) -> Boolean)? = null
    private var bindViewHolder: ((data: T?, items: MutableList<T>, position: Int, binding: B) -> Unit)? = null

    override fun isForViewType(data: T?, position: Int): Boolean = viewType?.invoke(data, position)
            ?: (data != null)

    override fun bindViewHolder(data: T?, items: MutableList<T>, position: Int, holder: RecyclerView.ViewHolder) {
        bindViewHolder?.invoke(data, items, position, getBinding(holder.itemView))
    }

    fun isForViewType(viewType: (data: T?, position: Int) -> Boolean) {
        this.viewType = viewType
    }

    fun bindViewHolder(bindViewHolder: (data: T?, items: MutableList<T>, position: Int, binding: B) -> Unit) {
        this.bindViewHolder = bindViewHolder
    }


}