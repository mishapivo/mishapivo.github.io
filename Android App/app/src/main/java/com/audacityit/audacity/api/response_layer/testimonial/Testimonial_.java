
package com.audacityit.audacity.api.response_layer.testimonial;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Testimonial_ {

    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("image")
    @Expose
    private String image;
    @SerializedName("project")
    @Expose
    private String project;
    @SerializedName("url")
    @Expose
    private String url;
    @SerializedName("feedback")
    @Expose
    private String feedback;

    public Testimonial_(String name, String image, String project, String url, String feedback) {
        this.name = name;
        this.image = image;
        this.project = project;
        this.url = url;
        this.feedback = feedback;
    }

    /**
     * 
     * @return
     *     The name
     */
    public String getName() {
        return name;
    }

    /**
     * 
     * @param name
     *     The name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 
     * @return
     *     The image
     */
    public String getImage() {
        return image;
    }

    /**
     * 
     * @param image
     *     The image
     */
    public void setImage(String image) {
        this.image = image;
    }

    /**
     * 
     * @return
     *     The project
     */
    public String getProject() {
        return project;
    }

    /**
     * 
     * @param project
     *     The project
     */
    public void setProject(String project) {
        this.project = project;
    }

    /**
     * 
     * @return
     *     The url
     */
    public String getUrl() {
        return url;
    }

    /**
     * 
     * @param url
     *     The url
     */
    public void setUrl(String url) {
        this.url = url;
    }

    /**
     * 
     * @return
     *     The feedback
     */
    public String getFeedback() {
        return feedback;
    }

    /**
     * 
     * @param feedback
     *     The feedback
     */
    public void setFeedback(String feedback) {
        this.feedback = feedback;
    }

}
