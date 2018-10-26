/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package farmacia.hibernate.util;

//import javax.imageio.spi.ServiceRegistry;
import org.hibernate.cfg.AnnotationConfiguration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

/**
 * Hibernate Utility class with a convenient method to get Session Factory
 * object.
 *
 * @author jaico
 */
public class NewHibernateUtil {

    private static final SessionFactory sessionFactory;

    static {
//        try {
            // Create the SessionFactory from standard (hibernate.cfg.xml) 
            // config file.
//            sessionFactory = new AnnotationConfiguration().configure().buildSessionFactory();

            Configuration configuration = new Configuration();
            configuration.configure("hibernate.cfg.xml");
            ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder().applySettings(
            configuration.getProperties()).build();
            sessionFactory = configuration.buildSessionFactory(serviceRegistry);

//        } catch (Throwable ex) {
//            // Log the exception. 
//            System.err.println("Initial SessionFactory creation failed." + ex);
//            throw new ExceptionInInitializerError(ex);
//        }
    }

    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }
    
    public static void cerrar(){
        sessionFactory.close();
    }
    
    public static void cerrar(SessionFactory sessionFactory){
        sessionFactory.close();
    }
}
