package de.zettsystems.cycle;

import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

@Component
public class CircularDependencyC {
    private final CircularDependencyA circularDependencyA;

    public CircularDependencyC(@Lazy CircularDependencyA circularDependencyA) {
        this.circularDependencyA = circularDependencyA;
    }
}