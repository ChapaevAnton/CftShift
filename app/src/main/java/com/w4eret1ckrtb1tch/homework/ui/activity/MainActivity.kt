package com.w4eret1ckrtb1tch.homework.ui.activity

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.snackbar.Snackbar
import com.w4eret1ckrtb1tch.homework.R
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun showMessage(description: String, view: View, dataLoad: (() -> Unit)?) {
        val snackBar = Snackbar.make(view, description, Snackbar.LENGTH_LONG)
        snackBar
            .setMaxInlineActionWidth(resources.getDimensionPixelSize(R.dimen.design_snackbar_action_inline_max_width))
            .setAction(R.string.ok) { dataLoad?.invoke(); snackBar.dismiss() }
            .show()
    }
}