package com.geeksforgeeks.springapp;

import com.geeksforgeeks.springapp.controller.ProductController;
import com.geeksforgeeks.springapp.model.Product;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;
import java.util.Arrays;
import java.util.UUID;

@SpringBootApplication		// contains behavior of @Configuration, @EnableAutoConfiguration, @ComponentScan
public class MySpringAppApplication {

	public static void main(String[] args) throws IOException {
//		//Product product = new Product();
//		Product product1 = Product.builder()
//				.productId(UUID.randomUUID())
//				.name("Prod1")
//				.inventoryCount(10)
//				.photoUrlList(Arrays.asList("one", "two", "three", "four"))
//				.price(2500).build();
//		product1.getName();
//		product1.setName("Prod1");
//
//		// You want a new product product2 which is a copy of product1 except price.
////		Product product2 = Product.builder()
////				.productId(UUID.randomUUID())
////				.name("Prod1")
////				.inventoryCount(10)
////				.photoUrlList(Arrays.asList("one", "two", "three", "four"))
////				.price(2000).build();
//		//simpler way with lombook
//		Product product2 = product1.withPrice(2000)
//				.withInventoryCount(3);	// this method can be checked in target folder after build. Cmd: mvn clean install

		//ProductController productController = new ProductController();
		SpringApplication.run(MySpringAppApplication.class, args);
		//System.out.println(productController.getAllProducts());
	}

}
