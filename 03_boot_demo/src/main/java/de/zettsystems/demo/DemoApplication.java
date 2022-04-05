package de.zettsystems.demo;

import de.zettsystems.demo.components.ComponentScanned;
import de.zettsystems.demo.components.GetMeFromAc;
import de.zettsystems.demo.components.SetterInjection;
import de.zettsystems.demo.helloworld.HelloWorldService;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.PostConstruct;

@SpringBootApplication
public class DemoApplication {
	@Autowired // field base injection discouraged
	private ComponentScanned componentScanned;
	private final HelloWorldService helloWorldService;
	private SetterInjection setterInjection;
	private final BeanFactory beanFactory;

	// constructor based DI - with one constructor there is no need to add @Autowired
	// best practice
	public DemoApplication(HelloWorldService helloWorldService, BeanFactory beanFactory) {
		this.helloWorldService = helloWorldService;
		this.beanFactory = beanFactory;
	}

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

	@Autowired //setter based injection - use it for optional beans and to solve circular dependencies
	public void setSetterInjection(SetterInjection setterInjection) {
		this.setterInjection = setterInjection;
	}

	@PostConstruct // is called after ac has been constructed
	public void greetings() {
		System.out.println(helloWorldService.sayHello());
		System.out.println(componentScanned.areYouThere());
		System.out.println(setterInjection.haveYouBeenInjected());
		// Get beans programmatically
		final GetMeFromAc bean = beanFactory.getBean(GetMeFromAc.class);
		System.out.println(bean.comeAndGetMe());
	}

}
