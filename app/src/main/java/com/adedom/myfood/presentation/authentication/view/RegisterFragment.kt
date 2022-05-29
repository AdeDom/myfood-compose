package com.adedom.myfood.presentation.authentication.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.adedom.myfood.base.BaseFragment
import com.adedom.myfood.databinding.FragmentRegisterBinding
import com.adedom.myfood.presentation.authentication.state.RegisterUiState
import com.adedom.myfood.presentation.authentication.view_model.RegisterViewModel
import kotlinx.coroutines.launch
import org.kodein.di.instance

class RegisterFragment : BaseFragment() {

    private lateinit var binding: FragmentRegisterBinding

    private val viewModel by instance<RegisterViewModel>()

    companion object {
        fun newInstant(): RegisterFragment {
            return RegisterFragment()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentRegisterBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun setupViewModel() {
        lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.uiState.collect { uiState ->
                    when (uiState) {
                        RegisterUiState.Initial -> {
                        }
                        RegisterUiState.Login -> {
                            val authenticationActivity = activity as? AuthenticationActivity
                            authenticationActivity?.openLoginPage()
                        }
                    }
                }
            }
        }
    }

    override fun setupAction() {
        binding.tvLogin.setOnClickListener {
            viewModel.loginAction()
        }
    }
}