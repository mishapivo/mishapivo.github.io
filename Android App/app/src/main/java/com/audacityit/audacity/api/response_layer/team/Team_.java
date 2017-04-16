
package com.audacityit.audacity.api.response_layer.team;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Team_ {

    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("designation")
    @Expose
    private String designation;
    @SerializedName("photo")
    @Expose
    private String photo;

    public Team_(String name, String designation, String photo) {
        this.name = name;
        this.designation = designation;
        this.photo = photo;
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
     *     The designation
     */
    public String getDesignation() {
        return designation;
    }

    /**
     * 
     * @param designation
     *     The designation
     */
    public void setDesignation(String designation) {
        this.designation = designation;
    }

    /**
     * 
     * @return
     *     The photo
     */
    public String getPhoto() {
        return photo;
    }

    /**
     * 
     * @param photo
     *     The photo
     */
    public void setPhoto(String photo) {
        this.photo = photo;
    }

}
