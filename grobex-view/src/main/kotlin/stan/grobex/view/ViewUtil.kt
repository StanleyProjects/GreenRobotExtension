package stan.grobex.view

import android.graphics.drawable.ColorDrawable
import android.graphics.drawable.Drawable
import android.view.View
import android.view.ViewGroup

enum class Visibility {
    VISIBLE,
    INVISIBLE,
    GONE,
}

fun Visibility.asViewValue(): Int {
    return when (this) {
        Visibility.VISIBLE -> View.VISIBLE
        Visibility.INVISIBLE -> View.INVISIBLE
        Visibility.GONE -> View.GONE
    }
}

interface Padding {
    val left: Int
    val top: Int
    val right: Int
    val bottom: Int
}

private data class PaddingImpl(
    override val left: Int,
    override val top: Int,
    override val right: Int,
    override val bottom: Int
) : Padding

fun padding(
    left: Int,
    top: Int,
    right: Int,
    bottom: Int
): Padding {
    return PaddingImpl(
        left = left,
        top = top,
        right = right,
        bottom = bottom
    )
}

val noPadding = padding(0, 0, 0, 0)

fun View.getPadding(): Padding {
    return padding(
        left = paddingLeft,
        top = paddingTop,
        right = paddingRight,
        bottom = paddingBottom
    )
}

fun View.setPadding(padding: Padding) {
    setPadding(
        padding.left,
        padding.top,
        padding.right,
        padding.bottom
    )
}

fun View.updatePadding(
    left: Int = paddingLeft,
    top: Int = paddingTop,
    right: Int = paddingRight,
    bottom: Int = paddingBottom
) {
    setPadding(
        left,
        top,
        right,
        bottom
    )
}

internal val UNSPECIFIED_ON_CLICK: () -> Unit = { error("not for use") }

internal object ViewDefault {
    val background = ColorDrawable(0)
    val visibility = Visibility.VISIBLE
    val padding = noPadding
    val onClick = UNSPECIFIED_ON_CLICK
    fun isClickable(onClick: () -> Unit): Boolean {
        return onClick !== UNSPECIFIED_ON_CLICK
    }
}

fun View.configure(
    layoutParams: ViewGroup.LayoutParams,
    //
    background: Drawable,
    visibility: Visibility,
    padding: Padding,
    onClick: () -> Unit,
    isClickable: Boolean
) {
    this.layoutParams = layoutParams
    this.background = background // todo
    this.visibility = visibility.asViewValue()
    setPadding(padding)
    this.isClickable = isClickable
    if (onClick !== UNSPECIFIED_ON_CLICK) {
        setOnClickListener { onClick() }
    }
}
