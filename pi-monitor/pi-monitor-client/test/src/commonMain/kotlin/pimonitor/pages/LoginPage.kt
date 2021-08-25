package pimonitor.pages

import bitframe.authentication.LoginCredentials

interface LoginPage : Page {
    suspend fun loginWith(credentials: LoginCredentials)
    suspend fun expectUserToBeLoggedIn()
    suspend fun expectUserToNotBeLoggedIn()
}