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

    override fun setupViewModel() {
        lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.uiState.collect { uiState ->
                    when (uiState) {
                        LoginUiState.Initial -> {
                        }
                        LoginUiState.EmailSuccess -> {
                            binding.tvErrorEmail.isVisible = false
                        }
                        LoginUiState.EmailFailed -> {
                            binding.tvErrorEmail.isVisible = true
                        }
                        LoginUiState.PasswordSuccess -> {
                            binding.tvErrorPassword.isVisible = false
                        }
                        LoginUiState.PasswordFailed -> {
                            binding.tvErrorPassword.isVisible = true
                        }
                        LoginUiState.ShowLoading -> {
                            binding.progressBar.isVisible = true
                            binding.btnLogin.setBackgroundResource(R.drawable.shape_overlay_button_grey)
                            binding.btnLogin.isClickable = false
                        }
                        LoginUiState.HideLoading -> {
                            binding.progressBar.isVisible = false
                            binding.btnLogin.setBackgroundResource(R.drawable.shape_overlay_button_yellow)
                            binding.btnLogin.isClickable = true
                        }
                        LoginUiState.Register -> {
                            val authenticationActivity = activity as? AuthenticationActivity
                            authenticationActivity?.openRegisterPage()
                        }
                        LoginUiState.LoginSuccess -> {
                            val intent = Intent(context, MainActivity::class.java)
                            startActivity(intent)
                            activity?.finishAffinity()
                        }
                        is LoginUiState.LoginError -> {
                            val errorMessage = uiState.error.message ?: uiState.error.code.orEmpty()
                            Toast.makeText(context, errorMessage, Toast.LENGTH_SHORT).show()
                        }
                    }
                }
            }
        }
    }

    override fun setupAction() {
        binding.edtEmail.addTextChangedListener { email ->
            viewModel.emailAction(email.toString())
        }

        binding.edtPassword.addTextChangedListener { password ->
            viewModel.passwordAction(password.toString())
        }

        binding.btnLogin.setOnClickListener {
            viewModel.loginAction()
        }

        binding.tvSignUp.setOnClickListener {
            viewModel.registerAction()
        }
    }
}