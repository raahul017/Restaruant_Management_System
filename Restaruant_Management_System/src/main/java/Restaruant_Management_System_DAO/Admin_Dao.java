package Restaruant_Management_System_DAO;
import org.hibernate.Transaction;


import java.util.List;

import org.hibernate.Session;

import com.mysql.cj.x.protobuf.MysqlxCrud.Order;

import Restaruant_Management_System_Entity.Admin;
import Restaruant_Management_System_Util.Restaruant_Management_System_Util;

public class Admin_Dao {

    // Method to update an admin
    public void UpdateAdmin(Admin admin) {
        Session session = null;
        try {
            session = Restaruant_Management_System_Util.getSessionFactory().openSession();
            session.beginTransaction();
            session.merge(admin);
            session.getTransaction().commit();
        } catch (Exception e) {
            if (session != null) {
                session.getTransaction().rollback();
            }
            e.printStackTrace();
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }
    
 // Method to save an admin
    public void SaveAdmin(Admin admin) {
        Session session = null;
        try {
            session = Restaruant_Management_System_Util.getSessionFactory().openSession();
            session.beginTransaction();
            session.persist(admin);
            session.getTransaction().commit();
        } catch (Exception e) {
            if (session != null) {
                session.getTransaction().rollback();
            }
            e.printStackTrace();
        } finally {
            if (session != null) {
                session.close();
            }
        }
        
    }    
    // Method to get all orders
    public List<Order> getAllOrders() {
        try (Session session = Restaruant_Management_System_Util.getSessionFactory().openSession()) {
            return session.createQuery("FROM Order", Order.class).getResultList();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

}

