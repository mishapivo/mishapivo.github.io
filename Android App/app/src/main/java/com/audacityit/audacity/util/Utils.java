package com.audacityit.audacity.util;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.location.LocationManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.provider.Settings;
import android.telephony.SmsManager;
import android.text.Html;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.WindowManager;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.StringTokenizer;

/**
 * Created by Hasmath on 6/9/2016.
 */
public class Utils {

    public static void printLog(String key, String message) {
        Log.d(key, message);
        Log.i(key, message);
        Log.v(key, message);
    }

    public static void showLongToast(Context context, String message) {
        Toast.makeText(context, message, Toast.LENGTH_LONG).show();
    }

    public static void showShortToast(Context context, String message) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
    }



    public static void sendShortMessage(Activity context, String phoneNumber, String message) {

        try {
            // To chheck deliver status use pending intent
            SmsManager smsManager = SmsManager.getDefault();
            smsManager.sendTextMessage(phoneNumber, null, message, null, null);
            Utils.showLongToast(context, "Message Send");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public static void sendLongMessage(Activity context, String phoneNumber, String message) {

        try {
            SmsManager smsManager = SmsManager.getDefault();
            ArrayList<String> parts = smsManager.divideMessage(message);
            smsManager.sendMultipartTextMessage(phoneNumber, null, parts, null, null);
        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }

    public static void makeCallIndirect(Activity context, String phoneNumber) {
        String phone = phoneNumber;
        Intent callIntent = new Intent(Intent.ACTION_CALL);
        phone = "tel:" + phone;
        callIntent.setData(Uri.parse(phone));
        try {
            context.startActivity(callIntent);
            //  finish();
        }
        catch (android.content.ActivityNotFoundException ex)
        {
            Utils.showLongToast(context, "Call failed please try again");
        }
    }

    public static void sendMessageBlank(Activity context, String number) {
        String uriStr = "sms:"+number;
        Uri smsUri = Uri.parse(uriStr);
        Intent smsIntent = new Intent(Intent.ACTION_VIEW, smsUri);
        //	smsIntent.putExtra("sms_body", number);
        context.startActivity(smsIntent);
    }

    public static void shareContent(Activity context, String content) {

        Intent sendIntent = new Intent();
        sendIntent.setAction(Intent.ACTION_SEND);
        sendIntent.putExtra(Intent.EXTRA_TEXT, content);
        sendIntent.setType("text/plain");
        context.startActivity(sendIntent);
    }

    public static int getDeviceWidth(Context context)  {

        DisplayMetrics displayMetrics = new DisplayMetrics();
        WindowManager windowmanager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        windowmanager.getDefaultDisplay().getMetrics(displayMetrics);
        return displayMetrics.widthPixels;
    }

    public static int getDeviceHeight(Context context) {

        DisplayMetrics displayMetrics = new DisplayMetrics();
        WindowManager windowmanager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        windowmanager.getDefaultDisplay().getMetrics(displayMetrics);
        return displayMetrics.heightPixels;
    }

    public static boolean internetCheck(Context context)
    {
        boolean available=false;
        ConnectivityManager connectivity= (ConnectivityManager)context.getSystemService(Context.CONNECTIVITY_SERVICE);

        if(connectivity!=null)
        {
            NetworkInfo[] networkInfo= connectivity.getAllNetworkInfo();
            if(networkInfo!=null)
            {
                for(int i=0; i<networkInfo.length;i++)
                {
                    if(networkInfo[i].getState()==NetworkInfo.State.CONNECTED)
                    {
                        available=true;
                        break;
                    }
                }
            }


        }

        return available;
    }

    public boolean gpsCheck(Context context)
    {
        boolean available=false;

        LocationManager manager=(LocationManager)context.getSystemService(context.LOCATION_SERVICE);

        boolean statusOfGps=manager.isProviderEnabled(LocationManager.GPS_PROVIDER);
        boolean statusOfNetwork=manager.isProviderEnabled(LocationManager.NETWORK_PROVIDER);

        if(statusOfGps && statusOfNetwork)
        {
            available=true;
        }
        else if(statusOfGps || statusOfNetwork)
            available=true;
        return available;

    }

    public void navigateToInternetSettings(Activity context)
    {
        Intent intent=new Intent(Settings.ACTION_DATA_ROAMING_SETTINGS);
        context.startActivity(intent);
    }

    public void navigateToGpsSettings(Activity context)
    {
        Intent intent=new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
        context.startActivity(intent);
    }

    public static void launchActivity(Context context, Class className) {
        Intent intent = new Intent(context, className);
        context.startActivity(intent);
    }

    public static String urlBuilder(String url, ArrayList<String>params) {
        for(int i = 0; i < params.size(); i++) {
            url = url + "/" + params.get(i) ;
        }
        return url;
    }

    public static ArrayList<String> parseImage(String image) {
        ArrayList<String> imageList = new ArrayList<String>();

        StringTokenizer tokenizer = new StringTokenizer(image,",");
        while (tokenizer.hasMoreTokens()) {
            //System.out.println(st.nextToken());
            imageList.add(tokenizer.nextToken());
        }

        return imageList;
    }

    public static void browseUrl(Context context, String url) {
        Intent openBrowserIntent = new Intent(Intent.ACTION_VIEW);
        openBrowserIntent.setData(Uri.parse(url));
        context.startActivity(openBrowserIntent);
    }

    public static String removeHTMLChar(String string){
        String result = "";
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
            result = Html.fromHtml(string,Html.FROM_HTML_MODE_LEGACY).toString();
        } else {
            result = Html.fromHtml(string).toString();
        }
        return  result;
    }


    public static int dip2px(float dp, Context ctx) {
        return (int) (dp * ctx.getResources().getDisplayMetrics().density + 0.5);
    }

    public static float px2dip(int px, Context ctx) {
        return (float) px / ctx.getResources().getDisplayMetrics().density;
    }


}
