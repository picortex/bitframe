package unit

import bitframe.core.actors.users.User
import bitframe.core.actors.users.UserContact
import bitframe.core.daos.MongoDaoConfig
import expect.expect
import kotlin.test.Test

class CollectionNameGeneratorTest {
    @Test
    fun should_generate_a_better_name_for_users_collection() {
        val collectionName = MongoDaoConfig.defaultCollectionNameOf(User::class)
        expect(collectionName).toBe("users.Users")
    }

    @Test
    fun should_generate_a_better_name_for_user_contacts_collection() {
        val collectionName = MongoDaoConfig.defaultCollectionNameOf(UserContact::class)
        expect(collectionName).toBe("users.UserContacts")
    }
}