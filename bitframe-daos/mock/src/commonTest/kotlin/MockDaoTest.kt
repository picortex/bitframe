import bitframe.core.MockDao
import bitframe.core.contains
import bitframe.core.find
import bitframe.core.isEqualTo
import expect.expect
import expect.expectFailure
import kotlinx.coroutines.test.runTest
import later.await
import kotlin.test.Test

class MockDaoTest {
    @Test
    fun can_add_items_to_dao() = runTest {
        val dao = MockDao<Human>()
        val pete = dao.create(Human(name = "peter")).await()
        expect(pete.name).toBe("peter")
        expect(pete.uid).toBe("human-1")
    }

    @Test
    fun can_load_an_already_saved_data() = runTest {
        val dao = MockDao<Human>()
        val pete = dao.create(Human(name = "peter")).await()
        val uid = pete.uid
        val peter = dao.load(uid).await()
        expect(pete).toBe(peter)
    }

    @Test
    fun loading_an_id_that_is_not_available_throws() = runTest {
        expectFailure {
            val dao = MockDao<Human>()
            dao.load("seven").await()
        }
    }

    @Test
    fun can_execute_a_query() = runTest {
        val dao = MockDao<Human>()
        repeat(10) { dao.create(Human("h$it")).await() }
        val query = find(Human::name isEqualTo "h4").limit(5)
        val human = dao.execute(query).await().first()
        expect(human.name).toBe("h4")
    }

    @Test
    fun can_execute_a_query_with_a_limit() = runTest {
        val dao = MockDao<Human>()
        repeat(10) { dao.create(Human("h$it")).await() }
        val query1 = find(Human::uid contains "human").limit(5)
        val res1 = dao.execute(query1).await()
        expect(res1).toBeOfSize(5)

        val query2 = find(Human::uid contains "human").limit(20)
        val res2 = dao.execute(query2).await()
        expect(res2).toBeOfSize(10)
    }
}