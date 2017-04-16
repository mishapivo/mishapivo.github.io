package com.audacityit.audacity.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.audacityit.audacity.R;
import com.audacityit.audacity.api.response_layer.team.Team_;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

import static com.audacityit.audacity.util.UtilMethods.dpToPx;
import static com.audacityit.audacity.util.UtilMethods.isTablet;
import static com.audacityit.audacity.util.UtilMethods.spToPx;

/**
 * Created by Tushar Saha on 12/1/15.
 * Audacity IT Solutions Ltd.
 */
public class TeamAdapter extends RecyclerView.Adapter<TeamAdapter.TeamViewHolder> {

    private Context mContext;
    private List<Team_> team;

    public TeamAdapter(Context context, List<Team_> team) {
        mContext = context;
        this.team = team;
    }

    @Override
    public TeamViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_team, null);
        TeamViewHolder vh = new TeamViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(TeamViewHolder holder, int position) {
        if (isTablet(mContext)) {
            holder.memberImgView.getLayoutParams().width = dpToPx(mContext, 130);
            holder.memberImgView.getLayoutParams().height = dpToPx(mContext, 130);
            holder.memberNameView.setTextSize(spToPx(mContext, 17));
            holder.memberDesignationView.setTextSize(spToPx(mContext, 15));
        }


        Glide.with(mContext).load(team.get(position).getPhoto()).skipMemoryCache(true).diskCacheStrategy(DiskCacheStrategy.SOURCE).listener(new RequestListener<String, GlideDrawable>() {
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
        }).into(holder.memberImgView);

        holder.memberNameView.setText(team.get(position).getName());
        holder.memberDesignationView.setText(team.get(position).getDesignation());
    }

    @Override
    public int getItemCount() {
        return team.size();
    }

    class TeamViewHolder extends RecyclerView.ViewHolder {
        private CircleImageView memberImgView;
        private TextView memberNameView;
        private TextView memberDesignationView;

        public TeamViewHolder(View itemView) {
            super(itemView);
            memberImgView = (CircleImageView) itemView.findViewById(R.id.memberImgView);
            memberNameView = (TextView) itemView.findViewById(R.id.memberNameView);
            memberDesignationView = (TextView) itemView.findViewById(R.id.memberDesignationView);
        }
    }
}
