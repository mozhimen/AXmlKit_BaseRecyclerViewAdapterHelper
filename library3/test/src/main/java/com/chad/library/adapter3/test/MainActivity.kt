package com.chad.library.adapter3.test

import android.view.View
import com.chad.library.adapter3.test.anim.AnimActivity
import com.chad.library.adapter3.test.bundle.BundleActivity
import com.chad.library.adapter3.test.databinding.ActivityMainBinding
import com.mozhimen.uik.databinding.bases.activity.databinding.BaseActivityVDB
import com.mozhimen.kotlin.utilk.android.content.startContext

class MainActivity : BaseActivityVDB<ActivityMainBinding>() {

    fun goAnim(view: View) {
        startContext<AnimActivity>()
    }

    fun goBundle(view: View) {
        startContext<BundleActivity>()
    }
}