package com.example.viswanathankp.github_kotlin.view.main

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.example.viswanathankp.github_kotlin.R

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                    .replace(R.id.container,
                            MainFragment.newInstance())
                    .commit()
        }
    }

}
