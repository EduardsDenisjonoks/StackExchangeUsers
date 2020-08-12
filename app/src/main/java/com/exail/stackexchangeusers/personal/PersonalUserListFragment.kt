package com.exail.stackexchangeusers.personal

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.exail.stackexchangeusers.R
import com.exail.stackexchangeusers.base.FragmentBase
import com.exail.stackexchangeusers.databinding.FragmentPersonalUserListBinding
import com.exail.stackexchangeusers.models.User
import com.exail.stackexchangeusers.personal.adapters.UserPageAdapter
import com.google.android.material.button.MaterialButton
import kotlinx.coroutines.flow.collectLatest

class PersonalUserListFragment : FragmentBase() {

    private val personalUserListViewModel: PersonalUserListViewModel by viewModels()

    private val navController by lazy { findNavController() }
    private val userPageAdapter by lazy {
        UserPageAdapter { user -> showUserDetails(user) }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = DataBindingUtil.inflate<FragmentPersonalUserListBinding>(
            inflater,
            R.layout.fragment_personal_user_list,
            container,
            false
        )

        initDataBinding(binding)
        initUserListView(binding.listView)
        initSearchButton(binding.btnSearch)

        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        initObservers()
    }

    private fun initDataBinding(binding: FragmentPersonalUserListBinding) {
        binding.lifecycleOwner = viewLifecycleOwner
        binding.viewModel = personalUserListViewModel
    }

    private fun initUserListView(listView: RecyclerView) {
        listView.adapter = userPageAdapter
        listView.layoutManager = LinearLayoutManager(requireContext())
        listView.addItemDecoration(
            DividerItemDecoration(
                requireContext(),
                LinearLayoutManager.VERTICAL
            )
        )
    }

    private fun initObservers() {
        viewLifecycleOwner.lifecycleScope.launchWhenCreated {
            personalUserListViewModel.users.collectLatest {
                userPageAdapter.submitData(it)
            }
        }
    }

    private fun initSearchButton(button: MaterialButton) {
        button.setOnClickListener {
            userPageAdapter.refresh()
        }
    }

    private fun showUserDetails(user: User) {
        navController.navigate(R.id.action_user_list_to_details)
    }
}