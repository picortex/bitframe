import bitframe.DaoMock
import bitframe.dao.contains
import bitframe.dao.exceptions.EntityNotFoundException
import bitframe.dao.find
import bitframe.dao.isEqualTo
import expect.*
import kommander.expect
import koncurrent.Rejected
import koncurrent.later.then
import kotlin.test.Test

class DaoMockTest {
    @Test
    fun can_add_items_to_dao() {
        val dao = DaoMock<Person>()
        dao.create(Person(name = "peter")).then { pete ->
            expect(pete.name).toBe("peter")
            expect(pete.uid).toBe("Person-1")
        }
    }

    @Test
    fun can_load_an_already_saved_data() {
        val dao = DaoMock<Person>()
        dao.create(Person(name = "peter")).then { pete ->
            val uid = pete.uid
            dao.load(uid).then { peter ->
                expect(pete).toBe(peter)
            }
        }
    }

    @Test
    fun loading_an_id_that_is_not_available_throws() {
        val dao = DaoMock<Person>()
//        dao.load("seven").complete {
//            val state = expect(it).toBe<Rejected>()
//            expect(state.cause).toBe<EntityNotFoundException>()
//        }
    }

    @Test
    fun can_execute_a_query() {
        val dao = DaoMock<Person>()
        repeat(10) { dao.create(Person("h$it")) }
        val query = find(Person::name isEqualTo "h4").limit(5)
        dao.execute(query).then { people ->
            expect(people.first().name).toBe("h4")
        }
    }

    @Test
    fun can_execute_a_query_with_a_limit() {
        val dao = DaoMock<Person>()
        repeat(10) { dao.create(Person("h$it")) }
        val query1 = find(Person::uid contains "Person").limit(5)
        dao.execute(query1).then { people ->
            expectCollection(people).toBeOfSize(5)
        }

        val query2 = find(Person::uid contains "Person").limit(20)
        dao.execute(query2).then { people ->
            expectCollection(people).toBeOfSize(10)
        }
    }
}