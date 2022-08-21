@file:OptIn(InternalSerializationApi::class)

package bitframe.server

import bitframe.core.*
import com.mongodb.client.model.Filters.eq
import kotlinx.collections.interoperable.List
import kotlinx.collections.interoperable.toInteroperableList
import kotlinx.serialization.InternalSerializationApi
import koncurrent.Later
import koncurrent.later.await
import koncurrent.later
import org.bson.types.ObjectId
import org.litote.kmongo.coroutine.coroutine
import org.litote.kmongo.eq
import org.litote.kmongo.reactivestreams.KMongo

class MongoDao<D : Savable>(
    override val config: MongoDaoConfig<D>
) : Dao<D> {

    private val client = KMongo.createClient(config.connectionString())
    private val database = client.getDatabase(config.database)
    private val collection = database.getCollection(config.collection, config.clazz.java).coroutine
    private val scope get() = config.scope

    override fun create(input: D): Later<D> = scope.later {
        val id = ObjectId.get()
        val output = input.copySavable("${config.prefix}-$id", deleted = false) as D
        collection.insertOne(output)
        output
    }

    override fun update(obj: D): Later<D> = scope.later {
        collection.updateOne(obj::uid eq obj.uid, obj)
        obj
    }

    override fun load(uid: String): Later<D> = scope.later {
        collection.findOne(eq("uid", uid)) ?: error("Entity with uid = $uid not found")
    }

    override fun loadOrNull(uid: String): Later<D?> = scope.later {
        try {
            load(uid).await()
        } catch (err: Throwable) {
            null
        }
    }

    override fun execute(query: Query): Later<List<D>> = scope.later {
        collection.execute(query).toList().toInteroperableList()
    }

    override fun delete(uid: String): Later<D> = scope.later {
        val item = load(uid).await()
        update(item.copySavable(uid = uid, deleted = true) as D).await()
    }

    override fun all(condition: Condition<*>?): Later<List<D>> = scope.later {
        if (condition == null) {
            collection.find().toList().toInteroperableList()
        } else {
            collection.find(condition.toMongoFilter()).toList().toInteroperableList()
        }
    }
}