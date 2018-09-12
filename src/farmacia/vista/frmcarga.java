/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package farmacia.vista;


import com.sun.awt.AWTUtilities;
import java.awt.Cursor;
import java.awt.Dimension;
import javax.swing.*;

/**
 *
 * @author fecyp
 */
public class frmcarga extends JFrame implements Runnable{
Thread tiempo;
JLabel jlcarga;
    frmcarga()
    {
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
             new frmusuariologin().setVisible(true);
             
             
            
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
    public static void main(String args[]) {
        try {
            javax.swing.UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
                new frmcarga().setVisible(true);
           
    }
}
