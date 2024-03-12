package Restaruant_Management_System;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import Restaruant_Management_System_DAO.Reservation_Tables_Dao;
import Restaruant_Management_System_Entity.Reservation_Tables;
import jakarta.transaction.SystemException;

import static org.junit.jupiter.api.Assertions.*;

public class Test_Add_Reservation_Tables {
	
	private Reservation_Tables_Dao reservationDao;
	
	@BeforeEach
	public void setUp() {
		reservationDao = new Reservation_Tables_Dao();
	}

	@Test
	public void testNewReservationTableInsert() {
		Reservation_Tables reservation = new Reservation_Tables();
		reservation.setFirstName("John");
		reservation.setLastName("Doe");
		reservation.setEmail("john@example.com");
		reservation.setSeats(4);
		reservation.setDate("2024-03-10");
		reservation.setTime("18:00");

		assertTrue(reservationDao.TestsaveReservationTable(reservation));
	}
}
