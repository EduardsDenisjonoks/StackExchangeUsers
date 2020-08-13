package com.exail.stackexchangeusers.user

import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
import com.exail.stackexchangeusers.R
import com.exail.stackexchangeusers.base.ActivityBase
import com.exail.stackexchangeusers.databinding.ActivityUserDetailsBinding
import com.google.android.material.appbar.MaterialToolbar

class UserDetailsActivity : ActivityBase() {

    companion object {
        const val ARG_USER_ID = "ARG_USER_ID"
        const val ARG_USER = "ARG_USER"
    }

    private val navController by lazy { findNavController(R.id.nav_host) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = DataBindingUtil.setContentView<ActivityUserDetailsBinding>(
            this,
            R.layout.activity_user_details
        )

        initNavGraph()
        initToolbar(binding.toolbar)
    }

    private fun initToolbar(toolbar: MaterialToolbar) {
        val upListener = AppBarConfiguration.OnNavigateUpListener {
            val navigated = navController.navigateUp()
            if (!navigated) {
                onBackPressed()
            }
            navigated
        }
        val appBarConfiguration =
            AppBarConfiguration.Builder().setFallbackOnNavigateUpListener(upListener).build()
        toolbar.setupWithNavController(navController, appBarConfiguration)
    }

    private fun initNavGraph() {
        navController.setGraph(R.navigation.nav_user_details, intentToBundle())
    }

    private fun intentToBundle() = UserDetailsFragmentArgs(
        userId = intent.getIntExtra(ARG_USER_ID, -1),
        user = intent.getParcelableExtra(ARG_USER)
    ).toBundle()
}