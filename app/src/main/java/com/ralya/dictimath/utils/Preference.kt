package com.ralya.dictimath.utils

import android.content.Context
import android.content.SharedPreferences

class Preference (val context: Context) {
    companion object {
        const val USER_PREFF = "USER PREFF"
    }

    var sharedPreference = context.getSharedPreferences(USER_PREFF, 0)

    fun setValues (key : String, value: String) {
        val editor: SharedPreferences.Editor = sharedPreference.edit()
        editor.putString(key, value)
        editor.apply()
    }

    fun getValues (key: String) : String? {
        return sharedPreference.getString(key, null)
    }

}