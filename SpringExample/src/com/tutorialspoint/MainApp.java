package com.tutorialspoint;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class MainApp {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		AbstractApplicationContext context = new ClassPathXmlApplicationContext("Beans.xml");
		HelloWorld objA = (HelloWorld) context.getBean("helloWorld");
		
		
		objA.getMessage();
		
		
		context.registerShutdownHook();
		
	}

}
