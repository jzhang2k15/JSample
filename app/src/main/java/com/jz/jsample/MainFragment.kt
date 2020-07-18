package com.jz.jsample

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.app_fragment_main.*

/**
 * @author zhange
 * @date 2020/7/17.
 * description：
 */
class MainFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = inflater.inflate(R.layout.app_fragment_main, container, false)

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        tvMain.postDelayed({
            // TODO-使用 ARouter 跳转
//            requireActivity().startActivity(
//                Intent(
//                    requireActivity(),
//                    DownloadMainActivity::class.java
//                )
//            )
            requireActivity().finish()
        }, 1000)
    }


}