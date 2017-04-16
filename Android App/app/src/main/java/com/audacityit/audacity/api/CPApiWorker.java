package com.audacityit.audacity.api;

import android.text.TextUtils;
import android.util.Log;

import com.audacityit.audacity.Constants;
import com.audacityit.audacity.api.response_layer.APIResponse;
import com.audacityit.audacity.util.Utils;
import com.google.gson.Gson;

import java.io.IOException;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.util.concurrent.TimeUnit;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;


/**
 * Created by sourav_aits on 9/26/16.
 */
public class CPApiWorker {


    //OkHttpClient
    private static OkHttpClient mClient = null;
    private static String KEY_AUTHORIZATION = "Authorization";

    //MediaType
    private static final MediaType MEDIA_TYPE = MediaType.parse(Constants.API_CONTENT_TYPE);

    public static OkHttpClient getClient() throws NoSuchAlgorithmException, KeyManagementException {
        if (mClient == null) {
            OkHttpClient.Builder httpBuilder = new OkHttpClient.Builder();


            httpBuilder
                    .connectTimeout(15, TimeUnit.SECONDS)   // Connection Timeout
                    .readTimeout(20, TimeUnit.SECONDS);     // Read Timeout

            /*//Stetho
            if (BuildConfig.DEBUG) {
                httpBuilder.addNetworkInterceptor(new StethoInterceptor());
            }*/

            mClient = httpBuilder.build();
        }
        return mClient;
    }



    public static APIResponse executeGETPrivate(String url) {
        APIResponse apiResponse = new APIResponse();
        try {

            OkHttpClient client = CPApiWorker.getClient();
            Request request = new Request.Builder()
                    .url(Constants.API_BASE_PATH + url)
                    .header(KEY_AUTHORIZATION,  Constants.API_AUTHORIZATIONKEY)
                    .get()
                    .build();
            Response response = client.newCall(request).execute();

            apiResponse.setResponseCode(response.code());
            if(response.body().toString().isEmpty()) {
                apiResponse.setResponse(null);
            } else {
                apiResponse.setResponse(response.body().string());
            }

            apiResponse.setResponseCode(response.code());

            /*Gson gson = new Gson();
            ResponseMessage responseMessage = gson.fromJson(apiResponse.getResponse(), ResponseMessage.class);
            responseMessage.setMessage(HNAppSettings.getInstance().getAppConfiguration().getMessageByCode(responseMessage.getMessageCode()));
            apiResponse.setResponseMessage(responseMessage);*/

            return apiResponse;

        } catch (KeyManagementException e) {
            e.printStackTrace();
            apiResponse.setError(true);
            String errorMessage = e.getMessage();

            if(TextUtils.isEmpty(errorMessage)) {
                errorMessage = "KeyManagementException";
            }
            apiResponse.setResponseMessage(errorMessage);
            apiResponse.setResponseCode(404);// Default  = Not Found
            return apiResponse;
        } catch (IOException e) {
            e.printStackTrace();
            apiResponse.setError(true);
            String errorMessage = e.getMessage();

            if(TextUtils.isEmpty(errorMessage)) {
                errorMessage = "IOException";
            }
            apiResponse.setResponseMessage(errorMessage);
            apiResponse.setResponseCode(404);// Default  = Not Found
            return apiResponse;
        } catch (Exception e) {
            e.printStackTrace();
            apiResponse.setError(true);
            String errorMessage = e.getMessage();

            if(TextUtils.isEmpty(errorMessage)) {
                errorMessage = "Unknown Exception";
            }
            apiResponse.setResponseMessage(errorMessage);
            apiResponse.setResponseCode(404);// Default  = Not Found
            return apiResponse;
        }

    }

