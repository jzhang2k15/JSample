package com.jz.base.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment

/**
 * @author zhange
 * @date 2020/8/4.
 * description：Fragment 基类，具有基本的功能
 */
abstract class BaseFragment : Fragment() {

    @LayoutRes
    protected abstract fun getLayoutId(): Int

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?): View = inflater.inflate(getLayoutId(),
            container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
        initData()
    }

    /*用来初始化页面布局*/
    open fun initViews() {}

    /*用来初始化页面数据加载*/
    open fun initData() {}

    fun nav(): NavController = NavHostFragment.findNavController(this)


}