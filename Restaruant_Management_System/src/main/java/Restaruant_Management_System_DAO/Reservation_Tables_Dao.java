package Restaruant_Management_System_DAO;
import java.util.Scanner;

import java.util.List;

import org.hibernate.Session;

import org.hibernate.query.Query;

import Restaruant_Management_System_Entity.Reservation_Tables;
import Restaruant_Management_System_Entity.User;
import Restaruant_Management_System_Util.Restaruant_Management_System_Util;
import jakarta.transaction.Transaction;
import jakarta.transaction.SystemException;


public class Reservation_Tables_Dao {
	
	
	public static void displayPendingReservationRequests(Session session) {
        System.out.println("Pending Reservation Requests:");
        List<Reservation_Tables> pendingRequests = session.createQuery("FROM Reservation_Tables WHERE approved = false", Reservation_Tables.class).list();
        for (Reservation_Tables request : pendingRequests) {
            System.out.println("Table ID: " + request.getTableId() + ", Name: " + request.getFirstName() + " " + request.getLastName() + ", Email: " + request.getEmail());
        }
    }
	
	public static void approveReservationTable(Session session) {
		Scanner scan = new Scanner(System.in);
		System.out.println("Approve Table Reservation");
        Reservation_Tables_Dao rt = new Reservation_Tables_Dao();
        // Assuming you have a method to display available food items
        rt.displayPendingReservationRequests(session);
        System.out.print("Enter table reservation ID to approve: ");
        int reservationId = scan.nextInt();
        Reservation_Tables reservation = session.get(Reservation_Tables.class, reservationId);
        if (reservation != null) {
            // Approve the reservation
            reservation.setApproved(true);
            session.beginTransaction();
            session.update(reservation);
            session.getTransaction().commit();
            System.out.println("Table reservation approved successfully!");
        } else {
            System.out.println("Table reservation not found with ID: " + reservationId);
        }
	}
	
	
	public boolean TestsaveReservationTable(Reservation_Tables reservation) {
        org.hibernate.Transaction transaction = null;
        try (Session session = Restaruant_Management_System_Util.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.persist(reservation);
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

	
	
	 public static void bookReservationTable(Session session, User user) throws IllegalStateException, SystemException {
	        Scanner scan = new Scanner(System.in);
	        System.out.println("Book A Reservation Table");
	        System.out.print("Enter the number of seats required: ");
	        int seats = scan.nextInt();
	        System.out.print("Enter the date (YYYY-MM-DD): ");
	        String date = scan.next();
	        System.out.print("Enter the time (HH:MM): ");
	        String time = scan.next();

	        // Prompt the user to enter personal information for the reservation
	        System.out.print("Enter your first name: ");
	        String firstName = scan.next();
	        System.out.print("Enter your last name: ");
	        String lastName = scan.next();
	        System.out.print("Enter your email: ");
	        String email = scan.next();

	        // Create a new Reservation_Tables object
	        Reservation_Tables reservation = new Reservation_Tables(firstName, lastName, email);

	        // Set additional reservation details
	        reservation.setSeats(seats);
	        reservation.setDate(date);
	        reservation.setTime(time);
	        reservation.setUser(user);

	        
	        Transaction transaction = null;
	 
	        try {
	        	
	        	// Begin a transaction
		        session.beginTransaction();
		 
		        // Create a new Reservation_Tables object
		        Reservation_Tables reservation1 = new Reservation_Tables(firstName, lastName, email);

		        // Set additional reservation details
		        reservation1.setSeats(seats);
		        reservation1.setDate(date);
		        reservation1.setTime(time);
		        reservation1.setUser(user);
		        
	            // Save the reservation to the database
	            session.save(reservation1);

	            // Commit the transaction
	            session.getTransaction().commit();
	            System.out.println("Reservation table booked successfully!");
	        } catch (Exception e) {
				if (transaction != null) {
					transaction.rollback();
				}
				e.printStackTrace();
			}
	    }


	public static void displayPastReservationRequests(Session session) {
		// TODO Auto-generated method stub
		try {
            // Query to retrieve past reservation requests that have been approved
            Query<Object[]> query = session.createQuery("select r.tableId, r.email, r.firstName, r.lastName from Reservation_Tables r where r.approved = true", Object[].class);
            List<Object[]> pastRequests = query.list();

            // Display past reservation requests
            if (pastRequests.isEmpty()) {
                System.out.println("No past reservation requests found.");
            } else {
                System.out.println("Past Table Reservation Requests:");
                for (Object[] row : pastRequests) {
                    long tableId = (long) row[0];
                    String email = (String) row[1];
                    String firstName = (String) row[2];
                    String lastName = (String) row[3];
                    System.out.println("Table ID: " + tableId + ", Name: " + firstName + " " + lastName + ", Email: " + email);
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
		
}
	 


