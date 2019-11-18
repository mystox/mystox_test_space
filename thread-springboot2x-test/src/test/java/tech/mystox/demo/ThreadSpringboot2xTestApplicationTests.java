package tech.mystox.demo;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.test.context.junit4.SpringRunner;
import tech.mystox.demo.service.ClassSynFuncTest;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ThreadSpringboot2xTestApplicationTests {



	@Autowired
	ClassSynFuncTest classSynFuncTest;

	@Autowired
	private ThreadPoolTaskExecutor threadPoolTaskExecutor;

	@Test
	public void contextLoads() {

		for (int i=0; i<10; i++)
		threadPoolTaskExecutor.execute(()->classSynFuncTest.addSomething(""));


		try {
			Thread.sleep(120000l);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

	}
	@Test
	public void contextLoads2() {

		for (int i=0; i<10; i++)
			threadPoolTaskExecutor.execute(()->{
//				ClassSynFuncTest classSynFuncTest = new ClassSynFuncTest();
				classSynFuncTest.addSomethingSyn("");
			});

		try {
			Thread.sleep(12000l);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

	}



}
