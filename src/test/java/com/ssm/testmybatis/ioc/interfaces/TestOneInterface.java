package com.ssm.testmybatis.ioc.interfaces;

import com.ssm.service.IUserService;
import com.ssm.testmybatis.base.UnitTestBase;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.BlockJUnit4ClassRunner;
//import org.springframework.test.context.ContextConfiguration;
//import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;




@RunWith(BlockJUnit4ClassRunner.class)
public class TestOneInterface extends UnitTestBase {

	public TestOneInterface() {
		super("classpath*:spring-ioc.xml");
	}
	
	@Test
	public void testSay() {
		IUserService oneInterface = super.getBean("oneInterface");
		oneInterface.sayHello("这是我的参数，不是返回值");
	}
}
