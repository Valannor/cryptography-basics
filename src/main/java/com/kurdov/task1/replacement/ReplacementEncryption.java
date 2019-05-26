package com.kurdov.task1.replacement;

import java.util.*;

public class ReplacementEncryption {

    private static Map<Character, Integer> conter(String s) {
        Map<Character, Integer> map = new HashMap<>();

        for (char c : s.toCharArray()) {
            Integer count = map.get(c);
            if (count == null || count == 0) {
                map.put(c, 1);
            } else {
                map.put(c, ++count);
            }
        }

        return map;
    }

    private static void printInValueOrder(Map<Character, Integer> map) {
        List<Map.Entry<Character, Integer>> list = new ArrayList<>();
        list.addAll(map.entrySet());

        list.sort((o1, o2) -> {
            if (o1.getValue() > o2.getValue())
                return -1;
            if (o1.getValue() < o2.getValue())
                return 1;
            return 0;
        });

        for (Map.Entry entry : list) {
            System.out.println(entry);
        }
    }

    public static void main(String[] args) {
        printInValueOrder(conter("уехся ебя еуюкячшья геиьзбеыгя сенет ия бещбшэянш. " +
                "шхеьгет ш бшугет, ияузн ебя чикзияья ыйсечшвй йукеъй ш ыйуи уеьиге чикзияья " +
                "e есшб геиьзбеызг йщ чаёйбйь еууйся хеьечзбгй; я гяг ёуяья кзияуи сяьижз. уе " +
                "чёз жзёузке геиьру чаюкахбйьш есшб ия скйхшн ши чеьыизт йукеъа. ш чёз " +
                "ъаьш щшчзфебигш ш эзьзфебигш. юеуенй ыуе ыйсечшвз ч ёчезт яьыбеёуш хьеуяье шф эзьшген,"));
    }
}
