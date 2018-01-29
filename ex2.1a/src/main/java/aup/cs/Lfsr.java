package aup.cs;

public final class Lfsr {
    boolean[] arr;
    boolean[] coefs;
    
    /**
     * Print 1 character to System.out.
     * For example printCharInt('*') prints 42
     * @param c the character to print
     */
    static void printCharInt(char c) {
        int i = (int)c;
        System.out.println(i);
    }
    
    /**
     * Print the base 2 of an integer.
     * For example printIntBase2(42) should print 101010
     * @param n the number to print
     */
    static void printIntBase2(int n) {
        while (n > 0) {
            System.out.println(n % 2);
            n = n / 2;
        }
    }

    /**
     * Print the given bit array to System.out.
     * It should print 1 for each true value and 0 for a false one
     * @param arr the boolean array to print
     */
    static void printBitArray(boolean[] arr) {
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == true) {
                System.out.println(1);
            } else {
                System.out.println(0);
            }
        }
    }
    
    /**
     * Computes the next bit of LFSR.
     * @param arr the bit array (initially the seed)
     * @param coefs the binary coefficients
     * @return a boolean the combination of adding the multiplication of each bit with a coefficient
     */
    boolean feedback() {
        int counter = 0;
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] && coefs[coefs.length - 1 - i] == true) {
                counter++;
            }
        }
        boolean comb = counter % 2 == 1;
        return comb;
    }

    /**
     * Computes the next state of the bit array.
     * @param arr the bit array (initially the seed)
     * @param coefs the binary coefficients
     * @return the next bit to use for encoding.
     */
    boolean feedbackUpdate() {
        boolean next = feedback();
        boolean ret = arr[arr.length - 1];
        for (int i = arr.length - 1; i > 0; i--) {
            arr[i] = arr[i - 1];
        }
        arr[0] = next;
        return ret;
    }
}