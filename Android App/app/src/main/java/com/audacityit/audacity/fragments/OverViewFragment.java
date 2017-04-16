package com.audacityit.audacity.fragments;


import android.content.Context;
import android.graphics.Rect;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.audacityit.audacity.R;
import com.audacityit.audacity.adapter.OverviewAdapter;
import com.audacityit.audacity.api.response_layer.overview.Overviewcontent;
import com.audacityit.audacity.api.response_layer.overview.Overviewitem;
import com.audacityit.audacity.settings.CPSetting;
import com.audacityit.audacity.settings.ViewBackgroundTarget;
import com.audacityit.audacity.tracker.AppInitializer;
import com.audacityit.audacity.util.DBHelper;
import com.audacityit.audacity.util.NavHandlerListener;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

import static com.audacityit.audacity.util.UtilMethods.dpToPx;
import static com.audacityit.audacity.util.UtilMethods.getDrawableIdFromFileName;
import static com.audacityit.audacity.util.UtilMethods.getWindowSize;
import static com.audacityit.audacity.util.UtilMethods.isTablet;
import static com.audacityit.audacity.util.UtilMethods.spToPx;


public class OverViewFragment extends Fragment {

    private static final double APP_BAR_HEIGHT_RATIO = 0.60;
    private static final double COUNTER_VIEW_HEIGHT_RATIO = 0.10;
    private static final double TAB_COMPANY_LOGO_W_H_RATIO = 4;
    private static final int TAB_COMPANY_NAME_TEXT_SIZE = 22;
    private static final int TAB_COMPANY_TAG_TEXT_SIZE = 15;
    private static final int TAB_COUNTER_TEXT_SIZE = 20;
    private static final int ROW_VERTICAL_SPACING = 20;
    private static NavHandlerListener navHandlerListener = null;
    private RecyclerView overviewRv = null;
    private AppBarLayout appBarLayout = null;
    private CollapsingToolbarLayout collapsingToolbar = null;
    private Overviewcontent overviewcontent;
    private List<Overviewitem> basicInfoList = null;
    private RelativeLayout parallaxView;
    private CircleImageView companyLogoImgView;
    private TextView companyNameTv;
    private TextView companyTagTv;
    private TextView projectCompleteTv;
    private TextView projectQueueTv;
    private TextView projectWaitingTv;
    private Toolbar toolbar;
    private Context mContext;
    private AdView adView = null;

    public OverViewFragment() {

    }

    public static OverViewFragment newInstance(NavHandlerListener listener) {
        OverViewFragment fragment = new OverViewFragment();
        navHandlerListener = listener;
        return fragment;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_over_view, container, false);
        toolbar = (Toolbar) view.findViewById(R.id.toolbarOverview);
        toolbar.setNavigationIcon(R.drawable.ic_navigation);
        adView = (AdView)view.findViewById(R.id.adMob);
        mContext = container.getContext();

