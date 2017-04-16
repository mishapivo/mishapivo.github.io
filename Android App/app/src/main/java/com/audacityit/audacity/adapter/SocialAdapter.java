package com.audacityit.audacity.adapter;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.audacityit.audacity.R;
import com.audacityit.audacity.api.response_layer.social.Social_;
import com.audacityit.audacity.api.response_layer.testimonial.Testimonial_;
import com.audacityit.audacity.util.QuoteTextView;
import com.audacityit.audacity.util.UtilMethods;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;

import java.util.List;

import static com.audacityit.audacity.util.UtilMethods.browseUrl;

/**
 * Created by Tushar Saha on 12/4/15.
 * Audacity IT Solutions Ltd.
 */
public class SocialAdapter extends RecyclerView.Adapter<SocialAdapter.SocialViewHolder> {

    private Context mContext;
    private List<Social_> projectList;
    private String fbColor = "#3b5998";
    private String linkedInColor = "#077bb7";
    private String twitterColor = "#07b0ef";
    private String youtubeColor = "#ce2726";
    public SocialAdapter(Context context, List<Social_> projectList) {
        mContext = context;
        this.projectList = projectList;
    }

    @Override
    public SocialViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.layout_social, null);
        SocialViewHolder vh = new SocialViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(SocialViewHolder holder, final int position) {
        //holder.socialNameView.setText(projectList.get(position).getName());
        //Log.d(mContext+"---->---"," - "+projectList.get(position).getImage());
        Glide.with(mContext).load(projectList.get(position).getImage()).skipMemoryCache(true).diskCacheStrategy(DiskCacheStrategy.SOURCE).listener(new RequestListener<String, GlideDrawable>() {
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
        }).into(holder.socialImgView);

        holder.socialImgView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(mContext+"----->---"," - "+projectList.get(position).getUrl());
                browseUrl(mContext, projectList.get(position).getUrl());
            }
        });
    }

    @Override
    public int getItemCount() {
        return projectList.size();
    }

    static class SocialViewHolder extends RecyclerView.ViewHolder {

        private ImageView socialImgView;

        public SocialViewHolder(View itemView) {
            super(itemView);
            socialImgView = (ImageView) itemView.findViewById(R.id.socialImgView);
        }
    }
}