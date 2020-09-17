package stan.grobex.common.view

import android.view.View
import stan.grobex.common.content.px

fun View.px(dp: Float): Int {
    return context.px(dp = dp).toInt()
}
