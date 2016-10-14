package com.ozerian.app.service.impl;

import com.ozerian.app.service.WordCounterService;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * This class is an implementation of the appropriate service interface with
 * override methods for consume word and it's consuming quantity calculation.
 * This service has "thread safe" logic for it's main functions.
 */
public class WordCounterServiceImpl implements WordCounterService {

    public static final int NEW_WORD_INITIAL_VALUE = 1;

    private Map<String, AtomicInteger> wordsStorage;

    /**
     * Constructor with initialization of "thread-safe"
     * ConcurrentHasMap object for words storage.
     */
    public WordCounterServiceImpl() {
        this.wordsStorage = new ConcurrentHashMap<>();
    }

    /**
     * This method receives a word with checking it for "null" value (if so,
     * it throws NullPointerException). After that this word is being checked if
     * it is present in "words storage" (Map). If so, we get AtomicInteger value of
     * this word and increment it. In other case, method put new word in "words storage"
     * with AtomicInteger initial value - "1" (first transfer to method).
     *
     * @param word String word for method's consuming.
     * @return boolean if method is executed success.
     */
    @Override
    public boolean consumeWord(String word) {
        if (word != null && !word.isEmpty()) {
            String consumedWord = word.toLowerCase();

            if (wordsStorage.containsKey(consumedWord)) {
                wordsStorage.get(consumedWord).incrementAndGet();
            } else {
                wordsStorage.put(consumedWord, new AtomicInteger(NEW_WORD_INITIAL_VALUE));
            }
            return true;

        } else {
            throw new NullPointerException("Passed word is null or empty!");
        }
    }

    /**
     * Method for getting of word passing's quantity with
     * check for null of input String. It returns appropriate
     * passing quantity if the input word is existing in "word storage" or
     * it returns "0" if this word are not in "word storage". If the input value
     * is null or empty this method throw NullPointerException.
     *
     * @param word String input word for checking.
     * @return int quantity of word passing's in consumeWord() service method.
     */
    @Override
    public int countWordPassing(String word) {
        if (word != null && !word.isEmpty()) {

            String checkingWord = word.toLowerCase();

            if (wordsStorage.containsKey(checkingWord)) {
                return wordsStorage.get(checkingWord).get();
            } else {
                return 0;
            }

        } else {
            throw new NullPointerException("Passed word is null or empty!");
        }
    }

    /**
     * Get Map in which words are storage.
     *
     * @return Map ConcurrentHasMap object with storage words.
     */
    public Map<String, AtomicInteger> getWordsStorage() {
        return wordsStorage;
    }

}
