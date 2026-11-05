package com.minidam.adam;

import org.springframework.boot.SpringApplication;

public class TestAdamApplication {

	public static void main(String[] args) {
		SpringApplication.from(AdamApplication::main).with(TestcontainersConfiguration.class).run(args);
	}

}
