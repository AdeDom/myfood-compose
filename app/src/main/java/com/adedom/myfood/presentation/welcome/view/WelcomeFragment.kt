package com.adedom.myfood.presentation.welcome.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.adedom.myfood.base.BaseFragment
import com.adedom.myfood.databinding.FragmentWelcomeBinding

class WelcomeFragment : BaseFragment() {

    private lateinit var binding: FragmentWelcomeBinding

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
}