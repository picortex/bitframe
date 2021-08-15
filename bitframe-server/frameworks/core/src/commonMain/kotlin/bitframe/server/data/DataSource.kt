package bitframe.server.data

interface DataSource {
    fun create(params: Map<String, *>): Map<String, *>
    fun update(entity: Map<String, *>): Map<String, *>
    fun delete(uid: String): Map<String, *>
    fun wipe(uid: String): Map<String, *>
    fun load(uid: String): Map<String, *>
    fun all(conditions: Map<String, *>): List<Map<String, *>>
}