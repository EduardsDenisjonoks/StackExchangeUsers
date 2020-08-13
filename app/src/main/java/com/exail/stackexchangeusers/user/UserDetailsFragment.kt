package com.exail.stackexchangeusers.user

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.navArgs
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.exail.stackexchangeusers.R
import com.exail.stackexchangeusers.base.FragmentBase
import com.exail.stackexchangeusers.databinding.FragmentUserDetailsBinding

class UserDetailsFragment : FragmentBase() {

    private val args: UserDetailsFragmentArgs by navArgs()
    private val userDetailsViewModel: UserDetailsViewModel by viewModels()
    private lateinit var loaderView: SwipeRefreshLayout


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
        initLoaderView(binding.refreshLayout)

        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        initObservers()

        fetchUser()
        setUserData()
    }

    private fun initDataBinding(binding: FragmentUserDetailsBinding) {
        binding.lifecycleOwner = viewLifecycleOwner
        binding.userViewModel = userDetailsViewModel
    }

    private fun initLoaderView(loaderView: SwipeRefreshLayout) {
        this.loaderView = loaderView
        this.loaderView.setOnRefreshListener {
            fetchUser()
        }
    }

    private fun initObservers() {
        //loader observer
        userDetailsViewModel.getLoadingLiveData().observe(
            viewLifecycleOwner,
            Observer { isLoading -> loaderView.isRefreshing = isLoading })

        //error observer
        userDetailsViewModel.getErrorLiveData().observe(
            viewLifecycleOwner,
            Observer { error -> Toast.makeText(context, error, Toast.LENGTH_LONG).show() })
    }

    private fun fetchUser() {
        userDetailsViewModel.loadUser(args.userId)
    }

    private fun setUserData() {
        args.user?.let { userDetailsViewModel.setUser(it) }
    }
}