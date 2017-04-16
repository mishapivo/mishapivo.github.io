
package com.audacityit.audacity.api.response_layer.methodology;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Methodologyitem {

    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("details")
    @Expose
    private String details;
    @SerializedName("icon")
    @Expose
    private String icon;

    public Methodologyitem(String title, String details, String icon) {
        this.title = title;
        this.details = details;
        this.icon = icon;
    }

    /**
     * 
     * @return
     *     The title
     */
    public String getTitle() {
        return title;
    }

    /**
     * 
     * @param title
     *     The title
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * 
     * @return
     *     The details
     */
    public String getDetails() {
        return details;
    }

    /**
     * 
     * @param details
     *     The details
     */
    public void setDetails(String details) {
        this.details = details;
    }

    /**
     * 
     * @return
     *     The icon
     */
    public String getIcon() {
        return icon;
    }

    /**
     * 
     * @param icon
     *     The icon
     */
    public void setIcon(String icon) {
        this.icon = icon;
    }

}
