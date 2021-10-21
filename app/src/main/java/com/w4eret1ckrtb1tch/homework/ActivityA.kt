package com.w4eret1ckrtb1tch.homework

import android.graphics.Color

class ActivityA : BaseActivity() {

    override val color: Int
        get() = intent.getIntExtra(getKey<ActivityA>(), Color.RED)

    override fun startActivity() {
        val intent = newIntent<ActivityB>(context = this, color = Color.YELLOW)
        startActivity(intent)
    }

}