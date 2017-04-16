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
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.audacityit.audacity.R;
import com.audacityit.audacity.adapter.MethodologyAdapter;
import com.audacityit.audacity.api.response_layer.methodology.Methodologycontent;
import com.audacityit.audacity.api.response_layer.methodology.Methodologyitem;
import com.audacityit.audacity.settings.CPSetting;
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

import static com.audacityit.audacity.util.UtilMethods.dpToPx;
import static com.audacityit.audacity.util.UtilMethods.getWindowSize;

/**
 * Created by Tushar Saha on 12/4/15.
 * Audacity IT Solutions Ltd.
 */
public class MethodologyFragment extends Fragment {

    private static final double APP_BAR_HEIGHT_RATIO = 0.40;
    private static final double LOGO_HEIGHT_RATIO = 0.25;
    private static final int ROW_VERTICAL_SPACING = 20;
    private static NavHandlerListener navHandlerListener = null;
    private RecyclerView methodologyRv = null;
    private AppBarLayout appBarLayout = null;
    private CollapsingToolbarLayout collapsingToolbar = null;
    private RelativeLayout parallaxView;
    private ImageView companyLogoImgView = null;
    private List<Methodologyitem> methodologyList = null;
    private Methodologycontent methodologycontent;
    private Context mContext;
    private AdView adView = null;

    public MethodologyFragment() {

    }

    public static MethodologyFragment newInstance(NavHandlerListener listener) {
        MethodologyFragment fragment = new MethodologyFragment();
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
        View view = inflater.inflate(R.layout.fragment_methodology, container, false);
        Toolbar toolbar = (Toolbar) view.findViewById(R.id.toolbarMethodology);
        toolbar.setNavigationIcon(R.drawable.ic_navigation);
        adView = (AdView)view.findViewById(R.id.adMob);
        mContext = container.getContext();

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
        collapsingToolbar.setTitle(getString(R.string.nav_text_methodology));
        collapsingToolbar.setTitleEnabled(true);
        parallaxView = (RelativeLayout) view.findViewById(R.id.parallaxView);
        companyLogoImgView = (ImageView) view.findViewById(R.id.companyLogoImgView);
        companyLogoImgView.getLayoutParams().height = (int) (getWindowSize(getActivity()).y *
                LOGO_HEIGHT_RATIO);
        methodologyRv = ((RecyclerView) view.findViewById(R.id.methodologyRv));
        methodologyRv.addItemDecoration(new SpacesItemDecoration(ROW_VERTICAL_SPACING));
        methodologyRv.setHasFixedSize(true);
        methodologyRv.setLayoutManager(new LinearLayoutManager(getActivity()));
        return view;
    }

    private void getMethodologyData() {
        DBHelper dbHelper = CPSetting.getInstance().getDbHelper();
        methodologyList = new ArrayList<>();
        methodologyList = dbHelper.getMethodology().getMethodologyitems();
        methodologycontent = dbHelper.getMethodology().getMethodologycontent();
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
            } else if (parent.getChildAdapterPosition(view) ==
                    parent.getAdapter().getItemCount() - 1) {
                outRect.bottom = verticalSpace;
            } else {
                outRect.bottom = verticalSpace;
            }
        }
    }
    @Override
    public void onResume() {
        AppInitializer.getInstance().trackScreenView("Methodology Screen");
        getMethodologyData();
        initializeAdMob();
        Glide.with(mContext).load(methodologycontent.getLogo()).skipMemoryCache(true).diskCacheStrategy(DiskCacheStrategy.SOURCE).listener(new RequestListener<String, GlideDrawable>() {
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

        Glide.with(mContext).load(methodologycontent.getBackground()).skipMemoryCache(true).diskCacheStrategy(DiskCacheStrategy.SOURCE).listener(new RequestListener<String, GlideDrawable>() {
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
        }).into(new com.audacityit.audacity.settings.ViewBackgroundTarget.GlideDrawableViewBackgroundTarget(parallaxView));


        methodologyRv.setAdapter(new MethodologyAdapter(getActivity(), methodologyList));
        super.onResume();
    }

}