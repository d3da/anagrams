package anagrams;

import java.util.Scanner;

/**
 * CLI Front End
 */
public class FrontEnd {

    static void handleInput(Builder builder) {

        Scanner in = new Scanner(System.in);

        String inputLine = utils.readInput(in);

        while (inputLine != null) {

            builder.build(inputLine);

            inputLine = utils.readInput(in);
        }
    }

    private static Builder defaultBuilder =
            new AnagramSentenceBuilder();

    public static void main(String[] args) {
        Builder builder = defaultBuilder;

        handleInput(builder);
    }
}
