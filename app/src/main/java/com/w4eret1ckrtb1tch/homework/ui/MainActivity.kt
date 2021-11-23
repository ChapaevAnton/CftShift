package com.w4eret1ckrtb1tch.homework.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.add
import androidx.fragment.app.commit
import com.w4eret1ckrtb1tch.homework.R
import com.w4eret1ckrtb1tch.homework.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private var binding: ActivityMainBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding?.root)

        if (savedInstanceState == null) {
            supportFragmentManager.commit {
                setReorderingAllowed(true)
                add<ImageLoadFragment>(R.id.fragment_container)
            }
        }
    }

    override fun onDestroy() {
        binding = null
        super.onDestroy()
    }
}