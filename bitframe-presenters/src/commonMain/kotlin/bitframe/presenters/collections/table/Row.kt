package bitframe.presenters.collections.table

data class Row<out D>(
    val number: Int,
    val data: D
)