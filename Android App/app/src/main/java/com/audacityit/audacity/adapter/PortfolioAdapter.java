package com.audacityit.audacity.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.OvershootInterpolator;
import android.widget.ImageView;
import android.widget.TextView;

import com.audacityit.audacity.R;
import com.audacityit.audacity.api.response_layer.portfolio.Portfolio_;
import com.audacityit.audacity.settings.PortfolioFilter;
import com.audacityit.audacity.util.AITSRecyclerView;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;

import java.util.List;

/**
 * Created by Tushar Saha on 11/28/15.
 * Audacity IT Solutions Ltd.
 */
public class PortfolioAdapter extends AITSRecyclerView.Adapter<PortfolioAdapter.PortfolioListRowHolder>
        implements View.OnClickListener, View.OnLongClickListener {

    private static final OvershootInterpolator OVERSHOOT_INTERPOLATOR = new OvershootInterpolator(4);
    //    private static final int ROW_HEIGHT_TABLET = 350;
//    private static final int ROW_HEIGHT_PHONE = 230;
    private Context mContext = null;
    private List<Portfolio_> projectList;
    private int lastPosition = -1;
    private boolean isDataChanged = false;
    private PortfolioFilter filter;

    public PortfolioAdapter(Context context, List<Portfolio_> projectList) {
        mContext = context;
        this.projectList = projectList;
        filter = new PortfolioFilter(projectList,this);
    }

    @Override
    public PortfolioListRowHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.layout_portfolio_item, null);
        PortfolioListRowHolder vh = new PortfolioListRowHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(final PortfolioListRowHolder holder, final int position) {
        if (!isDataChanged) {
            Animation animation = AnimationUtils.loadAnimation(mContext,
                    (position > lastPosition) ? R.anim.up_from_bottom
                            : R.anim.down_from_top);
            holder.itemView.startAnimation(animation);
            lastPosition = position;
        }

        holder.headingTv.setText(projectList.get(position).getTitle());
        String tag = "";
        for(int i=0 ; i<projectList.get(position).getTags().size() ; i++){
            tag+=projectList.get(position).getTags().get(i).getTag();
            if(i!=projectList.get(position).getTags().size()-1){
                tag+=",";
            }
        }
        holder.tagTv.setText(tag);

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
        }).into(holder.portfolioImageView);


        holder.portfolioImageView.setOnLongClickListener(this);
        if (position == projectList.size() - 1) {
            isDataChanged = false;
        }
    }


    @Override
    public int getItemCount() {
        return projectList.size();
    }


    // set adapter filtered list
    public void setList(List<Portfolio_> list) {
        this.projectList = list;
    }
    //call when you want to filter
    public void filterList(String text) {
        filter.filter(text);
    }
    //get length
    public int filterListSize() {
        return projectList.size();
    }

    @Override
    public void onClick(View v) {
    }

    @Override
    public boolean onLongClick(View v) {
        return false;
    }

    public class PortfolioListRowHolder extends AITSRecyclerView.ViewHolder {
        private ImageView portfolioImageView;
        private TextView headingTv;
        private TextView tagTv;

        public PortfolioListRowHolder(View view) {
            super(view);

            portfolioImageView = (ImageView) view.findViewById(R.id.portfolioImageView);
            headingTv = (TextView) view.findViewById(R.id.headingTv);
            tagTv = (TextView) view.findViewById(R.id.tagTv);
        }
    }
}
