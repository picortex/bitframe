package unit

import bitframe.core.MockDaoFactory
import bitframe.core.CompoundDao
import bitframe.core.Savable
import bitframe.core.get
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