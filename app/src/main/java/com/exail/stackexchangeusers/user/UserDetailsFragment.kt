package com.exail.stackexchangeusers.user

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.exail.stackexchangeusers.R
import com.exail.stackexchangeusers.base.FragmentBase
import com.exail.stackexchangeusers.databinding.FragmentUserDetailsBinding

class UserDetailsFragment : FragmentBase() {

    private val args: UserDetailsFragmentArgs by navArgs()
    private val userDetailsViewModel: UserDetailsViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = DataBindingUtil.inflate<FragmentUserDetailsBinding>(
            inflater,
            R.layout.fragment_user_details,
            container,
            false
        )

        initDataBinding(binding)

        val user = args.user
        if (user != null) {
            userDetailsViewModel.setUser(user)
        }

        return binding.root
    }

    private fun initDataBinding(binding: FragmentUserDetailsBinding){
        binding.lifecycleOwner = viewLifecycleOwner
        binding.userViewModel = userDetailsViewModel
    }



}