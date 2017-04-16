package com.audacityit.audacity.api.response_layer;

import com.audacityit.audacity.api.response_layer.all.CPAllData;
import com.audacityit.audacity.api.response_layer.client.Client;

/**
 * Created by imranaits on 10/17/16.
 */

public class CPAllResponse extends APIResponse{


    public CPAllData getCpAllData() {
        return cpAllData;
    }

    public void setCpAllData(CPAllData cpAllData) {
        this.cpAllData = cpAllData;
    }

    public CPAllData cpAllData;
}
