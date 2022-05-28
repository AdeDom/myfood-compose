package com.adedom.myfood.presentation.welcome.view

import android.os.Bundle
import androidx.fragment.app.commit
import com.adedom.myfood.base.BaseActivity
import com.adedom.myfood.databinding.ActivityEmptyBinding

class WelcomeActivity : BaseActivity() {

    private val binding by lazy {
        ActivityEmptyBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        if (savedInstanceState == null) {
            supportFragmentManager.commit {
                replace(binding.frameLayout.id, WelcomeFragment.newInstant())
            }
        }
    }
}