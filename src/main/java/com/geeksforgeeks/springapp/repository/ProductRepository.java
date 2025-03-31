package com.geeksforgeeks.springapp.repository;

import com.geeksforgeeks.springapp.mapper.ProductRowMapper;
import com.geeksforgeeks.springapp.model.Product;
import jakarta.validation.constraints.NotNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

//// JDBC repository
//@Repository
//public class ProductRepository {
//
//    private final JdbcTemplate jdbcTemplate;
//
//    public ProductRepository(JdbcTemplate jdbcTemplate) {
//        this.jdbcTemplate = jdbcTemplate;
//    }
//
//    public int addProduct(@NotNull Product product) {
//        String sqlCommand = "INSERT INTO products (productid, name, price) VALUES (?, ?, ?)";
//        return this.jdbcTemplate.update(sqlCommand, product.getProductId(), product.getName(), product.getPrice());
//    }
//
//    public List<Product> getAllProducts() {
//        String sqlCommand = "SELECT * FROM products";
//        return this.jdbcTemplate.query(sqlCommand, new ProductRowMapper());
//    }
//}

// JPA repository
@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {    //Interger- nature of primary key, Product- nature of table
    @Query("select count(p) from Product p")
    long getProductCount();

    long countByPriceLessThanEqualAndPriceGreaterThan(double price, double price1);
}
