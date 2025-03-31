package com.geeksforgeeks.springapp.controller;

import com.geeksforgeeks.springapp.model.Product;
import com.geeksforgeeks.springapp.repository.ProductRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

@RestController
//@Slf4j
public class ProductController {

    private static final Logger log = LoggerFactory.getLogger(ProductController.class);
    private ProductRepository productRepository;

    @Autowired
    public ProductController(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public List<Product> getAllProducts() {
        log.info("Fetching all products");
        List<Product> productList = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader("src/productlist.txt"));) {
            String line = br.readLine();
            while(line != null) {
                String[] info = line.split("\\|");
                Product product = Product.builder()
                        .productId(Integer.parseInt(info[0]))
                        .name(info[1])
                        .price( Double.parseDouble(info[2]) )
                        .build();
                productList.add(product);
                line = br.readLine();
            }
            log.info("Fetched {} products.", productList.size());
        } catch (IOException e) {
            log.error("Exception while fetching products: {}", e.getMessage());
            return productList;
        }

        return productList;
    }

    public void addNewProduct(Product product) {
        log.info("Adding a new product");
        List<Product> productList = this.getAllProducts();
        product.setProductId((productList.get(productList.size()-1).getProductId() + 1));
        productList.add(product);

        try(BufferedWriter br = new BufferedWriter(new FileWriter("src/productlist.txt"))) {
            for(Product p: productList) {
                br.write(p.getProductId() + "|" + p.getName() + "|" + p.getPrice());
                br.newLine();
            }
            log.info("Added a new product: {}", product.hashCode());
        } catch (IOException e) {
            log.error("Exception while adding a new product: {}", e.getMessage());
            throw new RuntimeException(e);
        }
    }

    @GetMapping("/get-products")
    public ResponseEntity<List<Product>> getProducts() {
        try {
            log.info("Requesting GET method API: {}", "/get-products");
            //List<Product> products = this.getAllProducts();
            List<Product> products = this.productRepository.findAll();
            return ResponseEntity.ok().body(products);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/add-product")
    @Transactional(rollbackFor = Exception.class) // need to throw exception in order to make this work. Currently, we are catching exception.
    public ResponseEntity<Product> addProduct(@RequestBody Product product) {
        try {
            log.info("Requesting POST method API: {}", "/add-product");
            //this.addNewProduct(product);
            //int value = this.productRepository.addProduct(product);
            Product savedProduct = this.productRepository.save(product);
            log.info("Product added. Returned value: {}", savedProduct);
            return new  ResponseEntity<>(product, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
