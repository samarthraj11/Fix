package com.credit.domain.data.session

import android.content.Context
import com.credit.domain.session.SessionManager
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SessionManagerImpl @Inject constructor(
    @ApplicationContext context: Context
) : SessionManager {
    private val prefs = context.getSharedPreferences(PREFS, Context.MODE_PRIVATE)

    override fun isLoggedIn(): Boolean = prefs.getBoolean(KEY_LOGGED_IN, false)

    override fun login(phoneNumber: String) {
        prefs.edit()
            .putBoolean(KEY_LOGGED_IN, true)
            .putString(KEY_PHONE, phoneNumber)
            .apply()
    }

    override fun logout() {
        prefs.edit().clear().apply()
    }

    override fun getPhoneNumber(): String? = prefs.getString(KEY_PHONE, null)

    private companion object {
        const val PREFS = "credit_session"
        const val KEY_LOGGED_IN = "logged_in"
        const val KEY_PHONE = "phone"
    }
}
