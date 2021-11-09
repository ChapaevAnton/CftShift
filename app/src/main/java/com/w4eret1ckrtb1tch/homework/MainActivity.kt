package com.w4eret1ckrtb1tch.homework

import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.w4eret1ckrtb1tch.homework.di.ViewModelFactory
import com.w4eret1ckrtb1tch.homework.presentation.MainState
import com.w4eret1ckrtb1tch.homework.presentation.MainViewModel
import dagger.Lazy
import dagger.android.support.DaggerAppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject

class MainActivity : DaggerAppCompatActivity() {

    //TODO: DI OK
    @Inject
    lateinit var viewModelFactory: Lazy<ViewModelFactory>
    private val viewModel by lazy {
        ViewModelProvider(this, viewModelFactory.get()).get(
            MainViewModel::class.java
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        loadButton.setOnClickListener {
            viewModel.loadStrings()
        }

        viewModel.state.observe(this) { newState ->
            renderState(newState)
        }
    }

    private fun renderState(state: MainState) {
        when (state) {
            MainState.Loading -> {
                Toast.makeText(this, "loading", Toast.LENGTH_SHORT).show()
                remoteText.text = ""
                localText.text = ""
            }

            is MainState.Success -> {
                remoteText.text = state.remoteString
                localText.text = state.localString
            }
        }
    }
}