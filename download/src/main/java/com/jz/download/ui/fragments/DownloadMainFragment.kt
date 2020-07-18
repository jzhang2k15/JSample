package com.jz.download.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.jz.download.R

/**
 * @author zhange
 * @date 2020/7/17.
 * description：
 */
class DownloadMainFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = inflater.inflate(R.layout.download_fragment_download_main, container, false)
}