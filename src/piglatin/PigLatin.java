package piglatin;

/**
 * PigLatin.java by Cameron Cook the user inputs a sentence or just a word and
 * the program returns the sentence in pig latin
 */
import java.util.ArrayList;
import java.util.Scanner;
import java.util.StringTokenizer;

public class PigLatin {

    public static void main(String[] args) {
        boolean used = false;
        String pigLatinSentence = "";
        Scanner keyboard = new Scanner(System.in);
        System.out.println("Please enter a sentence: ");
        String sentence = keyboard.nextLine();

        ArrayList<StringBuffer> wordsOfSentence = convert(sentence);

        for (int i = 0; i < wordsOfSentence.size(); i++) {
            used = false;
            if (checkIfSingleConsonant(wordsOfSentence.get(i)) == true) {
                pigLatinSentence = pigLatinSentence + scModify(wordsOfSentence.get(i)) + " ";
                used = true;

            }
            if (checkifVowel(wordsOfSentence.get(i)) == true && used == false) {
                pigLatinSentence = pigLatinSentence + modifyVowel(wordsOfSentence.get(i)) + " ";
                used = true;
            }
            if (checkmultipyConsonants(wordsOfSentence.get(i)) > 0 && used == false) {
                int numOfConsonants = checkmultipyConsonants(wordsOfSentence.get(i));
                pigLatinSentence = pigLatinSentence + modifyConsonants(wordsOfSentence.get(i), numOfConsonants) + " ";
                used = true;
            }

        }
        System.out.println("");
        System.out.print("In pig Latin that sentence is: ");
        System.out.println(pigLatinSentence);
    }

    /**
     * this takes the string of words that was inputed;
     *
     * @param s is the string that the user inputed
     * @return arraylist of the words in the string the user inputed
     */
    public static ArrayList<StringBuffer> convert(String s) {
        StringTokenizer st = new StringTokenizer(s);
        ArrayList<StringBuffer> sentence = new ArrayList<>();

        while (st.hasMoreTokens()) {
            sentence.add(new StringBuffer(st.nextToken()));

        }
        return sentence;
    }

    /**
     * checks to see if there is just a single consonant.
     *
     * @param s the stringbuffer that is being checked
     * @return true if it is a single consonant, false if not
     */
    public static boolean checkIfSingleConsonant(StringBuffer s) {
        char[] consonants = {'b', 'c', 'd', 'f', 'g', 'h', 'j', 'k', 'l', 'm', 'n', 'p', 'q', 'r', 's', 't', 'v', 'w', 'x', 'z'};
        char[] vowel = {'a', 'e', 'i', 'o', 'u', 'y'};
        for (int i = 0; i < vowel.length; i++) {
            if (s.charAt(0) == vowel[i]) {
                return false;
            }
        }
        for (int i = 0; i < consonants.length; i++) {
            if (s.charAt(0) == consonants[i]) {
                for (int x = 0; x < consonants.length; x++) {
                    if (s.charAt(1) == consonants[x]) {
                        return false;
                    }
                }
            }
        }
        return true;
    }

    /**
     * modifies string for the condition of a single consonant
     *
     * @param s the stringbuffer that needs modified
     * @return the string modified
     */
    public static String scModify(StringBuffer s) {
        String pigLatin = "";
        char temp;

        temp = s.charAt(0);
        s.deleteCharAt(0);
        s.append(temp + "ay");
        return s.toString();
    }

    /**
     * check if the first letter is a vowel
     *
     * @param s the stringbuffer it checks
     * @return true if condition is true, false if not
     */
    public static boolean checkifVowel(StringBuffer s) {
        char[] vowel = {'a', 'e', 'i', 'o', 'u', 'y'};
        for (int i = 0; i < vowel.length; i++) {
            if (s.charAt(0) == vowel[i]) {
                return true;
            }
        }
        return false;
    }

    /**
     * modifies the string
     *
     * @param s stringbuffer that it is going to modify
     * @return the string modified
     */
    public static StringBuffer modifyVowel(StringBuffer s) {
        s = s.append("yay");
        return s;
    }

    /**
     * checks if it has multiply consonants and if it does counts them
     *
     * @param s the stringbuffer it is looking for consonants in
     * @return a int if it has multiple consonants, and -1 if it doesn't
     */
    public static int checkmultipyConsonants(StringBuffer s) {

        int stop = 0;
        char[] vowel = {'a', 'e', 'i', 'o', 'u', 'y'};
        char[] consonants = {'b', 'c', 'd', 'f', 'g', 'h', 'j', 'k', 'l', 'm', 'n', 'p', 'q', 'r', 's', 't', 'v', 'w', 'x', 'z'};
        for (int x = 0; x < s.length(); x++) {
            for (int i = 0; i < vowel.length; i++) {
                if (s.charAt(x) == vowel[i]) {
                    stop = x;
                    return stop;
                }
            }
        }

        return -1;
    }

    /**
     * modifies the word based on how many consonants it has
     *
     * @param s stringbuffer that needs to be modified
     * @param num the number of consonants that need to be moved
     * @return the string modified
     */
    public static StringBuffer modifyConsonants(StringBuffer s, int num) {
        char[] c = new char[num];

        for (int i = 0; i < num; i++) {
            c[i] = s.charAt(i);
        }
        for (int i = 0; i < num; i++) {
            s.deleteCharAt(0);
        }
        for (int i = 0; i < num; i++) {
            s.append(c[i]);
        }
        s.append("ay");
        return s;
    }
}
