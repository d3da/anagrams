package anagrams;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.Scanner;

public class AnagramWordBuilder {

    private Scanner scan;
    private AnagramBuilder amaker;
    private WordCheck wcheck;

    private File f;
    private PrintStream p;
    String path = "./tmp";

    public AnagramWordBuilder() {

        f = new File(path);
        f.deleteOnExit();
        try {
            p = new PrintStream(f);
            scan = new Scanner(f, "UTF-8");

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        wcheck = new WordCheck();
        amaker = new AnagramBuilder(p);
    }

    @Deprecated
    public static void main(String[] args) {
        AnagramWordBuilder self = new AnagramWordBuilder();

        Scanner input = new Scanner(System.in);
        System.out.println("\nEnter word");
        while (input.hasNextLine()) {
            self.findCombisAndMatch(input.nextLine());
            System.out.println("\nEnter word");
        }
    }



    @Deprecated
    String[] findWordsInStream(boolean printWords) {

        String[] validWords = new String[0];
        String line;

        // FIXME while looped is skipped on entering a new word

        while (scan.hasNextLine()) {
            line = scan.nextLine();

            System.out.print(String.format("\rChecking %s", line));

            if (wcheck.isInDicts(line) && line != null) {

                validWords = utils.addToStringArray(validWords, line);

                if (printWords) {
                    System.out.println("\r" + line);
                }
            }
        }
        return validWords;
    }

    @Deprecated
    void findCombisAndMatch(String word) {

        amaker.printLetterCombinations(word);

        System.out.println(String.format("%d character combinations", amaker.combiCount));
        System.out.println("\nDICTIONARY WORDS:");
        findWordsInStream(true);

    }
}
