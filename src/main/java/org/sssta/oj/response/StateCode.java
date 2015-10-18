package org.sssta.oj.response;

/**
 * Created by cauchywei on 15/8/26.
 */
public class StateCode {

    public static final int OK = 0;
    public static final int UNKNOWN_ERROR = -1;

    /////////////////////////////////////////////////////////////////////
    public static final int MISSING_ARGUMENT = 100;
    public static final int ILLEGAL_ARGUMENT = 101;


    /////////////////////////////////////////////////////////////////////

    public static final int USER_ALREADY_EXISTED = 200;
    public static final int USER_NOT_EXIST = 201;

    public static final int PASSWORD_ERROR = 202;

    ////////////////////////////////////////////////////////////////////
    public static final int INVALID_TOKEN = 401;
    public static final int ACCESS_DENIED = 403;



    /////////////////////////////////////////////////////////////////////

    public static final int SERVER_ERROR = 500;

}