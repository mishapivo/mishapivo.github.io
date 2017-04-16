
package com.audacityit.audacity.api.response_layer.overview;

import java.util.ArrayList;
import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Overview {

    @SerializedName("overviewcontent")
    @Expose
    private Overviewcontent overviewcontent;
    @SerializedName("overviewitems")
    @Expose
    private List<Overviewitem> overviewitems = new ArrayList<Overviewitem>();
    @SerializedName("error")
    @Expose
    private boolean error;
    @SerializedName("message")
    @Expose
    private String message;

    /**
     * 
     * @return
     *     The overviewcontent
     */
    public Overviewcontent getOverviewcontent() {
        return overviewcontent;
    }

    /**
     * 
     * @param overviewcontent
     *     The overviewcontent
     */
    public void setOverviewcontent(Overviewcontent overviewcontent) {
        this.overviewcontent = overviewcontent;
    }

    /**
     * 
     * @return
     *     The overviewitems
     */
    public List<Overviewitem> getOverviewitems() {
        return overviewitems;
    }

    /**
     * 
     * @param overviewitems
     *     The overviewitems
     */
    public void setOverviewitems(List<Overviewitem> overviewitems) {
        this.overviewitems = overviewitems;
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
