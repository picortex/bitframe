package pimonitor.pages

import pimonitor.authentication.Credentials

interface LoginPage : Page {
    suspend fun loginWith(credentials: Credentials)
    suspend fun expectUserToBeLoggedIn()
    suspend fun expectUserToNotBeLoggedIn()
}