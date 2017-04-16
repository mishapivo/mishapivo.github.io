package com.audacityit.audacity.api.response_layer;

import com.audacityit.audacity.api.response_layer.version.VersionCode;

/**
 * Created by imranaits on 10/27/16.
 */

public class CPVersionResponse extends APIResponse {
    public VersionCode getVersionCode() {
        return versionCode;
    }

    public void setVersionCode(VersionCode versionCode) {
        this.versionCode = versionCode;
    }

    VersionCode versionCode;
}
