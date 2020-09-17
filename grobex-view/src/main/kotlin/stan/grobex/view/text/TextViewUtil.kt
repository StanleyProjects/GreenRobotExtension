package stan.grobex.view.text

import android.content.Context
import android.graphics.Color
import android.graphics.Typeface
import android.graphics.drawable.Drawable
import android.text.TextWatcher
import android.view.ViewGroup
import android.widget.TextView
import stan.grobex.view.configure
import stan.grobex.view.entity.Gravity
import stan.grobex.view.entity.Padding
import stan.grobex.view.entity.TypeDimension
import stan.grobex.view.entity.Visibility
import stan.grobex.view.entity.asViewValue
import stan.grobex.view.group.wrapped

internal object TextViewDefault {
    val textSizeDimension: TypeDimension = TypeDimension.SCALED_PIXEL
    const val textSize: Float = 12f
    const val isAllCaps: Boolean = false
    val textWatchers: Set<TextWatcher> = emptySet()
    val textColor: Int = Color.parseColor("#000000")
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
    id: Int, // todo default
    background: Drawable, // todo default
    visibility: Visibility, // todo default
    padding: Padding, // todo default
    onClick: () -> Unit, // todo default
    onLongClick: () -> Boolean, // todo default
    isClickable: Boolean, // todo default
    keepScreenOn: Boolean, // todo default
    // text view
    gravity: Gravity, // todo default
    text: String, // todo default
    textSizeUnit: TypeDimension, // todo default
    textSize: Float, // todo default
    textColor: Int, // todo default
    typeface: Typeface?, // todo default
    typefaceStyle: TypefaceStyle, // todo default
    isAllCaps: Boolean, // todo default
    textWatchers: Set<TextWatcher>, // todo default
    //
    block: TextView.() -> Unit = {}
): TextView {
    return TextView(context).apply {
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
