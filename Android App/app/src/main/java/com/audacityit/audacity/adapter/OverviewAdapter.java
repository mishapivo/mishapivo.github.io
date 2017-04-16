package com.audacityit.audacity.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.audacityit.audacity.R;
import com.audacityit.audacity.api.response_layer.overview.Overviewitem;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;

import java.util.List;

/**
 * Created by Tushar Saha on 12/2/15.
 * Audacity IT Solutions Ltd.
 */
public class OverviewAdapter extends RecyclerView.Adapter<OverviewAdapter.OverviewRowHolder> {
    private Context mContext;
    private List<Overviewitem> basicInfoList;

    public OverviewAdapter(Context context, List<Overviewitem> basicInfoList) {
        mContext = context;
        this.basicInfoList = basicInfoList;
    }

    @Override
    public OverviewRowHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.layout_overview_item, null);
        OverviewRowHolder vh = new OverviewRowHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(OverviewRowHolder holder, int position) {

        Glide.with(mContext).load(basicInfoList.get(position).getIcon()).skipMemoryCache(true).diskCacheStrategy(DiskCacheStrategy.SOURCE).listener(new RequestListener<String, GlideDrawable>() {
            @Override
            public boolean onException(Exception e, String model, Target<GlideDrawable> target, boolean isFirstResource) {
                //Toast.makeText(mContext, "Image is not available in cache. Need to fetch from server", Toast.LENGTH_LONG).show();

                return false;
            }

            @Override
            public boolean onResourceReady(GlideDrawable resource, String model, Target<GlideDrawable> target, boolean isFromMemoryCache, boolean isFirstResource) {
                //Toast.makeText(mContext, "Load Completed and loading from cache memory", Toast.LENGTH_LONG).show();
                return false;
            }
        }).into(holder.overviewItemIcon);

        holder.overviewItemTitle.setText(basicInfoList.get(position).getTitle());
        holder.overviewItemBody.setText(basicInfoList.get(position).getDescription());
        if (position == getItemCount() - 1) {
            holder.overviewItemBody.append("\n\n\n");
        }
    }

    @Override
    public int getItemCount() {
        return basicInfoList.size();
    }

    public class OverviewRowHolder extends RecyclerView.ViewHolder {
        private ImageView overviewItemIcon;
        private TextView overviewItemTitle;
        private TextView overviewItemBody;

        public OverviewRowHolder(View view) {
            super(view);
            overviewItemIcon = (ImageView) view.findViewById(R.id.overviewItemIcon);
            overviewItemTitle = (TextView) view.findViewById(R.id.overviewItemTitle);
            overviewItemBody = (TextView) view.findViewById(R.id.overviewItemBody);
        }
    }
}
