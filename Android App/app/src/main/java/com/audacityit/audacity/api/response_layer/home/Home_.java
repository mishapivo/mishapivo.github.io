
package com.audacityit.audacity.api.response_layer.home;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Home_ {

    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("background")
    @Expose
    private String background;

    public Home_(String title, String background) {
        this.title = title;
        this.background = background;
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

}
