package com.ozerian.app.service;

import com.ozerian.app.exceptions.ThereIsNoOpponentException;
import com.ozerian.app.model.Player;

import java.util.NavigableSet;
import java.util.TreeSet;

/**
 * This class is an implementation of the appropriate service interface with
 * override methods for new player's registration word and it's consuming quantity calculation.
 */
public class FindOpponentServiceImpl implements FindOpponentService {

    private NavigableSet<Player> readyPlayers;

    public FindOpponentServiceImpl() {
        this.readyPlayers = new TreeSet<>();
    }

    @Override
    public boolean registerReadyPlayer(Player newPlayer) {

        if (checkIfPlayerIsReady(newPlayer)) {
            boolean addNewPlayer = readyPlayers.add(newPlayer);
            return addNewPlayer;
        } else {
            return false;
        }
    }

    private boolean checkIfPlayerIsReady(Player newPlayer) {
        if (newPlayer.isReady()) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public Player findOpponent(Player readyPlayer) throws ThereIsNoOpponentException {

        if (readyPlayer != null) {
            Player closestLessOrEqualPlayer = readyPlayers.floor(readyPlayer);
            Player closestHigherPlayer = readyPlayers.higher(readyPlayer);
            return findOpponentAllCases(readyPlayer, closestLessOrEqualPlayer, closestHigherPlayer);
        } else {
            throw new NullPointerException("Transferred player is null!");
        }
    }

    private Player findOpponentAllCases(Player readyPlayer, Player closestLessOrEqualPlayer, Player closestHigherPlayer) throws ThereIsNoOpponentException {

        if (closestHigherPlayer == null && closestLessOrEqualPlayer == null) {
            throw new ThereIsNoOpponentException("There are no ready opponents!");
        } else if (closestHigherPlayer == null && closestLessOrEqualPlayer != null) {
            readyPlayers.remove(closestLessOrEqualPlayer);
            return closestLessOrEqualPlayer;
        } else if (closestHigherPlayer != null && closestLessOrEqualPlayer == null) {
            readyPlayers.remove(closestHigherPlayer);
            return closestHigherPlayer;
        } else {
            return findDeltaAndOpponent(readyPlayer, closestLessOrEqualPlayer, closestHigherPlayer);
        }
    }

    private Player findDeltaAndOpponent(Player readyPlayer, Player closestLessOrEqualPlayer, Player closestHigherPlayer) {
        double lessOrEqualDelta = readyPlayer.getRating() - closestLessOrEqualPlayer.getRating();
        double higherDelta = closestHigherPlayer.getRating() - readyPlayer.getRating();

        if (lessOrEqualDelta > higherDelta) {
            readyPlayers.remove(closestHigherPlayer);
            return closestHigherPlayer;
        } else {
            readyPlayers.remove(closestLessOrEqualPlayer);
            return closestLessOrEqualPlayer;
        }
    }

    public NavigableSet<Player> getReadyPlayers() {
        return readyPlayers;
    }

}
