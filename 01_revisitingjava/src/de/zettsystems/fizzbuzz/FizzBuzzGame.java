package de.zettsystems.fizzbuzz;

public class FizzBuzzGame {
    static final int FIZZ_NUMBER = 3;
    static final int BUZZ_NUMBER = 5;
    static final int LIMIT = 100;

    /**
     * Children are sitting in a classroom. The first kid says 1 and then one after the other continues counting.
     * Each number that is dividable by 3 is replaced with 'Fizz', each number dividable by 5 is replaced with 'Buzz'.
     * Numbers that are dividable by 3 as well as 5 will be replaced by 'FizzBuzz'.
     * A typical game would look something like this:
     * 1, 2, Fizz, 4, Buzz, Fizz, 7, 8, Fizz, Buzz, 11, Fizz, 13, 14, FizzBuzz, 16, ..., 97, 98, Fizz, Buzz
     */
    public static void main(String[] args) {
        for (int i = 1; i <= LIMIT; i++) {
            String result = determineWord(i);
            if (i < LIMIT) {
                result = result + ", ";
            }
            System.out.print(result);
        }
    }

    static String determineWord(int i) {
        if (i % FIZZ_NUMBER == 0 && i % BUZZ_NUMBER == 0) {
            return "FizzBuzz";
        } else if (i % FIZZ_NUMBER == 0) {
            return "Fizz";
        } else if (i % BUZZ_NUMBER == 0) {
            return "Buzz";
        } else {
            return "" + i;
        }
    }

}
