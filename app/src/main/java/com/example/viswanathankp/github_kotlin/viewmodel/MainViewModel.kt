package com.example.viswanathankp.github_kotlin.viewmodel

import android.app.Application
import android.arch.lifecycle.MutableLiveData
import android.databinding.ObservableField
import com.example.viswanathankp.github_kotlin.domain.userlist.SearchData
import com.example.viswanathankp.github_kotlin.domain.userlist.UserListUsecase
import com.example.viswanathankp.github_kotlin.domain.userlist.UserListUsecaseImpl
import com.example.viswanathankp.github_kotlin.model.Result
import com.example.viswanathankp.github_kotlin.utils.Constants
import android.arch.lifecycle.Transformations
import com.example.viswanathankp.github_kotlin.domain.userlist.SearchUseCaseInterface


class MainViewModel(val app: Application) : ObservableViewModel(app), SearchUseCaseInterface {

    override fun handleState(searchData: SearchData) {
        when (searchData.state) {
            Constants.STATE_SUCCESS -> onSuccess(searchData.result)
            Constants.STATE_FAILURE -> onError()
            Constants.STATE_EMPTY_TEXT -> {
                isLoading.set(false)
                informationText.set(true)
                listMutable.postValue(null)
            }
            Constants.STATE_LOADING -> {
                isLoading.set(true)
                informationText.set(false)
                listMutable.postValue(null)
            }
        }
    }

    var isLoading = ObservableField<Boolean>(false)
    var informationText = ObservableField<Boolean>(true)
    private val searchUseCase : UserListUsecase = UserListUsecaseImpl()

    private val listMutable = MutableLiveData<Result>()

    init {
        Transformations.map(searchUseCase.getStateLiveData()) {
            it -> handleState(it)
        }
        searchUseCase.setInterface(this)
    }

    fun getUserList() = listMutable

    override fun onCleared() {
        super.onCleared()
        searchUseCase.clear()
    }

    private fun onSuccess(result: Result?) {
        isLoading.set(false)
        listMutable.postValue(result)
    }

    private fun onError(error: String = "Something went wrong.") {
        isLoading.set(false)
        //show toast
    }

    fun loadMoreData(){
        TODO("pagination")
    }

    fun queryGithub(query: String){
        searchUseCase.searchUser(query)
    }
}
