package com.example.fooddistributor.model;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Admin {
    private static List<Product> productlist=new ArrayList<Product>();

    public static void addProduct(Product product){
      writeData(product);
    }

    public static List<Product> readProduct(){
        readProductData("product.txt");
        return productlist;

    }

    public static Product updateData(String id,Product product) throws Exception {
        boolean found=false;
        readProductData("product.txt");
        if(!productlist.isEmpty()){
           for(int i=0;i<productlist.size();i++){
               if(productlist.get(i).getUuid().equals(product.getUuid())){
                   productlist.set(i,product);
                   writeDataFull(productlist);
                   found=true;
                   break;
               }

           }
        }
        if(found==false){
            throw new Exception("Product not found");
        }
        return product;
    }

    public static void deleteData(String id) throws Exception {
        boolean found=false;
        readProductData("product.txt");
        if(!productlist.isEmpty()){
            for(int i=0;i<productlist.size();i++){
                if(productlist.get(i).getUuid().equals(id)){
                    productlist.remove(productlist.get(i));
                    writeDataFull(productlist);
                    found=true;
                    break;
                }

            }
        }
        if(found!=true){
            throw new Exception("Product not found");
        }

    }
    public static void writeDataFull(List<Product>prolist){
        File file1 = new File("product.txt");
        file1.delete();
        for(Product product:prolist){
            StringBuilder productData = new StringBuilder(product.getUuid()+"|"+product.getName() + "|" + product.getPrice() + "|" + product.getQty() + "|" + product.getBrand() + "|" + product.getCategory());
            saveToFile("product.txt", productData.toString(), true);
        }


    }
    public static void writeData(Product product){
        StringBuilder productData = new StringBuilder(product.getUuid()+"|"+product.getName() + "|" + product.getPrice() + "|" + product.getQty() + "|" + product.getBrand() + "|" + product.getCategory());
        saveToFile("product.txt", productData.toString(), true);

    }
    public static void saveToFile(String fileName, String text, boolean append) {

        try {
            File file1 = new File(fileName);
            FileWriter fw = new FileWriter(file1, append);
            PrintWriter pw = new PrintWriter(fw);
            pw.println(text);
            pw.close();
        } catch (IOException er) {
            System.out.print(er);
        }
    }
    public static List<Product> getProduct()  {
        if (productlist== null) {
            readProductData("product.txt");
        }
        return productlist;
    }

    public static void readProductData(String filename){
        File file;
        Scanner load=null;
        productlist.clear();
        try{
            file = new File(filename);
            load=new Scanner(file);
            if (file.length() != 0) {
                while (load.hasNextLine()) {
                    String line = load.nextLine();
                    String[] items = line.split("\\|");
                    String uuid = items[0];
                    String p_name = items[1];
                    Integer p_qty = Integer.parseInt(items[2]);
                    Integer p_price = Integer.parseInt(items[3]);
                    String p_catogory = items[4];
                    String p_brand = items[5];
                    Product productInfo = new Product(uuid,p_name,p_qty,p_price,p_catogory,p_brand);
                    productlist.add(productInfo);

                }
            }
        }
        catch(IOException err){
            System.out.print(err);
        }

        load.close();
    }
}

