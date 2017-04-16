
package com.audacityit.audacity.api.response_layer.getstarted;

import java.util.ArrayList;
import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Getstarted {

    @SerializedName("range1")
    @Expose
    private String range1;
    @SerializedName("range2")
    @Expose
    private String range2;
    @SerializedName("range3")
    @Expose
    private String range3;
    @SerializedName("range4")
    @Expose
    private String range4;
    @SerializedName("phone")
    @Expose
    private String phone;
    @SerializedName("email")
    @Expose
    private String email;
    @SerializedName("web")
    @Expose
    private String web;
    @SerializedName("tags")
    @Expose
    private List<Tag> tags = new ArrayList<Tag>();
    @SerializedName("error")
    @Expose
    private boolean error;
    @SerializedName("message")
    @Expose
    private String message;

    /**
     * 
     * @return
     *     The range1
     */
    public String getRange1() {
        return range1;
    }

    /**
     * 
     * @param range1
     *     The range1
     */
    public void setRange1(String range1) {
        this.range1 = range1;
    }

    /**
     * 
     * @return
     *     The range2
     */
    public String getRange2() {
        return range2;
    }

    /**
     * 
     * @param range2
     *     The range2
     */
    public void setRange2(String range2) {
        this.range2 = range2;
    }

    /**
     * 
     * @return
     *     The range3
     */
    public String getRange3() {
        return range3;
    }

    /**
     * 
     * @param range3
     *     The range3
     */
    public void setRange3(String range3) {
        this.range3 = range3;
    }

    /**
     * 
     * @return
     *     The range4
     */
    public String getRange4() {
        return range4;
    }

    /**
     * 
     * @param range4
     *     The range4
     */
    public void setRange4(String range4) {
        this.range4 = range4;
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
     *     The tags
     */
    public List<Tag> getTags() {
        return tags;
    }

    /**
     * 
     * @param tags
     *     The tags
     */
    public void setTags(List<Tag> tags) {
        this.tags = tags;
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
