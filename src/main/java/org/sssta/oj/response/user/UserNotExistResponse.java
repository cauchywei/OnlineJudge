package org.sssta.oj.response.user;

import org.sssta.oj.response.Response;
import org.sssta.oj.response.StateCode;

/**
 * Created by cauchywei on 15/8/26.
 */
public class UserNotExistResponse extends Response {
    public UserNotExistResponse(String username) {
        status = StateCode.USER_NOT_EXIST;
        message = "the user named " + username + " don\'t exist.";
    }

    public UserNotExistResponse(Integer userId) {
        status = StateCode.USER_NOT_EXIST;
        message = "the user " + userId + " don\'t exist.";
    }
}
