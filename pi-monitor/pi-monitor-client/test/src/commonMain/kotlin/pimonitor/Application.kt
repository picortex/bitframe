package pimonitor

interface Application {
    fun expectUserToBeLoggedIn()
    fun expectUserToNotBeLoggedIn()
}