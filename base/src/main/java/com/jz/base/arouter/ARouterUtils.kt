package com.jz.base.arouter

import com.alibaba.android.arouter.launcher.ARouter
import com.jz.base.utils.LogJ
import com.jz.base.initAny


/**
 * @author zhange
 * @date 2020/7/18.
 * description：ARouter 的工具类
 * 这部分的逻辑其实跟 base module 无关，应该提取出去
 */
// ============================= 所有的跳转都写在这里 ==========================
fun gotoDownloadMainActivity(string: String, int: String, boolean: String) {
    goto(DownloadARouterPath.MAIN_ACTIVITY) {
        put("stringKey", "testString")
        put("intKey", "testInt")
        put("booleanKey", "testBoolean")
    }
}

/*跳转到 App 模块的 TestActivity*/
fun gotoAppTestActivity() {
    goto(AppARouterPath.TEST_ACTIVITY)
}


/*跳转到 wanandroid 的 HomeActivity*/
fun gotoWAHomeActivity() {
    goto(WanAndroidPath.HOME_ACTIVITY)
}

/*路由跳转*/
private fun goto(path: String, paramsFunction: (HashMap<String, Any>.() -> Unit)? = null) {
    // 拿到 navigation 对象
    val navigation = ARouter.getInstance().build(path).apply {
        // 如果参数不为空
        paramsFunction?.let { func ->
            // 根据参数生成对应的 map 对象
            val map = initAny(HashMap(), func)
            // 遍历 map 对象，依据 K, V 去添加参数
            map.forEach { entry ->
                val value = entry.value
                LogJ.d("entry = $entry")
                when (value) {
                    is Long -> withLong(entry.key, value)
                    is String -> withString(entry.key, value)
                    is Boolean -> withBoolean(entry.key, value)
                    is Int -> withInt(entry.key, value)
                    else -> {
                        LogJ.e("暂时不支持这种类型的参数")
                    }
                }
            }
        }
    }
    LogJ.d("navigation = $navigation")
    navigation.navigation()
}


// =============================== 所有的 ARouter 路径都写在这里 ======================================
object AppARouterPath {
    const val GROUP = "app"
    const val MAIN_ACTIVITY = "/app/main_activity"
    const val TEST_ACTIVITY = "/app/test_activity"
}

object DownloadARouterPath {
    const val GROUP = "download"
    const val MAIN_ACTIVITY = "/download/main_activity"
}

object WanAndroidPath {
    const val HOME_ACTIVITY = "/wanandroid/home_activity"
}

object JsonConst {
    const val PATH = "/jsonService/json"
}

