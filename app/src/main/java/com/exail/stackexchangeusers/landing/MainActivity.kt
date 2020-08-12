package com.exail.stackexchangeusers.landing

import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
import com.exail.stackexchangeusers.R
import com.exail.stackexchangeusers.base.ActivityBase
import com.exail.stackexchangeusers.databinding.ActivityMainBinding
import com.google.android.material.appbar.MaterialToolbar
class MainActivity : ActivityBase() {

    private val navController by lazy { findNavController(R.id.nav_host) }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = DataBindingUtil.setContentView<ActivityMainBinding>(this,
            R.layout.activity_main
        )

        initToolbar(binding.toolbar)
    }

    private fun initToolbar(toolbar: MaterialToolbar) {
        val appBarConfiguration = AppBarConfiguration(navController.graph)
        toolbar.setupWithNavController(navController, appBarConfiguration)
    }
}