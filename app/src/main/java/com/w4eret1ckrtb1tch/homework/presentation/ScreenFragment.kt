package com.w4eret1ckrtb1tch.homework.presentation

import android.content.Context
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.w4eret1ckrtb1tch.homework.MainActivity
import com.w4eret1ckrtb1tch.homework.R
import dagger.Lazy
import kotlinx.android.synthetic.main.fragment_screen.*
import javax.inject.Inject

class ScreenFragment : Fragment(R.layout.fragment_screen) {

    //TODO: DI OK
    // QUESTION: 10.11.2021 lazy от Kotlin или Lazy от Dagger?
    @Inject
    lateinit var viewModelFactory: Lazy<ViewModelFactory>
    private val viewModel by viewModels<ScreenViewModel>(factoryProducer = {
        viewModelFactory.get()
    })

    override fun onAttach(context: Context) {
        super.onAttach(context)
        (activity as MainActivity).fragmentComponent.inject(this)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        loadButton.setOnClickListener {
            viewModel.loadStrings()
        }

        viewModel.state.observe(viewLifecycleOwner) { newState ->
            renderState(newState)
        }
    }

    private fun renderState(state: MainState) {
        when (state) {
            MainState.Loading -> {
                Toast.makeText(requireContext(), "loading", Toast.LENGTH_SHORT).show()
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