package com.w4eret1ckrtb1tch.homework

import android.content.Context
import android.content.Intent
import kotlin.random.Random

class ActivityC : BaseActivity() {

    override val color: Int
        get() = intent.getIntExtra(COLOR_EXTRA, 0)

    override fun startActivity() {
        val intent = ActivityA.newIntent(this, Random.nextInt()).apply {
            flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
        }
        startActivity(intent)
    }

    companion object {
        fun newIntent(context: Context, color: Int) =
            Intent(context, ActivityC::class.java).putExtra(COLOR_EXTRA, color)

        private const val COLOR_EXTRA = "com.homework.ActivityC.COLOR_EXTRA"
    }
}