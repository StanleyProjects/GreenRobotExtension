package stan.grobex.view

import android.view.ViewGroup
import kotlin.reflect.KClass

fun KClass<ViewGroup>.layoutParams(width: Int, height: Int): ViewGroup.LayoutParams {
    return ViewGroup.LayoutParams(width, height)
}

fun KClass<ViewGroup>.layoutParams(size: Int): ViewGroup.LayoutParams {
    return layoutParams(width = size, height = size)
}

val KClass<ViewGroup>.wrapped get() = layoutParams(size = ViewGroup.LayoutParams.WRAP_CONTENT)
val KClass<ViewGroup>.matched get() = layoutParams(size = ViewGroup.LayoutParams.MATCH_PARENT)
