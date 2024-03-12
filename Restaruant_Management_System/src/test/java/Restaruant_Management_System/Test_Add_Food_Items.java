package Restaruant_Management_System;

import org.junit.jupiter.api.BeforeEach;

import org.junit.jupiter.api.Test;

import Restaruant_Management_System_DAO.Food_Items_Dao;
import Restaruant_Management_System_Entity.Food_Items;
import jakarta.transaction.SystemException;

import static org.junit.jupiter.api.Assertions.*;


public class Test_Add_Food_Items {
	
	Food_Items_Dao fooddao;
	
	 @BeforeEach
	    public void setUp() {
	     
		 fooddao = new Food_Items_Dao();
	     
	    }	 
	 @Test
	 public void testNewFoodItemsInsert() {
	     Food_Items f = new Food_Items();
	     
	     f.setName("Mutton Biryani");
	     f.setDescription("Mutton Biryani Boneless");
	     f.setPrice(350);

	     // Assert that login is successful
	     try {
			assertTrue(fooddao.TestsaveFood(f));
		} catch (IllegalStateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	 }
}



