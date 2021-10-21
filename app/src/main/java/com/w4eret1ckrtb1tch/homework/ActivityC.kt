package com.w4eret1ckrtb1tch.homework

import android.content.Intent
import android.graphics.Color

class ActivityC : BaseActivity() {

    override val color: Int
        get() = intent.getIntExtra(getKey<ActivityC>(), 0)

    override fun startActivity() {
        val intent = newIntent<ActivityA>(this, Color.BLUE).apply {
            flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
        }
        startActivity(intent)
    }

}