package com.adedom.myfood.presentation.authentication.view

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.core.widget.addTextChangedListener
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.adedom.myfood.R
import com.adedom.myfood.base.BaseFragment
import com.adedom.myfood.databinding.FragmentLoginBinding
import com.adedom.myfood.presentation.authentication.action.LoginUiAction
import com.adedom.myfood.presentation.authentication.state.LoginUiState
import com.adedom.myfood.presentation.authentication.view_model.LoginViewModel
import com.adedom.myfood.presentation.main.view.MainActivity
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

    override fun setupUiState() {
        lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.uiState.collect { uiState ->
                    when (uiState) {
                        LoginUiState.Initial -> {
                            binding.btnLogin.setBackgroundResource(R.drawable.shape_overlay_button_grey)
                            binding.btnLogin.isClickable = false
                        }
                        is LoginUiState.Email -> {
                            binding.tvErrorEmail.isVisible = uiState.isError
                            binding.btnLogin.isClickable = uiState.isLogin
                            if (uiState.isLogin) {
                                binding.btnLogin.setBackgroundResource(R.drawable.shape_overlay_button_yellow)
                            } else {
                                binding.btnLogin.setBackgroundResource(R.drawable.shape_overlay_button_grey)
                            }
                        }
                        is LoginUiState.Password -> {
                            binding.tvErrorPassword.isVisible = uiState.isError
                            binding.btnLogin.isClickable = uiState.isLogin
                            if (uiState.isLogin) {
                                binding.btnLogin.setBackgroundResource(R.drawable.shape_overlay_button_yellow)
                            } else {
                                binding.btnLogin.setBackgroundResource(R.drawable.shape_overlay_button_grey)
                            }
                        }
                        is LoginUiState.LoginError -> {
                            val errorMessage = uiState.error.message ?: uiState.error.code.orEmpty()
                            Toast.makeText(context, errorMessage, Toast.LENGTH_SHORT).show()
                        }
                        is LoginUiState.Loading -> {
                            binding.progressBar.isVisible = uiState.isLoading
                            binding.btnLogin.isClickable = uiState.isLogin
                            if (uiState.isLogin) {
                                binding.btnLogin.setBackgroundResource(R.drawable.shape_overlay_button_yellow)
                            } else {
                                binding.btnLogin.setBackgroundResource(R.drawable.shape_overlay_button_grey)
                            }
                        }
                    }
                }
            }
        }

        lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.uiAction.collect { uiAction ->
                    when (uiAction) {
                        LoginUiAction.Register -> {
                            val authenticationActivity = activity as? AuthenticationActivity
                            authenticationActivity?.openRegisterPage()
                        }
                        LoginUiAction.LoginSuccess -> {
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
        binding.edtEmail.addTextChangedListener { email ->
            viewModel.setEmail(email.toString())
            viewModel.onEmailEventToState()
        }

        binding.edtPassword.addTextChangedListener { password ->
            viewModel.setPassword(password.toString())
            viewModel.onPasswordEventToState()
        }

        binding.btnLogin.setOnClickListener {
            viewModel.onLoginEvent()
        }

        binding.tvSignUp.setOnClickListener {
            viewModel.onRegisterEvent()
        }
    }
}