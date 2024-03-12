package Restaruant_Management_System_DAO;
import java.util.Scanner;


import org.hibernate.Session;
import Restaruant_Management_System_Entity.Orders;
import java.util.List;
import Restaruant_Management_System_Entity.Food_Items;
import Restaruant_Management_System_Util.Restaruant_Management_System_Util;
import jakarta.transaction.SystemException;
import org.hibernate.Transaction;

public class Food_Items_Dao {
	
	 public static void displayAvailableFoodItems(Session session) {
	        System.out.println("Available Food Items:");
	        List<Food_Items> foodItems = session.createQuery("FROM Food_Items", Food_Items.class).list();
	        for (Food_Items item : foodItems) {
	            System.out.println("ID: " + item.getItemId() + ", Name: " + item.getName() + ", Description: " + item.getDescription() + ", Price: " + item.getPrice());
	        }
	    }
	 

	 public boolean TestsaveFood(Food_Items food_items) {
	     Transaction transaction = null;
	     try (Session session = Restaruant_Management_System_Util.getSessionFactory().openSession()) {
	         transaction = session.beginTransaction();
	         session.persist(food_items);
	         transaction.commit();
	         return true;
	     } catch (Exception e) {
	         if (transaction != null) {
	             transaction.rollback();
	         }
	         e.printStackTrace();
	         return false;
	     }
	 }



	         
	 public static void addFood_Items() throws IllegalStateException, SystemException {
		 
		 
		// Adding Food Items
		 
		 Scanner scan = new Scanner(System.in); 
         System.out.println("Enter the details of the food item:");
         System.out.print("Name: ");
         String name = scan.nextLine();
         System.out.print("Description: ");
         String description = scan.nextLine();
         System.out.print("Price: ");
         double price;
         try {
             price = Double.parseDouble(scan.nextLine());
         } catch (NumberFormatException e) {
             // Prompt the user to enter the price again
             System.out.print("Price: ");
             price = Double.parseDouble(scan.nextLine());
         }

         Transaction transaction = null;
 		
 		try (Session session = Restaruant_Management_System_Util.getSessionFactory().openSession()) {
 			
 		// Begin a transaction
 	         session.beginTransaction();
 	         
 	     // Create a new Food_Items object
 	         Food_Items foodItem = new Food_Items();
 	         foodItem.setName(name);
 	         foodItem.setDescription(description);
 	         foodItem.setPrice(price);
 
             // Save the food item to the database
             session.save(foodItem);

             // Commit the transaction
             session.getTransaction().commit();
             System.out.println("Food item added successfully!");
         } catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
			e.printStackTrace();
		}
	 }
	 
	 
	 public static void updateFoodItem(Session session, Scanner scan) {
	        displayAvailableFoodItems(session); // Display available food items for reference

	        System.out.println("Enter the ID of the food item you want to update: ");
	        int itemId = scan.nextInt();
	        scan.nextLine(); // Consume newline

	        // Retrieve the food item from the database
	        Food_Items foodItem = session.get(Food_Items.class, itemId);
	        if (foodItem == null) {
	            System.out.println("Food item not found with ID: " + itemId);
	            return;
	        }

	        // Display the current details of the food item
	        System.out.println("Current details of the food item:");
	        System.out.println("Name: " + foodItem.getName());
	        System.out.println("Description: " + foodItem.getDescription());
	        System.out.println("Price: " + foodItem.getPrice());

	        // Prompt for new details
	        System.out.println("Enter the updated details of the food item:");
	        System.out.print("Name (Enter '-' to keep current): ");
	        String name = scan.nextLine();
	        if (!name.equals("-")) {
	            foodItem.setName(name);
	        }
	        System.out.print("Description (Enter '-' to keep current): ");
	        String description = scan.nextLine();
	        if (!description.equals("-")) {
	            foodItem.setDescription(description);
	        }
	        System.out.print("Price (Enter '-' to keep current): ");
	        String priceInput = scan.nextLine();
	        if (!priceInput.equals("-")) {
	            try {
	                double price = Double.parseDouble(priceInput);
	                foodItem.setPrice(price);
	            } catch (NumberFormatException e) {
	                System.out.println("Invalid price format. Price not updated.");
	            }
	        }

	        // Update the food item in the database
	        try {
	            session.beginTransaction();
	            session.update(foodItem);
	            session.getTransaction().commit();
	            System.out.println("Food item updated successfully!");
	        } catch (Exception e) {
	            if (session.getTransaction() != null) {
	                session.getTransaction().rollback();
	            }
	            e.printStackTrace();
	            System.out.println("Failed to update food item.");
	        }
	    }
	 
	 
	 public static void deleteFoodItem(Session session, Scanner scan) {
	        displayAvailableFoodItems(session); // Display available food items for reference

	        System.out.println("Enter the ID of the food item you want to delete: ");
	        int itemId = scan.nextInt();
	        scan.nextLine(); // Consume newline

	        // Retrieve the food item from the database
	        Food_Items foodItem = session.get(Food_Items.class, itemId);
	        if (foodItem == null) {
	            System.out.println("Food item not found with ID: " + itemId);
	            return;
	        }

	        // Display the details of the food item to be deleted
	        System.out.println("Details of the food item to be deleted:");
	        System.out.println("Name: " + foodItem.getName());
	        System.out.println("Description: " + foodItem.getDescription());
	        System.out.println("Price: " + foodItem.getPrice());

	        // Confirm deletion
	        System.out.println("Are you sure you want to delete this food item? (yes/no): ");
	        String confirmation = scan.nextLine().trim().toLowerCase();
	        if (confirmation.equals("yes")) {
	            // Delete the food item from the database
	            try {
	                session.beginTransaction();
	                session.delete(foodItem);
	                session.getTransaction().commit();
	                System.out.println("Food item deleted successfully!");
	            } catch (Exception e) {
	                if (session.getTransaction() != null) {
	                    session.getTransaction().rollback();
	                }
	                e.printStackTrace();
	                System.out.println("Failed to delete food item.");
	            }
	        } else {
	            System.out.println("Deletion canceled.");
	        }
	    }


	 
	public void displayOrders(Session session) {
		// TODO Auto-generated method stub
		
	}
	
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

}
