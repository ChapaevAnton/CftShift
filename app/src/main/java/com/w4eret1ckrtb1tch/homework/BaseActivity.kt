package com.w4eret1ckrtb1tch.homework

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.w4eret1ckrtb1tch.homework.databinding.ActivityMainBinding

abstract class BaseActivity : AppCompatActivity() {

    open val tag: String
        get() = "Lifecycle/${this::class.simpleName}"

    abstract val color: Int

    abstract fun startActivity()

    private var _binding: ActivityMainBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        Log.i(tag, "onCreate")
        super.onCreate(savedInstanceState)
        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.activityLayout.setBackgroundColor(color)
        binding.title.text = this::class.simpleName
        binding.buttonNext.setOnClickListener {
            startActivity()
        }
    }

    override fun onStart() {
        Log.i(tag, "onStart")
        super.onStart()
    }

    override fun onResume() {
        Log.i(tag, "onResume")
        super.onResume()
    }

    override fun onPause() {
        Log.i(tag, "onPause")
        super.onPause()
    }

    override fun onStop() {
        Log.i(tag, "onStop")
        super.onStop()
    }

    override fun onDestroy() {
        binding.buttonNext.setOnClickListener(null)
        _binding = null
        Log.i(tag, "onDestroy")
        super.onDestroy()
    }

}