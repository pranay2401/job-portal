package com.jobPortal;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
//@ComponentScan({ "com.jobPortal.controllers", "com.jobPortal.models", "com.jobPortal.services" })
public class JobPortalApplication {

	public static void main(String[] args) {

		SpringApplication.run(JobPortalApplication.class, args);

	}

}
