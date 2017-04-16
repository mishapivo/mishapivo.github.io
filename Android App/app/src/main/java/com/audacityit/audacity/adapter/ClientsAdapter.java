package com.audacityit.audacity.adapter;

import android.animation.Animator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.LinearInterpolator;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.audacityit.audacity.R;
import com.audacityit.audacity.api.response_layer.client.Client_;
import com.audacityit.audacity.util.TagGroup;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

import static com.audacityit.audacity.util.UtilMethods.browseUrl;

/**
 * Created by Tushar Saha on 12/3/15.
 * Audacity IT Solutions Ltd.
 */
public class ClientsAdapter extends RecyclerView.Adapter<ClientsAdapter.ClientsViewHolder> {

    private ValueAnimator mAnimator;
    private int updatePosition = -1;
    private int widthSpec = View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED);
    private int heightSpec = View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED);
    private Context mContext = null;
    private List<Client_> projectList;

    OnItemClickListener onItemClickListener;

    public ClientsAdapter(Context context, List<Client_> projectList, OnItemClickListener onItemClickListener) {
        mContext = context;
        this.projectList = projectList;
        this.onItemClickListener = onItemClickListener;
    }

    @Override
    public ClientsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_clients, null);
        ClientsViewHolder vh = new ClientsViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(final ClientsViewHolder holder, final int position) {

        if (projectList.get(position).getTags() != null) {
            holder.tagGroup.setTags(projectList.get(position).getTags());
            holder.tagGroup.setOnTagClickListener(mTagClickListener);
        }

        if (!TextUtils.isEmpty(projectList.get(position).getName())) {
            holder.companyNameTv.setText(projectList.get(position).getName());
        }
        if (!TextUtils.isEmpty(projectList.get(position).getCompany())) {
            holder.delegateNameTv.setText(projectList.get(position).getCompany());
        }
        if (!TextUtils.isEmpty(projectList.get(position).getCountry())) {
            holder.countryTag.setText(projectList.get(position).getCountry());
        }


        Glide.with(mContext).load(projectList.get(position).getLogo()).skipMemoryCache(true).diskCacheStrategy(DiskCacheStrategy.SOURCE).listener(new RequestListener<String, GlideDrawable>() {
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
        }).into(holder.clientImgView);

        holder.clientsItemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                changeVisibilityState(position, holder);
            }
        });
    }

    private void changeVisibilityState(int position, final ClientsViewHolder holder) {
        updatePosition = position;
        expand(position, holder.extendedView, holder.extendedView);
    }

    private void expand(final int position, View view , RelativeLayout relativeLayout) {
        view.measure(widthSpec, heightSpec);
        mAnimator = slideAnimator(view, 0, view.getMeasuredHeight());
        doAnimation(position, relativeLayout, 0, view.getMeasuredHeight());
    }



    private ValueAnimator slideAnimator(final View view, int start, int end) {
        ValueAnimator animator = ValueAnimator.ofInt(start, end);
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                int value = (Integer) valueAnimator.getAnimatedValue();
                ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
                layoutParams.height = value;
                view.setLayoutParams(layoutParams);
            }
        });
        return animator;
    }


    private TagGroup.OnTagClickListener mTagClickListener = new TagGroup.OnTagClickListener() {
        @Override
        public void onTagClick(String text) {

        }

        @Override
        public void onTagClick(com.audacityit.audacity.api.response_layer.client.Tag tag) {
            browseUrl(mContext, tag.getUrl());
        }

    };


    public void doAnimation(final int position, final RelativeLayout expandableContainer, int mCollpsableHeight, int mOriginalHeight) {
        ValueAnimator valueAnimator;

        if(projectList.get(position).getIsExpanded() == 0) {
            projectList.get(position).setIsExpanded(1);
            valueAnimator = ValueAnimator.ofInt(mCollpsableHeight, mOriginalHeight);
        } else {
            projectList.get(position).setIsExpanded(0);
            valueAnimator = ValueAnimator.ofInt(mOriginalHeight, mCollpsableHeight);
        }

        if(position == projectList.size()-1  && projectList.get(position).getIsExpanded() == 0){
            valueAnimator.setDuration(300);
        }else{
            valueAnimator.setDuration(300);
        }
        valueAnimator.setInterpolator(new LinearInterpolator());
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            public void onAnimationUpdate(ValueAnimator animation) {
                Integer value = (Integer) animation.getAnimatedValue();
                expandableContainer.getLayoutParams().height = value.intValue();
                expandableContainer.requestLayout();

            }
        });

        valueAnimator.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {

            }

            @Override
            public void onAnimationEnd(Animator animation) {

                if(projectList.size() -1 == position) {
                    onItemClickListener.onItemClick(position);
                }
            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        });
        valueAnimator.start();

    }

    public interface OnItemClickListener {
         void onItemClick(int position);
    }

    @Override
    public int getItemCount() {
        return projectList.size();
    }

    class ClientsViewHolder extends RecyclerView.ViewHolder {
        private LinearLayout clientsItemView;
        private RelativeLayout extendedView;
        private TextView companyNameTv;
        private TextView delegateNameTv;
        private TextView countryTag;
        private TagGroup tagGroup;
        private CircleImageView clientImgView;
        private ImageView arrowImgView;


        public ClientsViewHolder(View itemView) {
            super(itemView);
            clientsItemView = (LinearLayout) itemView.findViewById(R.id.clientsItemView);
            extendedView = (RelativeLayout) itemView.findViewById(R.id.extendedView);
            tagGroup = (TagGroup) itemView.findViewById(R.id.tagGroup);
            companyNameTv = (TextView) itemView.findViewById(R.id.companyNameTv);
            delegateNameTv = (TextView) itemView.findViewById(R.id.delegateNameTv);
            countryTag = (TextView) itemView.findViewById(R.id.countryTag);
            clientImgView = (CircleImageView) itemView.findViewById(R.id.clientImgView);
            arrowImgView = (ImageView) itemView.findViewById(R.id.arrowImgView);

            extendedView.getLayoutParams().height =0;
            extendedView.requestLayout();
        }
    }

}
