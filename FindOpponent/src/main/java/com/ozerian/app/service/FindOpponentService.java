package com.ozerian.app.service;

import com.ozerian.app.model.Player;

public interface FindOpponentService {

    boolean registerReadyPlayer(Player newPlayer);

    Player findOpponent(Player readyPlayer);
}
