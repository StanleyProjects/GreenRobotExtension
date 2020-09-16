package stan.grobex.view

import android.content.Context
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
    isClickable: Boolean = ViewDefault.isClickable(onClick),
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
            isClickable = isClickable
        )
        this.orientation = orientation.asViewValue()
        block()
    }
}

fun KClass<LinearLayout>.layoutParams(
    width: Int,
    height: Int,
    weight: Float
): LinearLayout.LayoutParams {
    return LinearLayout.LayoutParams(width, height, weight)
}

fun LinearLayout.textView(
    context: Context = this.context,
    width: Int = ViewGroup.LayoutParams.WRAP_CONTENT,
    height: Int = ViewGroup.LayoutParams.WRAP_CONTENT,
    weight: Float = LinearLayoutDefault.weight,
    // view
    background: Drawable = ViewDefault.background,
    visibility: Visibility = ViewDefault.visibility,
    padding: Padding = ViewDefault.padding,
    onClick: () -> Unit = ViewDefault.onClick,
    isClickable: Boolean = ViewDefault.isClickable(onClick),
    // text view
    text: String = "",
    textSizeUnit: TypeDimension = TextViewDefault.textSizeDimension,
    textSize: Float = TextViewDefault.textSize,
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
            weight = weight
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

fun LinearLayout.editText(
    context: Context = this.context,
    width: Int = ViewGroup.LayoutParams.WRAP_CONTENT,
    height: Int = ViewGroup.LayoutParams.WRAP_CONTENT,
    weight: Float = LinearLayoutDefault.weight,
    // view
    background: Drawable = ViewDefault.background,
    visibility: Visibility = ViewDefault.visibility,
    padding: Padding = ViewDefault.padding,
    onClick: () -> Unit = ViewDefault.onClick,
    isClickable: Boolean = ViewDefault.isClickable(onClick),
    // text view
    text: CharSequence = "",
    textSizeUnit: TypeDimension = TextViewDefault.textSizeDimension,
    textSize: Float = TextViewDefault.textSize,
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
            weight = weight
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
