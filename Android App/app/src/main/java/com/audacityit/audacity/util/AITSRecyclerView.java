package com.audacityit.audacity.util;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by Tushar Saha on 12/15/15.
 * Audacity IT Solutions Ltd.
 */
public class AITSRecyclerView extends RecyclerView {
    private View emptyView;

    public AITSRecyclerView(Context context) {
        super(context);
    }

    public AITSRecyclerView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public AITSRecyclerView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    @Override
    public void setAdapter(Adapter adapter) {
        super.setAdapter(adapter);
        if (adapter != null) {
            adapter.registerAdapterDataObserver(emptyObserver);
        }
        emptyObserver.onChanged();
    }

    public void setEmptyView(View emptyView) {
        this.emptyView = emptyView;
    }

    private AdapterDataObserver emptyObserver = new AdapterDataObserver() {

        @Override
        public void onChanged() {
            Adapter<?> adapter = getAdapter();
            if (adapter != null && emptyView != null) {
                if (adapter.getItemCount() == 0) {
                    emptyView.setVisibility(View.VISIBLE);
                    AITSRecyclerView.this.setVisibility(View.GONE);
                } else {
                    emptyView.setVisibility(View.GONE);
                    AITSRecyclerView.this.setVisibility(View.VISIBLE);
                }
            }

        }
    };
}

