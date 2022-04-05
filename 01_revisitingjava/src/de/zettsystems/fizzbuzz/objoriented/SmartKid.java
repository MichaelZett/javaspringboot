package de.zettsystems.fizzbuzz.objoriented;

public class SmartKid extends AbstractKid {
	public SmartKid(String name) {
		super(name);
	}

	@Override
	protected String decideWord(int number) {
		if (isNumberDividableBy(number, Word.FIZZ.number)
				&& isNumberDividableBy(number, Word.BUZZ.number)) {
			return Word.FIZZ.word + Word.BUZZ.word;
		} else if (isNumberDividableBy(number, Word.FIZZ.number)) {
			return Word.FIZZ.word;
		} else if (isNumberDividableBy(number, Word.BUZZ.number)) {
			return Word.BUZZ.word;
		} else {
			return String.valueOf(number);
		}
	}

	private static boolean isNumberDividableBy(int number, int divisor) {
		return number % divisor == 0;
	}

}
