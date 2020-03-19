package edu.iit.cs445.gheo1.common;

import java.util.concurrent.atomic.AtomicInteger;

public class UniqueIdGenerator {
    static AtomicInteger atomicInteger = new AtomicInteger();
    public static String getUniqueID() {
        return Integer.toString(atomicInteger.incrementAndGet());
    }
}
