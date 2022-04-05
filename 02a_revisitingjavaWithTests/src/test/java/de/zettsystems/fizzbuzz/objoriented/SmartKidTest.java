package de.zettsystems.fizzbuzz.objoriented;


import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;

class SmartKidTest {
    private SmartKid testee = new SmartKid("Smart");

    @ParameterizedTest
    @CsvSource(value = {"1, 1", "3, Fizz","45, FizzBuzz" , "10, Buzz"})
    void shouldSayCsv(int number, String result) {
        assertThat(testee.decideWord(number)).isEqualTo(result);
    }

    @ParameterizedTest
    @ValueSource(ints = {1, 2, 4, 7, 14, 16, 97})
    void shouldSayNumber(int number) {
        assertThat(testee.decideWord(number)).isEqualTo(String.valueOf(number));
    }

    @ParameterizedTest
    @ValueSource(ints = {3, 6, 9, 42, 84, 99})
    void shouldSayFizz(int number) {
        assertThat(testee.decideWord(number)).isEqualTo("Fizz");
    }

    @ParameterizedTest
    @ValueSource(ints = {5, 10, 20, 35, 65, 100})
    void shouldSayBuzz(int number) {
        assertThat(testee.decideWord(number)).isEqualTo("Buzz");
    }

    @ParameterizedTest
    @ValueSource(ints = {15, 30, 45, 60, 90})
    void shouldSayFizzBuzz(int number) {
        assertThat(testee.decideWord(number)).isEqualTo("FizzBuzz");
    }
}