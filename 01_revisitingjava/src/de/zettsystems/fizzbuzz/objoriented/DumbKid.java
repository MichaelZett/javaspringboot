package de.zettsystems.fizzbuzz.objoriented;

import java.security.SecureRandom;

public class DumbKid extends AbstractKid {
	private final SecureRandom random = new SecureRandom();

	public DumbKid(String name) {
		super(name);
	}

	@Override
	protected String decideWord(int number) {
		boolean nextBoolean = random.nextBoolean();
		if (nextBoolean) {
			if (random.nextBoolean()) {
				return String.valueOf(number);
			} else {
				return Word.FIZZ.word;
			}
		} else {
			if (random.nextBoolean()) {
				return Word.FIZZ.word + Word.BUZZ.word;
			} else {
				return Word.BUZZ.word;
			}
		}
	}

}
