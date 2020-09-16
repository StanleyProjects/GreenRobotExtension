package stan.grobex.view

interface Padding {
    val left: Int
    val top: Int
    val right: Int
    val bottom: Int
}

private data class PaddingImpl(
    override val left: Int,
    override val top: Int,
    override val right: Int,
    override val bottom: Int
) : Padding

fun padding(
    left: Int = 0,
    top: Int = 0,
    right: Int = 0,
    bottom: Int = 0
): Padding {
    return PaddingImpl(
        left = left,
        top = top,
        right = right,
        bottom = bottom
    )
}

val noPadding = padding()
