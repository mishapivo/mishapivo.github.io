
package com.audacityit.audacity.api.response_layer.sidebar;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Sidebar {

    @SerializedName("background")
    @Expose
    private String background;
    @SerializedName("logo")
    @Expose
    private String logo;
    @SerializedName("phone")
    @Expose
    private String phone;
    @SerializedName("email")
    @Expose
    private String email;
    @SerializedName("web")
    @Expose
    private String web;
    @SerializedName("error")
    @Expose
    private boolean error;
    @SerializedName("message")
    @Expose
    private String message;

    /**
     * 
     * @return
     *     The background
     */
    public String getBackground() {
        return background;
    }

    /**
     * 
     * @param background
     *     The background
     */
    public void setBackground(String background) {
        this.background = background;
    }

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
     *     The phone
     */
    public String getPhone() {
        return phone;
    }

    /**
     * 
     * @param phone
     *     The phone
     */
    public void setPhone(String phone) {
        this.phone = phone;
    }

    /**
     * 
     * @return
     *     The email
     */
    public String getEmail() {
        return email;
    }

    /**
     * 
     * @param email
     *     The email
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * 
     * @return
     *     The web
     */
    public String getWeb() {
        return web;
    }

    /**
     * 
     * @param web
     *     The web
     */
    public void setWeb(String web) {
        this.web = web;
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
