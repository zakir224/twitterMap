package com.test.twittermap.data

import com.test.twittermap.data.preference.PreferenceHelper


class AppDataManager private constructor(private val mPreferencesHelper: PreferenceHelper) : DataManager {

    override fun getPreferedRadius(): Int {
        return mPreferencesHelper.getPreferedRadius()
    }

    override fun setPreferedRadius(radius: Int) {
        mPreferencesHelper.setPreferedRadius(radius)
    }

    companion object {
        private var instance: AppDataManager? = null

        fun getInstance(mPreferencesHelper: PreferenceHelper): AppDataManager {
            if (instance == null) {
                instance = AppDataManager(mPreferencesHelper)
            }
            return instance as AppDataManager
        }
    }
}
