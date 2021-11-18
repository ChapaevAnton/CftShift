package com.w4eret1ckrtb1tch.homework.ui.activity

import android.os.Bundle
import com.w4eret1ckrtb1tch.homework.databinding.ActivityMainBinding
import com.w4eret1ckrtb1tch.homework.presentation.utils.FragmentFactoryImpl
import com.w4eret1ckrtb1tch.homework.presentation.utils.FragmentRouter
import dagger.Lazy
import dagger.android.support.DaggerAppCompatActivity
import javax.inject.Inject

class MainActivity : DaggerAppCompatActivity() {

    private var binding: ActivityMainBinding? = null

    @Inject
    lateinit var fragmentFactory: Lazy<FragmentFactoryImpl>

    @Inject
    lateinit var fragmentRouter: Lazy<FragmentRouter>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportFragmentManager.fragmentFactory = fragmentFactory.get()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding?.root)

        if (savedInstanceState == null) {
            fragmentRouter.get().openListFragment(this, null)
        }
    }

    override fun onDestroy() {
        binding = null
        super.onDestroy()
    }
}