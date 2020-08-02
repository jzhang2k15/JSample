package com.jz.jsample.gift.filehandler

import com.blankj.utilcode.util.FileUtils
import com.jz.base.LogJ
import com.jz.download.utils.handle.FileHandler
import com.jz.download.utils.handle.FileHandlerDecorator
import com.jz.jsample.gift.FILE_GIFT_LOTTIE_IMAGE
import com.jz.jsample.gift.FILE_GIFT_LOTTIE_JSON
import java.io.File

/**
 * @author zhange
 * @date 2020/8/1.
 * description：礼物文件的处理器
 */
class GiftFileHandler(fileHandler: FileHandler, private val block: (() -> Unit)? = null) :
    FileHandlerDecorator(fileHandler) {

    override fun handle(file: File?): File? {
        val handle = super.handle(file)
        // 这里默认上级传过来的是一个文件夹，文件夹下面应该方有 images 文件夹，以及一个 data.json 的 lottie 动画文件
        handle?.let {
            if (!handle.exists() || !handle.isDirectory) {
                LogJ.w(TAG, "GiftFileHandler 收到的文件不存在或者不是目录")
                return null
            }
            val files = it.listFiles() ?: return null
            // 这时候列表里面应该有一个 zip 文件，还有一个解压之后的目录
            // 这时候列表里面应该有一个 zip 文件，还有一个解压之后的目录
            for (i in files.indices) {
                val target = files[i]
                // 在解压得到的文件夹里面进行处理
                if (target.isDirectory) {
                    // 里面应该是一个 data.json 文件，以及一个 images 的文件夹
                    val jsonFile =
                        File(target, FILE_GIFT_LOTTIE_JSON)
                    if (!jsonFile.exists()) {
                        LogJ.w(TAG, "解压出来的文件夹里面没有 data.json 文件，请检查")
                        return null
                    }
                    val imagesDir =
                        File(target, FILE_GIFT_LOTTIE_IMAGE)
                    if (!imagesDir.exists()) {
                        LogJ.w(TAG, "解压出来的文件夹里面没有 images 文件夹，请检查")
                        return null
                    }
                    LogJ.d(TAG, "json 文件路径：" + jsonFile.absolutePath)
                    LogJ.d(TAG, "json 文件理想路径：" + handle + "/" + jsonFile.name)
                    LogJ.d(TAG, "image 文件路径：" + imagesDir.absolutePath)
                    LogJ.d(TAG, "image 文件理想路径：" + handle + "/" + imagesDir.name)
                    if (jsonFile.absolutePath != handle.toString() + "/" + jsonFile.name) {
                        // 假如解压到的文件路径跟预设的不一致，则移动文件
                        val imageMove: Boolean = FileUtils.move(
                            imagesDir.absolutePath,
                            handle.toString() + "/" + imagesDir.name
                        )
                        val jsonMove: Boolean = FileUtils.move(
                            jsonFile.absolutePath,
                            handle.toString() + "/" + jsonFile.name
                        )
                        LogJ.d(TAG, "开始移动文件")
                        LogJ.d(TAG, "imageMove = $imageMove")
                        LogJ.d(TAG, "jsonMove = $jsonMove")
                        if (imageMove && jsonMove) {
                            FileUtils.delete(target)
                            LogJ.d(TAG, "移动完成，删除原来的文件夹")
                            val name: String = handle.name
//                            GiftCacheHelper.getInstance()
//                                .addImages(name, handle.getAbsolutePath())
//                            GiftCacheHelper.getInstance()
//                                .addJson(name, handle.toString() + "/" + jsonFile.name)
                            var id: Int
                            id = if (name.contains("_")) {
                                name.substring(0, name.indexOf("_")).toInt()
                            } else {
                                name.toInt()
                            }
                            // 操作完成，发起回调
                            block?.invoke()
                        }
                    }
                }
            }
        }
        return null
    }

}