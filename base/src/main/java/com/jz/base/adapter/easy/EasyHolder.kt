package com.jz.base.adapter.easy

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import org.jetbrains.annotations.NotNull

/**
 * @author zhange
 * @date 2020/8/13.
 * description：Holder 的基类
 */
class EasyHolder(itemView: View) : RecyclerView.ViewHolder(itemView)