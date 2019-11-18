package tech.mystox.test.aop;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import tech.mystox.test.aop.service.TestAnnotionService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringbootAopTestApplicationTests {

//	@Autowired
//	TestService testAspectService;
	@Autowired
	TestAnnotionService testAnnotionService;

	@Test
	public void contextLoads() {

		String abc = testAnnotionService.testAspect("abc");
		System.out.println(abc + "result");
	}

}
