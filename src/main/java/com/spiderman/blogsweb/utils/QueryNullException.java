package com.spiderman.blogsweb.utils;

/**
 * 查询null异常
 */
public class QueryNullException extends Exception{

    public QueryNullException() {
        super();
    }

    public QueryNullException(String message) {
        super(message);
    }

    public QueryNullException(String message, Throwable cause) {
        super(message, cause);
    }

    public QueryNullException(Throwable cause) {
        super(cause);
    }
}
