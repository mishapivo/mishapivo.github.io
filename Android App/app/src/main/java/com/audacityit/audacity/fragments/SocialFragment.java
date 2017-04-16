package com.audacityit.audacity.fragments;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.audacityit.audacity.R;
import com.audacityit.audacity.adapter.SocialAdapter;
import com.audacityit.audacity.api.response_layer.social.Social_;
import com.audacityit.audacity.settings.CPSetting;
import com.audacityit.audacity.tracker.AppInitializer;
import com.audacityit.audacity.util.DBHelper;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;

import java.util.ArrayList;
import java.util.List;

import static com.audacityit.audacity.util.ColorUtil.addAlpha;
import static com.audacityit.audacity.util.ColorUtil.getSocialButtonColorState;
import static com.audacityit.audacity.util.UtilMethods.isLollipopOrHigher;

/**
 * Created by Tushar Saha on 12/7/15.
 * Audacity IT Solutions Ltd.
 */
public class SocialFragment extends Fragment implements View.OnClickListener {
    private List<Social_> socialList;
    private RecyclerView rvSocial;
    private AdView adView = null;
    private Context mContext;
    public SocialFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_social, container, false);
        rvSocial = (RecyclerView) view.findViewById(R.id.rvSocial);
        adView = (AdView)view.findViewById(R.id.adMob);
        mContext = container.getContext();
        //rvSocial.setHasFixedSize(true);

        loadDataFormDB();

        return view;
    }

    private void loadDataFormDB() {
        DBHelper dbHelper = CPSetting.getInstance().getDbHelper();
        socialList = new ArrayList<>();
        socialList = dbHelper.getSocial();
        rvSocial.setLayoutManager(new LinearLayoutManager(getActivity()));
        rvSocial.setAdapter(new SocialAdapter(getActivity(), socialList));

    }

    private GradientDrawable getButtonShape(String color) {
        GradientDrawable shape;
        if (isLollipopOrHigher()) {
            shape = new GradientDrawable();
            shape.setCornerRadius(3);
            shape.setColor(getSocialButtonColorState(color));
        } else {
            shape = new GradientDrawable();
            shape.setColors(new int[]{Color.parseColor(color), Color.parseColor(addAlpha(color, 0.75f))});
            shape.setCornerRadius(3);
        }
        return shape;
    }


    @Override
    public void onClick(View v) {

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

    @Override
    public void onResume() {
        AppInitializer.getInstance().trackScreenView("Social Screen");
        rvSocial.setAdapter(new SocialAdapter(getActivity(), socialList));
        initializeAdMob();
        super.onResume();
    }
}
