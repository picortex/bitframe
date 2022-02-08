package unit

import bitframe.actors.modal.Savable
import bitframe.daos.MockDaoFactory
import bitframe.daos.CompoundDao
import bitframe.daos.get
import kotlin.test.Test

class CompoundDaoTest {
    interface Animal : Savable
    interface Cat : Animal
    interface Dog : Animal

    val daoFactory = MockDaoFactory()

    @Test
    fun can_construct_a_compound_dao() {
        val catDao = daoFactory.get<Cat>()
        val dogDao = daoFactory.get<Dog>()

        val dao = CompoundDao(catDao, dogDao)
    }
}