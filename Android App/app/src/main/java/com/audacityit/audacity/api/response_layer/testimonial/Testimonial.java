
package com.audacityit.audacity.api.response_layer.testimonial;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class Testimonial {

    @SerializedName("items")
    @Expose
    private List<Testimonial_> testimonial = new ArrayList<Testimonial_>();
    @SerializedName("error")
    @Expose
    private boolean error;
    @SerializedName("message")
    @Expose
    private String message;

    /**
     * 
     * @return
     *     The testimonial
     */
    public List<Testimonial_> getTestimonial() {
        return testimonial;
    }

    /**
     * 
     * @param testimonial
     *     The testimonial
     */
    public void setTestimonial(List<Testimonial_> testimonial) {
        this.testimonial = testimonial;
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
