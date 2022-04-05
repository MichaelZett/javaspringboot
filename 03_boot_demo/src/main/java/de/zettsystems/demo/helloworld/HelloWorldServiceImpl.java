package de.zettsystems.demo.helloworld;

/**
 * A simple Pojo... no Spring here...
 */
public class HelloWorldServiceImpl implements HelloWorldService {
    @Override
    public String sayHello() {
        return "Hello World!";
    }
}