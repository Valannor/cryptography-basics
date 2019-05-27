package com.kurdov.task4.replacement_with_key.encoder;

import java.util.Arrays;
import java.util.Comparator;

public class Encoder {

    public String encrypt(String data, String key) {

        //Filling table for encryption
        char[][] table = fillEncryptionTable(data, key);

        //Sorting table for encryption
        Arrays.sort(table, Comparator.comparingInt(o -> o[0]));

        //Building result string
        StringBuilder encryptedData = new StringBuilder();
        for (char[] column : table) {
            for (int columnCursor = 1; columnCursor < column.length; columnCursor++) {
                char c = column[columnCursor];
                encryptedData.append(c);
            }
        }

        return encryptedData.toString();
    }

    private char[][] fillEncryptionTable(String data, String key) {
        int dataLength = data.length();
        int keyWidth = key.length();

        int tableDepth = dataLength / keyWidth;
        //1 for key-header and 1 more if extra letters left
        tableDepth = (dataLength % keyWidth != 0) ? tableDepth + 2 : tableDepth + 1;

        char[][] table = new char[keyWidth][tableDepth];
        int dataCursor = 0;
        int keyCursor = 0;

        //Filling the first line of the table
        for (int lineCursor = 0; lineCursor < keyWidth; lineCursor++) {
            table[lineCursor][0] = key.charAt(keyCursor++);
        }

        //Filling the table
        for (int columnCursor = 1; columnCursor < tableDepth; columnCursor++) {
            for (int lineCursor = 0; lineCursor < keyWidth; lineCursor++) {
                if (dataCursor < data.length()) {
                    table[lineCursor][columnCursor] = data.charAt(dataCursor++);
                }
            }
        }

        return table;
    }

    public String decrypt(String data, String key) {

        //Filling table for decryption
        char[] chars = key.toCharArray();
        Arrays.sort(chars);
        String sortedKey = new String(chars);
        char[][] table = fillDecryptionTable(data, sortedKey);

        //Sorting table back
        char[][] decryptedTable = new char[table.length][table[0].length];
        for (int i = 0; i < key.length(); i++) {
            char c = key.charAt(i);
            for (int j = 0; j < table.length; j++) {
                char[] column = table[j];
                if (column[0] == c) {
                    decryptedTable[i] = column;
                    table[j] = new char[1];
                    break;
                }
            }
        }

        //Building result string
        StringBuilder decryptedData = new StringBuilder();
        int dataCursor = 0;
        for (int columnCursor = 1; columnCursor < decryptedTable[0].length; columnCursor++) {
            for (int lineCursor = 0; lineCursor < key.length(); lineCursor++) {
                decryptedData.append(decryptedTable[lineCursor][columnCursor]);
                dataCursor++;
            }
        }

        return decryptedData.toString();
    }

    private char[][] fillDecryptionTable(String data, String key) {
        int dataLength = data.length();
        int keyWidth = key.length();

        int tableDepth = dataLength / keyWidth;
        //1 for key-header and 1 more if extra letters left
        tableDepth = (dataLength % keyWidth != 0) ? tableDepth + 2 : tableDepth + 1;

        char[][] table = new char[keyWidth][tableDepth];
        int dataCursor = 0;
        int keyCursor = 0;

        //Filling the first line of table
        for (int lineCursor = 0; lineCursor < keyWidth; lineCursor++) {
            table[lineCursor][0] = key.charAt(keyCursor++);
        }

        //Filling the table
        for (int lineCursor = 0; lineCursor < keyWidth; lineCursor++) {
            for (int columnCursor = 1; columnCursor < tableDepth; columnCursor++) {
                if (dataCursor < data.length()) {
                    table[lineCursor][columnCursor] = data.charAt(dataCursor++);
                }
            }
        }

        return table;
    }

    public static void main(String[] args) {
        Encoder encoder = new Encoder();

        String encr = encoder.encrypt("перестановочный шифр", "шифр");
        System.out.println(encr);

        String decr = encoder.decrypt(encr, "шифр");
        System.out.println(decr);
    }
}
