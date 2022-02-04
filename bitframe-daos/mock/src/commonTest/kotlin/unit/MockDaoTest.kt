package unit

import bitframe.daos.MockDao
import expect.expect
import expect.expectFailure
import kotlinx.coroutines.test.runTest
import later.await
import kotlin.test.Test

class MockDaoTest {
    @Test
    fun creates_a_dao_easily() {
        val dao = MockDao<Human>()
    }

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
}