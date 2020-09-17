package stan.grobex.view.entity

import android.util.TypedValue

enum class TypeDimension {
    PIXEL,
    DEVICE_INDEPENDENT,
    SCALED_PIXEL,
    POINT,
    INCH,
    MILLIMETER
}

fun TypeDimension.asViewValue(): Int {
    return when (this) {
        TypeDimension.PIXEL -> TypedValue.COMPLEX_UNIT_PX
        TypeDimension.DEVICE_INDEPENDENT -> TypedValue.COMPLEX_UNIT_DIP
        TypeDimension.SCALED_PIXEL -> TypedValue.COMPLEX_UNIT_SP
        TypeDimension.POINT -> TypedValue.COMPLEX_UNIT_PT
        TypeDimension.INCH -> TypedValue.COMPLEX_UNIT_IN
        TypeDimension.MILLIMETER -> TypedValue.COMPLEX_UNIT_MM
    }
}
