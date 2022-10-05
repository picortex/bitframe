import bitframe.DaoFactory
import bitframe.DaoFactoryFile
import bitframe.DaoFactoryFileConfig
import bitframe.dao.get
import expect.expect
import koncurrent.SynchronousExecutor
import koncurrent.later.then
import okio.Path.Companion.toPath
import okio.fakefilesystem.FakeFileSystem
import kotlin.test.Test

class DaoFactoryFileTest {
    private val factory: DaoFactory = DaoFactoryFile(
        config = DaoFactoryFileConfig(
            fs = FakeFileSystem(),
            db = "".toPath(),
            executor = SynchronousExecutor
        )
    )

    @Test
    fun can_easily_initiate_a_factory() {
        val dao = factory.get<Person>()
        dao.create(Person(name = "Jane"))
    }

    @Test
    fun gets_the_correct_prefix_of_id_for_a_class() {
        val dao = factory.get<Person>()
        dao.create(Person(name = "Peter")).then { pete ->
            expect(pete.uid).toBe("Person-1")
        }
    }
}