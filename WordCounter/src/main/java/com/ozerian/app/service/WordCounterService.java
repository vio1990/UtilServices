package com.ozerian.app.service;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * This interface for definition of main service methods.
 */
public interface WordCounterService {

    boolean consumeWord(String word);

    AtomicInteger countWordPassing(String word);
}
