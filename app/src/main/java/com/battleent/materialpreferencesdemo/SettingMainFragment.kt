package com.battleent.materialpreferencesdemo

import android.os.Bundle
import android.preference.PreferenceFragment
import com.battleent.materialpreferences.MaterialPreference

/**
 * Created by skydoves on 2018. 4. 17.
 * Copyright (c) 2018 battleent All rights reserved.
 */

class SettingMainFragment : PreferenceFragment() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        addPreferencesFromResource(R.xml.setting_main)
    }
}
