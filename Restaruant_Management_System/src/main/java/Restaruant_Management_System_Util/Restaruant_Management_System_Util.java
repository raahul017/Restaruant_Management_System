package Restaruant_Management_System_Util;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;
import org.hibernate.service.ServiceRegistry;
import java.util.*;
import Restaruant_Management_System_Entity.*;


public class Restaruant_Management_System_Util {
	private static SessionFactory sessionFactory;

    public static SessionFactory getSessionFactory() 
    {
        if (sessionFactory == null)
     
        {
            try {
                Configuration configuration = new Configuration();

                // Hibernate settings equivalent to hibernate.cfg.xml's properties
                Properties settings = new Properties();
                settings.put(Environment.DRIVER, "com.mysql.cj.jdbc.Driver");
                settings.put(Environment.URL, "jdbc:mysql://localhost:3306/Restaruant_Management_System");
                settings.put(Environment.USER, "root");
                settings.put(Environment.PASS, "root");
                settings.put(Environment.SHOW_SQL, "false");

                settings.put(Environment.CURRENT_SESSION_CONTEXT_CLASS, "thread");

                settings.put(Environment.HBM2DDL_AUTO, "update");

                configuration.setProperties(settings);
                configuration.addAnnotatedClass(Admin.class);
                configuration.addAnnotatedClass(User.class);
//                configuration.addAnnotatedClass(Servers.class);
                configuration.addAnnotatedClass(Food_Items.class);
                configuration.addAnnotatedClass(Reservation_Tables.class);
                configuration.addAnnotatedClass(Menu.class);
                configuration.addAnnotatedClass(Orders.class);

                
                ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
                    .applySettings(configuration.getProperties()).build();
                System.out.println("Hibernate Java Config serviceRegistry created");
                sessionFactory = configuration.buildSessionFactory(serviceRegistry);
                return sessionFactory;

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return sessionFactory;
    }
}

