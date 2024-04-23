package com.example.mojong;

import jakarta.annotation.PostConstruct;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.TimeZone;

@SpringBootApplication
public class MojongApplication {

	public static void main(String[] args) {
		SpringApplication.run(MojongApplication.class, args);
	}

	@PostConstruct
	public void settingTimeZone(){
		TimeZone.setDefault(TimeZone.getTimeZone("Asia/Seoul"));
	}

}
