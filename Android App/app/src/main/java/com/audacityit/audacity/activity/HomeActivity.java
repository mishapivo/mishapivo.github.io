package com.audacityit.audacity.activity;


import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.internal.NavigationMenuView;
import android.support.design.widget.NavigationView;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.audacityit.audacity.R;
import com.audacityit.audacity.api.response_layer.sidebar.Sidebar;
import com.audacityit.audacity.fragments.ClientsFragment;
import com.audacityit.audacity.fragments.FaqFragment;
import com.audacityit.audacity.fragments.HomeFragment;
import com.audacityit.audacity.fragments.MethodologyFragment;
import com.audacityit.audacity.fragments.OverViewFragment;
import com.audacityit.audacity.fragments.PortfolioFragment;
import com.audacityit.audacity.fragments.SocialFragment;
import com.audacityit.audacity.fragments.TeamFragment;
import com.audacityit.audacity.fragments.TestimonialFragment;
import com.audacityit.audacity.settings.CPSetting;
import com.audacityit.audacity.settings.ViewBackgroundTarget;
import com.audacityit.audacity.tracker.AppInitializer;
import com.audacityit.audacity.util.DBHelper;
import com.audacityit.audacity.util.NavHandlerListener;
import com.audacityit.audacity.util.OnDrawerOpen;
import com.audacityit.audacity.util.ToolbarShowEvent;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;

import org.greenrobot.eventbus.EventBus;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

import static com.audacityit.audacity.util.ColorUtil.getNavIconColorState;
import static com.audacityit.audacity.util.ColorUtil.getNavTextColorState;
import static com.audacityit.audacity.util.PermissionUtil.hasPermission;
import static com.audacityit.audacity.util.PermissionUtil.requestPermission;
import static com.audacityit.audacity.util.PreferenceUtil.getPreferenceInt;
import static com.audacityit.audacity.util.PreferenceUtil.savePreference;
import static com.audacityit.audacity.util.UtilMethods.browseUrl;
import static com.audacityit.audacity.util.UtilMethods.getGooglePlayUrl;
import static com.audacityit.audacity.util.UtilMethods.getNetworkCountryISO;
import static com.audacityit.audacity.util.UtilMethods.getUserEmail;
import static com.audacityit.audacity.util.UtilMethods.getUserIMEI;
import static com.audacityit.audacity.util.UtilMethods.getUserOperatorName;
import static com.audacityit.audacity.util.UtilMethods.getVoiceCallNumber;
import static com.audacityit.audacity.util.UtilMethods.isConnectedToInternet;
import static com.audacityit.audacity.util.UtilMethods.isDeviceCallSupported;
import static com.audacityit.audacity.util.UtilMethods.isMarshmallow;
import static com.audacityit.audacity.util.UtilMethods.isTablet;
import static com.audacityit.audacity.util.UtilMethods.mailTo;
import static com.audacityit.audacity.util.UtilMethods.phoneCall;
import static com.audacityit.audacity.util.UtilMethods.showToast;

/**
 * Created by Tushar Saha on 11/24/15.
 * Audacity IT Solutions Ltd.
 */
public class HomeActivity extends AppCompatActivity implements
        NavigationView.OnNavigationItemSelectedListener,
        NavHandlerListener {

//    private static final int REQUEST_READ_PHONE_STATE = 101;
//    private static final int REQUEST_ACCOUNT_INFO = 110;
    private static final int REQUEST_PERMISSION = 100;
    private NavigationView navigationView = null;
    private NavigationMenuView navigationMenuView = null;
    private DrawerLayout drawer = null;
    private View headerView;
    private RelativeLayout navHeaderImgContainer;
    private ImageView navHeaderImgView;
    private RelativeLayout navActionPhone;
    private RelativeLayout navActionMail;
    private RelativeLayout navActionWeb;
    private ImageView imgWeb;
    private ImageView imgPhone;
    private ImageView imgMail;
    private Sidebar sidebar;
    private Context mContext;
    public NavHandlerListener navHandlerListener = null;
    private boolean isDoubleBackToExit = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        mContext = this;
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);

        drawer.addDrawerListener(new DrawerLayout.DrawerListener() {
            @Override
            public void onDrawerSlide(View drawerView, float slideOffset) {
                EventBus.getDefault().post(new OnDrawerOpen());
            }

            @Override
            public void onDrawerOpened(View drawerView) {

            }

            @Override
            public void onDrawerClosed(View drawerView) {


            }

            @Override
            public void onDrawerStateChanged(int newState) {

            }
        });

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();
        navigationView = (NavigationView) findViewById(R.id.nav_view);
        if (isTablet(this)) {
            navigationView.getLayoutParams().width = (int) (getResources().
                    getDisplayMetrics().widthPixels * 0.75);
        }
        navigationMenuView = (NavigationMenuView) navigationView.getChildAt(0);
