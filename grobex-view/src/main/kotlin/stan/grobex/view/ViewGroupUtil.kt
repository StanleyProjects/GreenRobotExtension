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

interface Margin {
    val left: Int
    val top: Int
    val right: Int
    val bottom: Int
}

private data class MarginImpl(
    override val left: Int,
    override val top: Int,
    override val right: Int,
    override val bottom: Int
) : Margin

fun margin(
    left: Int = 0,
    top: Int = 0,
    right: Int = 0,
    bottom: Int = 0
): Margin {
    return MarginImpl(
        left = left,
        top = top,
        right = right,
        bottom = bottom
    )
}

val noMargin = margin()

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
