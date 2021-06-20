package com.khrushchev.postcard.exception;

public class PostCardException extends Exception {

    public PostCardException() {
    }

    public PostCardException(String message) {
        super(message);
    }

    public PostCardException(String message, Throwable cause) {
        super(message, cause);
    }

    public PostCardException(Throwable cause) {
        super(cause);
    }
}
