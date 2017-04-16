package com.audacityit.audacity.fragments;


import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.DecelerateInterpolator;
import android.widget.Button;
import android.widget.RelativeLayout;

import com.audacityit.audacity.R;
import com.audacityit.audacity.adapter.PortfolioAdapter;
import com.audacityit.audacity.api.response_layer.portfolio.Portfolio_;
import com.audacityit.audacity.settings.CPSetting;
import com.audacityit.audacity.tracker.AppInitializer;
import com.audacityit.audacity.util.AITSRecyclerView;
import com.audacityit.audacity.util.DBHelper;
import com.audacityit.audacity.util.OnDrawerOpen;
import com.audacityit.audacity.util.ToolbarShowEvent;
import com.github.clans.fab.FloatingActionButton;
import com.github.clans.fab.FloatingActionMenu;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import static com.audacityit.audacity.util.UtilMethods.getDrawableIdFromFileName;
import static com.audacityit.audacity.util.UtilMethods.isTablet;
import static com.audacityit.audacity.util.UtilMethods.loadJSONFromAsset;
import static com.audacityit.audacity.util.UtilMethods.mailTo;

/**
 * Created by Tushar Saha on 11/28/15.
 * Audacity IT Solutions Ltd.
 */
public class PortfolioFragment extends Fragment {
    private static final int ROW_SPAN_TABLET = 2;
    private AITSRecyclerView portfolioRecyclerView;
    private RelativeLayout noContentLayout;
    private List<Portfolio_> projectList;
    private List<String> platformList;
    private List<Portfolio_> filteredProjectList = new ArrayList<Portfolio_>();
    private PortfolioAdapter portfolioAdapter = null;
    private FloatingActionMenu fabMenu;
    private LinearLayoutManager mLinearLayoutManager;
    private GridLayoutManager gridLayoutManager;
    private boolean isLastItemVisible = false;
    private AdView adView = null;
    private Context mContext;
    private EventBus bus = EventBus.getDefault();
//    private AlphaAnimation alphaAnim = null;

    public PortfolioFragment() {

    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        setUpBackPressed();
        setHasOptionsMenu(true);
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mContext = container.getContext();
        View view = inflater.inflate(R.layout.fragment_portfolio, container, false);
        adView = (AdView)view.findViewById(R.id.adMob);
        portfolioRecyclerView = (AITSRecyclerView) view.findViewById(R.id.portfolioRecyclerView);
        noContentLayout = (RelativeLayout) view.findViewById(R.id.noContentLayout);
        if (isTablet(getActivity())) {
            gridLayoutManager = new GridLayoutManager(getActivity(), ROW_SPAN_TABLET);
            portfolioRecyclerView.setLayoutManager(gridLayoutManager);
            portfolioRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
                @Override
                public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                if (dy > 0) {
//                        Log.v("logCheck","scrolling down :" + gridLayoutManager.findLastVisibleItemPosition());
                    if (gridLayoutManager.findLastCompletelyVisibleItemPosition() ==
                            recyclerView.getAdapter().getItemCount() - 1) {
                        isLastItemVisible = true;
//                            hideFabButton();
                        hideFAB();
                    }

                } else {
//                        Log.v("logCheck","scrolling up :" + gridLayoutManager.findLastVisibleItemPosition());
                    if (isLastItemVisible && gridLayoutManager.findLastVisibleItemPosition() ==
                            recyclerView.getAdapter().getItemCount() - 1) {
                        isLastItemVisible = false;
//                            showFabButton();
                        showFAB();
                    }
                }
                }
            });
        } else {
            mLinearLayoutManager = new LinearLayoutManager(getActivity());
            portfolioRecyclerView.setLayoutManager(mLinearLayoutManager);
            portfolioRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
                @Override
                public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                if (dy > 0) {
//                        Log.v("logCheck","scrolling down :" + mLinearLayoutManager.findLastVisibleItemPosition());
                    if (mLinearLayoutManager.findLastCompletelyVisibleItemPosition() ==
                            recyclerView.getAdapter().getItemCount() - 1) {
                        isLastItemVisible = true;
//                            hideFabButton();
                        hideFAB();
                    }

                } else {
//                        Log.v("logCheck","scrolling up :" + mLinearLayoutManager.findLastVisibleItemPosition());
                    if (isLastItemVisible && mLinearLayoutManager.findLastVisibleItemPosition() ==
                            recyclerView.getAdapter().getItemCount() - 1) {
                        isLastItemVisible = false;
//                            showFabButton();
                        showFAB();
                    }
                }
                }
            });
        }
        portfolioRecyclerView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
            if(event.getAction() == MotionEvent.ACTION_MOVE) {
                fabMenu.setEnable(false);
            } else if(event.getAction() == MotionEvent.ACTION_UP) {
                fabMenu.setEnable(true);
            }
            return false;
            }
        });
        setEmptyView(view);
        fabMenu = (FloatingActionMenu) view.findViewById(R.id.fabMenu);
        fabMenu.setDimColor(ContextCompat.getColor(getActivity(), R.color.fab_background_color));
        fabMenu.setMenuButtonColorNormal(ContextCompat.getColor(getActivity(), R.color.fab_color_normal));
        fabMenu.setMenuButtonColorPressed(ContextCompat.getColor(getActivity(), R.color.fab_color_pressed));
        fabMenu.setClosedOnTouchOutside(true);
