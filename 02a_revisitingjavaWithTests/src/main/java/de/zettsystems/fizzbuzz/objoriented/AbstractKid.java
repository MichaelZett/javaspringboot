package de.zettsystems.fizzbuzz.objoriented;

public abstract class AbstractKid implements Kid {
    private String name;

    public AbstractKid(String name) {
        super();
        this.name = name;
    }

    @Override
    public void sayWord(int number) {
        System.out.println(this.getClass().getSimpleName() + "-" + name
                + " : " + decideWord(number));
    }

    protected abstract String decideWord(int number);

}