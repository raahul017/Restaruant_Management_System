package Restaruant_Management_System_DAO;
import Restaruant_Management_System_Entity.Orders;

import org.hibernate.Session;
import Restaruant_Management_System_Entity.Orders;
import Restaruant_Management_System_Entity.User;
import Restaruant_Management_System_Entity.Food_Items; // Add import for Food_Items

import java.util.ArrayList;
import java.util.Date; // Add import for Date
import java.util.List;
import java.util.Scanner;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
public class Orders_Dao {

    public static void viewOrdersByAdmin(Session session) {
        try {
            System.out.println("Orders:");
            List<Orders> orders = session.createQuery("FROM Orders", Orders.class).list();
            if (orders.isEmpty()) {
                System.out.println("No orders found.");
            } else {
                for (Orders order : orders) {
                    System.out.println("Order ID: " + order.getOrderId() +
                            ", User: " + order.getUser().getUsername() +
                            ", Food Item: " + order.getFoodItem().getName() +
                            ", Order Date: " + order.getOrderDate());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            // Handle exceptions as per your application's requirements
        }
    }
    

public static void orderFood(Session session, User user, Scanner scan) {
    boolean ordering = true;
    List<Orders> ordersList = new ArrayList<>();

    while (ordering) {
        Food_Items_Dao foodItemsDao = new Food_Items_Dao();
        foodItemsDao.displayAvailableFoodItems(session);

        System.out.print("Enter the ID of the food item you want to order (0 to stop ordering): ");
        int foodItemId = scan.nextInt();

        if (foodItemId == 0) {
            ordering = false;
        } else {
            Food_Items selectedFoodItem = session.get(Food_Items.class, foodItemId);
            if (selectedFoodItem != null) {
                Orders order = new Orders();
                order.setUser(user);
                order.setFoodItem(selectedFoodItem);
                order.setOrderDate(new Date(System.currentTimeMillis())); // Set current date/time
                ordersList.add(order);
                System.out.println("You have ordered: " + selectedFoodItem.getName());
            } else {
                System.out.println("Invalid food item ID! Please select a valid food item.");
            }
        }
    }

    if (!ordersList.isEmpty()) {
        Orders_Dao ordersDao = new Orders_Dao();
        for (Orders order : ordersList) {
            ordersDao.placeOrder(session, order);
        }
        System.out.println("Orders placed successfully!");
    } else {
        System.out.println("No orders placed.");
    }
}

    public void placeOrder(Session session, Orders order) {
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            session.save(order); // Save the order
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
		
	}

	public List<Orders> getOrders(Session session) {
        try {
            String hql = "FROM Orders";
            Query<Orders> query = session.createQuery(hql, Orders.class);
            return query.list();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    
}
