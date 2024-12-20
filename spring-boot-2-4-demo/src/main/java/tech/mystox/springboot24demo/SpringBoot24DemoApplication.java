package tech.mystox.springboot24demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringBoot24DemoApplication {

    public static void main(String[] args) {
        SpringApplication application = new SpringApplication(SpringBoot24DemoApplication.class);
        application.setWebApplicationType(WebApplicationType.REACTIVE);
        application.run(args);
//        SpringApplication.run(SpringBoot24DemoApplication.class, args);
    }

}
