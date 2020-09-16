package stan.grobex.view

import android.content.Context
import android.graphics.Typeface
import android.graphics.drawable.Drawable
import android.text.TextWatcher
import android.view.ViewGroup
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.TextView
import kotlin.reflect.KClass
import stan.grobex.view.text.TextViewDefault
import stan.grobex.view.text.editText
import stan.grobex.view.text.textView

internal object LinearLayoutDefault {
    val orientation: Orientation = Orientation.HORIZONTAL
    const val weight: Float = 0f
}

fun linearLayout(
    context: Context,
    layoutParams: ViewGroup.LayoutParams = ViewGroup::class.matched,
    // view
    background: Drawable = ViewDefault.background,
    visibility: Visibility = ViewDefault.visibility,
    padding: Padding = ViewDefault.padding,
    onClick: () -> Unit = ViewDefault.onClick,
    onLongClick: () -> Boolean = ViewDefault.onLongClick,
    isClickable: Boolean = ViewDefault.isClickable(onClick, onLongClick),
    // linear layout
    orientation: Orientation = LinearLayoutDefault.orientation,
    //
    block: LinearLayout.() -> Unit = {}
): LinearLayout {
    return LinearLayout(context).apply {
        configure(
            layoutParams = layoutParams,
            // view
            background = background,
            visibility = visibility,
            padding = padding,
            onClick = onClick,
            onLongClick = onLongClick,
            isClickable = isClickable
        )
        this.orientation = orientation.asViewValue()
        block()
    }
}

fun KClass<LinearLayout>.layoutParams(
    width: Int,
    height: Int,
    weight: Float,
    margin: Margin
): LinearLayout.LayoutParams {
    val result = LinearLayout.LayoutParams(width, height, weight)
    result.setMargin(margin)
    return result
}

fun LinearLayout.textView(
    context: Context = this.context,
    width: Int = ViewGroup.LayoutParams.WRAP_CONTENT,
    height: Int = ViewGroup.LayoutParams.WRAP_CONTENT,
    weight: Float = LinearLayoutDefault.weight,
    margin: Margin = noMargin,
    // view
    background: Drawable = ViewDefault.background,
    visibility: Visibility = ViewDefault.visibility,
    padding: Padding = ViewDefault.padding,
    onClick: () -> Unit = ViewDefault.onClick,
    onLongClick: () -> Boolean = ViewDefault.onLongClick,
    isClickable: Boolean = ViewDefault.isClickable(onClick, onLongClick),
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
        layoutParams = LinearLayout::class.layoutParams(
            width = width,
            height = height,
            weight = weight,
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

fun LinearLayout.editText(
    context: Context = this.context,
    width: Int = ViewGroup.LayoutParams.WRAP_CONTENT,
    height: Int = ViewGroup.LayoutParams.WRAP_CONTENT,
    weight: Float = LinearLayoutDefault.weight,
    margin: Margin = noMargin,
    // view
    background: Drawable = ViewDefault.background,
    visibility: Visibility = ViewDefault.visibility,
    padding: Padding = ViewDefault.padding,
    onClick: () -> Unit = ViewDefault.onClick,
    onLongClick: () -> Boolean = ViewDefault.onLongClick,
    isClickable: Boolean = ViewDefault.isClickable(onClick, onLongClick),
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
    block: EditText.() -> Unit = {}
): EditText {
    val result = editText(
        context = context,
        layoutParams = LinearLayout::class.layoutParams(
            width = width,
            height = height,
            weight = weight,
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
