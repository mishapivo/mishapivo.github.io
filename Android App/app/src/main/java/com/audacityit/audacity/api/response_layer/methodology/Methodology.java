
package com.audacityit.audacity.api.response_layer.methodology;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class Methodology {

    @SerializedName("methodologycontent")
    @Expose
    private Methodologycontent methodologycontent;
    @SerializedName("methodologyitems")
    @Expose
    private List<Methodologyitem> methodologyitems = new ArrayList<Methodologyitem>();
    @SerializedName("error")
    @Expose
    private boolean error;
    @SerializedName("message")
    @Expose
    private String message;

    /**
     * 
     * @return
     *     The methodologycontent
     */
    public Methodologycontent getMethodologycontent() {
        return methodologycontent;
    }

    /**
     * 
     * @param methodologycontent
     *     The methodologycontent
     */
    public void setMethodologycontent(Methodologycontent methodologycontent) {
        this.methodologycontent = methodologycontent;
    }

    /**
     * 
     * @return
     *     The methodologyitems
     */
    public List<Methodologyitem> getMethodologyitems() {
        return methodologyitems;
    }

    /**
     * 
     * @param methodologyitems
     *     The methodologyitems
     */
    public void setMethodologyitems(List<Methodologyitem> methodologyitems) {
        this.methodologyitems = methodologyitems;
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
