package stan.grobex.view

import android.widget.LinearLayout

enum class Orientation {
    HORIZONTAL, VERTICAL
}

fun Orientation.asViewValue(): Int {
    return when (this) {
        Orientation.HORIZONTAL -> LinearLayout.HORIZONTAL
        Orientation.VERTICAL -> LinearLayout.VERTICAL
    }
}
