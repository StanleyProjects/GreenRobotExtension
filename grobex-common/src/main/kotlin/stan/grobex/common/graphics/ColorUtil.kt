package stan.grobex.common.graphics

import android.graphics.Color

fun colorOf(hex: String): Int {
    return Color.parseColor(hex)
}