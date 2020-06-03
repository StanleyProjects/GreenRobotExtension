package stan.grobex.view

import android.view.View
import android.view.ViewGroup

enum class Visibility {
    VISIBLE,
    INVISIBLE,
    GONE,
}

fun Visibility.asViewValue(): Int {
    return when(this) {
        Visibility.VISIBLE -> View.VISIBLE
        Visibility.INVISIBLE -> View.INVISIBLE
        Visibility.GONE -> View.GONE
    }
}

data class Padding(
    val left: Int,
    val top: Int,
    val right: Int,
    val bottom: Int
)

val noPadding = Padding(0, 0, 0, 0)

fun View.setPadding(padding: Padding) {
    setPadding(
        padding.left,
        padding.top,
        padding.right,
        padding.bottom
    )
}

internal val UNSPECIFIED_ON_CLICK: () -> Unit = { error("not for use") }

interface ViewConfig {
    val visibility: Visibility
    val padding: Padding
    val onClick: () -> Unit
    val isClickable: Boolean
}

private class ViewConfigImpl(
    override val visibility: Visibility,
    override val padding: Padding,
    override val onClick: () -> Unit,
    override val isClickable: Boolean
) : ViewConfig

fun viewConfig(
    visibility: Visibility = Visibility.VISIBLE,
    padding: Padding = noPadding,
    onClick: () -> Unit = UNSPECIFIED_ON_CLICK,
    isClickable: Boolean = onClick !== UNSPECIFIED_ON_CLICK
): ViewConfig {
    return ViewConfigImpl(
        visibility = visibility,
        padding = padding,
        onClick = onClick,
        isClickable = isClickable
    )
}

fun <T : View> T.configure(
    layoutParams: ViewGroup.LayoutParams,
    config: ViewConfig,
    block: T.() -> Unit = {}
) {
    this.layoutParams = layoutParams
    visibility = config.visibility.asViewValue()
    setPadding(config.padding)
    isClickable = config.isClickable
    if(config.onClick !== UNSPECIFIED_ON_CLICK) {
        setOnClickListener { config.onClick() }
    }
    block()
}
