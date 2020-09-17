package stan.grobex.view.group

import android.content.Context
import android.graphics.Typeface
import android.graphics.drawable.Drawable
import android.text.TextWatcher
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import kotlin.reflect.KClass
import stan.grobex.view.ImageViewDefault
import stan.grobex.view.ViewDefault
import stan.grobex.view.configure
import stan.grobex.view.entity.Gravity
import stan.grobex.view.entity.Margin
import stan.grobex.view.entity.Orientation
import stan.grobex.view.entity.Padding
import stan.grobex.view.entity.TypeDimension
import stan.grobex.view.entity.Visibility
import stan.grobex.view.entity.asViewValue
import stan.grobex.view.entity.noMargin
import stan.grobex.view.imageView
import stan.grobex.view.text.TextViewDefault
import stan.grobex.view.text.TypefaceStyle
import stan.grobex.view.text.editText
import stan.grobex.view.text.textView
import stan.grobex.view.view

internal object LinearLayoutDefault {
    val orientation: Orientation = Orientation.HORIZONTAL
    val gravity: Gravity = Gravity.TOP_LEFT
    const val weight: Float = 0f
}

fun linearLayout(
    context: Context,
    layoutParams: ViewGroup.LayoutParams = ViewGroup::class.matched,
    // view
    id: Int = ViewDefault.id,
    background: Drawable = ViewDefault.background,
    visibility: Visibility = ViewDefault.visibility,
    padding: Padding = ViewDefault.padding,
    onClick: () -> Unit = ViewDefault.onClick,
    onLongClick: () -> Boolean = ViewDefault.onLongClick,
    isClickable: Boolean = ViewDefault.isClickable(onClick, onLongClick),
    keepScreenOn: Boolean = ViewDefault.keepScreenOn,
    // linear layout
    orientation: Orientation = LinearLayoutDefault.orientation,
    gravity: Gravity = LinearLayoutDefault.gravity,
    //
    block: LinearLayout.() -> Unit = {}
): LinearLayout {
    return LinearLayout(context).apply {
        configure(
            layoutParams = layoutParams,
            // view
            id = id,
            background = background,
            visibility = visibility,
            padding = padding,
            onClick = onClick,
            onLongClick = onLongClick,
            isClickable = isClickable,
            keepScreenOn = keepScreenOn
        )
        this.orientation = orientation.asViewValue()
        this.gravity = gravity.asViewValue()
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

fun LinearLayout.view(
    context: Context = this.context,
    width: Int = ViewGroup.LayoutParams.WRAP_CONTENT,
    height: Int = ViewGroup.LayoutParams.WRAP_CONTENT,
    weight: Float = LinearLayoutDefault.weight,
    margin: Margin = noMargin,
    // view
    id: Int = ViewDefault.id,
    background: Drawable = ViewDefault.background,
    visibility: Visibility = ViewDefault.visibility,
    padding: Padding = ViewDefault.padding,
    onClick: () -> Unit = ViewDefault.onClick,
    onLongClick: () -> Boolean = ViewDefault.onLongClick,
    isClickable: Boolean = ViewDefault.isClickable(onClick, onLongClick),
    keepScreenOn: Boolean = ViewDefault.keepScreenOn,
    //
    needToAdd: Boolean = true,
    block: View.() -> Unit = {}
): View {
    val result = view(
        context = context,
        layoutParams = LinearLayout::class.layoutParams(
            width = width,
            height = height,
            weight = weight,
            margin = margin
        ),
        // view
        id = id,
        background = background,
        visibility = visibility,
        padding = padding,
        onClick = onClick,
        onLongClick = onLongClick,
        isClickable = isClickable,
        keepScreenOn = keepScreenOn,
        //
        block = block
    )
    if (needToAdd) {
        addView(result)
    }
    return result
}

fun LinearLayout.textView(
    context: Context = this.context,
    width: Int = ViewGroup.LayoutParams.WRAP_CONTENT,
    height: Int = ViewGroup.LayoutParams.WRAP_CONTENT,
    weight: Float = LinearLayoutDefault.weight,
    margin: Margin = noMargin,
    // view
    id: Int = ViewDefault.id,
    background: Drawable = ViewDefault.background,
    visibility: Visibility = ViewDefault.visibility,
    padding: Padding = ViewDefault.padding,
    onClick: () -> Unit = ViewDefault.onClick,
    onLongClick: () -> Boolean = ViewDefault.onLongClick,
    isClickable: Boolean = ViewDefault.isClickable(onClick, onLongClick),
    keepScreenOn: Boolean = ViewDefault.keepScreenOn,
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
        id = id,
        background = background,
        visibility = visibility,
        padding = padding,
        onClick = onClick,
        onLongClick = onLongClick,
        isClickable = isClickable,
        keepScreenOn = keepScreenOn,
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
    id: Int = ViewDefault.id,
    background: Drawable = ViewDefault.background,
    visibility: Visibility = ViewDefault.visibility,
    padding: Padding = ViewDefault.padding,
    onClick: () -> Unit = ViewDefault.onClick,
    onLongClick: () -> Boolean = ViewDefault.onLongClick,
    isClickable: Boolean = ViewDefault.isClickable(onClick, onLongClick),
    keepScreenOn: Boolean = ViewDefault.keepScreenOn,
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
        id = id,
        background = background,
        visibility = visibility,
        padding = padding,
        onClick = onClick,
        onLongClick = onLongClick,
        isClickable = isClickable,
        keepScreenOn = keepScreenOn,
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

fun LinearLayout.imageView(
    context: Context = this.context,
    width: Int = ViewGroup.LayoutParams.WRAP_CONTENT,
    height: Int = ViewGroup.LayoutParams.WRAP_CONTENT,
    weight: Float = LinearLayoutDefault.weight,
    margin: Margin = noMargin,
    // view
    id: Int = ViewDefault.id,
    background: Drawable = ViewDefault.background,
    visibility: Visibility = ViewDefault.visibility,
    padding: Padding = ViewDefault.padding,
    onClick: () -> Unit = ViewDefault.onClick,
    onLongClick: () -> Boolean = ViewDefault.onLongClick,
    isClickable: Boolean = ViewDefault.isClickable(onClick, onLongClick),
    keepScreenOn: Boolean = ViewDefault.keepScreenOn,
    // imageView
    drawable: Drawable = ImageViewDefault.drawable,
    //
    needToAdd: Boolean = true,
    block: ImageView.() -> Unit = {}
): ImageView {
    val result = imageView(
        context = context,
        layoutParams = LinearLayout::class.layoutParams(
            width = width,
            height = height,
            weight = weight,
            margin = margin
        ),
        // view
        id = id,
        background = background,
        visibility = visibility,
        padding = padding,
        onClick = onClick,
        onLongClick = onLongClick,
        isClickable = isClickable,
        keepScreenOn = keepScreenOn,
        // imageView
        drawable = drawable,
        //
        block = block
    )
    if (needToAdd) {
        addView(result)
    }
    return result
}
