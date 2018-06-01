package com.example.viswanathankp.github_kotlin.api

import com.example.viswanathankp.github_kotlin.model.Result
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SearchRepository @Inject constructor (private val apiService: GithubService) {

    fun searchUsers(name: String, page: Int, per_page: Int): io.reactivex.Observable<Result> {
        return apiService.search(name, page, per_page)
    }

}