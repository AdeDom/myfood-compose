package com.adedom.myfood.presentation.welcome.view

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.adedom.myfood.base.BaseFragment
import com.adedom.myfood.databinding.FragmentWelcomeBinding
import com.adedom.myfood.presentation.authentication.view.AuthenticationActivity
import com.adedom.myfood.presentation.main.view.MainActivity
import com.adedom.myfood.presentation.welcome.action.WelcomeUiAction
import com.adedom.myfood.presentation.welcome.view_model.WelcomeViewModel
import com.adedom.myfood.utils.constant.AppConstant
import kotlinx.coroutines.launch
import org.kodein.di.instance

class WelcomeFragment : BaseFragment() {

    private lateinit var binding: FragmentWelcomeBinding

    private val viewModel by instance<WelcomeViewModel>()

    companion object {
        fun newInstant(): WelcomeFragment {
            return WelcomeFragment()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentWelcomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun setupUiState() {
        lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.uiAction.collect { uiAction ->
                    when (uiAction) {
                        WelcomeUiAction.Login -> {
                            val intent = Intent(context, AuthenticationActivity::class.java)
                            intent.putExtra(AppConstant.PAGE, AppConstant.LOGIN_PAGE)
                            startActivity(intent)
                        }
                        WelcomeUiAction.Register -> {
                            val intent = Intent(context, AuthenticationActivity::class.java)
                            intent.putExtra(AppConstant.PAGE, AppConstant.REGISTER_PAGE)
                            startActivity(intent)
                        }
                        is WelcomeUiAction.Skip -> {
                            val intent = Intent(context, MainActivity::class.java)
                            startActivity(intent)
                            activity?.finishAffinity()
                        }
                    }
                }
            }
        }
    }

    override fun setupUiAction() {
        binding.btnLogin.setOnClickListener {
            viewModel.onLoginEvent()
        }

        binding.btnRegister.setOnClickListener {
            viewModel.onRegisterEvent()
        }

        binding.tvSkip.setOnClickListener {
            viewModel.onSkipEvent()
        }
    }
}