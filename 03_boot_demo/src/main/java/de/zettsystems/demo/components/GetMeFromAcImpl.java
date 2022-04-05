package de.zettsystems.demo.components;

import org.springframework.stereotype.Component;

@Component
public class GetMeFromAcImpl implements GetMeFromAc {
    @Override
    public String comeAndGetMe() {
        return "You got me!";
    }
}
