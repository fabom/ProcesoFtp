//Hilo: Guardar archivo en servidor FTP

package proceso_ftp;

import java.io.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;


public class guarda_FTP implements Runnable { //interfaz runnable
    
@Override  //sobreescribe metodo de clase padre   
public void run(){ //Ejecuta proceso hilo
    
    String verarchi1,verarchi2;
    int opcion=0;
  
    FTPClient client = new FTPClient();
    try {
       client.connect("213.190.6.10");
         
      boolean acceso = client.login("u312507976.usuario1","Use-1234"); //usuario,clave
      
      if (acceso==true) {
         
        //Grafica de directorio archivo desde C:/
        JFileChooser dlg = new JFileChooser();
        dlg.setCurrentDirectory(new File("C:/"));
        opcion = dlg.showOpenDialog(dlg);
  
      //Si existe el archivo  
      if(opcion==JFileChooser.APPROVE_OPTION) {
        verarchi1= dlg.getSelectedFile().getPath();
        verarchi2= dlg.getSelectedFile().getName();
 
         client.setFileType(FTP.BINARY_FILE_TYPE);
         client.enterLocalPassiveMode();
        
         File archi=new File(verarchi1);
         FileInputStream canal=new FileInputStream(archi);  
             
         //Codigos de la tarea que me falta
        
         boolean guarda=client.storeFile(verarchi1, canal);
   
        if (guarda) 
        {JOptionPane.showMessageDialog(null,"Archivo guardado en FTP");
          client.rename(verarchi1, verarchi2);}}
    
         if(opcion==JFileChooser.CANCEL_OPTION) 
        {JOptionPane.showMessageDialog(null, "Accion cancelada por el usuario");} }            
    
  } catch (IOException ex) {
        Logger.getLogger(guarda_FTP.class.getName()).log(Level.SEVERE, null, ex);
    } } }   

  