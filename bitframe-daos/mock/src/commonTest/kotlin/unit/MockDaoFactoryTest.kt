package unit

import bitframe.core.daos.DaoFactory
import bitframe.core.daos.MockDaoFactory
import bitframe.core.daos.get
import expect.expect
import kotlinx.coroutines.test.runTest
import later.await
import kotlin.test.Test

class MockDaoFactoryTest {
    private val factory: DaoFactory = MockDaoFactory()

    @Test
    fun can_easily_initiate_a_factory() {
        val dao = factory.get<Human>()
        dao.create(Human(name = "Jane"))
    }

    @Test
    fun gets_the_correct_prefix_of_id_for_a_class() = runTest {
        val dao = factory.get<Human>()
        val pete = dao.create(Human(name = "Peter")).await()
        expect(pete.uid).toBe("human-1")
    }
}