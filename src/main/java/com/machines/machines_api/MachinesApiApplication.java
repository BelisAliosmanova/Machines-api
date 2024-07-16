package com.machines.machines_api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
public class MachinesApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(MachinesApiApplication.class, args);
	}

}
