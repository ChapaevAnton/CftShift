package com.w4eret1ckrtb1tch.homework

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.add
import androidx.fragment.app.commit
import com.w4eret1ckrtb1tch.homework.di.FragmentComponent
import com.w4eret1ckrtb1tch.homework.presentation.ScreenFragment

class MainActivity : AppCompatActivity() {

    lateinit var fragmentComponent: FragmentComponent

    override fun onCreate(savedInstanceState: Bundle?) {
        val activityComponent = (application as App).appComponent.activityComponent().build()
        activityComponent.inject(this)
        fragmentComponent = (application as App).appComponent.fragmentComponent().build()
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (savedInstanceState == null) {
            supportFragmentManager.commit {
                setReorderingAllowed(true)
                add<ScreenFragment>(R.id.container_fragment)
            }
        }
    }
}