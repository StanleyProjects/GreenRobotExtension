package stan.grobex.view.group

import android.view.ViewGroup
import kotlin.reflect.KClass
import stan.grobex.view.entity.Margin
import stan.grobex.view.entity.margin

fun KClass<ViewGroup>.layoutParams(width: Int, height: Int): ViewGroup.LayoutParams {
    return ViewGroup.LayoutParams(width, height)
}

fun KClass<ViewGroup>.layoutParams(size: Int): ViewGroup.LayoutParams {
    return layoutParams(width = size, height = size)
}

val KClass<ViewGroup>.wrapped get() = layoutParams(size = ViewGroup.LayoutParams.WRAP_CONTENT)
val KClass<ViewGroup>.matched get() = layoutParams(size = ViewGroup.LayoutParams.MATCH_PARENT)

fun ViewGroup.MarginLayoutParams.setMargin(margin: Margin) {
    setMargins(
        margin.left,
        margin.top,
        margin.right,
        margin.bottom
    )
}

fun KClass<ViewGroup>.layoutParams(width: Int, height: Int, margin: Margin): ViewGroup.MarginLayoutParams {
    val result = ViewGroup.MarginLayoutParams(width, height)
    result.setMargin(margin)
    return result
}

fun ViewGroup.MarginLayoutParams.getMargin(): Margin {
    return margin(
        left = leftMargin,
        top = topMargin,
        right = rightMargin,
        bottom = bottomMargin
    )
}
