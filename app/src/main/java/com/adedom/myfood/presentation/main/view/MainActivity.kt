package com.adedom.myfood.presentation.main.view

import android.os.Bundle
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.adedom.myfood.base.BaseActivity
import com.adedom.myfood.databinding.ActivityMainBinding
import com.adedom.myfood.presentation.main.state.MainUiState
import com.adedom.myfood.presentation.main.view_model.MainViewModel
import kotlinx.coroutines.launch
import org.kodein.di.instance

class MainActivity : BaseActivity() {

    private val binding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    private val viewModel: MainViewModel by instance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.uiState.collect { uiState ->
                    when (uiState) {
                        is MainUiState.Initial -> {
                        }
                    }
                }
            }
        }

        viewModel.callApiService()
    }
}