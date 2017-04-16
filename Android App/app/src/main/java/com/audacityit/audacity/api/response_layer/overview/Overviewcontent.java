
package com.audacityit.audacity.api.response_layer.overview;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
public class Overviewcontent {

    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("background")
    @Expose
    private String background;
    @SerializedName("logo")
    @Expose
    private String logo;
    @SerializedName("moto")
    @Expose
    private String moto;
    @SerializedName("count1text")
    @Expose
    private String count1text;
    @SerializedName("count1no")
    @Expose
    private String count1no;
    @SerializedName("count2text")
    @Expose
    private String count2text;
    @SerializedName("count2no")
    @Expose
    private String count2no;
    @SerializedName("count3text")
    @Expose
    private String count3text;
    @SerializedName("count3no")
    @Expose
    private String count3no;

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
     *     The moto
     */
    public String getMoto() {
        return moto;
    }

    /**
     *
     * @param moto
     *     The moto
     */
    public void setMoto(String moto) {
        this.moto = moto;
    }

    /**
     *
     * @return
     *     The count1text
     */
    public String getCount1text() {
        return count1text;
    }

    /**
     *
     * @param count1text
     *     The count1text
     */
    public void setCount1text(String count1text) {
        this.count1text = count1text;
    }

    /**
     *
     * @return
     *     The count1no
     */
    public String getCount1no() {
        return count1no;
    }

    /**
     *
     * @param count1no
     *     The count1no
     */
    public void setCount1no(String count1no) {
        this.count1no = count1no;
    }

    /**
     *
     * @return
     *     The count2text
     */
    public String getCount2text() {
        return count2text;
    }

    /**
     *
     * @param count2text
     *     The count2text
     */
    public void setCount2text(String count2text) {
        this.count2text = count2text;
    }

    /**
     *
     * @return
     *     The count2no
     */
    public String getCount2no() {
        return count2no;
    }

    /**
     *
     * @param count2no
     *     The count2no
     */
    public void setCount2no(String count2no) {
        this.count2no = count2no;
    }

    /**
     *
     * @return
     *     The count3text
     */
    public String getCount3text() {
        return count3text;
    }

    /**
     *
     * @param count3text
     *     The count3text
     */
    public void setCount3text(String count3text) {
        this.count3text = count3text;
    }

    /**
     *
     * @return
     *     The count3no
     */
    public String getCount3no() {
        return count3no;
    }

    /**
     *
     * @param count3no
     *     The count3no
     */
    public void setCount3no(String count3no) {
        this.count3no = count3no;
    }

}