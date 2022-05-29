package com.adedom.myfood.presentation.authentication.view

import android.os.Bundle
import androidx.fragment.app.commit
import com.adedom.myfood.base.BaseActivity
import com.adedom.myfood.databinding.ActivityEmptyBinding
import com.adedom.myfood.utils.constant.AppConstant

class AuthenticationActivity : BaseActivity() {

    private val binding by lazy {
        ActivityEmptyBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        val page = intent.getStringExtra(AppConstant.PAGE)

        if (page == AppConstant.LOGIN_PAGE) {
            openLoginPage()
        } else {
            openRegisterPage()
        }
    }

    fun openLoginPage() {
        supportFragmentManager.commit {
            replace(binding.frameLayout.id, LoginFragment.newInstant())
        }
    }

    fun openRegisterPage() {
        supportFragmentManager.commit {
            replace(binding.frameLayout.id, RegisterFragment.newInstant())
        }
    }
}