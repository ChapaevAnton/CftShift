package com.w4eret1ckrtb1tch.homework

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.fragment.app.Fragment
import com.w4eret1ckrtb1tch.homework.databinding.FragmentLayoutBinding

abstract class BaseFragment(@LayoutRes layoutResId: Int) : Fragment(layoutResId) {

    open val logTag: String
        get() = "Lifecycle/${this::class.simpleName}"

    private var _binding: FragmentLayoutBinding? = null
    open val binding get() = _binding!!

    abstract fun startFragment()
    abstract val color: Int?
    abstract val name: String?

    override fun onCreate(savedInstanceState: Bundle?) {
        Log.i(logTag, "onCreate")
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        Log.i(logTag, "onCreateView")
        _binding = FragmentLayoutBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        Log.i(logTag, "onViewCreated")
        super.onViewCreated(view, savedInstanceState)
        color?.let { binding.fragmentLayout.setBackgroundColor(it) }
        val title = name
        binding.title.text = title
        binding.buttonNext.setOnClickListener {
            startFragment()
        }
    }

    override fun onStart() {
        Log.i(logTag, "onStart")
        super.onStart()
    }

    override fun onResume() {
        Log.i(logTag, "onResume")
        super.onResume()
    }

    override fun onPause() {
        Log.i(logTag, "onPause")
        super.onPause()
    }

    override fun onStop() {
        Log.i(logTag, "onStop")
        super.onStop()
    }

    override fun onDestroyView() {
        _binding = null
        Log.i(logTag, "onDestroyView")
        super.onDestroyView()
    }

    override fun onDestroy() {
        Log.i(logTag, "onDestroy")
        super.onDestroy()
    }

}