package com.ozerian.app.service;

import com.ozerian.app.exceptions.ThereIsNoOpponentException;
import com.ozerian.app.model.Player;

/**
 * Interface for service with appropriate defined methods.
 */
public interface FindOpponentService {

    boolean registerReadyPlayer(Player newPlayer);

    Player findOpponent(Player readyPlayer) throws ThereIsNoOpponentException;
}
