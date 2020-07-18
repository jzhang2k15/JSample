package com.jz.base.arouter

import android.content.Context
import com.alibaba.android.arouter.facade.annotation.Route
import com.alibaba.android.arouter.facade.service.SerializationService
import com.google.gson.Gson
import java.lang.reflect.Type

/**
 * @author zhange
 * @date 2020/7/18.
 * description：
 */
@Route(path = JsonConst.PATH)
class JsonServiceImpl : SerializationService {
    private val mGson by lazy { Gson() }

    override fun <T : Any> json2Object(input: String, clazz: Class<T>): T {
        return mGson.fromJson<T>(input, clazz)
    }

    override fun init(context: Context) {
    }

    override fun object2Json(instance: Any): String {
        return mGson.toJson(instance)
    }

    override fun <T : Any> parseObject(input: String, clazz: Type): T {
        return mGson.fromJson<T>(input, clazz)
    }
}