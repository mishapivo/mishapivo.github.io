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
import com.audacityit.audacity.adapter.ClientsAdapter;
import com.audacityit.audacity.api.response_layer.client.Client_;
import com.audacityit.audacity.settings.CPSetting;
import com.audacityit.audacity.settings.SpeedyLinearLayoutManager;
import com.audacityit.audacity.tracker.AppInitializer;
import com.audacityit.audacity.util.DBHelper;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;

import java.util.ArrayList;
import java.util.List;

import static com.audacityit.audacity.util.UtilMethods.dpToPx;

/**
 * Created by Tushar Saha on 12/3/15.
 * Audacity IT Solutions Ltd.
 */
public class ClientsFragment extends Fragment implements ClientsAdapter.OnItemClickListener{
    //    private static final String ARG_PARAM1 = "param1";
    private static final int ROW_LEFT_SPACING = 10;
    private static final int ROW_RIGHT_SPACING = 10;
    private static final int ROW_BOTTOM_SPACING = 5;
    private RecyclerView rvClients;
    private List<Client_> projectList;
    private AdView adView = null;
    private Context mContext;
    public ClientsFragment() {

    }

//    public static ClientsFragment newInstance(String param) {
//        ClientsFragment fragment = new ClientsFragment();
//        Bundle args = new Bundle();
//        args.putString(ARG_PARAM1, param);
//        fragment.setArguments(args);
//        return fragment;
//    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_clients, container, false);
        rvClients = (RecyclerView) view.findViewById(R.id.rvClients);
        adView = (AdView)view.findViewById(R.id.adMob);
        rvClients.setHasFixedSize(true);
        mContext = container.getContext();
        rvClients.addItemDecoration(new SpacesItemDecoration(ROW_LEFT_SPACING,
                ROW_RIGHT_SPACING,
                ROW_BOTTOM_SPACING));
        //rvClients.setLayoutManager(new LinearLayoutManager(getActivity()));
        rvClients.setLayoutManager(new SpeedyLinearLayoutManager(mContext));
        return view;
    }

    @Override
    public void onItemClick(int position) {
        if(position == projectList.size()-1 && projectList.get(position).getIsExpanded() == 1) {
            new ClientsFragment.ExpandTask().execute();
        }
    }

    private class SpacesItemDecoration extends RecyclerView.ItemDecoration {
        private int leftSpaceing;
        private int rightSpaceing;
        private int bottomSpacing;

        public SpacesItemDecoration(int leftSpacing, int rightSpacing, int bottomSpacing) {
            this.leftSpaceing = dpToPx(getActivity(), leftSpacing);
            this.rightSpaceing = dpToPx(getActivity(), rightSpacing);
            this.bottomSpacing = dpToPx(getActivity(), bottomSpacing);
        }

        @Override
        public void getItemOffsets(Rect outRect, View view, RecyclerView parent,
                                   RecyclerView.State state) {
            outRect.left = leftSpaceing;
            outRect.right = rightSpaceing;
            if (parent.getChildAdapterPosition(view) != parent.getAdapter().getItemCount() - 1) {
                outRect.bottom = bottomSpacing;
            }
        }
    }

    @Override
    public void onResume() {
        AppInitializer.getInstance().trackScreenView("Clients Screen");
        getClientData();
        initializeAdMob();
        rvClients.setAdapter(new ClientsAdapter(getActivity(), projectList, this));
        super.onResume();
    }

    private void getClientData() {

        DBHelper dbHelper = CPSetting.getInstance().getDbHelper();
        projectList = new ArrayList<>();
        projectList = dbHelper.getClient();
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

    class ExpandTask extends AsyncTask<String,String,String> {

        @Override
        protected String doInBackground(String... params) {

            try {
                Thread.sleep(0);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            rvClients.smoothScrollToPosition(projectList.size()-1);
        }
    }
}
