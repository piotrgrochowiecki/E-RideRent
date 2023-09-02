package com.piotgrochowiecki.eriderent.service.functionalInterfaces;

@FunctionalInterface
public interface TriPredicate<T, U, V> {
    boolean test(T t, U u, V v);
}

