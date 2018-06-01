package com.example.viswanathankp.github_kotlin.di.module

import com.example.viswanathankp.github_kotlin.view.main.MainFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class MainFragmentModule {
    @ContributesAndroidInjector
    abstract fun mainFragment() : MainFragment
}