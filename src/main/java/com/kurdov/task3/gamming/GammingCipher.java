package com.kurdov.task3.gamming;

public class GammingCipher {

    private static final int CHARSET_FORMAT = 16;

    private int[] charToBits(char c) {
        int[] result = new int[CHARSET_FORMAT];

        StringBuilder binaryBuilder = new StringBuilder(Integer.toBinaryString(c));
        while (binaryBuilder.length() < CHARSET_FORMAT) {
            binaryBuilder.insert(0, "0");
        }

        String[] binaryArray = binaryBuilder.toString().split("");
        for (int i = 0; i < binaryArray.length; i++) {
            String bitString = binaryArray[i];
            result[i] = Integer.parseInt(bitString);
        }

        return result;
    }

    private char bitsToChar(int[] bits) {
        StringBuilder binary = new StringBuilder();
        for (int bit : bits) {
            binary.append(bit);
        }

        int convertedToDecimal = Integer.parseInt(binary.toString(), 2);

        return (char) convertedToDecimal;
    }

    public String encoder(String sourceText, int[] gamma) {

        //Loop for bitmap creation
        int[][] bitMap = new int[sourceText.length()][];
        for (int i = 0; i < sourceText.length(); i++) {
            char c = sourceText.charAt(i);
            bitMap[i] = charToBits(c);
        }

        //Loop to print bitmap
//        for (int[] bits : bitMap) {
//            for (int bit : bits) {
//                System.out.print(bit);
//            }
//        }
//        System.out.println();

        //Loop for bits inversion
        StringBuilder result = new StringBuilder();
        int gammaLength = gamma.length;
        int gammaMarshaller = 0;
        for (int[] bits : bitMap) {
            int[] encodedBits = new int[CHARSET_FORMAT];
            int cursor = 0;
            for (int bit : bits) {
                int encoded = bit ^ gamma[gammaMarshaller % gammaLength];
                encodedBits[cursor] = encoded;
                gammaMarshaller++;
                cursor++;
            }
            char encodedChar = bitsToChar(encodedBits);
            result.append(encodedChar);
        }
        return result.toString();
    }

    public static void main(String[] args) {
        GammingCipher gammingCipher = new GammingCipher();

        String text = "Военное искусство";
        int[] gamma = {1, 0, 0, 1, 0, 1, 1}; // TODO: 27.05.2019 create method to calculate gamma

        String encrypted = gammingCipher.encoder(text, gamma);
        System.out.println(encrypted);

        String decrypted = gammingCipher.encoder(encrypted, gamma);
        System.out.println(decrypted);
    }
}
