package stan.grobex.view.text

import android.content.Context
import android.graphics.Typeface
import android.graphics.drawable.Drawable
import android.text.TextWatcher
import android.view.ViewGroup
import android.widget.EditText
import stan.grobex.view.ViewDefault
import stan.grobex.view.configure
import stan.grobex.view.entity.Gravity
import stan.grobex.view.entity.Padding
import stan.grobex.view.entity.TypeDimension
import stan.grobex.view.entity.Visibility
import stan.grobex.view.group.wrapped

fun editText(
    context: Context,
    layoutParams: ViewGroup.LayoutParams = ViewGroup::class.wrapped,
    // view
    id: Int, // todo default
    background: Drawable = ViewDefault.background,
    visibility: Visibility = ViewDefault.visibility,
    padding: Padding = ViewDefault.padding,
    onClick: () -> Unit = ViewDefault.onClick,
    onLongClick: () -> Boolean, // todo default
    isClickable: Boolean = ViewDefault.isClickable(onClick, onLongClick),
    keepScreenOn: Boolean, // todo default
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
