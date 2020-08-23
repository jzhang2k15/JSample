package com.jz.base.adapter.easy

import android.content.res.Resources
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.annotation.NonNull
import androidx.annotation.Nullable
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import com.jz.base.getType

/**
 * @author zhange
 * @date 2020/8/13.
 * description：Holder 的创建者
 * 功能：
 * 1、给 adapter 提供 holder
 * @see getHolder
 * 2、给 adapter 提供判断 holder 类型的方法
 * @see isForViewType
 * 3、对外提供获取 Binding 布局的方法
 * @see getBinding
 * 4、对外暴露 holder 的具体绑定操作
 * @see bindViewHolder
 */
abstract class EasyHolderCreator<T, out B : ViewDataBinding>(@LayoutRes val layoutRes: Int) {

    // 根据数据 T，或者是数据在列表中的位置来判断是否该类型（具体逻辑子类实现）
    abstract fun isForViewType(data: T?, position: Int): Boolean

    // 利用 DataBindingUtil 拿到布局，然后返回 Holder 实例
    fun getHolder(@NonNull inflater: LayoutInflater, @Nullable parent: ViewGroup, attachToParent: Boolean): RecyclerView.ViewHolder =
            EasyHolder(DataBindingUtil.inflate<B>(inflater, layoutRes, parent, attachToParent).root)

    // 利用 DataBinding 拿到 binding 布局
    @Throws(Resources.NotFoundException::class)
    fun getBinding(root: View): B {
        return DataBindingUtil.getBinding(root)
                ?: throw Resources.NotFoundException("No itemView found for type B")
    }

    // 绑定 holder 的操作
    abstract fun bindViewHolder(data: T?, items: MutableList<T>, position: Int, holder: RecyclerView.ViewHolder)

}