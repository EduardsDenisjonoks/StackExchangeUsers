package com.exail.stackexchangeusers.required

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.exail.stackexchangeusers.R
import com.exail.stackexchangeusers.base.FragmentBase
import com.exail.stackexchangeusers.databinding.FragmentRequiredUserListBinding
import com.exail.stackexchangeusers.models.User
import com.exail.stackexchangeusers.required.adapters.UserAdapter
import com.exail.stackexchangeusers.user.UserDetailsActivity
import com.exail.stackexchangeusers.utils.hideKeyboard
import com.google.android.material.button.MaterialButton
import com.google.android.material.textfield.TextInputEditText


class RequiredUserListFragment : FragmentBase() {

    private val requiredUserListViewModel: RequiredUserListViewModel by viewModels()
    private val userAdapter by lazy { UserAdapter { user -> showUserDetails(user) } }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = DataBindingUtil.inflate<FragmentRequiredUserListBinding>(
            inflater,
            R.layout.fragment_required_user_list,
            container,
            false
        )

        initDataBinding(binding)
        initSearchInput(binding.inputSearchText)
        initSearchButton(binding.btnSearch)
        initUserListView(binding.listView)

        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        initObservers()
    }

    private fun initDataBinding(binding: FragmentRequiredUserListBinding) {
        binding.lifecycleOwner = viewLifecycleOwner
        binding.viewModel = requiredUserListViewModel
    }

    private fun initSearchInput(inputEditText: TextInputEditText) {
        inputEditText.setOnEditorActionListener { view, actionId, _ ->
            view.hideKeyboard()
            if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                requiredUserListViewModel.fetchUsers()
            }
            false
        }
    }

    private fun initSearchButton(button: MaterialButton) {
        button.setOnClickListener {
            it.hideKeyboard()
            requiredUserListViewModel.fetchUsers()
        }
    }

    private fun initUserListView(listView: RecyclerView) {
        listView.adapter = userAdapter
        listView.layoutManager = LinearLayoutManager(requireContext())
        listView.addItemDecoration(
            DividerItemDecoration(
                requireContext(),
                LinearLayoutManager.VERTICAL
            )
        )
    }

    private fun initObservers() {
        //user data observer
        requiredUserListViewModel.getUserListLiveData().observe(
            viewLifecycleOwner,
            Observer { users -> userAdapter.setUsers(users) })
        //error observer
        requiredUserListViewModel.getErrorLiveData().observe(
            viewLifecycleOwner,
            Observer { error -> Toast.makeText(context, error, Toast.LENGTH_LONG).show() })
    }

    private fun showUserDetails(user: User) {
        startActivity(Intent(activity, UserDetailsActivity::class.java).apply {
            this.putExtra(UserDetailsActivity.ARG_USER_ID, user.userId)
            this.putExtra(UserDetailsActivity.ARG_USER, user)
        })
    }
}