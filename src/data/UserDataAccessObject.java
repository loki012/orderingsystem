/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data;

import javax.swing.JOptionPane;
import model.User;
import java.sql.*;
import model.CheckoutUser;
import model.ItemOrdered;

/**
 *
 * @author HP
 */
public class UserDataAccessObject {

    public static void save(User user) {
        String query = "INSERT INTO user (name, mobilenumber, address, email, password, status) VALUES ('" + user.getName() + "','" + user.getMobilenumber() + "','" + user.getAddress() + "','" + user.getEmail() + "','" + user.getPassword() + "','true')";
        DBOperations.setDataOrDelete(query, "Registered Successfully!");
    }

    public static void checkout(CheckoutUser checkout) {
        String query = "INSERT INTO orders (OrderId, FullName, LastName, Country, Region, PostalCode, CityProvinces, address, email, mobilenumber, Payment, Subtotal, Total) VALUES ('" + checkout.getOrderId() + "','" + checkout.getFirstName() + "','" + checkout.getLastName() + "','" + checkout.getCountry() + "','" + checkout.getRegion() + "','" + checkout.getPostalCode() + "','" + checkout.getCityProvinces() + "','" + checkout.getAddress() + "','" + checkout.getEmail() + "','" + checkout.getPhoneNumber() + "','" + checkout.getPayment() + "','" + checkout.getSubTotal() + "','" + checkout.getTotal() + "')";
        DBOperations.setDataOrDelete(query, "Ordered Successfully!");
    }

    public static String itemordered(ItemOrdered itemordered) {
        String query = "INSERT INTO itemorders (OrderId, Item, Price, Quantity, Cost) VALUES ('"
                + itemordered.getOrderId() + "','" + itemordered.getItem() + "','"
                + itemordered.getPrice() + "','" + itemordered.getQuantity() + "','"
                + itemordered.getCost() + "')";
        return query;
    }

    public static User login(String email, String password) {
    User user = null;

    try {
        // Case-sensitive check for email and password
        String query = "SELECT * FROM user WHERE BINARY email = '" + email + "' AND BINARY password = '" + password + "'";
        ResultSet rs = DBOperations.getData(query); // Execute the case-sensitive query
        
        if (rs.next()) { // If a matching record is found
            user = new User();
            user.setStatus(rs.getString("status")); // Get the user status
            user.setName(rs.getString("name")); // Get the user's name
            user.setEmail(rs.getString("email")); // Get the user's email
            user.setMobilenumber(rs.getString("mobilenumber")); // Get the mobile number
            // Add other user details as needed
        }

        rs.close(); // Close the ResultSet to release resources
    } catch (Exception e) {
        // Display a user-friendly error message if something goes wrong
        JOptionPane.showMessageDialog(null, "Database error: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
    }

    return user; // Return the user object or null if no match is found
}

    public static User getName(String email) {
        User user = null;
       
        try {
            ResultSet rs = DBOperations.getData("SELECT * FROM user WHERE email = '" + email + "'");
            while (rs.next()) {
                user = new User();
                user.setName(rs.getString("Name"));
                user.setEmail(rs.getString("Email"));
             user.setMobilenumber(rs.getString("MobileNumber"));
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
        return user;
    }
    
    public static boolean emailExists(String email) {
        boolean exists = false; // Default to false

        try {
            // Query to find the email in the user table
            String query = "SELECT * FROM user WHERE email = '" + email + "'";
            ResultSet rs = DBOperations.getData(query); // Execute the query

            if (rs.next()) { // If there's at least one result, the email exists
                exists = true;
            }

            rs.close(); // Close the ResultSet
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Database Error", JOptionPane.ERROR_MESSAGE);
        }

        return exists; // Return whether the email exists or not
    }
    

    public static void save(String email, String newPassword) {
        String query = "UPDATE user SET password = '" + newPassword + "' WHERE email  = '" + email + "'";
        DBOperations.setDataOrDelete(query, "Password Changed Successfully");
    }

}
