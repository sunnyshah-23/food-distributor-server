package com.example.fooddistributor.model;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.io.Serializable;
public class Userdetails implements Serializable {
    private String name;
    private String address;
    private String contact;
    private String username;
    private String emailId;
    private String password;
    private boolean isAdmin=false;

    Userdetails(String name,String address,String contact,String username,String email,String password, boolean isAdmin){
        this.name=name;
        this.address=address;
        this.contact=contact;
        this.username=username;
        this.emailId=email;
        this.password=password;
        this.isAdmin=isAdmin;

    }



    //setters
    public void setName(String name) throws Exception {
        if(name.isEmpty() || name.length()<2) throw new Exception("Name cannot be empty");
        this.name=name;
        System.out.print(name.length());
    }
    public void setAddress(String address) throws Exception {
        if(address.isEmpty()) throw new Exception("Name cannot be empty");
        this.address=address;
    }
    public void setContact(String contact) throws Exception {
        if(contact.length()<10) throw new Exception("Contact info cannot be less than 10 digits, please register again!");
        this.contact = contact;

    }
    public void setUsername(String username) throws Exception {
        if(username.isEmpty())throw new Exception("Invalid username");
        this.username=username;
    }
    public void setEmailId(String email) throws Exception {
        String regex = "^(.+)@(.+)$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(email);
        if(!matcher.matches()) throw new Exception("Invalid Email, please register again!");
        this.emailId=email;
    }
    public void setPassword(String password) throws Exception {
        if(password.isEmpty()) throw new Exception("Invalid Password");
        this.password=password;
    }


    //getters
    public String getName() {
        return name;
    }
    public String getAddress() {
        return address;
    }
    public String getContact() {
        return contact;
    }
    public String getUsername() {
        return username;
    }
    public String getEmail() {
        return emailId;
    }
    public String getPassword() {
        return password;
    }
    public Boolean getIsAdmin() {
        return isAdmin;
    }

//    @Override
//    public String toString() {
//        return "userDetails object details";
//    }
}
