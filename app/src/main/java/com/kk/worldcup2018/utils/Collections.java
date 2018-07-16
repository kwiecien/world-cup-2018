package com.kk.worldcup2018.utils;

import java.util.List;

public class Collections {

    public static <T> boolean isNotEmpty(List<T> list) {
        return list != null && !list.isEmpty();
    }

}
