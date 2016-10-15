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

    /**
     * Constructor with initialization of TreeSet object for ready players
     * storing.
     */
    public FindOpponentServiceImpl() {
        this.readyPlayers = new TreeSet<>();
    }

    /**
     * This method is for registration of new player, which is
     * ready for new game. There is a check if player is ready. If so,
     * player is success added to the readyPlayers Set and method return "true".
     * In the case if player is not ready for game or such player is already
     * present in readyPlayers set - method returns "false".
     *
     * @param newPlayer Player for saving to the ready players set.
     * @return boolean if player is saved success.
     */
    @Override
    public boolean registerReadyPlayer(Player newPlayer) {

        if (checkIfPlayerIsReady(newPlayer)) {
            boolean addNewPlayer = readyPlayers.add(newPlayer);
            return addNewPlayer;
        } else {
            return false;
        }
    }

    /**
     * Private util method for checking if player is ready for the game.
     * If so - returns "true", in other case - returns "false".
     *
     * @param newPlayer Player for checking.
     * @return boolean if player is ready for the game.
     */
    private boolean checkIfPlayerIsReady(Player newPlayer) {
        if (newPlayer.isReady()) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Implemented service method with logic of search the opponent. At first, there is a check
     * if player is ready for the game. After that, method defines closest less and closest higher
     * player and checks all possible variants in findOpponentCheckAllCases().
     *
     * @param readyPlayer Player ready player for opponent search.
     * @return Player found opponent.
     * @throws ThereIsNoOpponentException Occurs when the Set with players is empty.
     */
    @Override
    public Player findOpponent(Player readyPlayer) throws ThereIsNoOpponentException {

        if (readyPlayer != null) {
            Player closestLessOrEqualPlayer = readyPlayers.floor(readyPlayer);
            Player closestHigherPlayer = readyPlayers.higher(readyPlayer);
            Player opponent = findOpponentCheckAllCases(readyPlayer, closestLessOrEqualPlayer, closestHigherPlayer);
            return opponent;
        } else {
            throw new NullPointerException("Transferred player is null!");
        }
    }

    /**
     * This method checks all possible variants, when one from the closest opponents is null.
     * And if two possible opponents are not null, method defines delta values and compare their.
     * After this we have found opponent and remove it from the set with ready players.
     *
     * @param readyPlayer Player for search the opponent.
     * @param closestLessOrEqualPlayer Player closest less or equal Player.
     * @param closestHigherPlayer closest higher or equal Player.
     * @return Player found opponent.
     * @throws ThereIsNoOpponentException Occurs when the Set with players is empty.
     */
    private Player findOpponentCheckAllCases(Player readyPlayer, Player closestLessOrEqualPlayer, Player closestHigherPlayer) throws ThereIsNoOpponentException {

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

    /**
     * Private method which calculates delta values between readyPlayer and it's closest
     * less or equal player and closest higher player and the same ready player. After
     * delta values comparison we define the closest opponent for the ready player.
     * In case when delta values are equals, the method choose closest less Player.
     *
     * @param readyPlayer Player for which we are searching an opponent.
     * @param closestLessOrEqualPlayer Player least less or equal player for the set.
     * @param closestHigherPlayer Player least higher player from the set.
     * @return Player the closest opponent.
     */
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

    /**
     * Set Set for ready players storage.
     *
     * @return NavigableSet object for players storage.
     */
    public NavigableSet<Player> getReadyPlayers() {
        return readyPlayers;
    }

}
