/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package farmacia.calculos;

import farmacia.vista.GUI;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.JOptionPane;

/**
 *
 * @author Propietario
 */
public class NumComprobante {
//ese metodo recibe el codigo actual como parametro, incluyendo el indicador de las boletas B y facturas F

    public String nuevoCodigo(String codAnterior) throws Exception {
        validarCadena(codAnterior);
        String res, numero;
        char tipo;
        res = "";
        tipo = codAnterior.toUpperCase().charAt(0);
        numero = codAnterior.substring(1);
        if (tipo == 'B') {
            res = "B" + codigoSiguiente(numero);
        } else if (tipo == 'F') {
            res = "F" + codigoSiguiente(numero);
        }
        return res;
        //ESTE ES EL CODIGO QUE SE GUARDARA EN LA BASE DE DATOS
    }

    void validarCadena(String cad) throws Exception {
        char tipo = cad.toUpperCase().charAt(0);
        if (cad.isEmpty() || cad.length() != 12) {
            throw new Exception("CODIGO INCORRECTO");
        }
        if (tipo != 'B' && tipo != 'F') {
            throw new Exception("COMPROBANTE INVALIDO");
        }
    }

    //este metodo solo recibe los digitos del codigo de la factura o boleta a tratar
    private String codigoSiguiente(String numero) throws Exception {
        int correlativo, num, digN, digC;
        StringBuffer res = new StringBuffer("");
        correlativo = Integer.parseInt(numero.substring(0, 3));//saca los tres primeris digitos, el correlativo
        num = Integer.parseInt(numero.substring(3));//este trunca los tres primeros y obtiene el numero
        //si el numero es menor que el maximo posible que es 99999999 entonces solo aumenta 1
        if (num < 99999999) {
            num++;
        } else {//sino, significa que el correlativo ha llegado a su maximo, entonces pasa al siguiente y el numero empieza otra vez
            correlativo++;
            num = 1;
        }

        if (correlativo == 1000) {
            throw new Exception("¡NUMERO LÍMITE PARA EL CORRELATIVO");
        }
        //hay que ver la NUMBERFORMATEXEPTION que esa sale sola y hay que validarla en el formulario      
        digN = nDigitos(num);
        digC = nDigitos(correlativo);
        for (int i = 0; i < 3 - digC; i++) {
            res.append("0");
        }
        res.append(correlativo);
        for (int i = 0; i < 8 - digN; i++) {
            res.append("0");
        }
        res.append(num);
        return res.toString();
    }

    private int nDigitos(int n) {
        return n >= 1 ? 1 + nDigitos(n / 10) : 0;//numero de digitos en una linea lol xd
    }

    public String toStringCod(String codigo) throws Exception {
        //devuelve el codigo incluyendo el guion que separa el corelativo con el numero 
        validarCadena(codigo);
        return codigo.substring(0, 4) + "-" + codigo.substring(4);
        //ESTE STRING ES SOLO PARA MOSTRAR, NO VA EN LA BASE DE DATOS
    }

    public String buscarBoleta() {
        Connection conexion;
        String id = "empty";
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conexion = DriverManager.getConnection("jdbc:mysql://localhost/farmaciav2", "root", "");

            Statement st = conexion.createStatement();

            String sql = "SELECT * FROM boletacabecera";

            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                id = rs.getString("idboleta");
            }
            if (id.equals("empty")) {
                id = nuevoCodigo("B00100000000");
            } else {
                id = nuevoCodigo(id);

            }

            id = toStringCod(id);
            conexion.close();
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "ERROR DE FORMATO");
        } catch (Exception ex) {
            System.err.println("Imposible realizar conexion con la BD " + ex.getMessage());
        }

        return id;

    }

   public String buscarFactura() {
        Connection conexion;
        String id = "empty";
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conexion = DriverManager.getConnection("jdbc:mysql://localhost/farmaciav2", "root", "");

            Statement st = conexion.createStatement();

            String sql = "SELECT * FROM facturacabecera";

            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                id = rs.getString("idfactura");
            }
            if (id.equals("empty")) {
                id = nuevoCodigo("F00100000000");

            } else {

                id = nuevoCodigo(id);

            }
           
            id = toStringCod(id);
            conexion.close();
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "ERROR DE FORMATO");
        } catch (Exception ex) {
            System.err.println("Imposible realizar conexion con la BD " + ex.getMessage());
        }

        return id;
    }

}
