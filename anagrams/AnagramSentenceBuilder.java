package anagrams;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.Scanner;

public class AnagramSentenceBuilder implements Builder{

    AnagramBuilder aMaker;
    SentenceBuilder sMaker;

    File anagrams;
    PrintStream anagramStream;
    Scanner anaScan;
    String path = "./nagaram";

    public void build(String inputLine) {

        this.findAnagramSentences(inputLine);
    }

    public AnagramSentenceBuilder() {

        anagrams = new File(path);

        anagrams.deleteOnExit();

        try {
            anagramStream = new PrintStream(anagrams);
            anaScan = new Scanner(anagrams);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        aMaker = new AnagramBuilder(anagramStream);
        sMaker = new SentenceBuilder();
    }

    public static void main(String[] args) {
        AnagramSentenceBuilder self = new AnagramSentenceBuilder();

        boolean enableInput = (args.length == 0);

        if (!enableInput) {

            for (String arg : args) {

                self.findAnagramSentences(arg);
            }

        } else {

            Scanner stdin = new Scanner(System.in);

            String word = utils.readInput(stdin);

            while (word != null) {

                self.findAnagramSentences(word);

                word = utils.readInput(stdin);
            }
        }
    }

    void findAnagramSentences(String word) {

        word = word.toLowerCase().replaceAll(" ", "");
        System.out.println(word + "\n---");

        word = utils.shuffle(word);

        aMaker.printLetterCombinations(word);
        System.out.print("\r---                         \n");
        // hacky ikr but this way the progress counter is fully erased
    }
}
