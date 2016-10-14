package com.ozerian.app.service;

import com.ozerian.app.model.Player;
import org.junit.Before;
import org.junit.Test;

import java.util.Set;
import java.util.TreeSet;

import static org.junit.Assert.*;

public class FindOpponentServiceImplTest {

    private FindOpponentServiceImpl findOpponentService;
    Set<Player> readyPlayers;

    @Before
    public void setUp() throws Exception {
        readyPlayers = new TreeSet<>();
        findOpponentService = new FindOpponentServiceImpl(readyPlayers);
        readyPlayers.add(new Player("1", 13.15, true));
        readyPlayers.add(new Player("2", 13.15, true));
        readyPlayers.add(new Player("3", 13.17, true));
        readyPlayers.add(new Player("4", 13.14, true));
        readyPlayers.add(new Player("5", 75.45, false));
        readyPlayers.add(new Player("6", 175.8, true));
        readyPlayers.add(new Player("7", 9.8, true));
        readyPlayers.add(new Player("8", 2.55, false));
        readyPlayers.add(new Player("9", 0, true));
        readyPlayers.add(new Player("10", 2.55, true));
        readyPlayers.add(new Player("11", 175.15, true));
        readyPlayers.add(new Player("12", 176.15, true));

    }

    @Test
    public void comparePlayersRatingEqual() throws Exception {
        Player readyPlayer1 = new Player("1", 11.14, true);
        Player readyPlayer2 = new Player("2", 11.14, true);
        assertEquals(0,  readyPlayer1.compareTo(readyPlayer2));
    }

    @Test
    public void comparePlayersRatingHigher() throws Exception {
        Player readyPlayer1 = new Player("1", 12.12, true);
        Player readyPlayer2 = new Player("2", 11.14, true);
        assertEquals(1,  readyPlayer1.compareTo(readyPlayer2));
    }

    @Test
    public void comparePlayersRatingLower() throws Exception {
        Player readyPlayer1 = new Player("1", 2.12, true);
        Player readyPlayer2 = new Player("2", 11.14, true);
        assertEquals(-1,  readyPlayer1.compareTo(readyPlayer2));
    }

    @Test
    public void registerReadyPlayer() throws Exception {
        Player readyPlayer = new Player("13", 11.14, true);
        findOpponentService.registerReadyPlayer(readyPlayer);
        assertEquals(true,  findOpponentService.registerReadyPlayer(readyPlayer));
    }

    @Test
    public void registerUnreadyPlayer() throws Exception {
        Player readyPlayer = new Player("14", 12.14, false);
        findOpponentService.registerReadyPlayer(readyPlayer);
        assertEquals(false,  findOpponentService.registerReadyPlayer(readyPlayer));

    }

    @Test
    public void findOpponentFirstEqualsRating() throws Exception {
        Player readyPlayer = new Player("15", 13.15, true);
        Player actualOpponent = findOpponentService.findOpponent(readyPlayer);
        Player expectedOpponent = new Player("1", 13.15, true);
        assertEquals(expectedOpponent, actualOpponent);
        assertEquals(false, readyPlayers.contains(actualOpponent));
    }

    @Test
    public void findOpponentFirstHighClosestRating() throws Exception {
        Player readyPlayer = new Player("16", 7.3, true);
        Player actualOpponent = findOpponentService.findOpponent(readyPlayer);
        Player expectedOpponent = new Player("7", 9.8, true);
        assertEquals(expectedOpponent, actualOpponent);
        assertEquals(false, readyPlayers.contains(actualOpponent));
    }

    @Test
    public void findOpponentFirstLowClosestRating() throws Exception {
        Player readyPlayer = new Player("17", 180.0, true);
        Player actualOpponent = findOpponentService.findOpponent(readyPlayer);
        Player expectedOpponent = new Player("12", 176.15, true);
        assertEquals(expectedOpponent, actualOpponent);
        assertEquals(false, readyPlayers.contains(actualOpponent));
    }

    @Test
    public void findOpponentBetweenTwoClosestRating() throws Exception {
        Player readyPlayer = new Player("18", 13.16, true);
        Player actualOpponent = findOpponentService.findOpponent(readyPlayer);
        Player expectedOpponent = new Player("1", 13.15, true);
        assertEquals(expectedOpponent, actualOpponent);
        assertEquals(false, readyPlayers.contains(actualOpponent));
    }



}