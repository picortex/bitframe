import bitframe.DaoFile
import bitframe.DaoFileConfig
import bitframe.dao.contains
import bitframe.dao.exceptions.EntityNotFoundException
import bitframe.dao.find
import bitframe.dao.isEqualTo
import expect.*
import koncurrent.Later
import koncurrent.LaterTestResult
import koncurrent.SynchronousExecutor
import koncurrent.later.*
import okio.Path.Companion.toPath
import okio.fakefilesystem.FakeFileSystem
import kotlin.test.Test

class DaoFileTest {

    private val fs = FakeFileSystem()
    private val dir = "/tmp/dao/file/person".toPath()

    private val dao = DaoFile(
        config = DaoFileConfig<Person>(fs, dir, SynchronousExecutor)
    )

    @Test
    fun can_add_items_to_dao() = dao.create(Person(name = "peter")).then { pete ->
        expect(pete.name).toBe("peter")
        expect(pete.uid).toBe("1")
    }.test()

    @Test
    fun can_load_an_already_saved_data() = dao.create(Person(name = "peter")).flatten { pete ->
        val uid = pete.uid
        dao.load(uid)
    }.then { peter ->
        expect(peter.name).toBe("peter")
    }.test()

    @Test
    fun loading_an_id_that_is_not_available_throws() = dao.load("seven").catch { err ->
        expect(err).toBe<EntityNotFoundException>()
    }.test()

    @Test
    fun can_execute_a_query(): LaterTestResult {
        repeat(10) { dao.create(Person("h$it")) }
        val query = find(Person::name isEqualTo "h4").limit(5)
        return dao.execute(query).then { people ->
            expect(people.first().name).toBe("h4")
        }.test()
    }

    @Test
    fun can_execute_a_query_with_a_limit(): LaterTestResult {
        val creators = buildList {
            repeat(10) { add(dao.create(Person("Person $it"))) }
        }
        return Later.all(*creators.toTypedArray()).flatten {
            val query = find(Person::name contains "Person").limit(5)
            dao.execute(query)
        }.then { people ->
            expect(collection = people).toBeOfSize(5)
        }.flatten {
            val query = find(Person::name contains "Person").limit(20)
            dao.execute(query)
        }.then { people ->
            expect(collection = people).toBeOfSize(10)
        }.test()
    }
}