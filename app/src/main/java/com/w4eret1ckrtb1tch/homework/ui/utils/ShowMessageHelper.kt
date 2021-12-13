package com.w4eret1ckrtb1tch.homework.ui.utils

import android.view.View
import androidx.fragment.app.Fragment
import com.google.android.material.snackbar.Snackbar
import com.w4eret1ckrtb1tch.homework.R

fun Fragment.showMessage(description: String, view: View, dataLoad: (() -> Unit)?) {
    val snackBar = Snackbar.make(view, description, Snackbar.LENGTH_LONG)
    snackBar
        .setMaxInlineActionWidth(resources.getDimensionPixelSize(R.dimen.design_snackbar_action_inline_max_width))
        .setAction(R.string.ok) { dataLoad?.invoke(); snackBar.dismiss() }
        .show()
}
