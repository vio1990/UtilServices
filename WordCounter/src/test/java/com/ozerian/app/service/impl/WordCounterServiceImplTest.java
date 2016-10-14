package com.ozerian.app.service.impl;

import org.junit.Before;
import org.junit.Test;

import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

import static org.junit.Assert.*;

public class WordCounterServiceImplTest {

    private WordCounterServiceImpl wordCounterService;
    Map<String, AtomicInteger> wordsStorage;

    @Before
    public void setUp() throws Exception {
        wordCounterService = new WordCounterServiceImpl();
        wordsStorage = wordCounterService.getWordsStorage();
        wordsStorage.put("word", new AtomicInteger(1));
        wordsStorage.put("house", new AtomicInteger(1));
        wordsStorage.put("car", new AtomicInteger(1));
        wordsStorage.put("girl", new AtomicInteger(1));

    }

    @Test
    public void consumeNewWordTestFirstTime() throws Exception {
        String newWord = "another";
        assertEquals(false, wordsStorage.containsKey(newWord));
        wordCounterService.consumeWord(newWord);
        assertEquals(true, wordsStorage.containsKey(newWord));
        assertEquals(1, wordsStorage.get(newWord).get());
    }

    @Test
    public void consumeExistingWordTestSecondTime() throws Exception {
        String existingWord = "word";

        assertEquals(true, wordsStorage.containsKey(existingWord));
        wordCounterService.consumeWord(existingWord);
        assertEquals(true, wordsStorage.containsKey(existingWord));
        assertEquals(2, wordsStorage.get(existingWord).get());
        assertEquals(2, wordCounterService.countWordPassing(existingWord));

        wordCounterService.consumeWord(existingWord);
        assertEquals(3, wordsStorage.get(existingWord).get());
        assertEquals(3, wordCounterService.countWordPassing(existingWord));

        wordCounterService.consumeWord(existingWord);
        assertEquals(4, wordsStorage.get(existingWord).get());
        assertEquals(4, wordCounterService.countWordPassing(existingWord));
    }

    @Test
    public void ignoreCaseNewWordConsumeTest() throws Exception {
        String existingWord = "WoRd";
        assertEquals(true, wordCounterService.consumeWord(existingWord));
        String ignoreCaseWord = existingWord.toLowerCase();
        assertEquals(2, wordsStorage.get(ignoreCaseWord).get());
    }

    @Test
    public void ignoreCaseNewWordPassTest() throws Exception {
        String existingWord = "CAR";
        assertEquals(1, wordCounterService.countWordPassing(existingWord));
        String ignoreCaseWord = existingWord.toLowerCase();
        assertEquals(1, wordsStorage.get(ignoreCaseWord).get());
    }

    @Test
    public void countNotConsumedWordPassing() throws Exception {
        String newWord = "game";
        assertEquals(false, wordsStorage.containsKey(newWord));
        assertEquals(0, wordCounterService.countWordPassing(newWord));
    }

    @Test(expected = NullPointerException.class)
    public void nullPointerConsumeNullTest() throws Exception {
        String newWord = null;
        wordCounterService.consumeWord(newWord);
    }

    @Test(expected = NullPointerException.class)
    public void nullPointerConsumeEmptyTest() throws Exception {
        String newWord = "";
        wordCounterService.consumeWord(newWord);
    }

    @Test(expected = NullPointerException.class)
    public void nullPointerExceptionCountNullTest() throws Exception {
        String newWord = null;
        wordCounterService.countWordPassing(newWord);
    }

    @Test(expected = NullPointerException.class)
    public void nullPointerExceptionCountEmptyTest() throws Exception {
        String newWord = "";
        wordCounterService.countWordPassing(newWord);
    }

}