package com.ozerian.app.exceptions;

/**
 * This exceptions occurs when there is a try to register or
 * find opponent for unready Player.
 */
public class PlayerIsNotReadyException extends Exception {

    public PlayerIsNotReadyException(String message) {
        super(message);
    }
}
