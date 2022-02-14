package bitframe.core

actual interface Savable {
    actual val uid: String
    actual val deleted: Boolean

    actual fun copySavable(uid: String, deleted: Boolean): Savable
}