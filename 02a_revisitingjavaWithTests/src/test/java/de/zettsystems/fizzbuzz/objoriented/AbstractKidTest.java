package de.zettsystems.fizzbuzz.objoriented;

import org.junit.jupiter.api.Test;

import static com.github.stefanbirkner.systemlambda.SystemLambda.tapSystemOut;
import static org.junit.jupiter.api.Assertions.*;

class AbstractKidTest {
    private AbstractKid testee = new NotSoAbstractKid("Testee");

    @Test
    void shouldSayWordOnCmd() throws Exception {
        String text = tapSystemOut(() -> testee.sayWord(3));

        assertEquals("NotSoAbstractKid-Testee : Word", text.trim());
    }

    class NotSoAbstractKid extends AbstractKid {
        public NotSoAbstractKid(String name) {
            super(name);
        }

        @Override
        protected String decideWord(int number) {
            return "Word";
        }
    }
}