package com.maxsch.rxjavalecture.ui

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.maxsch.rxjavalecture.R
import com.maxsch.rxjavalecture.presentation.MainViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel


class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val viewModel: MainViewModel by viewModel()

        viewModel.getResult()

        viewModel.result.observe(this) {
            Log.i("Result", it.toString())
        }
    }
}