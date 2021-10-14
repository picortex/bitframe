package bitframe.authentication.users

import kotlinx.serialization.Serializable
import kotlin.jvm.JvmStatic
import kotlin.jvm.JvmSynthetic
import contacts.Email as ValidEmail
import contacts.Phone as ValidPhone

@Serializable(with = ContactsSerializer::class)
sealed class Contacts {

    companion object {
        @JvmSynthetic
        operator fun invoke(vararg values: String) = of(*values)

        private fun instantiate(contact: String): Contacts = try {
            Email(contact)
        } catch (err: Throwable) {
            null
        } ?: try {
            Phone(contact)
        } catch (err: Throwable) {
            null
        } ?: throw IllegalArgumentException("$contact is not a valid email or phone")

        @JvmStatic
        fun of(vararg values: String): Contacts {
            var contacts: Contacts = None
            for (value in values) {
                contacts += instantiate(value)
            }
            return contacts
        }
    }

    @Serializable
    object None : Contacts()

    @Serializable
    data class Email(val email: ValidEmail) : Contacts() {
        constructor(email: String) : this(ValidEmail(email))
    }

    @Serializable
    data class Emails(val emails: Set<ValidEmail>) : Contacts() {
        constructor(vararg emails: String) : this(emails.map { ValidEmail(it) }.toSet())
    }

    @Serializable
    data class Phone(val phone: ValidPhone) : Contacts() {
        constructor(phone: String) : this(ValidPhone(phone))
    }

    @Serializable
    data class Phones(val phones: Set<ValidPhone>) : Contacts() {
        constructor(vararg phones: String) : this(phones.map { ValidPhone(it) }.toSet())
    }

    @Serializable
    data class EmailPhone(val email: ValidEmail, val phone: ValidPhone) : Contacts() {
        constructor(email: String, phone: String) : this(ValidEmail(email), ValidPhone(phone))
    }

    @Serializable
    data class Mixed(val emails: Set<ValidEmail>, val phones: Set<ValidPhone>) : Contacts()

    operator fun plus(other: Contacts): Contacts = when (this) {
        None -> other
        is Email -> when (other) {
            None -> this
            is Email -> Emails(email.toString(), other.email.toString())
            is Emails -> Emails(emails = setOf(email) + other.emails)
            is Phone -> EmailPhone(email, other.phone)
            is Phones -> Mixed(emails = setOf(email), phones = other.phones)
            is EmailPhone -> Mixed(emails = setOf(email) + other.email, phones = setOf(other.phone))
            is Mixed -> Mixed(emails = setOf(email) + other.emails, phones = other.phones)
        }
        is Emails -> when (other) {
            None -> this
            is Email -> Emails(emails + other.email)
            is Emails -> Emails(emails + other.emails)
            is Phone -> Mixed(emails, phones = setOf(other.phone))
            is Phones -> Mixed(emails, phones = other.phones)
            is EmailPhone -> Mixed(emails + other.email, phones = setOf(other.phone))
            is Mixed -> Mixed(emails + other.emails, phones = other.phones)
        }
        is Phone -> when (other) {
            None -> this
            is Email -> EmailPhone(other.email.toString(), phone.toString())
            is Emails -> Mixed(emails = other.emails, phones = setOf(phone))
            is Phone -> Phones(phone.toString(), other.phone.toString())
            is Phones -> Phones(phones = setOf(phone) + other.phones)
            is EmailPhone -> Mixed(emails = setOf(other.email), phones = setOf(phone, other.phone))
            is Mixed -> Mixed(emails = other.emails, phones = setOf(phone) + other.phones)
        }
        is Phones -> when (other) {
            None -> this
            is Email -> Mixed(setOf(other.email), phones)
            is Emails -> Mixed(other.emails, phones)
            is Phone -> Phones(phones + other.phone)
            is Phones -> Phones(phones + other.phones)
            is EmailPhone -> Mixed(emails = setOf(other.email), phones = phones + other.phone)
            is Mixed -> Mixed(emails = other.emails, phones = phones + other.phones)
        }
        is EmailPhone -> when (other) {
            None -> this
            is Email -> Mixed(setOf(email) + other.email, setOf(phone))
            is Emails -> Mixed(setOf(email) + other.emails, setOf(phone))
            is Phone -> Mixed(setOf(email), setOf(phone) + other.phone)
            is Phones -> Mixed(setOf(email), setOf(phone) + other.phones)
            is EmailPhone -> Mixed(setOf(other.email) + other.email, setOf(phone) + other.phone)
            is Mixed -> Mixed(setOf(email) + other.emails, setOf(phone) + other.phones)
        }
        is Mixed -> when (other) {
            None -> this
            is Email -> Mixed(emails + other.email, phones)
            is Emails -> Mixed(emails + other.emails, phones)
            is Phone -> Mixed(emails, phones + other.phone)
            is Phones -> Mixed(emails, phones + other.phones)
            is EmailPhone -> Mixed(emails + other.email, phones + other.phone)
            is Mixed -> Mixed(emails + other.emails, phones + other.phones)
        }
    }

    fun all() = when (this) {
        None -> emptySet()
        is Email -> setOf(email.value)
        is Emails -> emails.map { it.value }.toSet()
        is Phone -> setOf(phone.value)
        is Phones -> phones.map { it.value }.toSet()
        is EmailPhone -> setOf(email.value, phone.value)
        is Mixed -> (emails.map { it.value } + phones.map { it.value }).toSet()
    }

    @Deprecated("In favour of all().first())", replaceWith = ReplaceWith("all().first()"))
    fun firstValue() = firstValueOrNull() ?: throw RuntimeException("There are no Contacts inside the contacts container")

    @Deprecated("In favour of all().firstOrNull()", replaceWith = ReplaceWith("all().firstOrNull()"))
    fun firstValueOrNull() = all().firstOrNull()

    fun contains(value: String) = all().contains(value.lowercase())
}