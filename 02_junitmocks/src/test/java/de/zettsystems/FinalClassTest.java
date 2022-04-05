package de.zettsystems;

import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;
import org.mockito.Mockito;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class FinalClassTest {

    @Test
    void getStaticContent() {
        FinalClass testee = mock(FinalClass.class);

        when(testee.getName()).thenReturn("peter");

        assertThat(testee.getName()).isEqualTo("peter");

        try (MockedStatic<FinalClass> utilities = Mockito.mockStatic(FinalClass.class)) {
            utilities.when(FinalClass::getStaticContent).thenReturn("frank");

            assertThat(FinalClass.getStaticContent()).isEqualTo("frank");
        }

    }
}