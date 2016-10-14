package com.ozerian.app.service.impl;

import com.ozerian.app.service.WordCounterService;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * This class is an implementation of the appropriate service interface with
 * override methods for consume word and it's consuming quantity calculation.
 */
public class WordCounterServiceImpl implements WordCounterService {

    private static Map<String, AtomicInteger> wordsPassing = new ConcurrentHashMap<>();

    /**
     * This method receives a word and save it to the "storage"
     *
     * @param word
     * @return
     */
    @Override
    public boolean consumeWord(String word) {
        return false;
    }

    /**
     *
     * @param word
     * @return
     */
    @Override
    public AtomicInteger countWordPassing(String word) {
        return null;
    }

    public static Map<String, AtomicInteger> getWordsPassing() {
        return wordsPassing;
    }

}
