package stan.grobex.view

import android.content.Context
import android.view.ViewGroup
import android.widget.TextView

private val UNSPECIFIED_ON_CLICK: () -> Unit = { throw IllegalStateException("not for use") }

fun textView(
    context: Context,
    layoutParams: ViewGroup.LayoutParams = lpWrapped,
    padding: Padding = noPadding,
//    backgroundColor: Int = color.transparent,//todo
//    textColor: Int = color.black,//todo
//    textSize: Float = dpToPx(15f, context),//todo
    text: String = "",
    visibility: Visibility = Visibility.VISIBLE,
    onClick: () -> Unit = UNSPECIFIED_ON_CLICK,
    isClickable: Boolean = onClick !== UNSPECIFIED_ON_CLICK,
    isAllCaps: Boolean = false,
//    typeface: Typeface = Typeface.DEFAULT,
    block: TextView.() -> Unit = {}
) = TextView(context).apply {
    this.layoutParams = layoutParams
    setPadding(padding)
//    setBackgroundColor(backgroundColor)//todo
    this.text = text
    this.visibility = visibility.asViewValue()
    this.isClickable = isClickable
    this.isAllCaps = isAllCaps
    this.typeface = typeface
//    setTextColor(textColor)//todo
//    setTextSize(TypedValue.COMPLEX_UNIT_PX, textSize)//todo
    if(onClick !== UNSPECIFIED_ON_CLICK) {
        setOnClickListener { onClick() }
    }
    block()
}