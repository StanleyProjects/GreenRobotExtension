package stan.grobex.view

import android.content.Context
import android.graphics.Typeface
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
            isClickable = isClickable
        )
        block()
    }
}

fun KClass<FrameLayout>.layoutParams(
    width: Int,
    height: Int,
    gravity: Gravity,
    margin: Margin
): FrameLayout.LayoutParams {
    val result = FrameLayout.LayoutParams(width, height, gravity.asViewValue())
    result.setMargin(margin)
    return result
}

fun KClass<FrameLayout>.wrapped(
    gravity: Gravity = FrameLayoutDefault.gravity,
    margin: Margin = noMargin
): FrameLayout.LayoutParams {
    return layoutParams(
        width = ViewGroup.LayoutParams.WRAP_CONTENT,
        height = ViewGroup.LayoutParams.WRAP_CONTENT,
        gravity = gravity,
        margin = margin
    )
}

fun FrameLayout.textView(
    context: Context = this.context,
    width: Int = ViewGroup.LayoutParams.WRAP_CONTENT,
    height: Int = ViewGroup.LayoutParams.WRAP_CONTENT,
    layoutGravity: Gravity = FrameLayoutDefault.gravity,
    margin: Margin = noMargin,
    // view
    background: Drawable,
    visibility: Visibility = ViewDefault.visibility,
    padding: Padding = ViewDefault.padding,
    onClick: () -> Unit = ViewDefault.onClick,
    isClickable: Boolean = ViewDefault.isClickable(onClick),
    // text view
    gravity: Gravity = TextViewDefault.gravity,
    text: String = "",
    textSizeUnit: TypeDimension = TextViewDefault.textSizeDimension,
    textSize: Float = TextViewDefault.textSize,
    textColor: Int = TextViewDefault.textColor,
    typeface: Typeface? = TextViewDefault.typeface,
    typefaceStyle: TypefaceStyle = TextViewDefault.typefaceStyle,
    isAllCaps: Boolean = TextViewDefault.isAllCaps,
    textWatchers: Set<TextWatcher> = TextViewDefault.textWatchers,
    //
    needToAdd: Boolean = true,
    block: TextView.() -> Unit = {}
): TextView {
    val result = textView(
        context = context,
        layoutParams = FrameLayout::class.layoutParams(
            width = width,
            height = height,
            gravity = layoutGravity,
            margin = margin
        ),
        // view
        background = background,
        visibility = visibility,
        padding = padding,
        onClick = onClick,
        isClickable = isClickable,
        // text view
        gravity = gravity,
        text = text,
        textSizeUnit = textSizeUnit,
        textSize = textSize,
        textColor = textColor,
        typeface = typeface,
        typefaceStyle = typefaceStyle,
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
