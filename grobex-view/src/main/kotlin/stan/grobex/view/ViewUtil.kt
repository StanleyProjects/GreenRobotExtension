package stan.grobex.view

import android.graphics.drawable.ColorDrawable
import android.graphics.drawable.Drawable
import android.view.View
import android.view.ViewGroup

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
internal val UNSPECIFIED_ON_LONG_CLICK: () -> Boolean = { error("not for use") }

internal object ViewDefault {
    val background = ColorDrawable(0)
    val visibility = Visibility.VISIBLE
    val padding = noPadding
    val onClick = UNSPECIFIED_ON_CLICK
    val onLongClick: () -> Boolean = UNSPECIFIED_ON_LONG_CLICK
    fun isClickable(
        onClick: () -> Unit,
        onLongClick: () -> Boolean
    ): Boolean {
        return onClick !== UNSPECIFIED_ON_CLICK && onLongClick !== UNSPECIFIED_ON_LONG_CLICK
    }
}

fun View.configure(
    layoutParams: ViewGroup.LayoutParams,
    //
    background: Drawable,
    visibility: Visibility,
    padding: Padding,
    onClick: () -> Unit,
    onLongClick: () -> Boolean,
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
    if (onLongClick !== UNSPECIFIED_ON_LONG_CLICK) {
        setOnLongClickListener { onLongClick() }
    }
}
