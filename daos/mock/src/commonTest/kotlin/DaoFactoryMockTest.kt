import bitframe.DaoFactory
import bitframe.DaoFactoryMock
import bitframe.dao.get
import expect.expect
import kommander.expect
import koncurrent.later.then
import koncurrent.later.catch
import kotlin.test.Test

class DaoFactoryMockTest {
    private val factory: DaoFactory = DaoFactoryMock()

    @Test
    fun can_easily_initiate_a_factory() {
        val dao = factory.get<Person>()
        dao.create(Person(name = "Jane"))
    }

    @Test
    fun gets_the_correct_prefix_of_id_for_a_class() {
        val dao = factory.get<Person>()
        dao.create(Person(name = "Peter")).then { pete ->
            expect<String>(pete.uid).toBe("Person-1")
        }
    }
}