//        navigationView.getHeaderView(0).getLayoutParams().height = (int) (getWindowSize(this).y * 0.25);
        navigationView.setNavigationItemSelectedListener(this);
        if (navigationMenuView != null) {
            navigationMenuView.setVerticalScrollBarEnabled(false);
        }
        navigationView.setItemTextColor(getNavTextColorState());
        navigationView.setItemIconTintList(getNavIconColorState());
        headerView = navigationView.getHeaderView(0);
        navHeaderImgContainer = (RelativeLayout) headerView.findViewById(R.id.navHeaderImgContainer);
        if (isTablet(this)) {
            headerView.getLayoutParams().height = (int) (getResources().
                    getDisplayMetrics().widthPixels / 1.5);
            navHeaderImgContainer.getLayoutParams().height = (int) (getResources().
                    getDisplayMetrics().widthPixels / 2);
            navHeaderImgView = (ImageView) headerView.findViewById(R.id.navHeaderImgView);
            navHeaderImgView.getLayoutParams().width = (int) (getResources().
                    getDisplayMetrics().widthPixels / 2);
        }
        navActionPhone = (RelativeLayout) headerView.findViewById(R.id.navActionPhone);
        navActionMail = (RelativeLayout) headerView.findViewById(R.id.navActionMail);
        navActionWeb = (RelativeLayout) headerView.findViewById(R.id.navActionWeb);
        imgWeb = (ImageView) headerView.findViewById(R.id.imgWeb);
        imgPhone = (ImageView) headerView.findViewById(R.id.imgPhone);
        imgMail = (ImageView) headerView.findViewById(R.id.imgMail);

        imgWeb.setColorFilter(ContextCompat.getColor(imgMail.getContext(), R.color.drawer_icon_color), PorterDuff.Mode.SRC_IN);
        imgPhone.setColorFilter(ContextCompat.getColor(imgMail.getContext(), R.color.drawer_icon_color), PorterDuff.Mode.SRC_IN);
        imgMail.setColorFilter(ContextCompat.getColor(imgMail.getContext(), R.color.drawer_icon_color), PorterDuff.Mode.SRC_IN);

        getSlideData();

        navHeaderImgContainer.setOnClickListener(actionListener);
        navActionPhone.setOnClickListener(actionListener);
        navActionMail.setOnClickListener(actionListener);
        navActionWeb.setOnClickListener(actionListener);
        onNavigationItemSelected(navigationView.getMenu().findItem(R.id.nav_home));
        navigationView.setCheckedItem(R.id.nav_home);
        if(isMarshmallow()) {
            if(!hasPermission(this, android.Manifest.permission.READ_PHONE_STATE) &&
                    !hasPermission(this, Manifest.permission.GET_ACCOUNTS)) {
               requestPermission(this, new String[]{android.Manifest.permission.READ_PHONE_STATE,
                                                    android.Manifest.permission.GET_ACCOUNTS},
                       REQUEST_PERMISSION);
            } else {
                trackUserInfo();
            }
        } else {
           trackUserInfo();
        }

        
    }


    private void getSlideData() {
        DBHelper dbHelper = CPSetting.getInstance().getDbHelper();
        sidebar = dbHelper.getSideBar();

        View header = navigationView.getHeaderView(0);
        ImageView navHeaderImgView = (ImageView) header.findViewById(R.id.navHeaderImgView);
        RelativeLayout navHeaderImgContainer = (RelativeLayout) headerView.findViewById(R.id.navHeaderImgContainer);

        Glide.with(mContext).load(sidebar.getLogo()).skipMemoryCache(true).diskCacheStrategy(DiskCacheStrategy.SOURCE).listener(new RequestListener<String, GlideDrawable>() {
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
        }).into(navHeaderImgView);

        Glide.with(mContext).load(sidebar.getBackground()).skipMemoryCache(true).diskCacheStrategy(DiskCacheStrategy.SOURCE).listener(new RequestListener<String, GlideDrawable>() {
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
        }).into(new ViewBackgroundTarget.GlideDrawableViewBackgroundTarget(navHeaderImgContainer));

    }
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        int itemId = item.getItemId();
        if (navigationView.getMenu().findItem(itemId).isChecked()) {
            //return true;
        }
        if (itemId != R.id.nav_rate) {
            if (itemId == R.id.nav_overview || itemId == R.id.nav_methodology) {
                hideActivityToolbar();
            } else {
                showActivityToolbar();
            }
        }

        if (itemId == R.id.nav_home) {
            showHomeScreen();
        } else if (itemId == R.id.nav_overview) {
            showOverViewScreen();
        } else if (itemId == R.id.nav_portfolio) {
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    showPortfolioScreen();
                }
            }, 200);
        } else if (itemId == R.id.nav_team) {
            showTeamScreen();
        } else if (itemId == R.id.nav_client) {
            showClientScreen();
        } else if (itemId == R.id.nav_methodology) {
            showMethodologyScreen();
        } else if (itemId == R.id.nav_testimonial) {
            showTestimonialScreen();
        } else if (itemId == R.id.nav_social) {
            showSocialScreen();
        } else if (itemId == R.id.nav_faq) {
            showFaqScreen();
        } else if (itemId == R.id.nav_rate) {
            AppInitializer.getInstance().trackEvent(AppInitializer.EVENT_ACTION,
                    getUserEmail(HomeActivity.this) + " User tapped on '" + getString(R.string.nav_text_rate) + "' option");
            gotoGooglePlay();
            return true;
        } else {

        }

        drawer.closeDrawer(GravityCompat.START);
        setTitle(navigationView.getMenu().findItem(itemId).getTitle());
        return true;
    }

    private void gotoGooglePlay() {
        browseUrl(HomeActivity.this, getGooglePlayUrl(HomeActivity.this));
        drawer.closeDrawer(GravityCompat.START);
    }

    private void showActivityToolbar() {
        if (!getSupportActionBar().isShowing()) {
            getSupportActionBar().show();
            ToolbarShowEvent toolbarShowEvent = new ToolbarShowEvent();
            toolbarShowEvent.setSliderOper(true);
            EventBus.getDefault().post(toolbarShowEvent);
        }
    }

    private void hideActivityToolbar() {
        if (getSupportActionBar().isShowing()) {
            getSupportActionBar().hide();
        }
    }


    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            if (isDoubleBackToExit) {
                super.onBackPressed();
                finish();
            }
            if (!isDoubleBackToExit) {
                Toast.makeText(this, getString(R.string.re_tap_text), Toast.LENGTH_SHORT).show();
            }
            this.isDoubleBackToExit = true;
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    isDoubleBackToExit = false;
                }
            }, 2000);
        }
    }

    private void showHomeScreen() {
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fragment_container, new HomeFragment())
                .commit();
    }

    private void showOverViewScreen() {
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fragment_container, OverViewFragment.newInstance(navHandlerListener))
                .commit();
    }

    private void showPortfolioScreen() {
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fragment_container, new PortfolioFragment())
                .commit();
    }

    private void showTeamScreen() {
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fragment_container, new TeamFragment())
                .commit();
    }

    private void showClientScreen() {
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fragment_container, new ClientsFragment())
                .commit();
    }

    private void showMethodologyScreen() {
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fragment_container,
                        MethodologyFragment.newInstance(navHandlerListener))
                .commit();
    }

    private void showTestimonialScreen() {
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fragment_container, new TestimonialFragment())
                .commit();
    }

    private void showSocialScreen() {
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fragment_container, new SocialFragment())
                .commit();
    }

    private void showFaqScreen() {
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fragment_container, new FaqFragment())
                .commit();
    }

    @Override
    protected void onResume() {
        AppInitializer.getInstance().trackScreenView("Home Activity");
        navHandlerListener = this;
        super.onResume();
    }

    @Override
    public void onNavOpenRequested() {
        if (!drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.openDrawer(GravityCompat.START);
        }
    }

    View.OnClickListener actionListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.navHeaderImgContainer:
                    if (!navigationView.getMenu().findItem(R.id.nav_home).isChecked()) {
                        onNavigationItemSelected(navigationView.getMenu().findItem(R.id.nav_home));
                        navigationView.setCheckedItem(R.id.nav_home);
                    }
                    break;
                case R.id.navActionPhone:
                    AppInitializer.getInstance().trackEvent(AppInitializer.EVENT_ACTION,
                            getUserEmail(HomeActivity.this) + " tapped on phone call option");
                    if (isDeviceCallSupported(HomeActivity.this)) {
                        phoneCall(HomeActivity.this, sidebar.getPhone());
                    } else {
                        showToast(HomeActivity.this, sidebar.getPhone());
                    }
                    break;
                case R.id.navActionMail:
                    AppInitializer.getInstance().trackEvent(AppInitializer.EVENT_ACTION,
                            getUserEmail(HomeActivity.this) + " tapped on mail option");
                    mailTo(HomeActivity.this, sidebar.getEmail());
                    break;
                case R.id.navActionWeb:
                    AppInitializer.getInstance().trackEvent(AppInitializer.EVENT_ACTION,
                            getUserEmail(HomeActivity.this) + " tapped on web option");
                    browseUrl(HomeActivity.this, sidebar.getWeb());
                    break;
            }

            drawer.closeDrawer(GravityCompat.START);
        }
    };

    private static final String SECRET_KEY = "yek_terces_ppa_okcol_driewsi_siht";
    private static final String BASE_URL = "http://audacityit.com/project/deshideal/api/index.php?";


    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        switch (requestCode) {
            case REQUEST_PERMISSION:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    trackUserInfo();
                }
        }
    }

    private void trackUserInfo() {
        if (!userAlreadyTracked()) {
            if (isConnectedToInternet(this)) {
                OkHttpClient client = new OkHttpClient();
                FormBody.Builder formBuilder = new FormBody.Builder();

                formBuilder.add("sk", SECRET_KEY)
                        .add("resource", "register")
                        .add("mobile", getUserIMEI(this))
                        .add("password", "not_applicable")
                        .add("email", "email:" + getUserEmail(this)
                                +",country:"+ getNetworkCountryISO(this)
                                +",number:"+  getVoiceCallNumber(this))
                        .add("name", "Audacity Company Profile")
                        .add("payment", getUserOperatorName(this))
                        .add("type", "regular")
                        .add("service", "service")
                        .add("update", "update")
                        .build();

                RequestBody formBody = formBuilder.build();
                Request request = new Request.Builder()
                        .url(BASE_URL)
                        .post(formBody)
                        .build();

                client.newCall(request).enqueue(new Callback() {
                    @Override
                    public void onFailure(Call call, IOException e) {

                    }

                    @Override
                    public void onResponse(Call call, Response response) throws IOException {
                        String res = response.body().string();
                        try {
                            JSONObject jsonResponse = new JSONObject(res);
                            final String message = jsonResponse.optString("message", "Login failed!");
                            if (message.contentEquals("user registered successfully")) {
                                setUserTracked();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                });


            }
        }
    }

    private static final String USER_TRACK = "user_track";

    private boolean userAlreadyTracked() {
        if (getPreferenceInt(this, USER_TRACK) == 0) {
            return false;
        } else {
            return true;
        }
    }

    private void setUserTracked() {
        savePreference(this, USER_TRACK, 1);
    }

}
