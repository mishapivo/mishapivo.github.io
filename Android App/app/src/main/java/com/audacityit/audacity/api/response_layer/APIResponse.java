package com.audacityit.audacity.api.response_layer;

/**
 * Created by sourav_aits on 9/26/16.
 */
public class APIResponse {
    private int responseCode;

    public String getResponseMessage() {
        return responseMessage;
    }

    public void setResponseMessage(String responseMessage) {
        this.responseMessage = responseMessage;
    }

    private String responseMessage;

    public String getResponse() {
        return response;
    }

    public void setResponse(String response) {
        this.response = response;
    }

    public int getResponseCode() {
        return responseCode;
    }

    public void setResponseCode(int responseCode) {
        this.responseCode = responseCode;
        //setMessage(responseCode);
    }

    public boolean isError() {
        return isError;
    }

    public void setError(boolean error) {
        isError = error;
    }

    public boolean isError;

    private String response;


    /*public void  setMessage(int errorCode) {

        switch (errorCode) {
            case 200:
                setResponseMessage(Constants.RESPONSE_OK);
                break;

            case 201:
                setResponseMessage(Constants.RESPONSE_CREATED);
                break;

            case 202:
                setResponseMessage(Constants.RESPONSE_NON_AUTHORITATIVE_INFORMATION);
                break;

            case 203:
                setResponseMessage(Constants.RESPONSE_CREATED);
                break;

            case 204:
                setResponseMessage(Constants.RESPONSE_NO_CONTENT);
                break;

            case 205:
                setResponseMessage(Constants.RESPONSE_RESET_CONTENT);
                break;

            case 206:
                setResponseMessage(Constants.RESPONSE_PARTIAL_CONTENT);
                break;

            case 400:
                setResponseMessage(Constants.RESPONSE_BAD_REQUEST);
                break;

            case 401:
                setResponseMessage(Constants.RESPONSE_UNAUTHORIZED);
                break;

            case 402:
                setResponseMessage(Constants.RESPONSE_PAYMENT_REQUIRED);
                break;

            case 403:
                setResponseMessage(Constants.RESPONSE_FORBIDDEN);
                break;

            case 404:
                setResponseMessage(Constants.RESPONSE_NOT_FOUND);
                break;

            case 405:
                setResponseMessage(Constants.RESPONSE_METHOD_NOT_ALLOWED);
                break;

            case 406:
                setResponseMessage(Constants.RESPONSE_NOT_ACCEPTABLE);
                break;

            case 407:
                setResponseMessage(Constants.RESPONSE_PROXY_AUTHENTICATION_REQUIRED);
                break;

            case 408:
                setResponseMessage(Constants.RESPONSE_REQUEST_TIMEOUT);
                break;

            case 409:
                setResponseMessage(Constants.RESPONSE_CONFLICT);
                break;

            case 410:
                setResponseMessage(Constants.RESPONSE_GONE);
                break;

            case 411:
                setResponseMessage(Constants.RESPONSE_LENGTH_REQUIRED);
                break;

            case 412:
                setResponseMessage(Constants.RESPONSE_PRECONDITION_FAILED);
                break;

            case 413:
                setResponseMessage(Constants.RESPONSE_REQUEST_ENTITY_TOO_LARGE);
                break;

            case 414:
                setResponseMessage(Constants.RESPONSE_REQUEST_URI_TOO_LONG);
                break;

            case 415:
                setResponseMessage(Constants.RESPONSE_UNSUPPORTED_MEDIA_TYPE);
                break;

            case 416:
                setResponseMessage(Constants.RESPONSE_REQUESTED_RANGE_NOT_SATISFIABLE);
                break;

            case 417:
                setResponseMessage(Constants.RESPONSE_EXCEPTION_FAILED);
                break;

            case 500:
                setResponseMessage(Constants.RESPONSE_INTERNAL_SERVER_ERROR);
                break;

            case 501:
                setResponseMessage(Constants.RESPONSE_NOT_IMPLEMENTED);
                break;

            case 502:
                setResponseMessage(Constants.RESPONSE_BAD_GATEWAY);
                break;

           case 503:
                setResponseMessage(Constants.RESPONSE_SERVICE_UNAVAILABLE);
                break;

            case 504:
                setResponseMessage(Constants.RESPONSE_GATEWAY_TIMEOUT);
                break;

            case 505:
                setResponseMessage(Constants.RESPONSE_HTTP_VERSION_NOT_SUPPORTED);
                break;

            case 511:
                setResponseMessage(Constants.RESPONSE_NETWORK_AUTHENTICATION_REQUIRED);
                break;

        }
    }*/

}
