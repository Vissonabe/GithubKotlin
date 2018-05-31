package com.example.viswanathankp.github_kotlin.utils

import android.support.v7.widget.RecyclerView
import android.text.TextWatcher
import android.widget.EditText

@android.databinding.BindingAdapter(value = *arrayOf("textChangedListener"))
fun bindTextWatcher(editText: EditText, textWatcher: TextWatcher) {
    editText.addTextChangedListener(textWatcher)
}