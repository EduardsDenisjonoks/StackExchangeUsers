package com.exail.stackexchangeusers.user

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.*
import com.exail.stackexchangeusers.core.network.ApiResult
import com.exail.stackexchangeusers.models.User
import com.exail.stackexchangeusers.repository.UserRepository
import com.exail.stackexchangeusers.utils.epochTimeToFormattedString
import com.exail.stackexchangeusers.utils.nonNullOrBlank
import kotlinx.coroutines.launch

class UserDetailsViewModel @ViewModelInject constructor(private val userRepository: UserRepository) :
    ViewModel() {
    private val userLiveData = MutableLiveData<User>()
    private val loadingLiveData = MutableLiveData<Boolean>()
    private val errorLiveData = MutableLiveData<Int>()

    val profileImageLiveData: LiveData<String>
    val nameLiveData: LiveData<String>
    val reputationLiveData: LiveData<String>
    val bronzeCountLiveData: LiveData<String>
    val silverCountLiveData: LiveData<String>
    val goldCountLiveData: LiveData<String>
    val locationLiveData: LiveData<String>
    val ageLiveData: LiveData<String>
    val creationLiveData: LiveData<String>

    init {
        profileImageLiveData =
            Transformations.map(userLiveData) { user -> user.profileImage }
        nameLiveData =
            Transformations.map(userLiveData) { user -> user.name }
        reputationLiveData =
            Transformations.map(userLiveData) { user -> user.reputation.toString() }
        bronzeCountLiveData =
            Transformations.map(userLiveData) { user -> user.badgeCounts.bronze.toString() }
        silverCountLiveData =
            Transformations.map(userLiveData) { user -> user.badgeCounts.silver.toString() }
        goldCountLiveData =
            Transformations.map(userLiveData) { user -> user.badgeCounts.gold.toString() }
        locationLiveData =
            Transformations.map(userLiveData) { user -> user.location.nonNullOrBlank() }
        ageLiveData =
            Transformations.map(userLiveData) { user -> user.age.nonNullOrBlank() }
        creationLiveData =
            Transformations.map(userLiveData) { user -> user.creationDate.epochTimeToFormattedString() }
    }

    fun getLoadingLiveData(): LiveData<Boolean> = loadingLiveData

    fun getErrorLiveData(): LiveData<Int> = errorLiveData

    fun loadUser(userId: Int) {
        loadingLiveData.postValue(false)
        viewModelScope.launch {
            val result = userRepository.getUser(userId)
            loadingLiveData.postValue(false)
            when (result) {
                is ApiResult.Success -> setUser(result.data.first())
                is ApiResult.Error -> errorLiveData.postValue(result.appError.errorResource)
            }
        }
    }

    fun setUser(user: User) {
        userLiveData.value = user
    }
}