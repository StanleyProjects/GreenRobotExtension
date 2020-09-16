package stan.grobex.common.content

import android.content.Context
import android.util.TypedValue
import stan.grobex.common.util.applyDimension

fun Context.applyDimension(unit: Int, value: Float): Float {
    return applyDimension(unit = unit, value = value, resources = resources)
}

fun Context.px(dp: Float): Float {
    return applyDimension(unit = TypedValue.COMPLEX_UNIT_DIP, value = dp)
}
