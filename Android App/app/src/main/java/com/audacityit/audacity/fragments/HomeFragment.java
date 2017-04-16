package com.audacityit.audacity.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.audacityit.audacity.R;
import com.audacityit.audacity.adapter.HomePagerAdapter;
import com.audacityit.audacity.api.response_layer.client.Tag;
import com.audacityit.audacity.api.response_layer.home.Home_;
import com.audacityit.audacity.settings.CPSetting;
import com.audacityit.audacity.tracker.AppInitializer;
import com.audacityit.audacity.util.DBHelper;
import com.audacityit.audacity.util.ParallaxPageTransformer;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment {
//    private static final String ARG_PARAM1 = "param1";
    private String mParam1;
    private ViewPager homeViewPager;
    private List<Tag> tagList;
    private List<Home_> projectList;
    private AdView adView = null;
    private Context mContext;

//    private ParallaxPagerTransformer parallaxPagerTransformer;

    public HomeFragment() {

    }

//    public static HomeFragment newInstance(String param) {
//        HomeFragment fragment = new HomeFragment();
//        Bundle args = new Bundle();
//        args.putString(ARG_PARAM1, param);
//        fragment.setArguments(args);
//        return fragment;
//    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        homeViewPager = (ViewPager) view.findViewById(R.id.homeViewPager);
        adView = (AdView)view.findViewById(R.id.adMob);
        mContext = container.getContext();
//        parallaxPagerTransformer = new ParallaxPagerTransformer(R.id.sliderImageView);
        ParallaxPageTransformer pageTransformer = new ParallaxPageTransformer()
                .addViewToParallax(new ParallaxPageTransformer.ParallaxTransformInformation(R.id.sliderImageView, 2, 2));

        homeViewPager.setPageTransformer(true, pageTransformer);

        return view;
    }

    @Override
    public void onResume() {
        AppInitializer.getInstance().trackScreenView("Home Screen");
        getHomeData();
        homeViewPager.setAdapter(new HomePagerAdapter(getActivity(), projectList));

        initializeAdMob();
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

    private void getHomeData() {
        DBHelper dbHelper = CPSetting.getInstance().getDbHelper();
        projectList = new ArrayList<Home_>();
        projectList = dbHelper.getHome();
    }
}
