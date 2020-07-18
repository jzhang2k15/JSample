package com.jz.base

import android.content.res.Resources
import android.util.TypedValue

/**
 * @author zhange
 * @date 2020/6/14.
 * description：屏幕工具类
 */

/**
 * Float 的 dp 转为 px
 */
val Float.dp: Float
    get() = TypedValue.applyDimension(
        TypedValue.COMPLEX_UNIT_DIP,
        this,
        Resources.getSystem().displayMetrics
    )

/**
 * Int 的 dp 转为 px
 */
val Int.dp: Int
    get() = TypedValue.applyDimension(
        TypedValue.COMPLEX_UNIT_DIP,
        this.toFloat(),
        Resources.getSystem().displayMetrics
    ).toInt()

/**
 * Float 的 sp 转为 px
 */
val Float.sp: Float
    get() = TypedValue.applyDimension(
        TypedValue.COMPLEX_UNIT_SP,
        this,
        Resources.getSystem().displayMetrics
    )

/**
 * Int 的 sp 转为 px
 */
val Int.sp: Int
    get() = TypedValue.applyDimension(
        TypedValue.COMPLEX_UNIT_SP,
        this.toFloat(),
        Resources.getSystem().displayMetrics
    ).toInt()