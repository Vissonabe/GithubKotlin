package com.example.viswanathankp.github_kotlin.di.module

import com.example.viswanathankp.github_kotlin.view.main.MainActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityModule {
    @ContributesAndroidInjector(modules = arrayOf(
            MainFragmentModule::class))
    abstract fun contributeMainActivity() : MainActivity
}