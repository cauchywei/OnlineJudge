package org.sssta.oj.response;

/**
 * Created by cauchywei on 15/8/26.
 */
public class MissingArgumentResponse extends Response {
    public MissingArgumentResponse(String arg){
        status = StateCode.MISSING_ARGUMENT;
        message = "Missing Argument: " + arg + ".";
    }
}
