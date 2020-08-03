package com.jz.download.utils.handle

import com.liulishuo.okdownload.DownloadTask
import com.liulishuo.okdownload.core.cause.EndCause
import com.liulishuo.okdownload.core.cause.ResumeFailedCause
import com.liulishuo.okdownload.core.listener.DownloadListener1
import com.liulishuo.okdownload.core.listener.assist.Listener1Assist
import java.lang.Exception

/**
 * @author zhange
 * @date 2020/8/2.
 * description：仿照 DownloadListener3 写的 DSL 结构 listener
 */
class DownloadListenerDSL : DownloadListener1() {

    private var start: ((task: DownloadTask) -> Unit)? = null
    private var completed: ((task: DownloadTask) -> Unit)? = null
    private var canceled: ((task: DownloadTask) -> Unit)? = null
    private var error: ((task: DownloadTask, realCause: Exception?) -> Unit)? = null
    private var warn: ((task: DownloadTask) -> Unit)? = null
    private var progress: ((task: DownloadTask, currentOffset: Long, totalLength: Long) -> Unit)? =
        null

    override fun taskStart(task: DownloadTask, model: Listener1Assist.Listener1Model) {
        start?.invoke(task)
    }

    override fun taskEnd(
        task: DownloadTask,
        cause: EndCause,
        realCause: Exception?,
        model: Listener1Assist.Listener1Model
    ) {
        when (cause) {
            EndCause.COMPLETED -> {
                // 下载完成
                completed?.invoke(task)
            }
            EndCause.ERROR, EndCause.PRE_ALLOCATE_FAILED -> {
                // 下载出错
                error?.invoke(task, realCause)
            }
            EndCause.CANCELED -> {
                // 下载取消
                canceled?.invoke(task)
            }
            EndCause.FILE_BUSY, EndCause.SAME_TASK_BUSY -> {
                // 下载正忙, 下载任务正在进行
                warn?.invoke(task)
            }
        }
    }

    override fun progress(task: DownloadTask, currentOffset: Long, totalLength: Long) {
        progress?.invoke(task, currentOffset, totalLength)
    }

    override fun connected(
        task: DownloadTask,
        blockCount: Int,
        currentOffset: Long,
        totalLength: Long
    ) {
    }

    override fun retry(task: DownloadTask, cause: ResumeFailedCause) {}

    fun start(start: (task: DownloadTask) -> Unit) {
        this.start = start
    }

    fun completed(completed: (task: DownloadTask) -> Unit) {
        this.completed = completed
    }

    fun progress(progress: (task: DownloadTask, currentOffset: Long, totalLength: Long) -> Unit) {
        this.progress = progress
    }

    fun canceled(canceled: (task: DownloadTask) -> Unit) {
        this.canceled = canceled
    }

    fun error(error: (task: DownloadTask, realCause: Exception?) -> Unit) {
        this.error = error
    }

    fun warn(warn: (task: DownloadTask) -> Unit) {
        this.warn = warn
    }

}