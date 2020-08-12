package com.exail.stackexchangeusers.landing

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.fragment.NavHostFragment.findNavController
import com.exail.stackexchangeusers.R
import com.exail.stackexchangeusers.base.FragmentBase
import com.exail.stackexchangeusers.databinding.FragmentLandingBinding
import com.google.android.material.button.MaterialButton

class LandingFragment : FragmentBase() {

    private val navController by lazy { findNavController(this) }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = DataBindingUtil.inflate<FragmentLandingBinding>(
            inflater,
            R.layout.fragment_landing,
            container,
            false
        )

        initBtnPersonal(binding.btnPersonal)
        initBtnRequired(binding.btnRequired)

        return binding.root
    }


    private fun initBtnPersonal(button: MaterialButton) {
        button.setOnClickListener {
            navController.navigate(R.id.action_landing_to_personal)
        }
    }

    private fun initBtnRequired(button: MaterialButton) {
        button.setOnClickListener {
            navController.navigate(R.id.action_landing_to_required)
        }
    }
}