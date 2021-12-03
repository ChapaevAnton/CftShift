package com.maxsch.rxjavalecture.ui

import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.maxsch.rxjavalecture.R
import com.maxsch.rxjavalecture.presentation.MainViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val viewModel: MainViewModel by viewModels()

        viewModel.getResult()

        viewModel.result.observe(this) {
            Log.i("Result", it.toString())
        }
    }
}