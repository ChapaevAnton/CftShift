package com.maxsch.rxjavalecture

import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContentView(R.layout.activity_main)

		val viewModel: MainViewModel by viewModels()

		viewModel.getData()

		viewModel.result.observe(this) {
			Log.i("Result", it.toString())
		}
	}
}