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
import com.audacityit.audacity.api.response_layer.methodology.Methodologyitem;
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
public class MethodologyAdapter extends RecyclerView.Adapter<MethodologyAdapter.MethodologyRowHolder> {
    private Context mContext;
    private List<Methodologyitem> methodologyList;

    public MethodologyAdapter(Context context, List<Methodologyitem> methodologyList) {
        mContext = context;
        this.methodologyList = methodologyList;
    }


    @Override
    public MethodologyRowHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.layout_methodology_item, null);
        MethodologyRowHolder vh = new MethodologyRowHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(MethodologyRowHolder holder, int position) {

        Glide.with(mContext).load(methodologyList.get(position).getIcon()).skipMemoryCache(true).diskCacheStrategy(DiskCacheStrategy.SOURCE).listener(new RequestListener<String, GlideDrawable>() {
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
        }).into(holder.methodologyItemIcon);

        holder.methodologyItemTitle.setText(methodologyList.get(position).getTitle());
        holder.methodologyItemBody.setText(methodologyList.get(position).getDetails());
        if (position == getItemCount() - 1) {
            holder.methodologyItemBody.append("\n\n\n");
        }
    }

    @Override
    public int getItemCount() {
        return methodologyList.size();
    }

    public class MethodologyRowHolder extends RecyclerView.ViewHolder {
        private ImageView methodologyItemIcon;
        private TextView methodologyItemTitle;
        private TextView methodologyItemBody;

        public MethodologyRowHolder(View view) {
            super(view);
            methodologyItemIcon = (ImageView) view.findViewById(R.id.methodologyItemIcon);
            methodologyItemTitle = (TextView) view.findViewById(R.id.methodologyItemTitle);
            methodologyItemBody = (TextView) view.findViewById(R.id.methodologyItemBody);
        }
    }
}
