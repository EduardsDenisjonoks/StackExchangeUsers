package com.exail.stackexchangeusers.required

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.exail.stackexchangeusers.R
import com.exail.stackexchangeusers.core.network.ApiConfig
import com.exail.stackexchangeusers.models.DataResponse
import com.exail.stackexchangeusers.models.User
import com.exail.stackexchangeusers.repository.UserApi
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

class RequiredUserListViewModel @ViewModelInject constructor(private val userRepository: UserApi) :
    ViewModel() {

    private val userObservable = object : Observer<DataResponse<User>> {
        override fun onComplete() {

        }

        override fun onSubscribe(d: Disposable) {

        }

        override fun onNext(data: DataResponse<User>) {
            userListLiveData.value = data.items
        }

        override fun onError(e: Throwable) {
            errorLiveData.postValue(R.string.error_something_went_wrong)
        }
    }

    val searchQuery = MutableLiveData<String>()
    private val userListLiveData = MutableLiveData<List<User>>()
    private val errorLiveData = MutableLiveData<Int>()

    init {
        fetchUsers()
    }

    fun getErrorLiveData(): LiveData<Int> = errorLiveData

    fun getUserListLiveData(): LiveData<List<User>> = userListLiveData

    fun fetchUsers() {
        userRepository.getUsersObservable(
            1,
            20,
            ApiConfig.ORDER,
            ApiConfig.SORT,
            searchQuery.value ?: "",
            ApiConfig.SITE
        ).subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(userObservable)
    }

}