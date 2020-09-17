package stan.grobex.view

import android.content.Context
import android.graphics.Canvas
import android.graphics.ColorFilter
import android.graphics.drawable.Drawable
import android.view.ViewGroup
import android.widget.ImageView
import stan.grobex.view.entity.Padding
import stan.grobex.view.entity.Visibility

internal val UNSPECIFIED_DRAWABLE: Drawable = object : Drawable() {
    override fun draw(canvas: Canvas) {
        error("not for use")
    }
    override fun setAlpha(alpha: Int) {
        error("not for use")
    }
    override fun setColorFilter(colorFilter: ColorFilter?) {
        error("not for use")
    }
    override fun getOpacity(): Int {
        error("not for use")
    }
}

internal object ImageViewDefault {
    val drawable: Drawable = UNSPECIFIED_DRAWABLE
}

fun ImageView.configure(drawable: Drawable) {
    if (drawable !== UNSPECIFIED_DRAWABLE) {
        setImageDrawable(drawable)
    }
}

fun imageView(
    context: Context,
    layoutParams: ViewGroup.LayoutParams, // todo default
    // view
    id: Int, // todo default
    background: Drawable, // todo default
    visibility: Visibility, // todo default
    padding: Padding, // todo default
    onClick: () -> Unit, // todo default
    onLongClick: () -> Boolean, // todo default
    isClickable: Boolean, // todo default
    keepScreenOn: Boolean, // todo default
    // imageView
    drawable: Drawable, // todo default
    //
    block: ImageView.() -> Unit = {}
): ImageView {
    return ImageView(context).apply {
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
        configure(
            drawable = drawable
        )
        block()
    }
}
