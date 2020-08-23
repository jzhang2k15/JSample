package com.jz.base

import android.content.Context
import android.graphics.Color
import androidx.annotation.ColorRes
import androidx.annotation.FloatRange
import androidx.core.content.ContextCompat

/**
 * @author zhange
 * @date 2020/8/4.
 * description：
 */
/**
 * 对指定颜色的不透明度设置，0 是透明，1 是不透明
 */
fun colorAlpha(context: Context, @ColorRes colorRes: Int, @FloatRange(from = 0.0, to = 1.0) alpha: Float): Int {
    val color = ContextCompat.getColor(context, colorRes)
    val r = Color.red(color)
    val g = Color.green(color)
    val b = Color.blue(color)
    val a = (255 * alpha).toInt()
    LogJ.d("color = $color, r = $r, g = $g, b = $b, a = $a")
    return Color.argb(a, r, g, b)
}