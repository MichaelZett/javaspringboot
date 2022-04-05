package de.zettsystems.fizzbuzz;

public class FizzBuzzGame {

    /**
     * Children are sitting in a classroom. The first kid says 1 and then one after the other continues counting.
     * Each number that is dividable by 3 is replaced with 'Fizz', each number dividable by 5 is replaced with 'Buzz'.
     * Numbers that are dividable by 3 as well as 5 will be replaced by 'FizzBuzz'.
     * A typical game would look something like this:
     * 1, 2, Fizz, 4, Buzz, Fizz, 7, 8, Fizz, Buzz, 11, Fizz, 13, 14, FizzBuzz, 16, ..., 97, 98, Fizz, Buzz
     */
    public static void main(String[] args) {
        for (int i = 1; i <= 100; i++) {
            if (i % 3 == 0 && i % 5 == 0) {
                System.out.print("FizzBuzz");
            } else if (i % 3 == 0) {
                System.out.print("Fizz");
            } else if (i % 5 == 0) {
                System.out.print("Buzz");
            } else {
                System.out.print(i);
            }

            if (i < 100) {
                System.out.print(", ");
            }
        }
    }

}
