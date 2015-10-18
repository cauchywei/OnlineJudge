package org.sssta.oj.response.user;

import org.sssta.oj.response.Response;
import org.sssta.oj.response.StateCode;

/**
 * Created by cauchywei on 15/8/27.
 */
public class PasswordIncorrectResponse extends Response {
    public PasswordIncorrectResponse() {
        status = StateCode.PASSWORD_ERROR;
        message = "password incorrect.";
    }
}
