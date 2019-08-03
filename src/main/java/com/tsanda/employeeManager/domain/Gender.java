package com.tsanda.employeeManager.domain;

import java.util.HashMap;
import java.util.Map;

public enum Gender {
    MALE(0),
    FEMALE(1);

    private int value;
    private static Map map = new HashMap();

    private Gender(int value) {
        this.value = value;
    }

    static {
        for (Gender gender : Gender.values()) {
            map.put(gender.value, gender);
        }
    }

    public static Gender valueOf(int value) {
        return (Gender)map.get(value);
    }

    public int getValue() {
        return value;
    }
}
