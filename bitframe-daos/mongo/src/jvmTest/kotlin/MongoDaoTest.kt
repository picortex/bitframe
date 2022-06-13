import bitframe.core.contains
import bitframe.core.find
import bitframe.core.isEqualTo
import bitframe.server.MongoDao
import bitframe.server.MongoDaoConfig
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

    @Test
    fun can_execute_a_query() = runTest {
        repeat(10) { dao.create(Human("h$it")).await() }
        val query = find(Human::name isEqualTo "h4").limit(5)
        val human = dao.execute(query).await().first()
        expect(human.name).toBe("h4")
    }

    @Test
    fun can_execute_a_query_with_a_limit() = runTest {
        repeat(10) { dao.create(Human("h$it")).await() }
        val query1 = find(Human::uid contains "human").limit(5)
        val res1 = dao.execute(query1).await()
        expect(res1).toBeOfSize(5)

        val query2 = find(Human::uid contains "human").limit(20)
        val res2 = dao.execute(query2).await()
        expect(res2).toBeOfSize(10)
    }
}