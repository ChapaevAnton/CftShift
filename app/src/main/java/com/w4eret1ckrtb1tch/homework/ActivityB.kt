package com.w4eret1ckrtb1tch.homework

import android.graphics.Color

class ActivityB : BaseActivity() {

    override val color: Int
        get() = intent.getIntExtra(getKey<ActivityB>(), 0)

    override fun startActivity() {
        val intent = newIntent<ActivityC>(this, Color.GREEN)
        startActivity(intent)
    }

}