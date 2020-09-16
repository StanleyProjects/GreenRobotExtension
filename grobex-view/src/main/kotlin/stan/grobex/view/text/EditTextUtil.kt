package stan.grobex.view.text

import android.content.Context
import android.graphics.drawable.Drawable
import android.text.TextWatcher
import android.view.ViewGroup
import android.widget.EditText
import stan.grobex.view.Padding
import stan.grobex.view.TypeDimension
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
    isClickable: Boolean = ViewDefault.isClickable(onClick),
    // text view
    text: CharSequence,
    textSizeUnit: TypeDimension,
    textSize: Float,
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
            isClickable = isClickable
        )
        configure(
            text = text,
            textSizeUnit = textSizeUnit,
            textSize = textSize,
            isAllCaps = isAllCaps,
            textWatchers = textWatchers
        )
        block()
    }
}
