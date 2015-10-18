package org.sssta.oj.response.user;

import org.sssta.oj.models.User;
import org.sssta.oj.response.Response;

/**
 * Created by cauchywei on 15/8/27.
 */
public class UserInfoResponse extends Response {

    User user;

    public UserInfoResponse(User user) {
        status = 0;
        this.user = user;
    }
}
