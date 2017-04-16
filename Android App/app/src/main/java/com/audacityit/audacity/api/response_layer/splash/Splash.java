
package com.audacityit.audacity.api.response_layer.splash;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Splash {

    @SerializedName("logo")
    @Expose
    private String logo;
    @SerializedName("bgcolor")
    @Expose
    private String bgcolor;
    @SerializedName("error")
    @Expose
    private boolean error;
    @SerializedName("message")
    @Expose
    private String message;

    /**
     * 
     * @return
     *     The logo
     */
    public String getLogo() {
        return logo;
    }

    /**
     * 
     * @param logo
     *     The logo
     */
    public void setLogo(String logo) {
        this.logo = logo;
    }

    /**
     * 
     * @return
     *     The bgcolor
     */
    public String getBgcolor() {
        return bgcolor;
    }

    /**
     * 
     * @param bgcolor
     *     The bgcolor
     */
    public void setBgcolor(String bgcolor) {
        this.bgcolor = bgcolor;
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
