package com.audacityit.audacity.adapter;

import android.app.Activity;
import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.audacityit.audacity.R;
import com.audacityit.audacity.api.response_layer.home.Home_;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;

import java.util.List;

import static com.audacityit.audacity.util.UtilMethods.getActionBarSize;
import static com.audacityit.audacity.util.UtilMethods.getWindowSize;

/**
 * Created by Tushar Saha on 12/3/15.
 * Audacity IT Solutions Ltd.
 */
public class HomePagerAdapter extends PagerAdapter {

    private Context mContext;
    private LayoutInflater inflater;
    private ImageView sliderImageView = null;
    private TextView headlineTextView = null;
    private final static double TAG_VIEW_ASPECT_RATIO = 1d / 9d;
    private int displaySize;
    private List<Home_> projectList = null;

    public HomePagerAdapter(Activity context, List<Home_> projectList) {
        this.mContext = context;
        inflater = LayoutInflater.from(context);
        displaySize = getWindowSize(context).y - getActionBarSize(context);
        this.projectList = projectList;
    }


    @Override
    public int getCount() {
        return projectList.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        View itemView = inflater.inflate(R.layout.layout_home, container, false);
        sliderImageView = (ImageView) itemView.findViewById(R.id.sliderImageView);
        headlineTextView = (TextView) itemView.findViewById(R.id.headingTv);
        /*Glide.with(mContext)
                .load(projectList.get(position).getBackground())
                .placeholder(R.drawable.img_place_holder)
                //.error(R.drawable.user_placeholder_error)
                .into(sliderImageView);*/

        Glide.with(mContext).load(projectList.get(position).getBackground()).skipMemoryCache(true).diskCacheStrategy(DiskCacheStrategy.SOURCE).listener(new RequestListener<String, GlideDrawable>() {
            @Override
            public boolean onException(Exception e, String model, Target<GlideDrawable> target, boolean isFirstResource) {
               // Toast.makeText(mContext, "Image is not available in cache. Need to fetch from server", Toast.LENGTH_LONG).show();

                return false;
            }

            @Override
            public boolean onResourceReady(GlideDrawable resource, String model, Target<GlideDrawable> target, boolean isFromMemoryCache, boolean isFirstResource) {
                //Toast.makeText(mContext, "Load Completed and loading from cache memory", Toast.LENGTH_LONG).show();
                return false;
            }
        }).into(sliderImageView);

        headlineTextView.setText(projectList.get(position).getTitle());
        container.addView(itemView);
        return itemView;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((RelativeLayout) object);
    }

}