package com.webtab.shecpsims;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.webtab.shecpsims.mapper")
public class ShecPsimsApplication {

	public static void main(String[] args) {
		SpringApplication.run(ShecPsimsApplication.class, args);
	}

}
