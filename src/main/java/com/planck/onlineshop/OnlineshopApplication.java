package com.planck.onlineshop;

import com.planck.DAO.Connection;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class OnlineshopApplication {

	public static void main(String[] args) {
		SpringApplication.run(OnlineshopApplication.class, args);
		Connection connection = new Connection();
		connection.getConnection();
	}

}
