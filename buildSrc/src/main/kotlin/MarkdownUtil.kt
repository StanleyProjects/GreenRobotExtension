object Markdown {
    fun image(
        text: String,
        url: String
    ): String {
        return "![$text]($url)"
    }

    fun table(heads: List<String>, dividers: List<String>, rows: List<List<String>>): String {
        require(heads.size > 1) { "Size of heads must be more than 1!"}
        require(heads.size == dividers.size) {
            "Size of heads and size of dividers must be equal!"
        }
        require(rows.isNotEmpty()) { "Rows must be exist!"}
        require(rows.map { it.size }.distinct().size == 1) {
            "Size of columns in all rows must be equal!"
        }
        require(heads.size == rows.firstOrNull()!!.size) {
            "Size of heads and size of rows must be equal!"
        }
        val result = mutableListOf<String>()
        result.add(heads.joinToString(separator = "|"))
        result.add(dividers.joinToString(separator = "|"))
        result.addAll(rows.map { it.joinToString(separator = "|") })
        return result.joinToString(separator = SystemUtil.newLine)
    }
}
