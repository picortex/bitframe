package bitframe

interface DataSource<I, E> {
    fun create(params: I): E
    fun update(entity: E): E
    fun delete(entry: E): E
    fun wipe(entity: E): E
    fun load(uid: String): E
    fun all(): List<E>
}