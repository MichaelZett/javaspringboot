package de.zettsystems.fizzbuzz.objoriented;

import java.util.ArrayList;
import java.util.List;

public class Classroom {
	private List<Kid> kids;

	public Classroom() {
		super();
		kids = new ArrayList<>();
	}

	public void enter(Kid kid) {
		kids.add(kid);
	}

	public void playFizzBuzz() {
		for (int i = 1; i <= 100; i++) {
			kids.get((i - 1) % kids.size()).sayWord(i);
		}
	}

}
