//Hilo: Eliminar archivo de FTP 

package proceso_ftp;

import java.io.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;
import org.apache.commons.net.ftp.FTPClient;

public class elimina_FTP implements Runnable { //interfaz runnable
  
@Override  //sobreescribe metodo de clase padre   
public void run(){ //Ejecuta proceso hilo

    DefaultListModel m1 = new DefaultListModel();m1.clear();
    FTPClient client = new FTPClient();
    try {
        client.connect("213.190.6.10");
        boolean acceso = client.login("u312507976.usuario1","Use-1234"); //usuario,clave
       if (formu1.c1.getSelectedIndex()!=-1) {   
       if (acceso==true) {
       String archi=formu1.c1.getSelectedValue(); //nombre archivo
       boolean elimi = client.deleteFile(archi);
    if (elimi) {
        JOptionPane.showMessageDialog(null,"Archivo eliminado");
        int z=formu1.c1.getSelectedIndex();
        if (z>=0) { m1.removeElement(z);formu1.c1.setModel(m1);}
         } } }
     } catch (IOException ex) {
        Logger.getLogger(lecturageneral.class.getName()).log(Level.SEVERE, null, ex);}

}   }
   
  
 