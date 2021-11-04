package com.w4eret1ckrtb1tch.homework.ui.adapters

import android.content.res.Resources
import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView

class RecyclerDecoration(private val sidePagingDp: Int, private val bottomPagingDp: Int) :
    RecyclerView.ItemDecoration() {

    private val Int.convertPx: Int
        get() = (this * Resources.getSystem().displayMetrics.density).toInt()

    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        super.getItemOffsets(outRect, view, parent, state)
        outRect.left = sidePagingDp.convertPx
        outRect.right = sidePagingDp.convertPx
        outRect.bottom = bottomPagingDp.convertPx
    }
}