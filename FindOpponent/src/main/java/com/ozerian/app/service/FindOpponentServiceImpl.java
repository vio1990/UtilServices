package com.ozerian.app.service;

import com.ozerian.app.model.Player;

import java.util.Set;
import java.util.TreeSet;

/**
 * This class is an implementation of the appropriate service interface with
 * override methods for new player's registration word and it's consuming quantity calculation.
 */
public class FindOpponentServiceImpl implements FindOpponentService {

    private Set<Player> readyPlayers;

    public FindOpponentServiceImpl() {
        this.readyPlayers = new TreeSet<>();
    }

    @Override
    public boolean registerReadyPlayer(Player newPlayer) {
        return false;
    }

    @Override
    public Player findOpponent(Player readyPlayer) {
        return null;
    }

    public Set<Player> getReadyPlayers() {
        return readyPlayers;
    }

}
