package bitframe.dao.exceptions

class EntityNotFoundException(
    val key: String,
    val value: Any
) : NoSuchElementException(
    "Entity($key=$value) is not found in dao"
) {
    constructor(uid: String) : this("uid", uid)
}