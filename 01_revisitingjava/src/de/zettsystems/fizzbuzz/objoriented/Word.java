package de.zettsystems.fizzbuzz.objoriented;

public enum Word {
	FIZZ(3, "Fizz"), BUZZ(5, "Buzz");

	final int number;
	final String word;

	private Word(int number, String word) {
		this.number = number;
		this.word = word;
	}
}
