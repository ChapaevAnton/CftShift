package com.w4eret1ckrtb1tch.homework.ui.transition

import android.transition.ChangeBounds
import android.transition.Fade
import android.transition.TransitionSet

class FadeTransition(durationOut: Long, durationIn: Long) : TransitionSet() {

    init {
        ordering = ORDERING_SEQUENTIAL
        addTransition(Fade(Fade.OUT).setDuration(durationOut))
            .addTransition(ChangeBounds())
            .addTransition(Fade(Fade.IN).setDuration(durationIn))
    }
}