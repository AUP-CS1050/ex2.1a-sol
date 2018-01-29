package aup.cs;

import java.util.Arrays;

/**
 * The class is defined as final since utility classes should not be extensible.
 */

public final class LFSREncoding {

    public static void main(String[] args) {
        LFSR lfsr = new LFSR();
        lfsr.arr = new boolean[]{true,false,false,false};
        lfsr.coefs = new boolean[]{false,false,true,true};
        testLfsr("Hello",  lfsr);
    }

    /**
     * Convert a char into a 16 bits array.
     * @param c the char to print
     * @return a boolean array of length 16 representing the bit presentation of the character
     */
    static boolean[] char2bitArray(char c) {
        int s = (int)c;
        int i = 0;
        boolean[] arr1 = new boolean[16];
        while (s > 0) {
            if (s % 2 == 0) {
                arr1[i] = false;
            } else {
                arr1[i] = true;
            }
            s = s / 2;
            i++;
        }
        return arr1;
    }
    
    /**
     * Convert a 16 bits array into a char.
     * @param arr a boolean array of length 16 representing a bit array
     * @return the character denoted by the bit array
     */
    static char bitArray2char(boolean[] arr) {
        int power = 0;
        int sum = 0;
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == true) {
                sum += (1 * Math.pow(2, power));
            }
            power++;
        }
        char c = (char)sum;
        return c;
    }
    
    /**
     * Test the previous two coding functions by converting the text into an array of characters.
     * Then for each character, it converts it into a bit array and
     * then converts the bit array back into a character.
     * Finally, the program constructs a new String given an array of all the generated characters.
     * Print the text before and after the transformations.
     * @param text an input string to test
     */
    static void testCoding(String text) {
        char[] chars = text.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            boolean[] binary = char2bitArray(chars[i]);
            char c = bitArray2char(binary);
            if (c != chars[i]) {
                System.out.println("error");
            }
        }
    }
    
    /**
     * Encode the char by XORing each bit with the returned one from feedbackUpdateLFSR.
     * @param c the char to encode
     * @param arr the bit array (initially the seed)
     * @param coefs the binary coefficients
     * @return the encoded char
     */
    static char encodeCharLfsr(char c, LFSR lfsr) {
        boolean[] charArray = char2bitArray(c);
        boolean update;
        for (int i = 0; i < charArray.length; i++) {
            update = lfsr.feedbackUpdate();
            if (charArray[i] == update) {
                charArray[i] = false;
            } else {
                charArray[i] = true;
            }
        }
        char charReturn = bitArray2char(charArray);
        return charReturn;
    }

    /**
     * Encode the char array by encoding each char in it.
     * @param chars the char array to encode
     * @param arr the bit array (initially the seed)
     * @param coefs the binary coefficients
     * @return the encoded char
     */
    static char[] encodeCharArrayLfsr(char[] chars, LFSR lfsr) {
        char[] encodedChar = new char[chars.length];
        for (int i = 0; i < chars.length; i++) {
            encodedChar[i] = encodeCharLfsr(chars[i], lfsr);
        }
        return encodedChar;
    }

    /**
     * Test the LFSR encoding and decoding.
     * 1. copy the bit array (the seed) and out aside
     * 2. Transform the text into a char array
     * 3. Encode using encodeCharArrayLFSR and print
     * 4. Decode the encoded message using the copied seed and encodeCharArrayLFSR and print
     * @param text the text to test
     * @param arr the bit array (initially the seed)
     * @param coefs the binary coefficients
     */
    static void testLfsr(String text, LFSR lfsr) {
        boolean[] seedCopy = java.util.Arrays.copyOf(lfsr.arr,lfsr.arr.length);
        char[] chars = text.toCharArray();
        char[] encodedChar = encodeCharArrayLfsr(chars, lfsr);
        String encodedMessage = new String(encodedChar);
        System.out.println(encodedMessage);
        lfsr.arr = seedCopy;
        char[] decodedMessage = encodeCharArrayLfsr(encodedChar, lfsr);
        String message = new String(decodedMessage);
        System.out.println(message);
    }
}
