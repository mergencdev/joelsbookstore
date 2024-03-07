package com.mergenc.androidcore.core.util

import android.content.Context
import android.content.res.Configuration
import java.util.Locale

object LanguageManager {

    private const val PREF_KEY_LANGUAGE = "pref_key_language"
    private const val APP_LANGUAGE = "application_language"

    fun setAppLanguage(context: Context, languageCode: String) {
        val locale = Locale(languageCode)
        Locale.setDefault(locale)

        val config = Configuration()
        config.setLocale(locale)

        val resources = context.resources
        resources.updateConfiguration(config, resources.displayMetrics)

        saveLanguagePreference(context, languageCode)
    }

    fun getAppLanguage(context: Context): String {
        val preferences = context.getSharedPreferences(APP_LANGUAGE, Context.MODE_PRIVATE)
        return preferences.getString(PREF_KEY_LANGUAGE, getPhoneLanguage()) ?: getPhoneLanguage()
    }

    private fun saveLanguagePreference(context: Context, languageCode: String) {
        val preferences = context.getSharedPreferences(APP_LANGUAGE, Context.MODE_PRIVATE)
        preferences.edit().putString(PREF_KEY_LANGUAGE, languageCode).apply()
    }

    private fun getPhoneLanguage(): String {
        return Locale.getDefault().language
    }
}