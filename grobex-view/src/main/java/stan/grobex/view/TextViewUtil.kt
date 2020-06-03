package stan.grobex.view

import android.content.Context
import android.view.ViewGroup
import android.widget.TextView

interface TextViewConfig {
    val text: String
    val isAllCaps: Boolean
}
private class TextViewConfigImpl(
    override val text: String,
    override val isAllCaps: Boolean
) : TextViewConfig

fun textViewConfig(
    text: String = "",
    isAllCaps: Boolean = false
): TextViewConfig {
    return TextViewConfigImpl(
        text = text,
        isAllCaps = isAllCaps
    )
}

fun TextView.configure(
    config: TextViewConfig
) {
    text = config.text
    isAllCaps = config.isAllCaps
}

fun textView(
    context: Context,
    layoutParams: ViewGroup.LayoutParams = ViewGroup::class.wrapped,
    viewConfig: ViewConfig = viewConfig(),
    textViewConfig: TextViewConfig = textViewConfig(),
    block: TextView.() -> Unit = {}
): TextView {
    return TextView(context).apply {
        configure(
            layoutParams = layoutParams,
            config = viewConfig,
            block = block
        )
        configure(
            config = textViewConfig
        )
    }
}
