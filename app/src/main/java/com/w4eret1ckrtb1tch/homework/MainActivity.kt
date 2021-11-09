package com.w4eret1ckrtb1tch.homework

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.w4eret1ckrtb1tch.homework.presentation.MainState
import com.w4eret1ckrtb1tch.homework.presentation.MainViewModel
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    //TODO: DI
    private val viewModel = MainViewModel()

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