package com.jz.download.utils.handle

import com.jz.base.LogJ
import com.jz.download.FILE_POSTFIX_DAT
import com.jz.download.FILE_POSTFIX_ZIP
import java.io.File

/**
 * @author zhange
 * @date 2020/8/1.
 * description：对 dat 文件的处理
 */
class DatFileHandler(fileHandler: FileHandler) : FileHandlerDecorator(fileHandler) {
    override fun handle(file: File?): File? {
        val handle = super.handle(file)
        handle?.let {
            return if (!it.exists()) {
                LogJ.w(TAG, "文件不存在，请检查")
                null
            } else {
                val filePostfix = it.absolutePath.substring(it.absolutePath.lastIndexOf("."))
                if (!FILE_POSTFIX_DAT.equals(filePostfix)) {
                    LogJ.w(TAG, "不是 .dat 文件，不走这的处理")
                    return null
                }
                // 提取到最后一个点之前的文件路径
                val prePath = it.absolutePath.substring(0, it.absolutePath.lastIndexOf("."))
                val zipFile = File("$prePath$FILE_POSTFIX_ZIP")
                val renameToResult = it.renameTo(zipFile)
                LogJ.d(TAG, "文件改名 ${it.name} --->>> ${zipFile.name}，结果：$renameToResult")
                if (!renameToResult) null else zipFile
            }
        }
        LogJ.w(TAG, "文件不存在，请检查")
        return null
    }
}