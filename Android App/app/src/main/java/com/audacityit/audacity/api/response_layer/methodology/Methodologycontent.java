
package com.audacityit.audacity.api.response_layer.methodology;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Methodologycontent {

    @SerializedName("background")
    @Expose
    private String background;
    @SerializedName("logo")
    @Expose
    private String logo;

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

}
