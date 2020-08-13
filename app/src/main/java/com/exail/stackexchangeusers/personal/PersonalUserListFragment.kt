package com.exail.stackexchangeusers.personal

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.paging.LoadState
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.exail.stackexchangeusers.R
import com.exail.stackexchangeusers.base.FragmentBase
import com.exail.stackexchangeusers.databinding.FragmentPersonalUserListBinding
import com.exail.stackexchangeusers.models.User
import com.exail.stackexchangeusers.personal.adapters.UserPageAdapter
import com.exail.stackexchangeusers.utils.hideKeyboard
import com.google.android.material.button.MaterialButton
import com.google.android.material.textfield.TextInputEditText
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class PersonalUserListFragment : FragmentBase() {

    private val personalUserListViewModel: PersonalUserListViewModel by viewModels()

    private val navController by lazy { findNavController() }
    private val userPageAdapter by lazy {
        UserPageAdapter { user -> showUserDetails(user) }.apply {
            this.addLoadStateListener { loadState ->
                val error = when {
                    loadState.prepend is LoadState.Error -> loadState.prepend as LoadState.Error
                    loadState.append is LoadState.Error -> loadState.append as LoadState.Error
                    loadState.refresh is LoadState.Error -> loadState.refresh as LoadState.Error
                    else -> null
                }

                error?.let {
                    Toast.makeText(context, R.string.error_something_went_wrong, Toast.LENGTH_LONG).show()
                }
            }
        }
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
        initSearchInput(binding.inputSearchText)
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

    private fun initSearchInput(inputEditText: TextInputEditText) {
        inputEditText.setOnEditorActionListener { view, actionId, _ ->
            view.hideKeyboard()
            if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                userPageAdapter.refresh()
            }
            false
        }
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
        viewLifecycleOwner.lifecycleScope.launch {
            personalUserListViewModel.users.collectLatest {
                userPageAdapter.submitData(it)
            }
        }
    }

    private fun initSearchButton(button: MaterialButton) {
        button.setOnClickListener {
            it.hideKeyboard()
            userPageAdapter.refresh()
        }
    }

    private fun showUserDetails(user: User) {
        navController.navigate(
            PersonalUserListFragmentDirections.actionUserListToDetails(
                user.userId,
                user
            )
        )
    }
}