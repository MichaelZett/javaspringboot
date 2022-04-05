package de.zettsystems.fizzbuzz.func;

import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class FizzBuzzGame {

    /**
     * Children are sitting in a classroom. The first kid says 1 and then one after the other continues counting.
     * Each number that is dividable by 3 is replaced with 'Fizz', each number dividable by 5 is replaced with 'Buzz'.
     * Numbers that are dividable by 3 as well as 5 will be replaced by 'FizzBuzz'.
     * A typical game would look something like this:
     * 1, 2, Fizz, 4, Buzz, Fizz, 7, 8, Fizz, Buzz, 11, Fizz, 13, 14, FizzBuzz, 16, ..., 97, 98, Fizz, Buzz
     */
    public static void main(String[] args) {
        String result = IntStream.rangeClosed(1, 100).mapToObj(FizzBuzzGame::determineWord).collect(Collectors.joining(","));
        System.out.println(result);
    }

    private static String determineWord(int i) {
        if (i % 3 == 0 && i % 5 == 0) {
            return "FizzBuzz";
        } else if (i % 3 == 0) {
            return "Fizz";
        } else if (i % 5 == 0) {
            return "Buzz";
        } else {
            return "" + i;
        }
    }

}
