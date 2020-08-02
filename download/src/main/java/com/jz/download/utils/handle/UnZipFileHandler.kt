package com.jz.download.utils.handle

import com.blankj.utilcode.util.ZipUtils
import com.jz.base.LogJ
import java.io.File
import java.io.IOException

/**
 * @author zhange
 * @date 2020/8/1.
 * description：解压能力的 handler
 */
class UnZipFileHandler(fileHandler: FileHandler, private val destDir: File? = null) :
    FileHandlerDecorator(fileHandler) {

    override fun handle(file: File?): File? {
        val handle = super.handle(file)
        handle?.let {
            if (!it.exists()) {
                LogJ.w(TAG, ".zip 文件不存在，请检查")
                return null
            }
            return try {
                // 解压文件到当前的目录下
                val targetFile = destDir ?: it.parentFile
                ZipUtils.unzipFile(it, targetFile)
                LogJ.d(TAG, "解压成功: zipFile.name = " + it.name)
                // 把当前的目录传递出去
                targetFile
            } catch (e: IOException) {
                LogJ.w(TAG, "解压失败，请检查")
                null
            }
        }
        LogJ.w(TAG, ".zip 文件不存在，请检查")
        return null
    }
}