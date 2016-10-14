package com.ozerian.app.service;

import com.ozerian.app.exceptions.PlayerIsNotReadyException;
import com.ozerian.app.exceptions.TheSamePlayersNickException;
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
        readyPlayersList.add(new Player("1", 13.15, true));
        readyPlayersList.add(new Player("2", 13.15, true));
        readyPlayersList.add(new Player("3", 13.17, true));
        readyPlayersList.add(new Player("4", 13.14, true));
        readyPlayersList.add(new Player("5", 75.45, true));
        readyPlayersList.add(new Player("6", 175.8, true));
        readyPlayersList.add(new Player("7", 9.8, true));
        readyPlayersList.add(new Player("8", 2.55, true));
        readyPlayersList.add(new Player("9", 0, true));
        readyPlayersList.add(new Player("10", 2.55, true));
        readyPlayersList.add(new Player("11", 175.15, true));
        readyPlayersList.add(new Player("12", 176.15, true));

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
        findOpponentService.registerReadyPlayer(readyPlayer);
        assertEquals(true,  findOpponentService.registerReadyPlayer(readyPlayer));
    }

    @Test
    public void findOpponentFirstEqualsRating() throws Exception {
        Player readyPlayer = new Player("15", 13.15, true);
        Player actualOpponent = findOpponentService.findOpponent(readyPlayer);
        Player expectedOpponent = new Player("1", 13.15, true);
        assertEquals(expectedOpponent, actualOpponent);
        assertEquals(false, readyPlayersList.contains(actualOpponent));
    }

    @Test
    public void findOpponentFirstHighClosestRating() throws Exception {
        Player readyPlayer = new Player("16", 7.3, true);
        Player actualOpponent = findOpponentService.findOpponent(readyPlayer);
        Player expectedOpponent = new Player("7", 9.8, true);
        assertEquals(expectedOpponent, actualOpponent);
        assertEquals(false, readyPlayersList.contains(actualOpponent));
    }

    @Test
    public void findOpponentFirstLowClosestRating() throws Exception {
        Player readyPlayer = new Player("17", 180.0, true);
        Player actualOpponent = findOpponentService.findOpponent(readyPlayer);
        Player expectedOpponent = new Player("12", 176.15, true);
        assertEquals(expectedOpponent, actualOpponent);
        assertEquals(false, readyPlayersList.contains(actualOpponent));
    }

    @Test
    public void findOpponentBetweenTwoClosestRating() throws Exception {
        Player readyPlayer = new Player("18", 13.16, true);
        Player actualOpponent = findOpponentService.findOpponent(readyPlayer);
        Player expectedOpponent = new Player("1", 13.15, true);
        assertEquals(expectedOpponent, actualOpponent);
        assertEquals(false, readyPlayersList.contains(actualOpponent));
    }

    @Test
    public void deleteOpponentFromListTest() throws Exception {
        Player readyPlayer = new Player("19", 76.1, true);
        Player expectedOpponent = new Player("5", 75.45, true);
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

    @Test(expected = PlayerIsNotReadyException.class)
    public void notReadyExceptionRegisterTest() throws Exception {
        Player unreadyPlayer = new Player("21", 25.15, false);
        findOpponentService.registerReadyPlayer(unreadyPlayer);
    }

    @Test(expected = PlayerIsNotReadyException.class)
    public void notReadyExceptionFindOpponentTest() throws Exception {
        Player unreadyPlayer = new Player("20", 15.15, false);
        findOpponentService.findOpponent(unreadyPlayer);
    }

    @Test(expected = TheSamePlayersNickException.class)
    public void theSamePlayerNameFindOpponentTest() throws Exception {
        Player theSameNickPlayer = new Player("2", 15.15, true);
        findOpponentService.findOpponent(theSameNickPlayer);
    }

    @Test(expected = TheSamePlayersNickException.class)
    public void theSamePlayerNameRegisterTest() throws Exception {
        Player theSameNickPlayer = new Player("3", 15.15, true);
        findOpponentService.registerReadyPlayer(theSameNickPlayer);
    }

}