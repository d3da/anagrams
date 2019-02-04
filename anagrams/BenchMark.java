package anagrams;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;

@Deprecated
public class BenchMark {

    static String benchWord = "raccen";

    public static void main(String[] args) {

        benchTime();
    }

    static long benchTime() {
        File f = new File("./bench");
        f.deleteOnExit();

        try {
            PrintStream printer = new PrintStream(f);
            AnagramBuilder aMaker = new AnagramBuilder(printer);

            aMaker.printLetterCombinations(benchWord);



        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        return 0l;
    }
}
