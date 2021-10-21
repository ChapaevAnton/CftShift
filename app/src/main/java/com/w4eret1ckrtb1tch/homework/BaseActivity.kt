package com.w4eret1ckrtb1tch.homework

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.w4eret1ckrtb1tch.homework.databinding.ActivityMainBinding

abstract class BaseActivity : AppCompatActivity() {

    open val tag: String
        get() = "Lifecycle/${this::class.simpleName}"

    private var _binding: ActivityMainBinding? = null
    open val binding get() = _binding!!

    abstract fun createFragment(): Fragment

    override fun onCreate(savedInstanceState: Bundle?) {
        Log.i(tag, "onCreate")
        super.onCreate(savedInstanceState)
        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val fragmentManager = supportFragmentManager
        var fragment: Fragment? = fragmentManager.findFragmentById(R.id.fragment_container)
        if (fragment == null) {
            fragment = createFragment()
            fragmentManager.beginTransaction()
                .add(R.id.fragment_container, fragment)
                .commit()
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
        Log.i(tag, "onDestroy")
        _binding = null
        super.onDestroy()
    }

}