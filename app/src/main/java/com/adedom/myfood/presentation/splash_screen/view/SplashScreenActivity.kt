package com.adedom.myfood.presentation.splash_screen.view

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.commit
import com.adedom.myfood.base.BaseActivity
import com.adedom.myfood.databinding.ActivityEmptyBinding

@SuppressLint("CustomSplashScreen")
class SplashScreenActivity : BaseActivity() {

    private val binding by lazy {
        ActivityEmptyBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        if (savedInstanceState == null) {
            supportFragmentManager.commit {
                replace(binding.frameLayout.id, SplashScreenFragment.newInstant())
            }
        }
    }
}