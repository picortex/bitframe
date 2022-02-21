import bitframe.core.User
import bitframe.core.UserContact
import bitframe.server.MongoDaoConfig
import expect.expect
import kotlin.test.Test

class CollectionNameGeneratorTest {
    @Test
    fun should_generate_a_better_name_for_users_collection() {
        val collectionName = MongoDaoConfig.defaultCollectionNameOf(User::class)
        expect(collectionName).toBe("core.Users")
    }

    @Test
    fun should_generate_a_better_name_for_user_contacts_collection() {
        val collectionName = MongoDaoConfig.defaultCollectionNameOf(UserContact::class)
        expect(collectionName).toBe("core.UserContacts")
    }
}