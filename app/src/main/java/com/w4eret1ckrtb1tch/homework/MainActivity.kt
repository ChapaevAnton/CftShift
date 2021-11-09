package com.w4eret1ckrtb1tch.homework

import android.os.Bundle
import androidx.fragment.app.add
import androidx.fragment.app.commit
import com.w4eret1ckrtb1tch.homework.di.ActivityScope
import com.w4eret1ckrtb1tch.homework.presentation.ScreenFragment
import dagger.android.support.DaggerAppCompatActivity

@ActivityScope
class MainActivity : DaggerAppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
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