package com.battleent.materialpreferencesdemo

import android.os.Bundle
import com.battleent.materialpreferences.AppCompatPreferenceActivity

class MainActivity : AppCompatPreferenceActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        transactionFragment()
    }

    private fun transactionFragment() {
        fragmentManager.beginTransaction().replace(android.R.id.content, SettingMainFragment()).commit()
    }
}
