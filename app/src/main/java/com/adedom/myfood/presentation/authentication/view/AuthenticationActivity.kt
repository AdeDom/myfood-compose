package com.adedom.myfood.presentation.authentication.view

import android.os.Bundle
import com.adedom.myfood.base.BaseActivity
import com.adedom.myfood.databinding.ActivityEmptyBinding

class AuthenticationActivity : BaseActivity() {

    private val binding by lazy {
        ActivityEmptyBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
    }
}