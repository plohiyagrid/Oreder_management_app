package com.griddynamics.order_management;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Entry point for the Order Management Spring Boot application.
 * <p>
 * This class bootstraps the application by invoking {@link SpringApplication#run(Class, String[])}
 * which starts the embedded server and initializes the Spring context.
 * </p>
 */
@SpringBootApplication
public class OrderManagementApplication {

	/**
	 * Main method used to launch the Order Management application.
	 *
	 * @param args command-line arguments passed during application startup
	 */
	public static void main(String[] args) {
		SpringApplication.run(OrderManagementApplication.class, args);
	}

}
