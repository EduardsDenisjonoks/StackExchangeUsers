package com.exail.stackexchangeusers.personal

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.exail.stackexchangeusers.R
import com.exail.stackexchangeusers.base.FragmentBase

class PersonalUserListFragment : FragmentBase() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_personal_user_list, container, false)
    }

}