package pimonitor.pages

import bitframe.authentication.Credentials

interface LoginPage : Page {
    suspend fun loginWith(credentials: Credentials)
    suspend fun expectUserToBeLoggedIn()
    suspend fun expectUserToNotBeLoggedIn()
}