        getOverViewData();
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navHandlerListener.onNavOpenRequested();
            }
        });

        appBarLayout = (AppBarLayout) view.findViewById(R.id.appbar_container);
        appBarLayout.getLayoutParams().height = (int) (getWindowSize(getActivity()).y *
                APP_BAR_HEIGHT_RATIO);
        collapsingToolbar = (CollapsingToolbarLayout) view.findViewById(R.id.collapsing_container);
        collapsingToolbar.setTitleEnabled(true);
        ((LinearLayout) view.findViewById(R.id.counterView)).
                getLayoutParams().height = (int) (getWindowSize(getActivity()).y *
                COUNTER_VIEW_HEIGHT_RATIO);

        parallaxView = ((RelativeLayout) view.findViewById(R.id.parallaxView));
        companyLogoImgView = ((CircleImageView) view.findViewById(R.id.companyLogoImgView));
        companyNameTv = ((TextView) view.findViewById(R.id.companyNameTv));
        companyTagTv = ((TextView) view.findViewById(R.id.companyTagTv));
        projectCompleteTv = ((TextView) view.findViewById(R.id.projectCompleteTv));
        projectQueueTv = ((TextView) view.findViewById(R.id.projectQueueTv));
        projectWaitingTv = ((TextView) view.findViewById(R.id.projectWaitingTv));

        if (isTablet(getActivity())) {
            companyLogoImgView.getLayoutParams().width = (int) (getResources().
                    getDisplayMetrics().widthPixels / TAB_COMPANY_LOGO_W_H_RATIO);
            companyLogoImgView.getLayoutParams().height = (int) (getResources().
                    getDisplayMetrics().widthPixels / TAB_COMPANY_LOGO_W_H_RATIO);
            companyNameTv.setTextSize(spToPx(getActivity(), TAB_COMPANY_NAME_TEXT_SIZE));
            companyTagTv.setTextSize(spToPx(getActivity(), TAB_COMPANY_TAG_TEXT_SIZE));
            projectCompleteTv.setTextSize(spToPx(getActivity(), TAB_COUNTER_TEXT_SIZE));
            projectQueueTv.setTextSize(spToPx(getActivity(), TAB_COUNTER_TEXT_SIZE));
            projectWaitingTv.setTextSize(spToPx(getActivity(), TAB_COUNTER_TEXT_SIZE));
        }

        overviewRv = ((RecyclerView) view.findViewById(R.id.overviewRv));
        overviewRv.addItemDecoration(new SpacesItemDecoration(ROW_VERTICAL_SPACING));
        overviewRv.setHasFixedSize(true);
        return view;
    }

    private void getOverViewData() {
        DBHelper dbHelper = CPSetting.getInstance().getDbHelper();
        basicInfoList = new ArrayList<>();
        basicInfoList = dbHelper.getOverview().getOverviewitems();
    }

    @Override
    public void onResume() {
        DBHelper dbHelper = CPSetting.getInstance().getDbHelper();
        overviewcontent = dbHelper.getOverview().getOverviewcontent();
        //collapsingToolbar.setTitle(overviewcontent.getTitle());
        toolbar.setTitle(overviewcontent.getTitle());
        initializeAdMob();
        AppInitializer.getInstance().trackScreenView("Overview Screen");
        getOverViewData();

        Glide.with(mContext).load(overviewcontent.getLogo()).skipMemoryCache(true).diskCacheStrategy(DiskCacheStrategy.SOURCE).listener(new RequestListener<String, GlideDrawable>() {
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
        }).into(companyLogoImgView);

        Glide.with(mContext).load(overviewcontent.getBackground()).skipMemoryCache(true).diskCacheStrategy(DiskCacheStrategy.SOURCE).listener(new RequestListener<String, GlideDrawable>() {
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
        }).into(new ViewBackgroundTarget.GlideDrawableViewBackgroundTarget(parallaxView));

        companyNameTv.setText(overviewcontent.getTitle());
        companyTagTv.setText(overviewcontent.getMoto());

        projectCompleteTv.setText(overviewcontent.getCount1no()+" \n"+overviewcontent.getCount1text());
        projectQueueTv.setText(overviewcontent.getCount2no()+" \n"+overviewcontent.getCount2text());
        projectWaitingTv.setText(overviewcontent.getCount3no()+" \n"+overviewcontent.getCount3text());
        overviewRv.setLayoutManager(new LinearLayoutManager(getActivity()));
        overviewRv.setAdapter(new OverviewAdapter(getActivity(), basicInfoList));
        super.onResume();

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

        public SpacesItemDecoration(int verticalSpace) {
            this.verticalSpace = dpToPx(getActivity(), verticalSpace);
        }

        @Override
        public void getItemOffsets(Rect outRect, View view, RecyclerView parent,
                                   RecyclerView.State state) {
//            outRect.left = 45;
            if (parent.getChildAdapterPosition(view) == 0) {
                outRect.top = verticalSpace;
                outRect.bottom = verticalSpace;
            } else if (parent.getChildAdapterPosition(view) == parent.getAdapter().getItemCount() - 1) {
                outRect.bottom = verticalSpace;
            } else {
                outRect.bottom = verticalSpace;
            }
        }
    }


}
