package com.example.viswanathankp.github_kotlin.adapter

import android.databinding.DataBindingUtil
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.viswanathankp.github_kotlin.R
import com.example.viswanathankp.github_kotlin.databinding.LayoutRecyclerItemBinding
import com.example.viswanathankp.github_kotlin.model.User

class GithubListAdapter:  RecyclerView.Adapter<UserViewHolder>() {

    var resultList : MutableList<User> = ArrayList()

    lateinit var binding : LayoutRecyclerItemBinding

    override fun getItemCount(): Int {
        return resultList.size
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        holder.bind(resultList[position])
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        binding = DataBindingUtil.inflate(layoutInflater, R.layout.layout_recycler_item, parent, false)
        return UserViewHolder(binding)
    }

    fun addItem(list: List<User>) {
        resultList = list.toMutableList()
        notifyDataSetChanged()
    }

    fun clearItem() {
        resultList.clear()
        notifyDataSetChanged()
    }
}

class UserViewHolder(private val binding: LayoutRecyclerItemBinding) : RecyclerView.ViewHolder(binding.root) {
    fun bind(data: User) {
        binding.user = data
        binding.executePendingBindings()
    }
}