package org.sssta.oj.response;

/**
 * Created by cauchywei on 15/8/27.
 */
public class ServerErrorResponse extends Response{
    public ServerErrorResponse() {
        status = StateCode.SERVER_ERROR;
        message = "Server error.";
    }
}
