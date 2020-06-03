package stan.grobex.view

import android.view.Gravity as AndroidGravity

enum class Gravity {
    TOP, BOTTOM, LEFT, RIGHT,
    TOP_LEFT,
    CENTER, CENTER_VERTICAL, CENTER_HORIZONTAL,
}

fun Gravity.asViewValue(): Int {
    return when(this) {
        Gravity.TOP -> AndroidGravity.TOP
        Gravity.TOP_LEFT -> AndroidGravity.TOP or AndroidGravity.LEFT
        Gravity.BOTTOM -> AndroidGravity.BOTTOM
        Gravity.LEFT -> AndroidGravity.LEFT
        Gravity.RIGHT -> AndroidGravity.RIGHT
        Gravity.CENTER -> AndroidGravity.CENTER
        Gravity.CENTER_VERTICAL -> AndroidGravity.CENTER_VERTICAL
        Gravity.CENTER_HORIZONTAL -> AndroidGravity.CENTER_HORIZONTAL
    }
}
