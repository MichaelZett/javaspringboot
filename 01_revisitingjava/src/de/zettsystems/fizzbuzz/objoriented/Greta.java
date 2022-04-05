package de.zettsystems.fizzbuzz.objoriented;

public class Greta extends AbstractKid {

	public Greta(String name) {
		super(name);
	}

	@Override
	protected String decideWord(int number) {
		return "how dare you!";
	}

}
