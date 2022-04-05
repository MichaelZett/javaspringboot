package de.zettsystems.fizzbuzz.objoriented;

public class FizzBuzzGame {

	public static void main(String[] args) {
		Classroom classroom = new Classroom();

		for (int i = 0; i < 5; i++) {
			final Kid createKid = createKid(i);
			classroom.enter(createKid);
		}

		classroom.playFizzBuzz();
	}

	private static Kid createKid(int i) {
		if (i % 2 == 0) {
			return new DumbKid(String.valueOf(i + 1));
		} else if (i % 3 == 0) {
			return new Greta(String.valueOf(i + 1));
		} else {
			return new SmartKid(String.valueOf(i + 1));
		}
	}

}
