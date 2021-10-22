package com.w4eret1ckrtb1tch.homework.presentation.list

import android.content.res.Resources
import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView

class MarginsItemDecoration(private val marginToDp: Int) : RecyclerView.ItemDecoration() {

    private val Int.toPx: Int get() = (this * Resources.getSystem().displayMetrics.density).toInt()

    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        super.getItemOffsets(outRect, view, parent, state)
        outRect.top = marginToDp.toPx
        outRect.left = marginToDp.toPx
        outRect.right = marginToDp.toPx
    }
}