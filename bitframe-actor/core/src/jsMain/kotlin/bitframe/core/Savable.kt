package bitframe.core

actual external interface Savable {
    actual val uid: String
    actual val deleted: Boolean

    @JsName("_ignore_copySavable")
    actual fun copySavable(uid: String, deleted: Boolean): Savable
}