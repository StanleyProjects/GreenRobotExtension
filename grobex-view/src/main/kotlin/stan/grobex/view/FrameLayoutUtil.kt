package stan.grobex.view

import android.content.Context
import android.graphics.drawable.Drawable
import android.text.TextWatcher
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.TextView
import kotlin.reflect.KClass
import stan.grobex.view.text.TextViewDefault
import stan.grobex.view.text.textView

internal object FrameLayoutDefault {
    val gravity: Gravity = Gravity.TOP_LEFT
}

fun frameLayout(
    context: Context,
    layoutParams: ViewGroup.LayoutParams = ViewGroup::class.matched,
    // view
    background: Drawable,
    visibility: Visibility = ViewDefault.visibility,
    padding: Padding = ViewDefault.padding,
    onClick: () -> Unit = ViewDefault.onClick,
    isClickable: Boolean = ViewDefault.isClickable(onClick),
    //
    block: FrameLayout.() -> Unit = {}
): FrameLayout {
    return FrameLayout(context).apply {
        configure(
            layoutParams = layoutParams,
            // view
            background = background,
            visibility = visibility,
            padding = padding,
            onClick = onClick,
            isClickable = isClickable,
            //
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

fun KClass<FrameLayout>.wrapped(gravity: Gravity = FrameLayoutDefault.gravity): FrameLayout.LayoutParams {
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
    layoutGravity: Gravity = FrameLayoutDefault.gravity,
    // view
    background: Drawable,
    visibility: Visibility = ViewDefault.visibility,
    padding: Padding = ViewDefault.padding,
    onClick: () -> Unit = ViewDefault.onClick,
    isClickable: Boolean = ViewDefault.isClickable(onClick),
    // text view
    text: String,
    textSizeUnit: TypeDimension = TextViewDefault.textSizeDimension,
    textSize: Float = TextViewDefault.textSize,
    isAllCaps: Boolean = TextViewDefault.isAllCaps,
    textWatchers: Set<TextWatcher>,
    //
    needToAdd: Boolean = true,
    block: TextView.() -> Unit = {}
): TextView {
    val result = textView(
        context = context,
        layoutParams = FrameLayout::class.layoutParams(
            width = width,
            height = height,
            gravity = layoutGravity
        ),
        // view
        background = background,
        visibility = visibility,
        padding = padding,
        onClick = onClick,
        isClickable = isClickable,
        // text view
        text = text,
        textSizeUnit = textSizeUnit,
        textSize = textSize,
        isAllCaps = isAllCaps,
        textWatchers = textWatchers,
        //
        block = block
    )
    if (needToAdd) {
        addView(result)
    }
    return result
}
