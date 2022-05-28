package com.adedom.myfood.presentation.splash_screen.view

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.adedom.myfood.base.BaseFragment
import com.adedom.myfood.databinding.FragmentSplashScreenBinding
import com.adedom.myfood.presentation.main.view.MainActivity
import com.adedom.myfood.presentation.splash_screen.state.SplashScreenUiState
import com.adedom.myfood.presentation.splash_screen.view_model.SplashScreenViewModel
import com.adedom.myfood.presentation.welcome.view.WelcomeActivity
import kotlinx.coroutines.launch
import org.kodein.di.instance

@SuppressLint("CustomSplashScreen")
class SplashScreenFragment : BaseFragment() {

    private lateinit var binding: FragmentSplashScreenBinding

    private val viewModel by instance<SplashScreenViewModel>()

    companion object {
        fun newInstant(): SplashScreenFragment {
            return SplashScreenFragment()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSplashScreenBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.uiState.collect { uiState ->
                    when (uiState) {
                        SplashScreenUiState.Initial -> {
                        }
                        is SplashScreenUiState.Authentication -> {
                            if (uiState.isLogin) {
                                val intent = Intent(context, MainActivity::class.java)
                                startActivity(intent)
                                activity?.finishAffinity()
                            } else {
                                val intent = Intent(context, WelcomeActivity::class.java)
                                startActivity(intent)
                                activity?.finishAffinity()
                            }
                        }
                    }
                }
            }
        }

        viewModel.getIsLogin()
    }
}