package com.audacityit.audacity.activity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.audacityit.audacity.R;
import com.audacityit.audacity.api.CPApiService;
import com.audacityit.audacity.api.response_layer.CPAllResponse;
import com.audacityit.audacity.api.response_layer.CPVersionResponse;
import com.audacityit.audacity.api.response_layer.all.Data;
import com.audacityit.audacity.api.response_layer.client.Client_;
import com.audacityit.audacity.api.response_layer.home.Home_;
import com.audacityit.audacity.api.response_layer.methodology.Methodologyitem;
import com.audacityit.audacity.api.response_layer.overview.Overviewitem;
import com.audacityit.audacity.api.response_layer.portfolio.Portfolio_;
import com.audacityit.audacity.api.response_layer.social.Social_;
import com.audacityit.audacity.api.response_layer.splash.Splash;
import com.audacityit.audacity.api.response_layer.team.Team_;
import com.audacityit.audacity.api.response_layer.testimonial.Testimonial_;
import com.audacityit.audacity.settings.CPSetting;
import com.audacityit.audacity.settings.NetworkTimeoutException;
import com.audacityit.audacity.tracker.AppInitializer;
import com.audacityit.audacity.util.DBHelper;
import com.audacityit.audacity.util.Utils;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.target.Target;

import java.io.File;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

import static com.audacityit.audacity.util.PreferenceUtil.getPreferenceInt;
import static com.audacityit.audacity.util.PreferenceUtil.savePreference;
import static com.audacityit.audacity.util.UtilMethods.getWindowSize;

public class WelcomeActivity extends AppCompatActivity {

    private static final int SPLASH_DURATION = 3;
    private ImageView splashImageView;
    private boolean isBackPressed = false;
    private Context mContext;
    private RelativeLayout frameNoInternet;
    private RelativeLayout relativeLoader;
    private RelativeLayout relativeError;
    private Button bntRetry;
    private Button bntError;
    private  int countLoader;
    private int count;
    private int currentVersion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        bntRetry = (Button) findViewById(R.id.bntRetry);
        bntError = (Button) findViewById(R.id.bntError);
        frameNoInternet = (RelativeLayout) findViewById(R.id.relativeNoInternet);
        relativeLoader = (RelativeLayout) findViewById(R.id.relativeLoader);
        relativeError = (RelativeLayout) findViewById(R.id.relativeError);
        mContext = this;
        CPSetting.getInstance().initAppSettings(mContext);


