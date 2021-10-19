package com.w4eret1ckrtb1tch.homework

import android.content.Context
import android.content.Intent
import kotlin.random.Random

class ActivityB : BaseActivity() {

    override val color: Int
        get() = intent.getIntExtra(COLOR_EXTRA, 0)

    override fun startActivity() {
        val intent = ActivityC.newIntent(this, Random.nextInt())
        startActivity(intent)
    }

    companion object {
        fun newIntent(context: Context, color: Int): Intent =
            Intent(context, ActivityB::class.java).putExtra(COLOR_EXTRA, color)

        private const val COLOR_EXTRA = "com.homework.ActivityB.COLOR_EXTRA"
    }
}