package com.example.anilife.util

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView

class CardItemDecoration constructor(private val space : Int): RecyclerView.ItemDecoration(){



    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        outRect.bottom = space
        outRect.left = space / 3
        outRect.right = space / 3
        outRect.top = space
    }
}