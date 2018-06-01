package com.example.viswanathankp.github_kotlin.di.component

import com.example.viswanathankp.github_kotlin.GithubApplication
import com.example.viswanathankp.github_kotlin.di.module.ActivityModule
import com.example.viswanathankp.github_kotlin.di.module.AppModule
import com.example.viswanathankp.github_kotlin.di.module.UseCaseModule
import com.example.viswanathankp.github_kotlin.di.module.ViewModelModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(
        modules = arrayOf(AndroidSupportInjectionModule::class, AppModule::class, ViewModelModule::class,
                UseCaseModule::class, ActivityModule::class))
interface AppComponent {

    @Component.Builder
    interface Builder{
        @BindsInstance fun application(application : GithubApplication) : Builder
        fun build() : AppComponent
    }

    fun inject(application : GithubApplication)
}