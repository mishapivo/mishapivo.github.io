
package com.audacityit.audacity.api.response_layer.all;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CPAllData {

    @SerializedName("data")
    @Expose
    private Data data;

    /**
     * 
     * @return
     *     The data
     */
    public Data getData() {
        return data;
    }

    /**
     * 
     * @param data
     *     The data
     */
    public void setData(Data data) {
        this.data = data;
    }

}
