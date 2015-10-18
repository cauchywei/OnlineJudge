package org.sssta.oj.response;

/**
 * Created by cauchywei on 15/8/27.
 */
public class UnknownErrorResponse extends Response {

    public UnknownErrorResponse() {
        status = StateCode.UNKNOWN_ERROR;
        message = "Unknown error.";
    }

}
