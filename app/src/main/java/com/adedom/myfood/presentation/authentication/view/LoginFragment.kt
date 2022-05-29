package com.adedom.myfood.presentation.authentication.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.adedom.myfood.base.BaseFragment
import com.adedom.myfood.databinding.FragmentLoginBinding

class LoginFragment : BaseFragment() {

    private lateinit var binding: FragmentLoginBinding

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
}