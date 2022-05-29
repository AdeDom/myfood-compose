package com.adedom.myfood.presentation.authentication.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.adedom.myfood.base.BaseFragment
import com.adedom.myfood.databinding.FragmentRegisterBinding

class RegisterFragment : BaseFragment() {

    private lateinit var binding: FragmentRegisterBinding

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
}