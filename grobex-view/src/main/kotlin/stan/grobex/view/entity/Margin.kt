package stan.grobex.view.entity

interface Margin {
    val left: Int
    val top: Int
    val right: Int
    val bottom: Int
}

private data class MarginImpl(
    override val left: Int,
    override val top: Int,
    override val right: Int,
    override val bottom: Int
) : Margin

fun margin(
    left: Int = 0,
    top: Int = 0,
    right: Int = 0,
    bottom: Int = 0
): Margin {
    return MarginImpl(
        left = left,
        top = top,
        right = right,
        bottom = bottom
    )
}

fun marginAll(
    value: Int
): Margin {
    return margin(
        left = value,
        top = value,
        right = value,
        bottom = value
    )
}

val noMargin = margin()
