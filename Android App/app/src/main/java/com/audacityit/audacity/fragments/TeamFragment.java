package com.audacityit.audacity.fragments;

import android.content.Context;
import android.graphics.Rect;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.audacityit.audacity.R;
import com.audacityit.audacity.adapter.TeamAdapter;
import com.audacityit.audacity.api.response_layer.team.Team_;
import com.audacityit.audacity.settings.CPSetting;
import com.audacityit.audacity.tracker.AppInitializer;
import com.audacityit.audacity.util.DBHelper;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;

import java.util.ArrayList;
import java.util.List;

import static com.audacityit.audacity.util.UtilMethods.dpToPx;

/**
 * Created by Tushar Saha on 12/1/15.
 * Audacity IT Solutions Ltd.
 */
public class TeamFragment extends Fragment {
    //    private static final String ARG_PARAM1 = "param1";
    private static final int VERTICAL_SPACING = 20;
    private static final int HORIZONTAL_SPACING = 8;
    private static final int START_END_SPACING_MULTIPLIER = 2;
    private RecyclerView rvTeam;
    private List<Team_> teamList;
    private static final int ROW_SPAN = 3;
    private AdView adView = null;
    private Context mContext;

    public TeamFragment() {

    }

//    public static TeamFragment newInstance(String param) {
//        TeamFragment fragment = new TeamFragment();
//        Bundle args = new Bundle();
//        args.putString(ARG_PARAM1, param);
//        fragment.setArguments(args);
//        return fragment;
//    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_team, container, false);
        rvTeam = (RecyclerView) view.findViewById(R.id.rvTeam);
        rvTeam.setHasFixedSize(true);
        adView = (AdView)view.findViewById(R.id.adMob);
        mContext = container.getContext();
        rvTeam.addItemDecoration(new SpacesItemDecoration(VERTICAL_SPACING,
                HORIZONTAL_SPACING));
        rvTeam.setItemAnimator(new DefaultItemAnimator());
        rvTeam.setLayoutManager(new GridLayoutManager(getActivity(), ROW_SPAN));
        getTeamData();
        if (teamList != null && teamList.size() > 0) {
            rvTeam.setAdapter(new TeamAdapter(getActivity(), teamList));
        }
        return view;
    }

    private void getTeamData() {
        DBHelper dbHelper = CPSetting.getInstance().getDbHelper();
        teamList = new ArrayList<>();
        teamList = dbHelper.getTeam();

    }


    private void initializeAdMob() {
        adView.setVisibility(View.GONE);
        AdRequest adRequest = new AdRequest.Builder()
                .addTestDevice("4FCD21102A4035D6B350F1665A309DAD")
                .addTestDevice("C9C1BE08010447E3331600321E32969E")
                .addTestDevice("61CC42F7B3101B978F6DFC42203FBE14")
                .addTestDevice("62014ABABBC6010A0D7B4BB885A2BCF5")
                .build();
        adView.setAdListener(new AdListener() {
            @Override
            public void onAdLoaded() {
                super.onAdLoaded();
                try{
                    getActivity().runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            if(adView!=null) {
                                adView.setVisibility(View.VISIBLE);
                            }
                        }
                    });
                }catch (Exception ex){
                    Log.d(mContext+" Error when load AdMob "," "+ex.getMessage());
                }
            }
        });
        adView.loadAd(adRequest);
    }
    private class SpacesItemDecoration extends RecyclerView.ItemDecoration {
        private int verticalSpace;
        private int horizontalSpace;

        public SpacesItemDecoration(int verticalSpace, int horizontalSpace) {
            this.verticalSpace = dpToPx(getActivity(), verticalSpace);
            this.horizontalSpace = dpToPx(getActivity(), horizontalSpace);
        }

        @Override
        public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
            outRect.top = verticalSpace;
            outRect.left = horizontalSpace;
            outRect.right = horizontalSpace;
            outRect.bottom = verticalSpace;

            // Add top margin only for the first row to avoid double space between items
            if (parent.getChildAdapterPosition(view) % ROW_SPAN <= ROW_SPAN) {
                outRect.top = verticalSpace * START_END_SPACING_MULTIPLIER;
            } else if (parent.getAdapter().getItemCount() %
                    parent.getChildAdapterPosition(view) < ROW_SPAN) {
                outRect.bottom = verticalSpace * START_END_SPACING_MULTIPLIER;
            }
        }
    }

    @Override
    public void onResume() {
        AppInitializer.getInstance().trackScreenView("Team Screen");
        initializeAdMob();
        super.onResume();
    }
}
