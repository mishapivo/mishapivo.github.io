
package com.audacityit.audacity.api.response_layer.portfolio;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class Portfolio {

    @SerializedName("items")
    @Expose
    private List<Portfolio_> portfolio = new ArrayList<Portfolio_>();
    @SerializedName("error")
    @Expose
    private boolean error;
    @SerializedName("message")
    @Expose
    private String message;

    /**
     * 
     * @return
     *     The portfolio
     */
    public List<Portfolio_> getPortfolio() {
        return portfolio;
    }

    /**
     * 
     * @param portfolio
     *     The portfolio
     */
    public void setPortfolio(List<Portfolio_> portfolio) {
        this.portfolio = portfolio;
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
