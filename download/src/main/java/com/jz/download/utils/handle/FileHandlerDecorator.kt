package com.jz.download.utils.handle

import java.io.File

/**
 * @author zhange
 * @date 2020/8/1.
 * description：
 */
abstract class FileHandlerDecorator(private val mFileHandler: FileHandler) : FileHandler {
    protected val TAG = FileHandlerDecorator::class.java.simpleName

    override fun handle(file: File?): File? {
        return mFileHandler.handle(file)
    }
}