package stan.grobex.view

import android.content.Context
import android.graphics.drawable.Drawable
import android.view.ViewGroup
import android.widget.TextView

internal object TextViewDefault {
    val textSizeDimension: TypeDimension = TypeDimension.SCALED_PIXEL
    const val textSize: Float = 12f
    const val isAllCaps: Boolean = false
}

fun TextView.configure(
    text: String,
    textSizeUnit: TypeDimension,
    textSize: Float,
    isAllCaps: Boolean
) {
    this.text = text
    setTextSize(textSizeUnit.asViewValue(), textSize)
    this.isAllCaps = isAllCaps
}

fun textView(
    context: Context,
    layoutParams: ViewGroup.LayoutParams = ViewGroup::class.wrapped,
    // view
    background: Drawable,
    visibility: Visibility = ViewDefault.visibility,
    padding: Padding = ViewDefault.padding,
    onClick: () -> Unit = ViewDefault.onClick,
    isClickable: Boolean = ViewDefault.isClickable(onClick),
    // text view
    text: String,
    textSizeUnit: TypeDimension,
    textSize: Float,
    isAllCaps: Boolean,
    //
    block: TextView.() -> Unit = {}
): TextView {
    return TextView(context).apply {
        configure(
            layoutParams = layoutParams,
            background = background,
            visibility = visibility,
            padding = padding,
            onClick = onClick,
            isClickable = isClickable,
            block = block
        )
        configure(
            text = text,
            textSizeUnit = textSizeUnit,
            textSize = textSize,
            isAllCaps = isAllCaps
        )
    }
}
