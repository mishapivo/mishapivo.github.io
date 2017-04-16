package com.audacityit.audacity.settings;

/**
 * Created by sourav_aits on 10/7/16.
 */

public class NetworkTimeoutException extends Exception {

    @Override
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    String message;

    public NetworkTimeoutException(String message) {
        this.message = message;
    }


}
