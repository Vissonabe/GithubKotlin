package com.example.viswanathankp.github_kotlin.api

object SearchRepositoryProvider {

    fun provideSearchRepository(): SearchRepository {
        return SearchRepository(GithubService.Factory.create())
    }

}