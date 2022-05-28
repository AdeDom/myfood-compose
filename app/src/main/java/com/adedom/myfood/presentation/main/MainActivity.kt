package com.adedom.myfood.presentation.main

import android.os.Bundle
import com.adedom.myfood.base.BaseActivity
import com.adedom.myfood.databinding.ActivityMainBinding
import org.kodein.di.instance

class MainActivity : BaseActivity() {

    private val binding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    private val viewModel: MainViewModel by instance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        viewModel.data.observe(this) { data ->
            binding.tvData.text = data
        }

        viewModel.getData()
    }
}