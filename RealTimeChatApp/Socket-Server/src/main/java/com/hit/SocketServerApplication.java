package com.hit;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.hit.Socket.SocketAplication;

@SpringBootApplication
public class SocketServerApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(SocketServerApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		// TODO Auto-generated method stub
		SocketAplication.startAplication();
	}

}
