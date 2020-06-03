package stan.grobex.view

import android.content.Context
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.TextView
import kotlin.reflect.KClass

fun frameLayout(
    context: Context,
    layoutParams: ViewGroup.LayoutParams = ViewGroup::class.matched,
    viewConfig: ViewConfig = viewConfig(),
    block: FrameLayout.() -> Unit = {}
): FrameLayout {
    return FrameLayout(context).apply {
        configure(
            layoutParams = layoutParams,
            config = viewConfig,
            block = block
        )
    }
}

fun KClass<FrameLayout>.layoutParams(
    width: Int,
    height: Int,
    gravity: Gravity
): FrameLayout.LayoutParams {
    return FrameLayout.LayoutParams(width, height, gravity.asViewValue())
}

fun KClass<FrameLayout>.wrapped(gravity: Gravity = Gravity.TOP_LEFT): FrameLayout.LayoutParams {
    return layoutParams(
        width = ViewGroup.LayoutParams.WRAP_CONTENT,
        height = ViewGroup.LayoutParams.WRAP_CONTENT,
        gravity = gravity
    )
}

fun FrameLayout.textView(
    context: Context = this.context,
    width: Int = ViewGroup.LayoutParams.WRAP_CONTENT,
    height: Int = ViewGroup.LayoutParams.WRAP_CONTENT,
    gravity: Gravity = Gravity.TOP_LEFT,
    viewConfig: ViewConfig = viewConfig(),
    textViewConfig: TextViewConfig = textViewConfig(),
    needToAdd: Boolean = true,
    block: TextView.() -> Unit = {}
): TextView {
    val result = textView(
        context = context,
        layoutParams = FrameLayout::class.layoutParams(
            width = width,
            height = height,
            gravity = gravity
        ),
        viewConfig = viewConfig,
        textViewConfig = textViewConfig,
        block = block
    )
    if (needToAdd) {
        addView(result)
    }
    return result
}
