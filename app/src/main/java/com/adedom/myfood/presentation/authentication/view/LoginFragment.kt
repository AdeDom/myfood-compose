package com.adedom.myfood.presentation.authentication.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.adedom.myfood.base.BaseFragment
import com.adedom.myfood.databinding.FragmentLoginBinding
import com.adedom.myfood.presentation.authentication.state.LoginUiState
import com.adedom.myfood.presentation.authentication.view_model.LoginViewModel
import kotlinx.coroutines.launch
import org.kodein.di.instance

class LoginFragment : BaseFragment() {

    private lateinit var binding: FragmentLoginBinding

    private val viewModel by instance<LoginViewModel>()

    companion object {
        fun newInstant(): LoginFragment {
            return LoginFragment()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentLoginBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun setupViewModel() {
        lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.uiState.collect { uiState ->
                    when (uiState) {
                        LoginUiState.Initial -> {
                        }
                        LoginUiState.Register -> {
                            val authenticationActivity = activity as? AuthenticationActivity
                            authenticationActivity?.openRegisterPage()
                        }
                    }
                }
            }
        }
    }

    override fun setupAction() {
        binding.tvSignUp.setOnClickListener {
            viewModel.registerAction()
        }
    }
}