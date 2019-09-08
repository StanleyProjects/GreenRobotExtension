package stan.grobex.view

import android.view.ViewGroup

fun lp(width: Int, height: Int) = ViewGroup.LayoutParams(width, height)
fun lp(size: Int) = lp(width = size, height = size)

val lpWrapped = lp(size = ViewGroup.LayoutParams.WRAP_CONTENT)