package com.example.fooddistributor.controller;

import com.example.fooddistributor.model.Auth;
import com.example.fooddistributor.model.ErrorResponse;
import com.example.fooddistributor.model.Userdetails;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class auth {

@PostMapping("/api/register")
public ResponseEntity<?> register(@RequestBody Userdetails userdetails) {
    try {
        Auth.register(userdetails);
        return ResponseEntity.ok(userdetails);
    } catch (Exception e) {

        ErrorResponse errorResponse = new ErrorResponse("Registration failed", e.getMessage());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
    }
}

   @PostMapping("/api/login")
    public  ResponseEntity<?>  login(@RequestBody Auth login){
       try {


            Userdetails user=login.login();
           return ResponseEntity.ok(user);
       } catch (Exception e) {
           ErrorResponse errorResponse = new ErrorResponse("Registration failed", e.getMessage());
           return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
       }

   }

}
