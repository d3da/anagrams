package anagrams;

import java.util.NoSuchElementException;
import java.util.Random;
import java.util.Scanner;

class utils {

    static String[] addToStringArray(String[] arr, String s) {

        String[] newArr = new String[arr.length + 1];
        System.arraycopy(arr, 0, newArr, 0, arr.length);

        newArr[arr.length] = s;
        return newArr;
    }


    static String delFromString(String str, int start, int end) {

        StringBuilder builder = new StringBuilder(str);
        builder.delete(start, end);
        return builder.toString();
    }

    static String appendCharToString(String str, char c) {

        StringBuilder builder = new StringBuilder(str);
        builder.append(c);
        return builder.toString();

    }

    static String delCharFromString(String str, int pos) {

        StringBuilder builder = new StringBuilder(str);
        builder.deleteCharAt(pos);
        return builder.toString();
    }

    static String readInput(Scanner s) {

        System.out.print("$ ");
        try {
            return s.nextLine();

        } catch (NoSuchElementException e) {
            // Reached EOF, return null to terminate program.
            System.err.println("EOF");
            return null;
        }
    }

    static String shuffle(String str) {

        StringBuilder sin = new StringBuilder(str);
        StringBuilder sout = new StringBuilder();
        Random r = new Random();

        int p;
        for (int i = 0; i < str.length(); i++) {

            p = r.nextInt(sin.length());
            sout.append(sin.charAt(p));
            sin.deleteCharAt(p);
        }

        return sout.toString();
    }
}
