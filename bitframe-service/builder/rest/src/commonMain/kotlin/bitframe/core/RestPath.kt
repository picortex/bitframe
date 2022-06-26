package bitframe.core

@Deprecated("in favour of bitframe.RestPath")
open class RestPath(root: String) {
    val create = "$root/create"
    val load = "$root/load"
    val update = "$root/update"
    val delete = "$root/delete"
    val all = "$root/all"
}