package org.sssta.oj.response;

/**
 * Created by cauchywei on 15/8/27.
 */
public class IllegalArgumentResponse extends Response {

    IllegalArgumentResponse(String arg) {
        this(arg,"");
    }

    public IllegalArgumentResponse(String arg, String desc) {
        status = StateCode.ILLEGAL_ARGUMENT;
        message = "Illegal argument: " + arg + ". " + desc;
    }
}
