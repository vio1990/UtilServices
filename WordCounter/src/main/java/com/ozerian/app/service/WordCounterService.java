package com.ozerian.app.service;

/**
 * This interface for definition of main service methods.
 */
public interface WordCounterService {

    boolean consumeWord(String word);

    int countWordPassing(String word);
}
