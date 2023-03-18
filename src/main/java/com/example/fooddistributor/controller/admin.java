package com.example.fooddistributor.controller;

import com.example.fooddistributor.model.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class admin {

    @PostMapping("/admin/product/add")
    public ResponseEntity<?> add(@RequestBody Product product) {
        try {
            Admin.addProduct(product);
            return ResponseEntity.ok("Product added successfully");
        } catch (Exception e) {

            ErrorResponse errorResponse = new ErrorResponse("Registration failed", e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
        }
    }
    @GetMapping("/admin/product")
    public ResponseEntity<?> getproduct() {
        try {
            return ResponseEntity.ok( Admin.readProduct());
        } catch (Exception e) {

            ErrorResponse errorResponse = new ErrorResponse("failed to load product", e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
        }
    }
    //update
    @PutMapping("/admin/product/{id}")
    public ResponseEntity<?> update(@PathVariable String id,@RequestBody Product product) {
        try {
            return ResponseEntity.ok( Admin.updateData(id,product));
        } catch (Exception e) {

            ErrorResponse errorResponse = new ErrorResponse("failed to update product", e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
        }
    }
    @DeleteMapping("/admin/product/{id}")
    public ResponseEntity<?> delete(@PathVariable String id) {
        try {
            Admin.deleteData(id);
            return ResponseEntity.ok( "product deleted");
        } catch (Exception e) {

            ErrorResponse errorResponse = new ErrorResponse("failed to update product", e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
        }
    }
}
