package bitframe.core

expect interface Savable {
    val uid: String
    val deleted: Boolean

    fun copySavable(uid: String, deleted: Boolean): Savable
}