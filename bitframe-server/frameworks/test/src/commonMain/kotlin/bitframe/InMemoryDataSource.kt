package bitframe

import bitframe.server.DataSource

class InMemoryDataSource(
    private val data: MutableMap<String, Map<String, *>> = mutableMapOf()
) : DataSource {

    override fun create(params: Map<String, *>): Map<String, *> {
        val uid = (data.size + 1).toString()
        return params.toMutableMap().apply { put("uid", uid) }.also { data[uid] = it }
    }

    private fun findUid(entity: Map<String, *>): String {
        return entity["uid"]?.toString() ?: throw IllegalArgumentException("Missing uid field in $entity")
    }

    override fun update(entity: Map<String, *>): Map<String, *> {
        val uid = findUid(entity)
        data[uid] = entity
        return entity
    }

    private fun obj(uid: String) = data[uid]?.toMutableMap() ?: throw Exception("Could not find entity with uid=$uid")

    override fun delete(uid: String): Map<String, *> {
        val obj = obj(uid)
        obj["deleted"] = true
        return update(obj)
    }

    override fun wipe(uid: String): Map<String, *> {
        val obj = obj(uid)
        data.remove(uid)
        return obj
    }

    override fun load(uid: String): Map<String, *> {
        return obj(uid)
    }

    override fun all(conditions: Map<String, *>): List<Map<String, *>> {
        return data.values.toList()
    }
}