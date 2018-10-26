/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package farmacia.vista;


import com.sun.awt.AWTUtilities;
import farmacia.jdbc.dao.DAOException;
import farmacia.jdbc.dao.DAOManager;
import farmacia.jdbc.dao.mysql.DAOManagerSQL;
import farmacia.jdbc.modelado.empleado;
import java.awt.Cursor;
import java.awt.Dimension;
import java.util.List;
import javax.swing.*;

/**
 *
 * @author fecyp
 */
public class frmcarga extends JFrame implements Runnable{
Thread tiempo;
JLabel jlcarga;
boolean verificar=false;
    frmcarga() throws DAOException
    {
       verificar();
        iniciar_componentes();
        //verificar usuario
          setLocationRelativeTo(null);
        AWTUtilities.setWindowOpaque(this, false);
        tiempo=new Thread(this);
        tiempo.start();
    }
    @Override
    public void run() {
          while(tiempo!=null)
        {
        try {
       
            Thread.sleep(3000);
            tiempo=null;
            this.dispose();
            if(verificar)//esta vacia
            {
                new frmregistrarSuperusuario().setVisible(true);
            }
            else //no esta vacia
            {
             new frmusuariologin().setVisible(true);
            }
             
            
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
        }
    }

    private void iniciar_componentes() {
         jlcarga = new JLabel();
         jlcarga.setIcon(new ImageIcon(getClass().getResource("/Files/cargar.gif")));
        add(jlcarga);
        jlcarga.setPreferredSize(new Dimension(800,357));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setCursor(new Cursor(Cursor.WAIT_CURSOR));
        setUndecorated(true);
         setOpacity(0.8F);
         pack();
    }
    public void verificar() throws DAOException
    {
        //verifica los usuarios si existen si no sse manda para crear uno nuevo
        DAOManagerSQL manager = null;
        try
        {
           manager = new DAOManagerSQL("localhost", "basefarmacia", "root", "");
           List<empleado> lista= manager.getEmpleadoDAO().obtenertodos();
           
          if(lista.isEmpty())
          {
              verificar=true;
          }
                manager.cerrarConexion();
        }catch(DAOException ex)
        {
            throw new DAOException("error al buscar" + ex.getMessage());
        }
    }
}
