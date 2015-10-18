package org.sssta.oj.response;

/**
 * Created by cauchywei on 15/8/26.
 */
public class DataResponse<T> extends Response {
    T result;

    public DataResponse(T result) {
        this.result = result;
    }
}
