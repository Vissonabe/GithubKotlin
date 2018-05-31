package com.example.viswanathankp.github_kotlin.domain.userlist

import android.arch.lifecycle.MutableLiveData
import com.example.viswanathankp.github_kotlin.model.Result
import io.reactivex.Observable

interface UserListUsecase {
    fun searchUser(tag: String)
    fun getStateLiveData() : MutableLiveData<SearchData>
    fun clear()
    fun setInterface(inter: SearchUseCaseInterface)
}