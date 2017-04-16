package com.audacityit.audacity.api;

import android.util.Log;

import com.audacityit.audacity.Constants;
import com.audacityit.audacity.api.response_layer.APIResponse;
import com.audacityit.audacity.api.response_layer.CPAllResponse;
import com.audacityit.audacity.api.response_layer.CPVersionResponse;
import com.audacityit.audacity.api.response_layer.all.CPAllData;
import com.audacityit.audacity.api.response_layer.version.VersionCode;
import com.audacityit.audacity.settings.NetworkTimeoutException;
import com.audacityit.audacity.util.Utils;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import rx.Observable;
import rx.Subscriber;

/**
 * Created by imranaits on 10/17/16.
 */

public class CPApiService {

    public static Observable<CPVersionResponse> getVersionCode() {
        return Observable.create(new Observable.OnSubscribe<CPVersionResponse>() {
            String response = "";
            @Override
            public void call(Subscriber<? super CPVersionResponse> subscriber) {
                try {
                    //ArrayList<String> params = new ArrayList<String>();
                    //params.add(rqHomePost.getUserId()+"");
                    // params.add(rqHomePost.getOffset()+"");
                    //String url = Utils.urlBuilder(Constants.API_GET_HOME_STATUS,params);
                    APIResponse apiResponse =  CPApiWorker.executeGETPrivate("version");

                    if(apiResponse.getResponseCode() != Constants.NOT_FOUND) {
                        response = apiResponse.getResponse(); // Optional Check

                        CPVersionResponse versionResponse = new CPVersionResponse();

                        versionResponse.setError(apiResponse.isError());

                        if(!apiResponse.isError()) {
                            GsonBuilder builder = new GsonBuilder();
                            builder.excludeFieldsWithoutExposeAnnotation();
                            Gson gson = builder.create();
                            VersionCode versionCode = gson.fromJson(apiResponse.getResponse(), VersionCode.class);
                            versionResponse.setVersionCode(versionCode);
                        } else {
                            versionResponse.setResponseMessage(apiResponse.getResponseMessage());
                        }


                        if (!subscriber.isUnsubscribed()) {
                            subscriber.onNext(versionResponse);
                            subscriber.onCompleted();
                        }

                    } else {
                        if (!subscriber.isUnsubscribed()) {

                            NetworkTimeoutException networkTimeoutException = new NetworkTimeoutException("Please check your internet connection");
                            subscriber.onError(networkTimeoutException);
                        }
                    }

                } catch (Exception e) {

                    if (!subscriber.isUnsubscribed()) {

                        Utils.printLog("API_RESPONSE", response);
                        e.printStackTrace();
                        Utils.printLog("ERROR", e.getMessage());
                        subscriber.onError(e);
                    }
                }
            }
        });

    }

    public static Observable<CPAllResponse> getAllPost() {
        return Observable.create(new Observable.OnSubscribe<CPAllResponse>() {
            String response = "";
            @Override
            public void call(Subscriber<? super CPAllResponse> subscriber) {
                try {
                    //ArrayList<String> params = new ArrayList<String>();
                    //params.add(rqHomePost.getUserId()+"");
                    // params.add(rqHomePost.getOffset()+"");
                    //String url = Utils.urlBuilder(Constants.API_GET_HOME_STATUS,params);
                    APIResponse apiResponse =  CPApiWorker.executeGETPrivate("all");

                    if(apiResponse.getResponseCode() != Constants.NOT_FOUND) {
                        response = apiResponse.getResponse(); // Optional Check

                        CPAllResponse allPostResponse = new CPAllResponse();

                        allPostResponse.setError(apiResponse.isError());

                        if(!apiResponse.isError()) {
                            GsonBuilder builder = new GsonBuilder();
                            builder.excludeFieldsWithoutExposeAnnotation();
                            Gson gson = builder.create();
                            CPAllData cpAllData = gson.fromJson(apiResponse.getResponse(), CPAllData.class);
                            allPostResponse.setCpAllData(cpAllData);
                        } else {
                            allPostResponse.setResponseMessage(apiResponse.getResponseMessage());
                        }


                        if (!subscriber.isUnsubscribed()) {
                            subscriber.onNext(allPostResponse);
                            subscriber.onCompleted();
                        }

                    } else {
                        if (!subscriber.isUnsubscribed()) {

                            NetworkTimeoutException networkTimeoutException = new NetworkTimeoutException("Please check your internet connection");
                            subscriber.onError(networkTimeoutException);
                        }
                    }

                } catch (Exception e) {

                    if (!subscriber.isUnsubscribed()) {

                        Utils.printLog("API_RESPONSE", response);
                        e.printStackTrace();
                        Utils.printLog("ERROR", e.getMessage());
                        subscriber.onError(e);
                    }
                }
            }
        });

    }
}
