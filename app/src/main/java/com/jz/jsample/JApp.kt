package com.jz.jsample

import android.app.Application
import android.os.Handler
import com.alibaba.android.arouter.launcher.ARouter

/**
 * @author zhange
 * @date 2020/7/18.
 * description：
 */
class JApp : Application() {

    companion object {
        lateinit var sApplication: JApp
        private lateinit var sHandler: Handler

        fun postToUIThread(runnable: Runnable?, delayMills: Long = 0) {
            runnable?.let {
                sHandler.postDelayed(it, delayMills)
            }
        }

        fun isDebug(): Boolean = BuildConfig.DEBUG
    }

    override fun onCreate() {
        super.onCreate()
        sApplication = this
        sHandler = Handler()

        initImmediately()
        initDelay()

    }

    /**
     * 立即初始化的操作
     */
    private fun initImmediately() {
        if (isDebug()) {           // 这两行必须写在init之前，否则这些配置在init过程中将无效
            ARouter.openLog()      // 打印日志
            ARouter.openDebug()    // 开启调试模式(如果在InstantRun模式下运行，必须开启调试模式！线上版本需要关闭,否则有安全风险)
        }
        // 初始化第三方库
        ARouter.init(this)
    }

    /**
     * 延后初始化的操作
     */
    private fun initDelay() {

    }

}