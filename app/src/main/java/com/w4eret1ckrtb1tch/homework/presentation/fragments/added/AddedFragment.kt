package com.w4eret1ckrtb1tch.homework.presentation.fragments.added

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.w4eret1ckrtb1tch.homework.R
import com.w4eret1ckrtb1tch.homework.databinding.FragmentAddedBinding

class AddedFragment : Fragment(R.layout.fragment_added) {

    private var _binding: FragmentAddedBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentAddedBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.add.setOnClickListener {

        }
    }

    override fun onDestroyView() {
        _binding = null
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
}