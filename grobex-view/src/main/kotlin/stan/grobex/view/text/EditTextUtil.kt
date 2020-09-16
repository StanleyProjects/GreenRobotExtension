package stan.grobex.view.text

import android.content.Context
import android.graphics.Typeface
import android.graphics.drawable.Drawable
import android.text.TextWatcher
import android.view.ViewGroup
import android.widget.EditText
import stan.grobex.view.Gravity
import stan.grobex.view.Padding
import stan.grobex.view.TypeDimension
import stan.grobex.view.TypefaceStyle
import stan.grobex.view.ViewDefault
import stan.grobex.view.Visibility
import stan.grobex.view.configure
import stan.grobex.view.wrapped

fun editText(
    context: Context,
    layoutParams: ViewGroup.LayoutParams = ViewGroup::class.wrapped,
    // view
    background: Drawable,
    visibility: Visibility = ViewDefault.visibility,
    padding: Padding = ViewDefault.padding,
    onClick: () -> Unit = ViewDefault.onClick,
    onLongClick: () -> Boolean = ViewDefault.onLongClick,
    isClickable: Boolean = ViewDefault.isClickable(onClick, onLongClick),
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
    block: EditText.() -> Unit = {}
): EditText {
    return EditText(context).apply {
        configure(
            layoutParams = layoutParams,
            background = background,
            visibility = visibility,
            padding = padding,
            onClick = onClick,
            onLongClick = onLongClick,
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
