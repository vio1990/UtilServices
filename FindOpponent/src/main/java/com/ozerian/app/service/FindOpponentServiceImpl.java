package com.ozerian.app.service;

import com.ozerian.app.model.Player;

/**
 * This class is an implementation of the appropriate service interface with
 * override methods for new player's registration word and it's consuming quantity calculation.
 */
public class FindOpponentServiceImpl implements FindOpponentService {

    @Override
    public boolean registerReadyPlayer() {
        return false;
    }

    @Override
    public Player findOpponent(Player readyPlayer) {
        return null;
    }
}
