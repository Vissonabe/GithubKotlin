package com.example.viswanathankp.github_kotlin.view.main

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProvider
import android.arch.lifecycle.ViewModelProviders
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.viswanathankp.github_kotlin.R
import com.example.viswanathankp.github_kotlin.adapter.GithubListAdapter
import com.example.viswanathankp.github_kotlin.databinding.MainFragmentBinding
import com.example.viswanathankp.github_kotlin.di.FragmentInjector
import com.example.viswanathankp.github_kotlin.model.Result
import com.example.viswanathankp.github_kotlin.utils.EndlessRecyclerOnScrollListener
import com.example.viswanathankp.github_kotlin.viewmodel.MainViewModel
import javax.inject.Inject

class MainFragment : Fragment(), FragmentInjector {

    companion object {
        fun newInstance() = MainFragment()
    }

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private lateinit var viewModel: MainViewModel
    private lateinit var binding: MainFragmentBinding
    private lateinit var adapter: GithubListAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        binding = DataBindingUtil.inflate(inflater,R.layout.main_fragment, container, false)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this, viewModelFactory)[MainViewModel::class.java]
        initView(binding)
        binding.viewModel = viewModel
    }

    private fun initView(binding: MainFragmentBinding) {
        binding.recyclerView.layoutManager = LinearLayoutManager(this.context)
        adapter = GithubListAdapter()
        binding.recyclerView.adapter = adapter

        binding.recyclerView.addOnScrollListener(object : EndlessRecyclerOnScrollListener(){
            override fun onLoadMore() {
                Log.d("Main", "scroll called")
                //viewModel.loadMoreData()
            }
        })

        binding.editText.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
            }

            override fun afterTextChanged(s: Editable) {
                viewModel.queryGithub(s.toString())
            }
        })

        val postListObserver = Observer<Result> { list ->
            if(list != null) {
                adapter.addItem(list.items)
            } else {
                adapter.clearItem()
            }
        }

        viewModel.getUserList().observe(this, postListObserver)
    }

}
