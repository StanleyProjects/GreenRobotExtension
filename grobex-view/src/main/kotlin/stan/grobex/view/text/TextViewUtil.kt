package stan.grobex.view.text

import android.content.Context
import android.graphics.Color
import android.graphics.Typeface
import android.graphics.drawable.Drawable
import android.text.TextWatcher
import android.view.ViewGroup
import android.widget.TextView
import stan.grobex.view.Gravity
import stan.grobex.view.Padding
import stan.grobex.view.TypeDimension
import stan.grobex.view.TypefaceStyle
import stan.grobex.view.ViewDefault
import stan.grobex.view.Visibility
import stan.grobex.view.asViewValue
import stan.grobex.view.configure
import stan.grobex.view.wrapped

internal object TextViewDefault {
    val textSizeDimension: TypeDimension = TypeDimension.SCALED_PIXEL
    const val textSize: Float = 12f
    const val isAllCaps: Boolean = false
    val textWatchers: Set<TextWatcher> = emptySet()
    val textColor: Int = Color.parseColor("#000")
    val typeface: Typeface? = null
    val typefaceStyle: TypefaceStyle = TypefaceStyle.NORMAL
    val gravity: Gravity = Gravity.TOP_LEFT
}

fun TextView.configure(
    gravity: Gravity,
    text: CharSequence,
    textSizeUnit: TypeDimension,
    textSize: Float,
    textColor: Int,
    typeface: Typeface?,
    typefaceStyle: TypefaceStyle,
    isAllCaps: Boolean,
    textWatchers: Set<TextWatcher>
) {
    setText(text)
    setTextSize(textSizeUnit.asViewValue(), textSize)
    setTextColor(textColor)
    setTypeface(typeface, typefaceStyle.asViewValue())
    this.isAllCaps = isAllCaps
    textWatchers.forEach(::addTextChangedListener)
    this.gravity = gravity.asViewValue()
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
    gravity: Gravity,
    text: String,
    textSizeUnit: TypeDimension,
    textSize: Float,
    textColor: Int,
    typeface: Typeface?,
    typefaceStyle: TypefaceStyle,
    isAllCaps: Boolean,
    textWatchers: Set<TextWatcher>,
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
            isClickable = isClickable
        )
        configure(
            text = text,
            textSizeUnit = textSizeUnit,
            textSize = textSize,
            textColor = textColor,
            typeface = typeface,
            typefaceStyle = typefaceStyle,
            isAllCaps = isAllCaps,
            textWatchers = textWatchers,
            gravity = gravity
        )
        block()
    }
}

fun TextView.onTextChanged(
    needToAdd: Boolean = true,
    block: (CharSequence?) -> Unit
): TextWatcher {
    val result = onTextChanged(block)
    if (needToAdd) {
        addTextChangedListener(result)
    }
    return result
}
