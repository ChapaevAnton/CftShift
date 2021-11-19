package com.w4eret1ckrtb1tch.homework

import android.util.Log
import java.util.concurrent.TimeUnit

class Timer(
    private var second: Long = 0,
    private val callbackTimer: (second: Long, isRunning: Boolean) -> Unit
) : Thread() {

    override fun run() {
        Log.d(TAG, "RUN")
        try {
            while (!currentThread().isInterrupted) {
                callbackTimer(second++, true)
                sleep(TimeUnit.SECONDS.toMillis(1L))
            }
        } catch (error: InterruptedException) {
            callbackTimer(second, false)
            Log.d(TAG, "STOP")
        }
    }

    fun cancel() {
        interrupt()
        Log.d(TAG, "INTERRUPT")
    }

    companion object {
        private const val TAG = "log_tag_debug"
    }
}