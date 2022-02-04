package unit

import bitframe.daos.MongoDao
import bitframe.daos.MongoDaoConfig
import expect.expect
import expect.expectFailure
import kotlinx.coroutines.test.runTest
import later.await
import kotlin.test.Ignore
import kotlin.test.Test

@Ignore
class MongoDaoTest {

    val config = MongoDaoConfig<Human>(
        host = "127.0.0.1:27017",
        username = "root",
        password = "example"
    )
    val dao = MongoDao(config)

    @Test
    fun can_add_items_to_dao() = runTest {
        val pete = dao.create(Human(name = "Peter")).await()
        expect(pete.name).toBe("Peter")
        expect(pete.uid).toBeNonNull()
    }

    @Test
    fun can_load_an_already_saved_data() = runTest {
        val pete = dao.create(Human(name = "Peter Justine")).await()
        val uid = pete.uid
        val peter = dao.load(uid).await()
        expect(pete).toBe(peter)
    }

    @Test
    fun loading_an_id_that_is_not_available_throws() = runTest {
        expectFailure {
            dao.load("seven").await()
        }
    }
}