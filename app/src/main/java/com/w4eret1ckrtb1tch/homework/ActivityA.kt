package com.w4eret1ckrtb1tch.homework

import android.content.Context
import android.content.Intent
import kotlin.random.Random

class ActivityA : BaseActivity() {

    override val color: Int
        get() = intent.getIntExtra(COLOR_EXTRA, 0)

    override fun startActivity() {
        val intent = ActivityB.newIntent(context = this, color = Random.nextInt())
        startActivity(intent)
    }

    companion object {
        fun newIntent(context: Context, color: Int) =
            Intent(context, ActivityA::class.java).putExtra(COLOR_EXTRA, color)

        private const val COLOR_EXTRA = "com.homework.ActivityA.COLOR_EXTRA"
    }
}