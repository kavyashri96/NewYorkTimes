package com.example.newyorktimes.data

import android.content.Context

class DataPreference(val context: Context) {

    private var appSharedPreference = context.getSharedPreferences("KEY", Context.MODE_PRIVATE)

    // an example usage
    var email: String?
        get() = appSharedPreference.getString("EMAIL", "")
        set(value) {
            appSharedPreference.edit().putString("EMAIL", value).apply()
        }
}