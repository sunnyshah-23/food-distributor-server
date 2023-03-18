package com.example.fooddistributor.model;

import java.util.UUID;
public class Product {
   private String name;
   private Integer qty;
   private Integer price;
   private String category;
   private String brand;
   private String uuid;

   Product(String uuid,String name,Integer qty,Integer price,String category,String brand){
       this.name=name;
       this.qty=qty;
       this.price=price;
       this.category=category;
       this.brand=brand;
       if(uuid != null && !uuid.isEmpty()) {
           this.uuid = uuid;
       } else {
           this.uuid = UUID.randomUUID().toString();
       }
   }

    public void setName(String name) {
        this.name = name;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public void setQty(Integer qty) {
        this.qty = qty;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getName() {
        return name;
    }

    public Integer getPrice() {
        return price;
    }

    public String getUuid() {
        return uuid;
    }

    public Integer getQty() {
        return qty;
    }

    public String getBrand() {
       return brand;
    }

    public String getCategory() {
        return category;
    }
}
