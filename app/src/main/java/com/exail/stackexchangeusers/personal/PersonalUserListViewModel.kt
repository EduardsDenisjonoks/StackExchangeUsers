package com.exail.stackexchangeusers.personal

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.exail.stackexchangeusers.models.User
import com.exail.stackexchangeusers.repository.UserPageSource
import com.exail.stackexchangeusers.repository.UserRepository
import kotlinx.coroutines.flow.Flow

class PersonalUserListViewModel @ViewModelInject constructor(userRepository: UserRepository) :
    ViewModel() {

    val searchQuery = MutableLiveData<String>()

    val users: Flow<PagingData<User>> = Pager(PagingConfig(pageSize = 20)) {
        UserPageSource(userRepository, searchQuery.value)
    }.flow.cachedIn(viewModelScope)

}