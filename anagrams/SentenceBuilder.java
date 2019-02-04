package anagrams;

import java.io.PrintStream;
import java.util.ArrayList;

public class SentenceBuilder {

    PrintStream p = System.out;
    WordCheck wc = new WordCheck();
    ArrayList<String[]> validSentences;

    private void printSentence(String[] sentence) {
        p.print("\r");
        for (int i=0; i < sentence.length; i++) {

            p.print(sentence[i] + " ");
        }
        p.println("                               ");
        p.flush();

        validSentences.add(sentence);
    }

    private void splitWords(String[] poWords, String uoLine) {

        if (uoLine.length() < 1) {

            printSentence(poWords);
        }

        for (int i=1; i < uoLine.length() + 1; i++) {

            String cword = utils.delFromString(uoLine, i, uoLine.length());

            if (wc.isInDicts(cword)) {

                String[] newWords = (utils.addToStringArray(poWords, cword));

                String rest = utils.delFromString(uoLine, 0, i);

                splitWords(newWords, rest);
            }
        }
    }

    public void splitWords(String line) {

        validSentences = new ArrayList<>();

        splitWords(new String[0], line);
    }

    public static void main(String[] args) {
        SentenceBuilder sm = new SentenceBuilder();
        sm.splitWords("nagaram");
        System.out.println(sm.validSentences.size());
    }
}
