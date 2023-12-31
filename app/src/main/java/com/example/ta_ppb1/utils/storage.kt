package com.example.ta_ppb1.utils

import android.content.Context
import com.example.ta_ppb1.constant.General

class storage<T> {
    fun save(ctx: Context, value: T) {
        val pref = ctx.getSharedPreferences(General.STORAGE_NAME, Context.MODE_PRIVATE)

        with(pref?.edit()) {
            this?.apply {
                when (value) {
                    is Int -> putInt(General.USER_ID, value)
                    is String -> putString(General.USER_ID, value)
                    is Boolean -> putBoolean(General.USER_ID, value)
                }
                apply()
            }
        }
    }

    fun get(ctx: Context, key: String, default: T): T? {
        val pref = ctx.getSharedPreferences(General.STORAGE_NAME, Context.MODE_PRIVATE)
        return when (default) {
            is Int -> pref.getInt(key, default) as T
            is String -> pref.getString(key, default) as T
            is Boolean -> pref.getBoolean(key, default) as T
            else -> null
        }
    }
}