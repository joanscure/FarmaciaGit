/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package farmacia.hibernate.util;

import farmacia.hibernate.modelo.Persona;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

/**
 *
 * @author jaico
 */
public class PruebaRegistros {
    
        public static void main(String[] args) {
        SessionFactory sessionFac = NewHibernateUtil.getSessionFactory();
        Session sesion1;
        sesion1 = sessionFac.openSession();
        Transaction tx = sesion1.beginTransaction();
        sesion1.save(crearPersona());
        tx.commit();
        
        sesion1.close();
        sessionFac.close();
        
        System.out.println("1111");
            System.out.println("Sesion abierta: " +sesion1.isOpen());
            System.out.println("Sesion factory cerrada: "+sessionFac.isClosed());
        NewHibernateUtil.cerrar();
            System.out.println("1");
        NewHibernateUtil.cerrar(sessionFac);
            System.out.println("2");
//         
    }
    
    private static Persona crearPersona(){
        Persona p;
        p = new Persona("jose", "perez", "gonzales", "12345678", true);
        return p;
    }
}
