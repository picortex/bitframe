@file:OptIn(InternalSerializationApi::class)

package bitframe

import bitframe.actor.Savable
import bitframe.dao.Condition
import bitframe.dao.Query
import bitframe.dao.connectionString
import bitframe.dao.exceptions.EntityNotFoundException
import com.mongodb.client.model.Filters.eq
import kotlinx.collections.interoperable.List
import kotlinx.collections.interoperable.toInteroperableList
import kotlinx.serialization.InternalSerializationApi
import koncurrent.Later
import org.bson.types.ObjectId
import org.litote.kmongo.coroutine.coroutine
import org.litote.kmongo.eq
import org.litote.kmongo.reactivestreams.KMongo

class DaoMongo<D : Savable>(val config: DaoMongoConfig<D>) : Dao<D> {

    private val client = KMongo.createClient(config.connectionString())
    private val database = client.getDatabase(config.database)
    private val collection = database.getCollection(config.collection, config.clazz.java)

    override fun create(input: D): Later<D> = Later(config.executor) { resolve, _ ->
        val id = ObjectId.get()
        val output = input.copySavable("${config.prefix}-$id", deleted = false) as D
        collection.insertOne(output)
        resolve(output)
    }

    override fun update(obj: D): Later<D> = Later(config.executor) { resolve, _ ->
        collection.updateOne(obj::uid eq obj.uid, obj)
        resolve(obj)
    }

    override fun load(uid: String): Later<D> = Later(config.executor) { resolve, reject ->
        val found = collection.findOne(eq("uid", uid))
        if (found != null) resolve(found) else reject(EntityNotFoundException(uid))
    }

    override fun loadOrNull(uid: String): Later<D?> = Later(config.executor) { resolve, _ ->
        resolve(collection.findOne(eq("uid", uid)))
    }

    override fun execute(query: Query): Later<List<D>> = Later(config.executor) { resolve, _ ->
        resolve(collection.execute(query).toList().toInteroperableList())
    }

    override fun delete(uid: String): Later<D> = Later(config.executor) { resolve, reject ->
        val found = collection.findOne(eq("uid", uid))
        if (found == null) {
            reject(EntityNotFoundException(uid))
            return@Later
        }
        val obj = found.copySavable(uid = uid, deleted = true) as D
        collection.updateOne(obj::uid eq obj.uid, obj)
        resolve(obj)
    }

    override fun all(condition: Condition<*>?): Later<List<D>> = Later(config.executor) { resolve, _ ->
        if (condition == null) {
            resolve(collection.find().toList().toInteroperableList())
        } else {
            resolve(collection.find(condition.toMongoFilter()).toList().toInteroperableList())
        }
    }
}