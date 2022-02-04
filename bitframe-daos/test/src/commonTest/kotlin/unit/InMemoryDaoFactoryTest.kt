package unit

import bitframe.daos.DaoFactory
import bitframe.daos.InMemoryDaoFactory
import bitframe.daos.get
import expect.expect
import kotlinx.coroutines.test.runTest
import later.await
import kotlin.test.Test

class InMemoryDaoFactoryTest {
    private val factory: DaoFactory = InMemoryDaoFactory()

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