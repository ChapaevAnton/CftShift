package com.w4eret1ckrtb1tch.homework

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.w4eret1ckrtb1tch.homework.databinding.FragmentSingleBinding

class SingleFragment : Fragment(R.layout.fragment_single) {

    private var binding: FragmentSingleBinding? = null
    private val handler by lazy { Handler(Looper.getMainLooper()) }
    private var secondTimer: Timer? = null
    private var timeSecond = 0L

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSingleBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        savedInstanceState?.getLong(TIMER_KEY)?.let {
            timeSecond = it
            startTimer()
        }
        binding?.apply {
            timer.text = timeSecond.toString()
            start.setOnClickListener {
                startTimer()
            }
            stop.setOnClickListener {
                stopTimer()
            }
        }
    }

    private fun startTimer() {
        secondTimer = Timer(timeSecond) { second, isRunning ->
            if (isRunning) {
                timeSecond = second
                handler.post {
                    binding?.timer?.text = timeSecond.toString()
                }
            }
        }
        secondTimer?.start()
    }

    private fun stopTimer() {
        secondTimer?.cancel()
        secondTimer = null
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putLong(TIMER_KEY, timeSecond)
    }

    override fun onDestroyView() {
        stopTimer()
        binding = null
        super.onDestroyView()
    }

    companion object {
        private const val TIMER_KEY = "com.homework.SingleFragment.TimerKey"
    }
}