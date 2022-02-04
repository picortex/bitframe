package bitframe.daos

import bitframe.daos.conditions.Condition
import bitframe.daos.conditions.matching
import bitframe.modal.HasId
import com.mongodb.ConnectionString
import com.mongodb.MongoClientSettings
import com.mongodb.MongoCredential
import com.mongodb.client.model.Filters.eq
import kotlinx.collections.interoperable.List
import kotlinx.collections.interoperable.toInteroperableList
import kotlinx.coroutines.delay
import kotlinx.serialization.InternalSerializationApi
import kotlinx.serialization.serializer
import later.Later
import later.await
import later.later
import org.bson.conversions.Bson
import org.bson.types.ObjectId
import org.litote.kmongo.coroutine.coroutine
import org.litote.kmongo.coroutine.insertOne
import org.litote.kmongo.eq
import org.litote.kmongo.json
import org.litote.kmongo.out
import org.litote.kmongo.reactivestreams.KMongo
import org.litote.kmongo.reactivestreams.getCollection
import org.litote.kmongo.util.idValue
import kotlin.reflect.KClass

class MongoDao<D : HasId>(
    val config: MongoDaoConfig<D>
) : Dao<D> {

    private val client = KMongo.createClient(config.connectionString())
    private val database = client.getDatabase(config.database)
    private val collection = database.getCollection(config.collection, config.clazz.java).coroutine
    private val scope get() = config.scope

    override fun create(input: D): Later<D> = scope.later {
        val id = ObjectId.get()
        val output = input.copy("${config.prefix}-$id") as D
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

    override fun delete(uid: String): Later<D> = scope.later {
        val item = load(uid).await()
        item
    }

    @OptIn(InternalSerializationApi::class)
    override fun all(condition: Condition<String, Any>?): Later<List<D>> = scope.later {
        if (condition == null) {
            collection.find().toList().toInteroperableList()
        } else {
            collection.find().toList().toInteroperableList()
        }
    }
}