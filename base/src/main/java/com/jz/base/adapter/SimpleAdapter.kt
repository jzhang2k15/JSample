package com.jz.base.adapter

import android.content.Context
import androidx.annotation.LayoutRes
import androidx.databinding.ViewDataBinding

/**
 * @author zhange
 * @date 2020/8/4.
 * description：
 */
abstract class SimpleAdapter<M, B : ViewDataBinding>(context: Context, @LayoutRes private val layoutRes: Int) :
        BaseAdapter<M, B>(context) {

    override fun getLayoutResId(): Int = layoutRes
}