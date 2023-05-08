package com.msusman.littlelemoncapstoneapp

import android.content.Context
import androidx.activity.ComponentActivity
import androidx.core.content.edit

/**
 * Created by Muhammad Usman : msusman97@gmail.com on 5/8/2023.
 */
class AppPrefs(context: Context) {


    val sharePreferences =
        context.getSharedPreferences("little_lemon", ComponentActivity.MODE_PRIVATE)

    var userSession: UserSession?
        get() {
            val firstname = sharePreferences.getString("first_name", null)
            val lastName = sharePreferences.getString("last_name", null)
            val email = sharePreferences.getString("email", null)
            if (listOf(firstname, lastName, email).any { it.isNullOrEmpty() }) {
                return null
            }
            return UserSession(firstname, lastName, email)
        }
        set(value) {
            sharePreferences.edit { putString("first_name", value?.firstName) }
            sharePreferences.edit { putString("last_name", value?.lastName) }
            sharePreferences.edit { putString("email", value?.email) }
        }

    fun logout() {
        sharePreferences.edit().clear().apply()
    }
}

class UserSession(
    var firstName: String? = null,
    var lastName: String? = null,
    var email: String? = null
) {

    fun isNotInitialized(): Boolean {
        return listOf(firstName, lastName, email).any { it.isNullOrEmpty() }
    }


}