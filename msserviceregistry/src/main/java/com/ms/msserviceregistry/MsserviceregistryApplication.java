package com.ms.msserviceregistry;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;
import org.springframework.context.annotation.EnableMBeanExport;

@SpringBootApplication
@EnableEurekaServer
public class MsserviceregistryApplication {

	public static void main(String[] args) {
		SpringApplication.run(MsserviceregistryApplication.class, args);
	}

}
