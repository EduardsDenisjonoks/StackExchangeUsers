package com.exail.stackexchangeusers.required.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.exail.stackexchangeusers.R
import com.exail.stackexchangeusers.databinding.ListItemUserBinding
import com.exail.stackexchangeusers.models.User

class UserAdapter(private val showDetails: (User) -> (Unit)) :
    RecyclerView.Adapter<UserAdapter.UserViewHolder>() {

    private val users = ArrayList<User>()

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

    override fun getItemCount(): Int = users.size

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        holder.bind(users[position])
    }

    fun setUsers(newUsers: List<User>){
        users.clear()
        users.addAll(newUsers)
        notifyDataSetChanged()
    }

    inner class UserViewHolder(private val binding: ListItemUserBinding) :
        RecyclerView.ViewHolder(binding.root) {

        init {
            binding.root.setOnClickListener {
                showDetails(users[bindingAdapterPosition])
            }
        }

        fun bind(user: User) {
            binding.name = user.name
            binding.reputation = user.reputation.toString()
        }
    }
}