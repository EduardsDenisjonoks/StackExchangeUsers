package com.exail.stackexchangeusers.personal.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.paging.LoadState
import androidx.paging.LoadStateAdapter
import androidx.recyclerview.widget.RecyclerView
import com.exail.stackexchangeusers.R
import com.exail.stackexchangeusers.databinding.ListItemLoadStateBinding

class UserLoadStateAdapter(private val retry: () -> Unit) :
    LoadStateAdapter<UserLoadStateAdapter.LoadStateViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, loadState: LoadState): LoadStateViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return LoadStateViewHolder(
            DataBindingUtil.inflate(
                inflater,
                R.layout.list_item_load_state,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: LoadStateViewHolder, loadState: LoadState) {
        holder.bindView(loadState)
    }

    inner class LoadStateViewHolder(private val binding: ListItemLoadStateBinding) :
        RecyclerView.ViewHolder(binding.root) {

        init {
            binding.btnRetry.setOnClickListener { retry.invoke() }
        }

        fun bindView(loadState: LoadState) {
            binding.isLoading = loadState is LoadState.Loading
            binding.showError = loadState !is LoadState.Loading

            if (loadState is LoadState.Error) {
                binding.error = loadState.error.message
                    ?: binding.root.context.getString(R.string.error_something_went_wrong)
            }
        }
    }

}