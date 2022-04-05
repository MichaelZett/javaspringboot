package de.zettsystems.demo.components;

import org.springframework.stereotype.Component;

@Component
public class SetterInjectionImpl implements SetterInjection {
    @Override
    public String haveYouBeenInjected() {
        return "I have been injected.";
    }
}
