package com.jz.download.utils.handle

import java.io.File

/**
 * @author zhange
 * @date 2020/8/1.
 * description：处理文件的接口，表示具有处理文件的能力
 */
interface FileHandler {

    // 在这个方法里面处理文件，然后通过返回值传递出去
    fun handle(file: File?): File?

}