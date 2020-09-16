package stan.grobex.view

import android.graphics.Typeface

enum class TypefaceStyle {
    NORMAL,
    BOLD,
    ITALIC,
    BOLD_ITALIC,
}

fun TypefaceStyle.asViewValue(): Int {
    return when (this) {
        TypefaceStyle.NORMAL -> Typeface.NORMAL
        TypefaceStyle.BOLD -> Typeface.BOLD
        TypefaceStyle.ITALIC -> Typeface.ITALIC
        TypefaceStyle.BOLD_ITALIC -> Typeface.BOLD_ITALIC
    }
}
