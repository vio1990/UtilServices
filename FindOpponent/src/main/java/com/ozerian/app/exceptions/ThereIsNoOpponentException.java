package com.ozerian.app.exceptions;

/**
 * This exception occurs when there is no opponents in the set
 * with another players.
 */
public class ThereIsNoOpponentException extends Exception {

    public ThereIsNoOpponentException(String message) {
        super(message);
    }
}
