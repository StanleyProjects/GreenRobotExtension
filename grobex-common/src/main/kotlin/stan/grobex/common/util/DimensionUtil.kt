package stan.grobex.common.util

import android.content.res.Resources
import android.util.TypedValue

fun applyDimension(unit: Int, value: Float, resources: Resources): Float {
    return TypedValue.applyDimension(unit, value, resources.displayMetrics)
}
