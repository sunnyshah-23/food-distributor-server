package com.example.fooddistributor.model;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.apache.catalina.User;

import java.io.*;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Scanner;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class Auth {
    private String username="";
    private String password="";
    @JsonIgnore
    public static HashSet<Userdetails> registerdUsers = null;

    Auth(String username,String password){
        this.username=username;
        this.password=password;
    }
    //register user
    public static void register(Userdetails userDetails) throws Exception {
        validateRegisterUser(userDetails);
        writeData(userDetails);
        System.out.print("data written");
    }



    //Login user
    public  Userdetails login() throws Exception {
        if (!getRegisteredUsers().isEmpty()) {
        for (Userdetails user : registerdUsers) {
                if (user.getUsername().equals(this.username)) {
                    if (user.getPassword().equals(this.password)) {
                        return user;
                    } else {
                        throw new Exception("Invalid Password");
                    }

                }
            }
        }

        throw new Exception("Invalid username or user not registered!");

    }

    //check is user is already registered
    public static void validateRegisterUser(Userdetails userDetails) throws Exception {

        if (!getRegisteredUsers().isEmpty()) {
            for (Userdetails user : registerdUsers) {
                if (user.getEmail().equals(userDetails.getEmail())) {
                    throw new Exception("Emailid already registered");
                } else if (user.getUsername().equals(userDetails.getUsername())) {
                    throw new Exception("username already taken");
                }
            }
        }
    }

    private static HashSet<Userdetails> getRegisteredUsers() throws IOException {
        if (registerdUsers == null) {
            readRegisterData("registerUser.txt");
        }
        return registerdUsers;
    }


        //create string of userdetails and call saveTofIle()
    public static void writeData(Userdetails user) throws IOException {
        StringBuilder registerData = new StringBuilder(user.getName() + "|" + user.getAddress() + "|" + user.getContact() + "|" + user.getUsername() + "|" + user.getEmail() + "|" + user.getPassword() + "|" + user.getIsAdmin());
        StringBuilder loginData = new StringBuilder(user.getUsername() + "|" + user.getEmail() + "|" + user.getPassword() + "|" + user.getIsAdmin());
        saveToFile("registerUser.txt", registerData.toString(), true);
        saveToFile("Login.txt", loginData.toString(), true);

    }

        //Write data to file
    public static void saveToFile(String fileName, String text, boolean append) {
        try{
            File file1 = new File(fileName);
            FileWriter fw = new FileWriter(file1, append);
            PrintWriter pw = new PrintWriter(fw);
            pw.println(text);
            pw.close();
        }
        catch(IOException er){
            System.out.print(er);
        }



    }

        //read user data from file and store it in Hashset registerUser
    public static void readRegisterData(String fileName) throws FileNotFoundException {
        registerdUsers=new HashSet<Userdetails>();
        File file = new File(fileName);
        Scanner load = new Scanner(file);
        if (file.length() != 0) {
            while (load.hasNextLine()) {
                String line = load.nextLine();
                String[] items = line.split("\\|");
                String r_name = items[0];
                String r_address = items[1];
                String r_contact = items[2];
                String r_username = items[3];
                String r_email = items[4];
                String r_password = items[5];
                Boolean r_isAdmin = Boolean.valueOf(items[6]);
                Userdetails r_userInfo = new Userdetails(r_name, r_address, r_contact, r_username, r_email, r_password, r_isAdmin);
                registerdUsers.add(r_userInfo);

            }
        }
        load.close();

    }
}



