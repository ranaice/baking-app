package com.github.ranaice.bakingapp.ui.decorator;

import android.graphics.Rect;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

/**
 * Used to give the same margin to all recycler view items
 */
public class SameMarginDecorator extends RecyclerView.ItemDecoration {

    private int margin;

    public SameMarginDecorator(int margin) {
        this.margin = margin;
    }

    @Override
    public void getItemOffsets(@NonNull Rect outRect,
                               @NonNull View view,
                               @NonNull RecyclerView parent,
                               @NonNull RecyclerView.State state) {

        if (parent.getChildAdapterPosition(view) == 0) {
            outRect.top = margin;
        }

        outRect.left = margin;
        outRect.bottom = margin;
        outRect.right = margin;
    }

}
