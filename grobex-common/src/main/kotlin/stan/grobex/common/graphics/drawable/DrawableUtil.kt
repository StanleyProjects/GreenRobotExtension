package stan.grobex.common.graphics.drawable

import android.graphics.drawable.PaintDrawable
import stan.grobex.common.graphics.colorOf

fun paintDrawable(color: Int, radius: Float): PaintDrawable {
    val result = PaintDrawable(color)
    result.setCornerRadius(radius)
    return result
}

fun paintDrawable(colorHex: String, radius: Float): PaintDrawable {
    return paintDrawable(color = colorOf(colorHex), radius = radius)
}
