package org.sssta.oj.response.user;

import org.sssta.oj.response.Response;
import org.sssta.oj.response.StateCode;

/**
 * Created by cauchywei on 15/8/26.
 */
public class UserExistResponse extends Response {
    public UserExistResponse(String username) {
        status = StateCode.USER_ALREADY_EXISTED;
        message = "the user named " + username + " already exist.";
    }
}
