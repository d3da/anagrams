package anagrams;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;

public class WordCheck {

    /* Note: the generated anagrams seem to be better if the wordlist
     *       is around 5000 lines long, for English and Dutch alike.
     */
    String[] dicts = {
            "/src/dict/english5k.txt",
//            "/dict/dutch4k.txt"
    };
    DictReader[] dictReaders;

    public WordCheck() {

        dictReaders = new DictReader[dicts.length];

        for (int i=0; i < dicts.length; i++) {
            dictReaders[i] = new DictReader(dicts[i]);
        }
    }

    class DictReader {

        BufferedReader inReader;
        Object[] wordArray;

        public DictReader(String dict) {

            InputStream iS = this.getClass().getResourceAsStream(dict);
            InputStreamReader i = new InputStreamReader(iS);
            inReader = new BufferedReader(i);

            wordArray = readDictIntoArray();
            Arrays.sort(wordArray);
        }

        Object[] readDictIntoArray() {

            ArrayList<String> wordlist = new ArrayList<>();

            try {
                String line;
                while ((line = inReader.readLine()) != null) {

                    line = line.toLowerCase();
                    wordlist.add(line);
                }


            } catch (IOException e) {
                e.printStackTrace();
            }
            return wordlist.toArray();
        }
    }

    boolean dictBinarySearch(String word, DictReader dr) {
        int i = Arrays.binarySearch(dr.wordArray, word);
        return (i >= 0);
    }

    public boolean isInDicts(String word) {

        for (DictReader dictReader : dictReaders) {

            if (dictBinarySearch(word, dictReader)) {
                return true;
            }
        }

        return false;
    }

    public static void main(String[] args) {

        String word = "a";

        System.out.println(word + " = " + new WordCheck().isInDicts(word));

    }
}
