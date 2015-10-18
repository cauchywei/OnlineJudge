package org.sssta.oj.response;

/**
 * Created by cauchywei on 15/8/26.
 */
public class OkResponse extends Response {
    public OkResponse() {
        status = StateCode.OK;
        message = "";
    }
}
