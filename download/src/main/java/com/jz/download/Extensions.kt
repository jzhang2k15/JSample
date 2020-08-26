package com.jz.download

import com.jz.base.utils.LogJ
import com.jz.base.utils.BigDecimalUtils
import com.jz.download.utils.handle.DownloadListenerDSL

/**
 * @author zhange
 * @date 2020/7/17.
 * description：拓展函数集合
 */

/**
 * 拿到一个只打印日志的 下载监听器
 */
fun getSimpleDownloadListenerDSL(): DownloadListenerDSL.() -> Unit {
    return {
        val TAG = "SimpleDownloadListenerDSL"

        start { task ->
            LogJ.d(TAG, "start, fileName: ${task.filename}")
        }

        completed { task ->
            LogJ.d(TAG, "completed, fileName: ${task.filename}")
        }

        progress { task, currentOffset, totalLength ->
            LogJ.d(
                TAG,
                "progress, fileName: ${task.filename}, progress = ${BigDecimalUtils.div(
                    currentOffset.toString(),
                    totalLength.toString(),
                    2
                )}"
            )
        }

        canceled { task ->
            LogJ.d(TAG, "canceled, fileName: ${task.filename}")
        }

        error { task, realCause ->
            LogJ.d(TAG, "error, fileName: ${task.filename}, realCause = $realCause")
        }

    }
}


