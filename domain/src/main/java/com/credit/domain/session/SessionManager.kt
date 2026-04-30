package com.credit.domain.session

interface SessionManager {
    fun isLoggedIn(): Boolean
    fun login(phoneNumber: String)
    fun logout()
    fun getPhoneNumber(): String?
}
