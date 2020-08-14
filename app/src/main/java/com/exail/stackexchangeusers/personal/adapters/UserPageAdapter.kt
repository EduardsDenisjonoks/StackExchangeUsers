package com.exail.stackexchangeusers.personal.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.RecyclerView
import com.exail.stackexchangeusers.R
import com.exail.stackexchangeusers.databinding.ListItemUserBinding
import com.exail.stackexchangeusers.models.User
import com.exail.stackexchangeusers.models.UserComparator

class UserPageAdapter(private val showDetails: (User) -> (Unit)) :
    PagingDataAdapter<User, UserPageAdapter.UserViewHolder>(UserComparator) {

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return UserViewHolder(
            DataBindingUtil.inflate(
                inflater,
                R.layout.list_item_user,
                parent,
                false
            )
        )
    }


    inner class UserViewHolder(private val binding: ListItemUserBinding) :
        RecyclerView.ViewHolder(binding.root) {

        init {
            binding.root.setOnClickListener {
                getItem(bindingAdapterPosition)?.let { showDetails(it) }
            }
        }

        fun bind(user: User?) {
            binding.name = user?.name ?: ""
            binding.reputation = user?.reputation?.toString() ?: "0"
        }
    }

}