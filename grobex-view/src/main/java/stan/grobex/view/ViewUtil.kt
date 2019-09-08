package stan.grobex.view

import android.view.View

enum class Visibility {
    VISIBLE,
    INVISIBLE,
    GONE,
}

fun Visibility.asViewValue() = when(this) {
    Visibility.VISIBLE -> View.VISIBLE
    Visibility.INVISIBLE -> View.INVISIBLE
    Visibility.GONE -> View.GONE
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