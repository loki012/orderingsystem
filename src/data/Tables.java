/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data;

import javax.swing.JOptionPane;

/**
 *
 * @author HP
 */
public class Tables {

    public static void main(String[] args) {
        try {
            //String userTable = "CREATE TABLE user (id int AUTO_INCREMENT primary key, name varchar(100), mobilenumber varchar(12), address varchar(200), email varchar(100), password varchar(100), status varchar(20), UNIQUE (email))";
            //DBOperations.setDataOrDelete(userTable, "User Table Created Succesfully");
 
            //String checkoutOrders = "CREATE TABLE orders (OrderID varchar(100), FullName varchar(100), LastName varchar(100), Country varchar(100), Region varchar(100), PostalCode varchar(10), CityProvinces varchar(100), address varchar(200), email varchar(100), mobilenumber varchar(12),  Payment varchar(100), Subtotal varchar(100), Total varchar(100))";
            //DBOperations.setDataOrDelete(checkoutOrders, "Order Table Created Succesfully");
            //String checkoutItems = "CREATE TABLE itemorders (OrderID varchar(100), Item varchar(100), Price varchar(50), Quantity varchar(100), Cost varchar(100))";
            //DBOperations.setDataOrDelete(checkoutItems, "Item Table Created Succesfully");
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }

}
