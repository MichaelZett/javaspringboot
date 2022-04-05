package de.zettsystems.demo.components;

import org.springframework.stereotype.Component;

@Component
public class ComponentScannedImpl implements ComponentScanned {
    @Override
    public String areYouThere() {
        return "Here, I am";
    }
}
