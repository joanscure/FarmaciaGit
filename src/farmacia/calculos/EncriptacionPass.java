/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package farmacia.calculos;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 *
 * @author fecyp
 */
public class EncriptacionPass {
    public static void main(String[] args) {
        String contraseña1="hola";
        String contraseña_encrip=cryptMD5(contraseña1);
        System.out.println("contraseña : "+ contraseña1);
        System.out.println("contraseña encriptada: "+contraseña_encrip);
        System.out.println("numero de caracteres: "+contraseña_encrip.length());
      
    }
    public static String cryptMD5(String textoPlano)
	{
		try
		{
		    final char[] HEXADECIMALES = { '0','1','2','3','4','5','6','7','8','9','a','b','c','d','e','f' };

		   MessageDigest msgdgt = MessageDigest.getInstance("MD5");
		   byte[] bytes = msgdgt.digest(textoPlano.getBytes());
		   StringBuilder strCryptMD5 = new StringBuilder(2 * bytes.length);
		   for (int i = 0; i < bytes.length; i++)
		   {
		       int low = (int)(bytes[i] & 0x0f);
		       int high = (int)((bytes[i] & 0xf0) >> 4);
		       strCryptMD5.append(HEXADECIMALES[high]);
		       strCryptMD5.append(HEXADECIMALES[low]);
		   }
		   return strCryptMD5.toString();
		} catch (NoSuchAlgorithmException e) {
		   return null;
		}
	}
}
