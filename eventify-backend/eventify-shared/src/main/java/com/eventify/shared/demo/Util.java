package com.eventify.shared.demo;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public abstract class Util {

    public static <T> List<T> emptyIfNull(List<T> list) {
        return list == null ? new ArrayList<>() : list;
    }

    public static <T> Set<T> emptyIfNull(Set<T> set) {
        return set == null ? new HashSet<>() : set;
    }
}