//      fabMenu.setMenuButtonColorRipple(R.color.fab_color_normal);
        setFabMenus();
        return view;
    }

    public void setFabHide(){
        if (fabMenu.isOpened()) {
            fabMenu.toggle(true);
        }
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


    private void setEmptyView(View view) {
        RelativeLayout noContentLayout = (RelativeLayout) view.findViewById(R.id.noContentLayout);
        ((Button) noContentLayout.findViewById(R.id.btnGetStarted)).
                setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        mailTo(getActivity(), getString(R.string.dev_mail));
                        //startActivity(new Intent(getActivity(), GetStartedActivity.class));
                    }
                });
        portfolioRecyclerView.setEmptyView(noContentLayout);
    }


    private void setFabMenus() {
        String jsonString = loadJSONFromAsset(getActivity(), "filter");
        if (!TextUtils.isEmpty(jsonString)) {
            try {
                JSONObject jsonObject = new JSONObject(jsonString).optJSONObject("filter");
                if (jsonObject != null) {
                    int menuColor = Color.parseColor(jsonObject.optString("menu_color"));
                    int menuPressedColor = Color.parseColor(jsonObject.optString("menu_pressed_color"));
                    JSONArray jsonArray = jsonObject.optJSONArray("options");
                    if (jsonArray != null && jsonArray.length() > 0) {
                        final FloatingActionButton fabAllButton = new FloatingActionButton(getActivity());
                        fabAllButton.setButtonSize(FloatingActionButton.SIZE_MINI);
                        fabAllButton.setLabelText("All");
                        fabAllButton.setImageResource(getDrawableIdFromFileName(getActivity(), "ic_fab_all"));
                        fabAllButton.setColorNormal(menuColor);
                        fabAllButton.setColorPressed(menuPressedColor);
//                          fabButton.setColorRipple(R.color.fab_color_normal);
                        fabAllButton.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
     //                           setFilter(fabAllButton.getLabelText());
                                portfolioAdapter.filterList("");
                                fabMenu.toggle(true);

                            }
                        });
                        fabMenu.addMenuButton(fabAllButton);
                        for (int i = 0; i < jsonArray.length(); i++) {
                            final FloatingActionButton fabButton = new FloatingActionButton(getActivity());
                            fabButton.setButtonSize(FloatingActionButton.SIZE_MINI);
                            fabButton.setLabelText(jsonArray.getJSONObject(i).optString("title"));
                            fabButton.setImageResource(getDrawableIdFromFileName
                                    (getActivity(), jsonArray.getJSONObject(i).optString("image")));
                            fabButton.setColorNormal(menuColor);
                            fabButton.setColorPressed(menuPressedColor);
//                          fabButton.setColorRipple(R.color.fab_color_normal);
                            fabButton.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    portfolioAdapter.filterList(fabButton.getLabelText());
                                    fabMenu.toggle(true);
                                }
                            });
                            fabMenu.addMenuButton(fabButton);
                        }
                    }
                }

            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }


    private void setUpBackPressed() {
        getView().setFocusableInTouchMode(true);
        getView().requestFocus();
        getView().setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (event.getAction() == KeyEvent.ACTION_DOWN) {
                    if (keyCode == KeyEvent.KEYCODE_BACK) {
                        if (fabMenu.isOpened()) {
                            fabMenu.toggle(true);
                            return true;
                        } else {
                            return false;
                        }
                    }
                }
                return false;
            }
        });
    }


    @Override
    public void onResume() {
        AppInitializer.getInstance().trackScreenView("Portfolio Screen");
        getPortfolioData();
        initializeAdMob();
        portfolioAdapter = new PortfolioAdapter(getActivity(), projectList);
        portfolioRecyclerView.setAdapter(portfolioAdapter);
//        portfolioRecyclerView.setAdapter(new ScaleInAnimationAdapter(portfolioAdapter));
        if (portfolioRecyclerView.getAdapter().getItemCount() == 0) {
            noContentLayout.setVisibility(View.VISIBLE);
            fabMenu.setVisibility(View.GONE);
        } else {
            showFAB();
        }

        super.onResume();
    }

    private void hideFAB() {
        if(fabMenu.isOpened()) {
            fabMenu.close(true);
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    fabMenu.animate().translationY(fabMenu.getHeight()).setInterpolator(new AccelerateInterpolator(2)).start();
                }
            },500);
        } else {
            fabMenu.animate().translationY(fabMenu.getHeight()).setInterpolator(new AccelerateInterpolator(2)).start();
        }

    }

    private void showFAB() {
        if(fabMenu.isOpened()) {
            fabMenu.close(true);
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    fabMenu.animate().translationY(0).setInterpolator(new DecelerateInterpolator(2)).start();
                }
            },500);
        } else {
            fabMenu.animate().translationY(0).setInterpolator(new DecelerateInterpolator(2)).start();
        }

    }

    private void getPortfolioData() {
        DBHelper dbHelper = CPSetting.getInstance().getDbHelper();
        projectList = new ArrayList<>();
        projectList = dbHelper.getPortfolio();

    }

    //event bus for reload data action
    @Subscribe( threadMode = ThreadMode.MAIN)
    public void onLoginEvent(ToolbarShowEvent event){
        if(event.isSliderOper()){
            if (fabMenu.isOpened()) {
                fabMenu.toggle(true);
            }
        }

    }


    @Subscribe( threadMode = ThreadMode.MAIN)
    public void onDrawerOpen(OnDrawerOpen event){
       if(fabMenu.isOpened()) {
           fabMenu.toggle(true);
       }
    }



    @Override
    public void onStart() {
        super.onStart();
        bus.register(this); // registering the bus
    }

    @Override
    public void onStop() {
        bus.unregister(this); // un-registering the bus
        super.onStop();
    }
}
