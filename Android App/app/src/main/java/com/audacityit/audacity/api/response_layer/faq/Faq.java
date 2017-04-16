
package com.audacityit.audacity.api.response_layer.faq;

import java.util.ArrayList;
import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Faq {

    @SerializedName("items")
    @Expose
    private List<Faq_> faq = new ArrayList<Faq_>();
    @SerializedName("error")
    @Expose
    private boolean error;
    @SerializedName("message")
    @Expose
    private String message;



    /**
     * 
     * @return
     *     The faq
     */
    public List<Faq_> getFaq() {
        return faq;
    }

    /**
     * 
     * @param faq
     *     The faq
     */
    public void setFaq(List<Faq_> faq) {
        this.faq = faq;
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
