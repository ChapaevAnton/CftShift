package com.w4eret1ckrtb1tch.homework.presentation.fragments.added

import android.animation.AnimatorInflater
import android.os.Bundle
import android.transition.TransitionInflater
import android.transition.TransitionManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.transition.doOnEnd
import androidx.fragment.app.Fragment
import com.google.android.material.textfield.TextInputLayout
import com.w4eret1ckrtb1tch.homework.R
import com.w4eret1ckrtb1tch.homework.databinding.FragmentAddedBinding
import com.w4eret1ckrtb1tch.homework.presentation.transition.FadeTransition

class AddedFragment : Fragment(R.layout.fragment_added) {

    private var binding: FragmentAddedBinding? = null
    private val fadeTransition by lazy { FadeTransition(durationOut = 1000L, durationIn = 1000L) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val inflater = TransitionInflater.from(requireContext())
        enterTransition = inflater.inflateTransition(R.transition.slide_bottom)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentAddedBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding?.apply {
            fadeTransition.doOnEnd {
                requireActivity().supportFragmentManager.popBackStack()
            }
            add.setOnClickListener {
                if (!editField.emptyTextInput()) {
                    runFadeTransition()
                }
            }
        }
    }

    override fun onDestroyView() {
        binding = null
        super.onDestroyView()
    }

    companion object {
        fun newInstance(bundle: Bundle?): AddedFragment {
            return AddedFragment().apply {
                arguments = bundle
            }
        }
        const val KEY_ADDED_FRAGMENT = "com.homework.added_fragment.arguments"
    }

    private fun TextInputLayout.emptyTextInput(): Boolean {
        val text = this.editText?.text.toString()
        if (text.isEmpty()) {
            val animatorSet =
                AnimatorInflater.loadAnimator(requireContext(), R.animator.wiggle_button).apply {
                    setTarget(binding?.editField)
                }
            animatorSet.start()
            return true
        }
        return false
    }

    private fun runFadeTransition() {
        binding?.apply {
            TransitionManager.beginDelayedTransition(root, fadeTransition)
            editField.visibility = View.INVISIBLE
            add.visibility = View.INVISIBLE
            progressBar.visibility = View.VISIBLE
        }
    }
}