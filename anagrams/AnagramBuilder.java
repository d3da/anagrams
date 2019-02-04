package anagrams;

import java.io.PrintStream;
import java.util.HashMap;

public class AnagramBuilder implements Builder {

    PrintStream printer;
    long combiCount;
    long totalCombis;

    boolean printProgress;
    long progressInterval = 111;
    // interval is not a round number to create the illusion of detail

    SentenceBuilder sMaker = new SentenceBuilder();

    public void build(String line) {
        this.printLetterCombinations(line);
    }

    public AnagramBuilder(PrintStream p) {
        printer = p;
        combiCount = 0;
        printProgress = true;
    }

    public static void main(String[] args) {

        String word = "truncate";

        AnagramBuilder self = new AnagramBuilder(System.out);

        self.printProgress = false;

        self.printLetterCombinations(word);



    }

    private void printLetterCombinations(String poString, String uoString) {

        if (uoString.length() <= 1) {

            printer.println(poString + uoString);

            combiCount++;

            if (printProgress) {
                printProgressString();
            }


            sMaker.splitWords(poString + uoString);

            return;
        }

        for (int i = 0; i < uoString.length(); i++) {

            // If a String starts with the same character as another, skip the duplicate
            boolean dupe = false;
            for (int p = 0; p < i; p++) {
                if (uoString.charAt(i) == uoString.charAt(p)) {
                    dupe = true;
                }
            }
            if (dupe) {
                continue;
            }

            String ordered = utils.appendCharToString(poString, uoString.charAt(i));

            String unordered = utils.delCharFromString(uoString, i);

            printLetterCombinations(ordered, unordered);
        }
    }

    public void printLetterCombinations(String s) {
//        System.out.println(amountOfCombinations(s) + " combinations\n");
        combiCount = 0;
        totalCombis = amountOfCombinations(s);
        printLetterCombinations("", s);

    }

    private long amountOfCombinations(String s) {

        HashMap<Character, Integer> charFreqs = new HashMap<>();

        for (int i=0; i < s.length(); i++) {

            char c = s.charAt(i);

            if (charFreqs.containsKey(c)) {
                int f = charFreqs.get(c) + 1;
                charFreqs.replace(c, f);
            } else {
                charFreqs.put(c, 1);
            }
        }
        long maxFact = factorial(s.length());

        for (Integer i : charFreqs.values()) {
            maxFact /= factorial(i);
        }
        return maxFact;
    }

    private long factorial(long x) {
        if (x <= 1) {
            return 1;
        } else {
            return (x * factorial(x - 1));
        }
    }

    private void printProgressString() {
        if (combiCount % progressInterval == 0) {
            long perc = 100 * combiCount / totalCombis;

            if ((0 > perc) || (perc > 100)) {
                System.err.println("\nProgress percentage is invalid.");
                new Error().printStackTrace();
            }

            System.out.print(String.format("\r%d / %d [%d%%]", combiCount, totalCombis, perc));
        }
    }
}
