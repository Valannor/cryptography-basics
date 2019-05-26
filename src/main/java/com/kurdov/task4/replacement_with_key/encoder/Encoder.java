package com.kurdov.task4.replacement_with_key.encoder;

import java.util.Arrays;
import java.util.Comparator;

public class Encoder {

    public String encrypt(String data, String key) {

        int dataLength = data.length();
        int keyWidth = key.length();

        int tableDepth = dataLength / keyWidth;
        //1 for key-header and 1 more if extra letters left
        tableDepth = (dataLength % keyWidth != 0) ? tableDepth + 2 : tableDepth + 1;

        char[][] table = new char[keyWidth][tableDepth];
        int dataCursor = 0;
        int keyCursor = 0;
        for (int columnCursor = 0; columnCursor < tableDepth; columnCursor++) {
            for (int lineCursor = 0; lineCursor < keyWidth; lineCursor++) {
                if (columnCursor == 0) {
                    table[lineCursor][columnCursor] = key.charAt(keyCursor++);
                } else {
                    if (dataCursor < data.length()) {
                        table[lineCursor][columnCursor] = data.charAt(dataCursor++);
                    }
                }
            }
        }

        Arrays.sort(table, Comparator.comparingInt(o -> o[0]));

        StringBuilder encryptedData = new StringBuilder();
        for (char[] column : table) {
            for (int columnCursor = 1; columnCursor < column.length; columnCursor++) {
                char c = column[columnCursor];
                encryptedData.append(c);
            }
        }

        return encryptedData.toString();
    }

    public static void main(String[] args) {
        Encoder encoder = new Encoder();
        String s = encoder.encrypt("перестановочный шифр", "шифр");

        System.out.println(s);
    }
}
