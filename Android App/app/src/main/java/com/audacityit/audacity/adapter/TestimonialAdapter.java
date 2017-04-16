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
import com.audacityit.audacity.api.response_layer.testimonial.Testimonial_;
import com.audacityit.audacity.util.QuoteTextView;
import com.audacityit.audacity.util.UtilMethods;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;

import java.util.List;

/**
 * Created by Tushar Saha on 12/4/15.
 * Audacity IT Solutions Ltd.
 */
public class TestimonialAdapter extends RecyclerView.Adapter<TestimonialAdapter.TestimonialViewHolder> {

    private Context mContext;
    private List<Testimonial_> projectList;

    public TestimonialAdapter(Context context, List<Testimonial_> projectList) {
        mContext = context;
        this.projectList = projectList;
    }

    @Override
    public TestimonialViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.layout_testimonial, null);
        TestimonialViewHolder vh = new TestimonialViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(TestimonialViewHolder holder, final int position) {
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
        }).into(holder.delegateImgView);

        holder.delegateNameView.setText(projectList.get(position).getName());
        holder.delegateDesignationView.setText(projectList.get(position).getProject());
        holder.quoteTextView.setText("[img src=ic_testimonial_quote_start/]" + " " +
                projectList.get(position).getFeedback() + " " +
                "[img src=ic_testimonial_quote_end/]");
        holder.webLinkImgView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UtilMethods.browseUrl(mContext, projectList.get(position).getUrl());
            }
        });
    }

    @Override
    public int getItemCount() {
        return projectList.size();
    }

    class TestimonialViewHolder extends RecyclerView.ViewHolder {

        private ImageView delegateImgView;
        private TextView delegateNameView;
        private TextView delegateDesignationView;
        private TextView quoteTextView;
        private ImageView webLinkImgView;

        public TestimonialViewHolder(View itemView) {
            super(itemView);
            delegateImgView = (ImageView) itemView.findViewById(R.id.delegateImgView);
            delegateNameView = (TextView) itemView.findViewById(R.id.delegateNameView);
            delegateDesignationView = (TextView) itemView.findViewById(R.id.delegateDesignationView);
            quoteTextView = (QuoteTextView) itemView.findViewById(R.id.quoteTextView);
            webLinkImgView = (ImageView) itemView.findViewById(R.id.webLinkImgView);
        }
    }
}
