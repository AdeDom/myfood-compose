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
import com.adedom.myfood.presentation.authentication.action.RegisterUiEvent
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

    override fun setupUiState() {
        lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.uiAction.collect { uiAction ->
                    when (uiAction) {
                        RegisterUiEvent.Login -> {
                            val authenticationActivity = activity as? AuthenticationActivity
                            authenticationActivity?.openLoginPage()
                        }
                    }
                }
            }
        }
    }

    override fun setupUiAction() {
        binding.tvLogin.setOnClickListener {
            viewModel.onLoginEvent()
        }
    }
}