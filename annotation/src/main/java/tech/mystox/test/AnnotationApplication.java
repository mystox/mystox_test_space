package tech.mystox.test;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import tech.mystox.test.service.FooService;

@SpringBootApplication
public class AnnotationApplication {

	public static void main(String[] args) {
		ConfigurableApplicationContext run = SpringApplication.run(AnnotationApplication.class, args);
		FooService bean = run.getBean(FooService.class);
		System.out.println(bean);
	}

}
