package Restaruant_Management_System_Demo;

import Restaruant_Management_System_Entity.Food_Items;
import Restaruant_Management_System_Entity.Reservation_Tables;
import Restaruant_Management_System_Entity.User;
import Restaruant_Management_System_Entity.Orders;
import Restaruant_Management_System_Util.Restaruant_Management_System_Util;
import org.hibernate.Session;

import Restaruant_Management_System_DAO.Food_Items_Dao;
import Restaruant_Management_System_DAO.Orders_Dao;
import Restaruant_Management_System_DAO.Reservation_Tables_Dao;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Login_App {

    public static void main(String[] args) {
    	Scanner scan = new Scanner(System.in);

    	System.out.println("Welcome to ANP Restaurant \n");

    	// Choose role
    	int roleChoice = 0;
    	while (roleChoice != 1 && roleChoice != 2) {
    	    System.out.println("Choose your role:");
    	    System.out.println("1. Admin");
    	    System.out.println("2. User");
    	    System.out.print("Enter your choice (1/2): ");
    	    if (scan.hasNextInt()) {
    	        roleChoice = scan.nextInt();
    	        if (roleChoice != 1 && roleChoice != 2) {
    	            System.out.println("Invalid choice! Please enter 1 or 2.");
    	        }
    	    } else {
    	        System.out.println("Invalid input! Please enter a number.");
    	        scan.next(); // Clear the invalid input from the scanner
    	    }
    	}

        String username;
        String password;

        System.out.print("Enter username: ");
        username = scan.next();
        System.out.print("Enter password: ");
        password = scan.next();

        Session session = Restaruant_Management_System_Util.getSessionFactory().openSession();

        try {
            // Retrieve user from database
            User user = session.get(User.class, username.toLowerCase()); // Convert to lowercase for case-insensitivity

            if (user != null && user.getPassword().equals(password) && user.getUsertype().equalsIgnoreCase("admin")) {
                System.out.println("\n\n ********** WELCOME TO " + user.getUsername() + " MODULE **********");
                System.out.println("1 Adding Food Items");
                System.out.println("2 Approve Table Reservation");
                System.out.println("3.View Orders");
                System.out.println("4.View Past Table Reservation Requests:");
                System.out.println("5.Update Food items: ");
                System.out.println("6.Delete Food items: ");
                System.out.println("7.Exit");
                

                int choice = 0;
                while (choice < 1 || choice > 7) {
                    System.out.println("Enter your Choice: ");
                    if (scan.hasNextInt()) {
                        choice = scan.nextInt();
                        if (choice < 1 || choice > 7) {
                            System.out.println("Invalid choice! Please enter a number between 1 and 7.");
                        }
                    } else {
                        System.out.println("Invalid input! Please enter a number.");
                        scan.next(); // Clear the invalid input from the scanner
                    }
                }
                
                switch (choice) {
                    case 1: 
                    	Food_Items_Dao.addFood_Items();
                        break;
                    case 2:
                    	Reservation_Tables_Dao.approveReservationTable(session);
                        break;
                    case 3: 
                        Orders_Dao.viewOrdersByAdmin(session);
                        break;
                    case 4:
                    	Reservation_Tables_Dao.displayPastReservationRequests(session);
                    	break;
                    case 5:
                        Food_Items_Dao.updateFoodItem(session, scan);
                        break;
                    case 6:
                        Food_Items_Dao.deleteFoodItem(session, scan);
                        break;
                    case 7:
                        System.out.println("Exiting Admin Module...");
                        return; // Exit from the admin module
                    default:
                        System.out.println("Invalid choice!");
                        break;

                }
            } else if (user != null && user.getPassword().equals(password) && user.getUsertype().equalsIgnoreCase("user")) {
                System.out.println("\n\n ********** WELCOME TO " + user.getUsername() + " MODULE ********** " );
                System.out.println("1.Order Food: ");
                System.out.println("2.Book An Reservation Table: ");
                System.out.println("3.Exit");

                int userChoice = 0;
                while (userChoice < 1 || userChoice > 3) {
                    System.out.println("Enter your Choice: ");
                    if (scan.hasNextInt()) {
                        userChoice = scan.nextInt();
                        if (userChoice < 1 || userChoice > 3) {
                            System.out.println("Invalid choice! Please enter a number between 1 and 3.");
                        }
                    } else {
                        System.out.println("Invalid input! Please enter a number.");
                        scan.next(); // Clear the invalid input from the scanner
                    }
                }
                switch (userChoice) {
                case 1:
                    Orders_Dao.orderFood(session, user, scan);
                    break;
                case 2:
                	Reservation_Tables_Dao.bookReservationTable(session, user);
                	break;
                case 7:
                    System.out.println("Exiting Admin Module...");
                    return; // Exit from the admin module
                default:
                    System.out.println("Invalid choice!");
                    break;
            }
        } else {
            System.out.println("Invalid credentials or role selection!");
        }
    } catch (Exception e) {
        e.printStackTrace();
    } finally {
        session.close(); // Close the session to release resources
    }
    }
}
