package com.ozerian.app.service;

import com.ozerian.app.exceptions.ThereIsNoOpponentException;
import com.ozerian.app.model.Player;
import org.junit.Before;
import org.junit.Test;

import java.util.Set;

import static org.junit.Assert.*;

public class FindOpponentServiceImplTest {

    private FindOpponentServiceImpl findOpponentService;
    Set<Player> readyPlayersList;

    @Before
    public void setUp() throws Exception {
        findOpponentService = new FindOpponentServiceImpl();
        readyPlayersList = findOpponentService.getReadyPlayers();
    }

    @Test
    public void comparePlayersRatingEquals() throws Exception {
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
        boolean actual = findOpponentService.registerReadyPlayer(readyPlayer);
        assertEquals(true,  actual);
    }

    @Test
    public void findOpponentFirstEqualsRating() throws Exception {
        readyPlayersList.add(new Player("1", 13.14, true));
        readyPlayersList.add(new Player("2", 13.15, true));
        readyPlayersList.add(new Player("3", 13.16, true));
        Player readyPlayer = new Player("4", 13.15, true);
        Player actualOpponent = findOpponentService.findOpponent(readyPlayer);
        Player expectedOpponent = new Player("2", 13.15, true);
        assertEquals(expectedOpponent, actualOpponent);
        assertEquals(false, readyPlayersList.contains(actualOpponent));
    }

    @Test
    public void findOpponentAllEqualsRating() throws Exception {
        readyPlayersList.add(new Player("1", 13.15, true));
        readyPlayersList.add(new Player("2", 13.15, true));
        readyPlayersList.add(new Player("3", 13.15, true));
        Player readyPlayer = new Player("4", 13.15, true);
        Player actualOpponent = findOpponentService.findOpponent(readyPlayer);
        Player expectedOpponent = new Player("1", 13.15, true);
        assertEquals(expectedOpponent, actualOpponent);
        assertEquals(false, readyPlayersList.contains(actualOpponent));
    }

    @Test
    public void findOpponentFirstHighClosestRating() throws Exception {
        readyPlayersList.add(new Player("1", 8.73, true));
        readyPlayersList.add(new Player("2", 9.6, true));
        readyPlayersList.add(new Player("3", 9.9, true));
        Player readyPlayer = new Player("4", 9.8, true);
        Player actualOpponent = findOpponentService.findOpponent(readyPlayer);
        Player expectedOpponent = new Player("3", 9.9, true);
        assertEquals(expectedOpponent, actualOpponent);
        assertEquals(false, readyPlayersList.contains(actualOpponent));
    }

    @Test
    public void findOpponentFirstLowClosestRating() throws Exception {
        readyPlayersList.add(new Player("1", 8.73, true));
        readyPlayersList.add(new Player("2", 25.6, true));
        readyPlayersList.add(new Player("3", 22.21, true));
        Player readyPlayer = new Player("4", 23.23, true);
        Player actualOpponent = findOpponentService.findOpponent(readyPlayer);
        Player expectedOpponent = new Player("3", 22.21, true);
        assertEquals(expectedOpponent, actualOpponent);
        assertEquals(false, readyPlayersList.contains(actualOpponent));
    }

    @Test
    public void findOpponentBetweenTwoClosestRating() throws Exception {
        readyPlayersList.add(new Player("1", 12.14, true));
        readyPlayersList.add(new Player("2", 12.16, true));
        Player readyPlayer = new Player("3", 12.15, true);
        Player actualOpponent = findOpponentService.findOpponent(readyPlayer);
        Player expectedOpponent = new Player("1", 12.14, true);
        assertEquals(expectedOpponent, actualOpponent);
        assertEquals(false, readyPlayersList.contains(actualOpponent));
    }

    @Test
    public void deleteOpponentFromSetTest() throws Exception {
        readyPlayersList.add(new Player("1", 12.12, true));
        readyPlayersList.add(new Player("2", 13.13, true));
        Player readyPlayer = new Player("3", 13.2, true);
        Player expectedOpponent = new Player("2", 13.13, true);
        assertEquals(true, readyPlayersList.contains(expectedOpponent));
        Player actualOpponent = findOpponentService.findOpponent(readyPlayer);
        assertEquals(expectedOpponent, actualOpponent);
        assertEquals(false, readyPlayersList.contains(actualOpponent));
    }

    @Test
    public void findOpponentHigherNullTest() throws Exception {
        readyPlayersList.add(new Player("1", 12.12, true));
        Player readyPlayer = new Player("2", 13.2, true);
        Player expectedOpponent = new Player("1", 12.12, true);
        assertEquals(true, readyPlayersList.contains(expectedOpponent));
        Player actualOpponent = findOpponentService.findOpponent(readyPlayer);
        assertEquals(expectedOpponent, actualOpponent);
        assertEquals(false, readyPlayersList.contains(actualOpponent));
    }

    @Test
    public void findOpponentLowerOrEqualNullTest() throws Exception {
        readyPlayersList.add(new Player("1", 14.15, true));
        Player readyPlayer = new Player("2", 13.2, true);
        Player expectedOpponent = new Player("1", 14.15, true);
        assertEquals(true, readyPlayersList.contains(expectedOpponent));
        Player actualOpponent = findOpponentService.findOpponent(readyPlayer);
        assertEquals(expectedOpponent, actualOpponent);
        assertEquals(false, readyPlayersList.contains(actualOpponent));
    }

    @Test(expected = NullPointerException.class)
    public void nullPointerRegisterNullTest() throws Exception {
        Player nullPlayer = null;
        findOpponentService.registerReadyPlayer(nullPlayer);
    }

    @Test(expected = NullPointerException.class)
    public void nullPointerFindOpponentNullTest() throws Exception {
        Player nullPlayer = null;
        findOpponentService.findOpponent(nullPlayer);
    }

    @Test(expected = ThereIsNoOpponentException.class)
    public void ThereIsNoOpponentTest() throws Exception {
        Player singlePlayer = new Player("2", 15.15, true);
        findOpponentService.findOpponent(singlePlayer);
    }

}