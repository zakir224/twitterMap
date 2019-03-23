package com.test.twittermap.data.preference

import android.content.Context
import android.content.SharedPreferences

class AppPreferenceHelper private constructor(context: Context) : PreferenceHelper {
    private val mPrefs: SharedPreferences

    init {
        mPrefs = context.getSharedPreferences(PREF_FILE, Context.MODE_PRIVATE)
    }


    override fun getPreferedRadius(): Int {
        return mPrefs.getInt(RADIUS, 5)
    }

    override fun setPreferedRadius(radius: Int) {
        mPrefs.edit().putInt(RADIUS, radius).apply()
    }

    companion object {
        private val RADIUS = "com.test.twittermap.data.preference.radius"
        private val PREF_FILE = "twitter_map"
        private var instance : AppPreferenceHelper? = null

        fun  getInstance(context: Context): AppPreferenceHelper {
            if (instance == null)  // NOT thread safe!
                instance = AppPreferenceHelper(context)

            return instance!!
        }
    }
}
