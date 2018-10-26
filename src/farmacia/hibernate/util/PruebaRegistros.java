/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package farmacia.hibernate.util;

import farmacia.hibernate.dao.DAOException;
import farmacia.hibernate.dao.DAOManager;
import farmacia.hibernate.dao.implementacion.DAOManagerIMPL;
import farmacia.hibernate.modelo.Empleado;
import farmacia.hibernate.modelo.Persona;
import farmacia.hibernate.modelo.Tipotrabajador;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

/**
 *
 * @author jaico
 */
public class PruebaRegistros {
    
        public static void main(String[] args) {
//        SessionFactory sessionFac = NewHibernateUtil.getSessionFactory();
//        Session sesion1;
//        sesion1 = sessionFac.openSession();
//        Transaction tx = sesion1.beginTransaction();
//        sesion1.save(crearPersona());
//        tx.commit();
//        
//        sesion1.close();
//        sessionFac.close();
//        
//        System.out.println("1111");
//            System.out.println("Sesion abierta: " +sesion1.isOpen());
//            System.out.println("Sesion factory cerrada: "+sessionFac.isClosed());
//        NewHibernateUtil.cerrar();
//            System.out.println("1");
//        NewHibernateUtil.cerrar(sessionFac);
//            System.out.println("2");
            try {
                DAOManager man = new DAOManagerIMPL();
                
                man.getEmpleadoDAO().insertar(crearEmpledao());
                
                
            } catch (DAOException ex) {
                System.out.println(ex.getMessage() + ":c");;
            }
    }
    
    private static Persona crearPersona(){
        Persona p;
        p = new Persona("Pedro", "Ramirez", "Cruz", "9832413", true);
        return p;
    }
    
    private static Empleado crearEmpledao(){
        Empleado e;
        Persona p;
        Tipotrabajador t;
        p = new Persona("Miguel", "Estudio", "Carrasco", "12312321", true);
        t = new Tipotrabajador("Prueba", true, true, true, true, true, true, true, true, true, true, true, true, true);
        e = new Empleado(p, t, "prueba", "prueba", true);
        return e;
    }
}