        currentVersion = getVersionCode();
        if(Utils.internetCheck(mContext)){
            getVersionCodeAPI();
        }else{
            if(isDataLoaded()){
                frameNoInternet.setVisibility(View.GONE);
                relativeError.setVisibility(View.GONE);
                loadUI();
            }else if(!Utils.internetCheck(mContext)){
                frameNoInternet.setVisibility(View.VISIBLE);
            }else{
                relativeError.setVisibility(View.VISIBLE);
            }
        }
        allOncliCk();

    }

    private void allOncliCk() {
        bntRetry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(isDataLoaded()){
                    frameNoInternet.setVisibility(View.GONE);
                    relativeError.setVisibility(View.GONE);
                    loadUI();
                }else if(Utils.internetCheck(mContext)){
                    frameNoInternet.setVisibility(View.GONE);
                    if(Utils.internetCheck(mContext)){
                        getVersionCodeAPI();
                    }else{
                        if(isDataLoaded()){
                            frameNoInternet.setVisibility(View.GONE);
                            relativeError.setVisibility(View.GONE);
                            loadUI();
                        }else if(Utils.internetCheck(mContext)){
                            frameNoInternet.setVisibility(View.VISIBLE);
                        }else{
                            relativeError.setVisibility(View.VISIBLE);
                        }
                    }
                }else{
                    frameNoInternet.setVisibility(View.VISIBLE);
                    relativeError.setVisibility(View.GONE);
                    //Utils.showLongToast(mContext, "Please check your internet connection");
                }
            }
        });

        bntError.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(isDataLoaded()){
                    frameNoInternet.setVisibility(View.GONE);
                    relativeError.setVisibility(View.GONE);
                    loadUI();
                }else if(Utils.internetCheck(mContext)){
                    getDataFromAPI();
                    relativeError.setVisibility(View.GONE);
                    frameNoInternet.setVisibility(View.GONE);
                }
            }
        });

    }

    private void loadUI() {
        DBHelper dbHelper = CPSetting.getInstance().getDbHelper();
        Splash splash = dbHelper.getSplash();
        getWindow().getDecorView().setBackgroundColor(Color.parseColor(splash.getBgcolor()));

        //set Responsive Splash Logo
        splashImageView = (ImageView) findViewById(R.id.splashImageView);
        splashImageView.getLayoutParams().width = (int) (getWindowSize(this).x * 0.75);

        Glide.with(mContext).load(splash.getLogo()).skipMemoryCache(true).diskCacheStrategy(DiskCacheStrategy.SOURCE).listener(new RequestListener<String, GlideDrawable>() {
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
        }).into(splashImageView);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if(!isBackPressed) {
                    startActivity(new Intent(WelcomeActivity.this, HomeActivity.class));
                    finish();
                }
            }
        }, SPLASH_DURATION * 1000);
    }


    ///store data in database
    private void storeInDatabase(Data data) {
        DBHelper dbHelper = CPSetting.getInstance().getDbHelper();
        //clear database value
        dbHelper.deleteCPAll();
        //insert all data
        dbHelper.insertCPAll(data);

        //store images
        storeImages(data);

        //get data form DB
        //getAllFromDB();
    }

    private void storeImages(Data data) {
        countLoader=0;
        count = 0;
        //splash image
        //storeSingleImage(data.getSplash().getLogo());


        //sidebar image
        count++;
        storeSingleImage(data.getSidebar().getLogo());
        count++;
        storeSingleImage(data.getSidebar().getBackground());

        //overview image
        count++;
        storeSingleImage(data.getOverview().getOverviewcontent().getBackground());
        count++;
        storeSingleImage(data.getOverview().getOverviewcontent().getLogo());
        for(Overviewitem overviewitem : data.getOverview().getOverviewitems()){
            count++;
            storeSingleImage(overviewitem.getIcon());
        }

        //home image
        for(Home_ home: data.getHome().getHome()){
            count++;
            storeSingleImage(home.getBackground());
        }

        //portfolio
        for(Portfolio_ portfolio : data.getPortfolio().getPortfolio()){
            count++;
            storeSingleImage(portfolio.getImage());
        }

        //team image
        for(Team_ team : data.getTeam().getTeam()){
            count++;
            storeSingleImage(team.getPhoto());
        }

        //client image
        for(Client_ client : data.getClient().getClient()){
            count++;
            storeSingleImage(client.getLogo());
        }

        //testimonial image
        for(Testimonial_ testimonial : data.getTestimonial().getTestimonial()){
            count++;
            storeSingleImage(testimonial.getImage());
        }

        //methodology image
        count++;
        storeSingleImage(data.getMethodology().getMethodologycontent().getBackground());
        for(Methodologyitem item : data.getMethodology().getMethodologyitems()){
            count++;
            storeSingleImage(item.getIcon());
        }


        //social image
        for(Social_ social : data.getSocial().getSocial()){
            count++;
            storeSingleImage(social.getImage());
        }

        //set data loaded
        setDataLoaded();
    }

    private void storeSingleImage(String url) {
        Glide.with(mContext)
                .load(url)
                .downloadOnly(new SimpleTarget<File>() {
                    @Override
                    public void onResourceReady(File resource, GlideAnimation<? super File> glideAnimation) {
                        countLoader++;
                        if(count<=countLoader){
                            relativeLoader.setVisibility(View.GONE);
                            loadUI();
                        }
                    }

                    @Override
                    public void onLoadFailed(Exception e, Drawable errorDrawable) {
                        super.onLoadFailed(e, errorDrawable);
                        countLoader++;
                        if(count<=countLoader){
                            relativeLoader.setVisibility(View.GONE);
                            loadUI();
                        }
                    }

                });
    }


    //get version code form API
    private void getVersionCodeAPI(){
        relativeLoader.setVisibility(View.VISIBLE);
        CPApiService.getVersionCode().subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<CPVersionResponse>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.d(mContext+" --- version --- "," error ");
                        if(e instanceof NetworkTimeoutException) {
                            NetworkTimeoutException networkTimeoutException = (NetworkTimeoutException) e;
                            Utils.showLongToast(mContext, networkTimeoutException.getMessage());
                            frameNoInternet.setVisibility(View.VISIBLE);
                            relativeError.setVisibility(View.GONE);
                        } else {
                            //Utils.showLongToast(mContext, "Sorry! Please try later.");
                            relativeError.setVisibility(View.VISIBLE);
                            frameNoInternet.setVisibility(View.GONE);
                        }
                        ///progress.dismiss();;
                        relativeLoader.setVisibility(View.GONE);
                    }

                    @Override
                    public void onNext(CPVersionResponse cpVersionResponse) {
                        if(cpVersionResponse.isError()) {
                            Utils.showLongToast(mContext, cpVersionResponse.getResponseMessage());
                            relativeError.setVisibility(View.VISIBLE);
                            frameNoInternet.setVisibility(View.GONE);
                            relativeLoader.setVisibility(View.GONE);
                        }else {
                            Log.d(mContext+" --- version --- "," code -> "+cpVersionResponse.getVersionCode().getVersionCode());
                            if(cpVersionResponse.getVersionCode().getVersionCode() != getVersionCode()){
                                if(Utils.internetCheck(mContext)){
                                    getDataFromAPI();
                                    frameNoInternet.setVisibility(View.GONE);
                                }else{
                                    if(isDataLoaded()){
                                        frameNoInternet.setVisibility(View.GONE);
                                        relativeError.setVisibility(View.GONE);
                                        loadUI();
                                    }else if(Utils.internetCheck(mContext)){
                                        frameNoInternet.setVisibility(View.VISIBLE);
                                    }else{
                                        relativeError.setVisibility(View.VISIBLE);
                                    }
                                }
                                setVersionCode(cpVersionResponse.getVersionCode().getVersionCode());
                            }else{
                                relativeLoader.setVisibility(View.GONE);
                                relativeError.setVisibility(View.GONE);
                                frameNoInternet.setVisibility(View.GONE);
                                if(isDataLoaded()){
                                    loadUI();
                                }else if(Utils.internetCheck(mContext)){
                                    frameNoInternet.setVisibility(View.VISIBLE);
                                }else{
                                    relativeError.setVisibility(View.VISIBLE);
                                }
                            }
                        }
                    }
                });
    }

    //get data from API
    private void getDataFromAPI() {
        //progress = ProgressDialog.show(this, "Loading.....", "", true);
        relativeLoader.setVisibility(View.VISIBLE);

        CPApiService.getAllPost().subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<CPAllResponse>() {
                    @Override
                    public void onCompleted() {

                    }
                    @Override
                    public void onError(Throwable e) {

                        if(e instanceof NetworkTimeoutException) {
                            NetworkTimeoutException networkTimeoutException = (NetworkTimeoutException) e;
                            Utils.showLongToast(mContext, networkTimeoutException.getMessage());
                            frameNoInternet.setVisibility(View.VISIBLE);
                            relativeError.setVisibility(View.GONE);
                        } else {
                            //Utils.showLongToast(mContext, "Sorry! Please try later.");
                            relativeError.setVisibility(View.VISIBLE);
                            frameNoInternet.setVisibility(View.GONE);
                        }
                        ///progress.dismiss();;
                        relativeLoader.setVisibility(View.GONE);
                    }

                    @Override
                    public void onNext(CPAllResponse allPostResponse) {
                        if(allPostResponse.isError()) {
                            Utils.showLongToast(mContext, allPostResponse.getResponseMessage());
                            relativeError.setVisibility(View.VISIBLE);
                            frameNoInternet.setVisibility(View.GONE);
                            relativeLoader.setVisibility(View.GONE);
                        } else {
                            storeInDatabase(allPostResponse.getCpAllData().getData());
                            relativeError.setVisibility(View.GONE);
                            frameNoInternet.setVisibility(View.GONE);
                        }
                    }
                });
    }


    private static final String DATA_LOAD = "data_load";
    private static final String VERSION_CODE = "version_code";

    private boolean isDataLoaded() {
        if (getPreferenceInt(this, DATA_LOAD) == 0) {
            return false;
        } else {
            return true;
        }
    }

    private int getVersionCode(){
        return getPreferenceInt(this, VERSION_CODE);
    }
    private void setVersionCode(int code){
        savePreference(this, VERSION_CODE, code);
    }

    private void setDataLoaded() {
        savePreference(this, DATA_LOAD, 1);
    }

    @Override
    protected void onResume() {
        AppInitializer.getInstance().trackScreenView("Welcome Screen");
        super.onResume();
    }

    @Override
    public void onBackPressed() {
        isBackPressed = true;
        super.onBackPressed();
    }

}
