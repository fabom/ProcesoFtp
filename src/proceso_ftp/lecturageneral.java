//Hilo: Lectura general solo de ARCHIVO.TXT en servidor FTP

package proceso_ftp;

import java.io.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;
import org.apache.commons.net.ftp.FTPClient;

public class lecturageneral implements Runnable { //interfaz runnable
    
@Override  //sobreescribe metodo de clase padre   
public void run(){ //Ejecuta proceso hilo
    DefaultListModel m1 = new DefaultListModel();
    FTPClient client = new FTPClient();
    try {
        client.connect("213.190.6.10");
        boolean acceso = client.login("u312507976.usuario1","Use-1234"); //usuario,clave

     //Si esta seleccionado nombre archivo        
     if (formu1.c1.getSelectedIndex()!=-1) {   
    
        String archivo=formu1.c1.getSelectedValue();
        if (acceso==true&&archivo.contains(".txt")) {
       InputStream archi=client.retrieveFileStream(formu1.c1.getSelectedValue());
       BufferedReader br = new BufferedReader(new InputStreamReader(archi));
       String linea;
     while ((linea = br.readLine()) != null) {
         m1.addElement(linea);formu1.c1.setModel(m1);
     } } 
      else {JOptionPane.showMessageDialog(null, "No es archivo txt");}        
     } 
     else {JOptionPane.showMessageDialog(null, "Seleccione archivo");}        
     
     } catch (IOException ex) {Logger.getLogger(lecturageneral.class.getName()).log(Level.SEVERE, null, ex);}

} }  
