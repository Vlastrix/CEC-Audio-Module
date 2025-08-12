package com.example.cecaudiomodule.ui.adapters

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView

class GridSpacingItemDecoration(
    private val spanCount: Int,
    private val spacingPx: Int,
    private val includeEdge: Boolean = true
) : RecyclerView.ItemDecoration() {

    override fun getItemOffsets(
        out: Rect, view: View, parent: RecyclerView, state: RecyclerView.State
    ) {
        val position = parent.getChildAdapterPosition(view)
        val column = position % spanCount

        if (includeEdge) {
            out.left = spacingPx - column * spacingPx / spanCount
            out.right = (column + 1) * spacingPx / spanCount
            if (position < spanCount) out.top = spacingPx
            out.bottom = spacingPx
        } else {
            out.left = column * spacingPx / spanCount
            out.right = spacingPx - (column + 1) * spacingPx / spanCount
            if (position >= spanCount) out.top = spacingPx
        }
    }
}
