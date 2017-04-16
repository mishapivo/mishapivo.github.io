
package com.audacityit.audacity.api.response_layer.version;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class VersionCode {

    @SerializedName("version_code")
    @Expose
    private int versionCode;
    @SerializedName("error")
    @Expose
    private boolean error;
    @SerializedName("message")
    @Expose
    private String message;

    /**
     * 
     * @return
     *     The versionCode
     */
    public int getVersionCode() {
        return versionCode;
    }

    /**
     * 
     * @param versionCode
     *     The version_code
     */
    public void setVersionCode(int versionCode) {
        this.versionCode = versionCode;
    }

    /**
     * 
     * @return
     *     The error
     */
    public boolean isError() {
        return error;
    }

    /**
     * 
     * @param error
     *     The error
     */
    public void setError(boolean error) {
        this.error = error;
    }

    /**
     * 
     * @return
     *     The message
     */
    public String getMessage() {
        return message;
    }

    /**
     * 
     * @param message
     *     The message
     */
    public void setMessage(String message) {
        this.message = message;
    }

}
