package com.spiderman.blogsweb.utils;

/**
 * 校验异常类
 */
public class CheckoutException extends Exception {

    public CheckoutException() {
        super();
    }

    public CheckoutException(String message) {
        super(message);
    }

    public CheckoutException(String message, Throwable cause) {
        super(message, cause);
    }

    public CheckoutException(Throwable cause) {
        super(cause);
    }
}
