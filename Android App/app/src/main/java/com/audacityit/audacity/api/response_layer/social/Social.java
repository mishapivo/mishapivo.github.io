
package com.audacityit.audacity.api.response_layer.social;

import java.util.ArrayList;
import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Social {

    @SerializedName("items")
    @Expose
    private List<Social_> social = new ArrayList<Social_>();
    @SerializedName("error")
    @Expose
    private boolean error;
    @SerializedName("message")
    @Expose
    private String message;

    /**
     * 
     * @return
     *     The social
     */
    public List<Social_> getSocial() {
        return social;
    }

    /**
     * 
     * @param social
     *     The social
     */
    public void setSocial(List<Social_> social) {
        this.social = social;
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
