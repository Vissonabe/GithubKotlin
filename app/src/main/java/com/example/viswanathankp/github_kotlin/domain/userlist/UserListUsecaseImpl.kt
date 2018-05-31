package com.example.viswanathankp.github_kotlin.domain.userlist

import android.arch.lifecycle.MutableLiveData
import com.example.viswanathankp.github_kotlin.api.SearchRepository
import com.example.viswanathankp.github_kotlin.api.SearchRepositoryProvider
import com.example.viswanathankp.github_kotlin.model.Result
import com.example.viswanathankp.github_kotlin.utils.Constants
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import io.reactivex.subjects.PublishSubject
import java.util.concurrent.TimeUnit

class UserListUsecaseImpl : UserListUsecase {

    var repository : SearchRepository = SearchRepositoryProvider.provideSearchRepository()
    val subject = PublishSubject.create<String>()
    private val disposable  = CompositeDisposable()
    var currentPageIndex : Int = 1
    lateinit var currentSearchString : String
    private val searchResultMutable = MutableLiveData<SearchData>()
    private var searchUseCaseInterface: SearchUseCaseInterface? = null

    init {
        initSubject(subject)
    }

    private fun searchUser(tag: String, page: Int): Observable<Result> {
        return repository.searchUsers(tag, page, 20)
    }

    override fun setInterface(inter: SearchUseCaseInterface) {
        searchUseCaseInterface = inter
    }


    override fun searchUser(tag: String) {
        currentSearchString = tag
        if(currentSearchString.isNotEmpty()) {
            subject.onNext(currentSearchString)
        }else{
            updateState(Constants.STATE_EMPTY_TEXT)
        }
    }

    private fun initSubject(subject : PublishSubject<String>) {
        disposable.add(subject
                .debounce(300, TimeUnit.MILLISECONDS)
                .filter({it.isNotEmpty()})
                .doOnNext { updateState(Constants.STATE_LOADING) }
                .distinctUntilChanged()
                .switchMap({
                    searchUser(it, currentPageIndex).subscribeOn(Schedulers.io())
                })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ result ->
                    updateState(result)
                }, {
                    updateState(Constants.STATE_FAILURE)
                }))
    }

    private fun updateState(state: Int){
            searchUseCaseInterface?.handleState(SearchData(state))
    }

    private fun updateState(result : Result){
        if(currentSearchString.isNotEmpty()) {
            searchUseCaseInterface?.handleState(SearchData(Constants.STATE_SUCCESS, result))
        }
    }

    override fun getStateLiveData() : MutableLiveData<SearchData>{
        return searchResultMutable
    }

    override fun clear() {
        searchUseCaseInterface = null
        disposable.clear()
    }
}