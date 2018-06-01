package com.example.viswanathankp.github_kotlin.di.module

import com.example.viswanathankp.github_kotlin.domain.userlist.UserListUsecase
import com.example.viswanathankp.github_kotlin.domain.userlist.UserListUsecaseImpl
import dagger.Binds
import dagger.Module

@Module
abstract class UseCaseModule {

    @Binds
    abstract fun bindUseCaseModule(userListUsecaseImpl: UserListUsecaseImpl) : UserListUsecase
}