    public static APIResponse executePostPrivate(String url, String param, String authorizationKey) {

        APIResponse apiResponse = new APIResponse();
        try {
            Utils.printLog("REQUEST_HEADER", "url = "+Constants.API_BASE_PATH + url + " param ="+param + " token = " + authorizationKey);
            RequestBody requestBody = RequestBody.create(MEDIA_TYPE, param);
            OkHttpClient client = CPApiWorker.getClient();
            Request request = new Request.Builder()
                    .url(Constants.API_BASE_PATH + url)
                    .header(KEY_AUTHORIZATION,  authorizationKey)
                    .post(requestBody)
                    .build();

            Response response = client.newCall(request).execute();

            apiResponse.setResponseCode(response.code());
            if(response.body().toString().isEmpty()) {
                apiResponse.setResponse(null);
            } else {
                apiResponse.setResponse(response.body().string());
            }

            apiResponse.setResponseCode(response.code());

            /*Gson gson = new Gson();
            ResponseMessage responseMessage = gson.fromJson(apiResponse.getResponse(), ResponseMessage.class);
            responseMessage.setMessage(HNAppSettings.getInstance().getAppConfiguration().getMessageByCode(responseMessage.getMessageCode()));
            apiResponse.setResponseMessage(responseMessage);*/

            return apiResponse;

        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            apiResponse.setError(true);
            String errorMessage = e.getMessage();

            if(TextUtils.isEmpty(errorMessage)) {
                errorMessage = "NoSuchAlgorithmException";
            }
            apiResponse.setResponseMessage(errorMessage);
            apiResponse.setResponseCode(404);// Default  = Not Found
            return apiResponse;

        } catch (KeyManagementException e) {
            e.printStackTrace();
            apiResponse.setError(true);
            String errorMessage = e.getMessage();

            if(TextUtils.isEmpty(errorMessage)) {
                errorMessage = "KeyManagementException";
            }
            apiResponse.setResponseMessage(errorMessage);
            apiResponse.setResponseCode(404);// Default  = Not Found
            return apiResponse;
        } catch (IOException e) {
            e.printStackTrace();
            apiResponse.setError(true);
            String errorMessage = e.getMessage();

            if(TextUtils.isEmpty(errorMessage)) {
                errorMessage = "IOException";
            }
            apiResponse.setResponseMessage(errorMessage);
            apiResponse.setResponseCode(404);// Default  = Not Found
            return apiResponse;
        } catch (Exception e) {
            e.printStackTrace();
            apiResponse.setError(true);
            String errorMessage = e.getMessage();

            if(TextUtils.isEmpty(errorMessage)) {
                errorMessage = "Unknown Exception";
            }
            apiResponse.setResponseMessage(errorMessage);
            apiResponse.setResponseCode(404);// Default  = Not Found
            return apiResponse;
        }

    }

    public static APIResponse executeGETPublic(String url) {

        Utils.printLog("REQUEST URL = ", Constants.API_BASE_PATH + url);

        APIResponse apiResponse = new APIResponse();
        try {

            OkHttpClient client = CPApiWorker.getClient();
            Request request;
            if(TextUtils.isEmpty(url)) {
                request = new Request.Builder()
                        .url(Constants.API_BASE_PATH)
                        .get()
                        .build();
            } else {
                request = new Request.Builder()
                        .url(Constants.API_BASE_PATH + url)
                        .get()
                        .build();
            }


            Response response = client.newCall(request).execute();

            apiResponse.setResponseCode(response.code());
            if(response.body().toString().isEmpty()) {
                apiResponse.setResponse(null);
            } else {
                apiResponse.setResponse(response.body().string());
            }

            apiResponse.setResponseCode(response.code());

            return apiResponse;

        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            apiResponse.setError(true);
            String errorMessage = e.getMessage();

            if(TextUtils.isEmpty(errorMessage)) {
                errorMessage = "NoSuchAlgorithmException";
            }
            apiResponse.setResponseMessage(errorMessage);
            apiResponse.setResponseCode(404);// Default  = Not Found
            return apiResponse;

        } catch (KeyManagementException e) {
            e.printStackTrace();
            apiResponse.setError(true);
            String errorMessage = e.getMessage();

            if(TextUtils.isEmpty(errorMessage)) {
                errorMessage = "KeyManagementException";
            }
            apiResponse.setResponseMessage(errorMessage);
            apiResponse.setResponseCode(404);// Default  = Not Found
            return apiResponse;
        } catch (IOException e) {
            e.printStackTrace();
            apiResponse.setError(true);
            String errorMessage = e.getMessage();

            if(TextUtils.isEmpty(errorMessage)) {
                errorMessage = "IOException";
            }
            apiResponse.setResponseMessage(errorMessage);
            apiResponse.setResponseCode(404);// Default  = Not Found
            return apiResponse;
        } catch (Exception e) {
            e.printStackTrace();
            apiResponse.setError(true);
            String errorMessage = e.getMessage();

            if(TextUtils.isEmpty(errorMessage)) {
                errorMessage = "Unknown Exception";
            }
            apiResponse.setResponseMessage(errorMessage);
            apiResponse.setResponseCode(404);// Default  = Not Found
            return apiResponse;
        }

    }


}
