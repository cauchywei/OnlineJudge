package org.sssta.oj.response.user;

import org.sssta.oj.models.User;
import org.sssta.oj.response.Response;
import org.sssta.oj.response.StateCode;

/**
 * Created by cauchywei on 15/8/27.
 */
public class UserPublicInfoResponse extends Response {

    User.PublicUser user;

    UserPublicInfoResponse(User.PublicUser user) {
        this.status = StateCode.OK;
        this.user = user;
    }
}