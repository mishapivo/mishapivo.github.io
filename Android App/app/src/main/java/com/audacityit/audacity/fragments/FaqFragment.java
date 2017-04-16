package com.audacityit.audacity.fragments;

import android.content.Context;
import android.graphics.Rect;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.audacityit.audacity.R;
import com.audacityit.audacity.adapter.FaqAdapter;
import com.audacityit.audacity.api.response_layer.faq.Faq_;
import com.audacityit.audacity.settings.CPSetting;
import com.audacityit.audacity.tracker.AppInitializer;
import com.audacityit.audacity.util.DBHelper;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Tushar Saha on 12/4/15.
 * Audacity IT Solutions Ltd.
 */
public class FaqFragment extends Fragment implements FaqAdapter.OnItemClickListener {
    //    private static final String ARG_PARAM1 = "param1";
    private static final int ROW_LEFT_SPACING = 10;
    private static final int ROW_RIGHT_SPACING = 10;
    private static final int ROW_BOTTOM_SPACING = 5;
    private RecyclerView rvFaqs;
    private List<Faq_> faqList;
    private AdView adView = null;
    private Context mContext;

    public FaqFragment() {

    }

//    public static FaqFragment newInstance(String param) {
//        FaqFragment fragment = new FaqFragment();
//        Bundle args = new Bundle();
//        args.putString(ARG_PARAM1, param);
//        fragment.setArguments(args);
//        return fragment;
//    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_faq, container, false);
        rvFaqs = (RecyclerView) view.findViewById(R.id.rvFaq);
        rvFaqs.setHasFixedSize(true);
        adView = (AdView)view.findViewById(R.id.adMob);
        mContext = container.getContext();
        rvFaqs.addItemDecoration(new SpacesItemDecoration(ROW_LEFT_SPACING,
                ROW_RIGHT_SPACING,
                ROW_BOTTOM_SPACING));
        rvFaqs.setLayoutManager(new LinearLayoutManager(getActivity()));
        getFaqData();
        if (faqList != null) {
            rvFaqs.setAdapter(new FaqAdapter(getActivity(), faqList, this));
        }
        return view;
    }

    @Override
    public void onItemClick(int position) {
        if(position == faqList.size()-1 && faqList.get(position).isShowingAnswer()) {
            new ExpandTask().execute();
        }
    }

    private class SpacesItemDecoration extends RecyclerView.ItemDecoration {
        private int leftSpaceing;
        private int rightSpaceing;
        private int bottomSpacing;

        public SpacesItemDecoration(int leftSpacing, int rightSpacing, int bottomSpacing) {
            this.leftSpaceing = leftSpacing;
            this.rightSpaceing = rightSpacing;
            this.bottomSpacing = bottomSpacing;
        }

        @Override
        public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
            outRect.left = leftSpaceing;
            outRect.right = rightSpaceing;
            if (parent.getChildAdapterPosition(view) != parent.getAdapter().getItemCount() - 1) {
                outRect.bottom = bottomSpacing;
            }
        }
    }

    private void getFaqData() {
        DBHelper dbHelper = CPSetting.getInstance().getDbHelper();
        faqList = new ArrayList<>();
        faqList = dbHelper.getFaq();
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
        AppInitializer.getInstance().trackScreenView("Faq Screen");
        initializeAdMob();
        super.onResume();
    }

    class ExpandTask extends AsyncTask<String,String,String> {

        @Override
        protected String doInBackground(String... params) {

            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            rvFaqs.scrollToPosition(faqList.size()-1);
        }
    }

}