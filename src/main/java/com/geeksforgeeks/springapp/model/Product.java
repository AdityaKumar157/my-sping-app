package com.geeksforgeeks.springapp.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.*;

import java.util.List;
import java.util.UUID;

@Data   // this annotations contains getters and setters, no args constructor, all args constructor
@With   // includes useful methods like with..() which makes copy of an object with one different value of one of the property.
@Builder    // this annotation includes many useful methods
@Entity     // this is model (Product class) represents database table.
@AllArgsConstructor
@NoArgsConstructor
public class Product {

    @Id     // make this field as a primary key
    private int productId;

    private String name;
    private double price;
    private String brandName;
//    private List<String> photoUrlList;
//    private int inventoryCount;

//    public int getInventoryCount() {
//        return inventoryCount;
//    }
//
//    public void setInventoryCount(int inventoryCount) {
//        this.inventoryCount = inventoryCount;
//    }
//
//    public UUID getProductId() {
//        return productId;
//    }
//
//    public void setProductId(UUID productId) {
//        this.productId = productId;
//    }
//
//    public String getName() {
//        return name;
//    }
//
//    public void setName(String name) {
//        this.name = name;
//    }
//
//    public double getPrice() {
//        return price;
//    }
//
//    public void setPrice(double price) {
//        this.price = price;
//    }
//
//    public List<String> getPhotoUrlList() {
//        return photoUrlList;
//    }
//
//    public void setPhotoUrlList(List<String> photoUrlList) {
//        this.photoUrlList = photoUrlList;
//    }
}
