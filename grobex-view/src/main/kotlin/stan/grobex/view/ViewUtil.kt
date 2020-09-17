package stan.grobex.view

import android.content.Context
import android.graphics.drawable.ColorDrawable
import android.graphics.drawable.Drawable
import android.view.View
import android.view.ViewGroup
import stan.grobex.view.entity.Padding
import stan.grobex.view.entity.Visibility
import stan.grobex.view.entity.asViewValue
import stan.grobex.view.entity.noPadding
import stan.grobex.view.entity.padding
import stan.grobex.view.group.wrapped

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
    const val id = View.NO_ID
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
    const val keepScreenOn: Boolean = false
}

fun View.configure(
    layoutParams: ViewGroup.LayoutParams,
    //
    id: Int,
    background: Drawable,
    visibility: Visibility,
    padding: Padding,
    onClick: () -> Unit,
    onLongClick: () -> Boolean,
    isClickable: Boolean,
    keepScreenOn: Boolean
) {
    this.id = id
    this.layoutParams = layoutParams
    this.background = background
    this.visibility = visibility.asViewValue()
    setPadding(padding)
    this.isClickable = isClickable
    if (onClick !== UNSPECIFIED_ON_CLICK) {
        setOnClickListener { onClick() }
    }
    if (onLongClick !== UNSPECIFIED_ON_LONG_CLICK) {
        setOnLongClickListener { onLongClick() }
    }
    this.keepScreenOn = keepScreenOn
}

fun view(
    context: Context,
    layoutParams: ViewGroup.LayoutParams = ViewGroup::class.wrapped,
    // view
    id: Int, // todo default
    background: Drawable, // todo default
    visibility: Visibility, // todo default
    padding: Padding, // todo default
    onClick: () -> Unit, // todo default
    onLongClick: () -> Boolean, // todo default
    isClickable: Boolean, // todo default
    keepScreenOn: Boolean, // todo default
    //
    block: View.() -> Unit = {}
): View {
    return View(context).apply {
        configure(
            layoutParams = layoutParams,
            id = id,
            background = background,
            visibility = visibility,
            padding = padding,
            onClick = onClick,
            onLongClick = onLongClick,
            isClickable = isClickable,
            keepScreenOn = keepScreenOn
        )
        block()
    }
}
