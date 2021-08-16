//Hilo: Consulta directorio archivos en servidor FTP

package proceso_ftp;

import java.io.*;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultListModel;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;

public class directorio_FTP implements Runnable { //interfaz runnable
    
@Override  //sobreescribe metodo de clase padre   
public void run(){ //Ejecuta proceso hilo
    DefaultListModel m1 = new DefaultListModel();m1.clear();
    FTPClient client = new FTPClient();
    try {
        client.connect("213.190.6.10");
         boolean acceso = client.login("u312507976.usuario1","Use-1234"); //usuario,clave

         if (acceso==true) {

      FTPFile[] archi=client.listFiles();           
      for (FTPFile muestra_archi:archi) {
         String nom=muestra_archi.getName();
         if (!".".equals(nom)&&!"..".equals(nom)) 
         {m1.addElement(nom);formu1.c1.setModel(m1);} }
      
     } } catch (IOException ex) {
        Logger.getLogger(directorio_FTP.class.getName()).log(Level.SEVERE, null, ex);
    }

} }  
