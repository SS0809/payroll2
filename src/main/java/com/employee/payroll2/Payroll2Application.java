package com.employee.payroll2;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
@Slf4j
public class Payroll2Application {

	public static void main(String[] args) {
		ApplicationContext context = SpringApplication.run(Payroll2Application.class, args);
		log.info("EMployee Payroll App Started in Environment : {}",context.getEnvironment().getProperty("environment"));
	}

}
