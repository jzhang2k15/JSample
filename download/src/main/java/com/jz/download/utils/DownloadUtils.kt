package com.jz.download.utils

import com.jz.download.utils.handle.DownloadListenerDSL
import com.liulishuo.okdownload.DownloadTask
import java.io.File

/**
 * @author zhange
 * @date 2020/8/1.
 * description：下载的工具类
 */
object DownloadUtils {

    /**
     * 下载
     *
     * @param url       下载的 url
     * @param parentDir 下载到的目录
     * @param fileName  下载之后保存的文件名
     * @param dsl       下载监听器
     */
    fun download(
        url: String,
        parentDir: File,
        fileName: String,
        dsl: (DownloadListenerDSL.() -> Unit)? = null
    ) {
        val task = DownloadTask.Builder(url, parentDir)
            .setFilename(fileName)
            // 任务已经完成的情况下，直接跳过
            .setPassIfAlreadyCompleted(true)
            .build()
        dsl?.let {
            task.enqueue(DownloadListenerDSL().apply(it))
        }
    }


}