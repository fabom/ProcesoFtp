//Hilo: Descarga de FTP archivo a C:/prueba/ 

package proceso_ftp;

import java.awt.Desktop;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import org.apache.commons.net.ftp.FTPClient;

public class descargar implements Runnable { //interfaz runnable
  
@Override  //sobreescribe metodo de clase padre   
public void run(){ //Ejecuta proceso hilo
 
  String ruta="c:/prueba/";     //ruta directorio a guardar
  String archi=formu1.c1.getSelectedValue(); //nombre archivo
  int opcion=0;

 //coneccion al servidor FTP 
  if (!"".equals(archi)) {
    
  FTPClient client = new FTPClient();  
   try {
     client.connect("213.190.6.10");
      boolean acceso = client.login("u312507976.usuario1","Use-1234"); //usuario,clave

    if (acceso==true) {
  
    FileOutputStream archivo=new FileOutputStream(ruta+archi);  
    client.setControlEncoding ("UTF-8"); 
    client.setFileType(FTPClient.BINARY_FILE_TYPE);  
    client.enterLocalPassiveMode();  
    
    boolean descarga = client.retrieveFile(archi,archivo);archivo.close();
    if (descarga) {
        JOptionPane.showMessageDialog(null,"Archivo descargado");
       //Grafica de directorio archivo y abrirlo desde C:/prueba
        JFileChooser dlg = new JFileChooser();
        dlg.setCurrentDirectory(new File(ruta));
        opcion = dlg.showOpenDialog(dlg);
        if(opcion==JFileChooser.APPROVE_OPTION)
         {String verarchi=dlg.getSelectedFile().getPath();
        File objarchivo=new File (verarchi);Desktop.getDesktop().open(objarchivo);}
    } 
    else {JOptionPane.showMessageDialog(null, "Error en la descarga");}
        
    if(opcion==JFileChooser.CANCEL_OPTION)
        {JOptionPane.showMessageDialog(null, "Accion cancelada por el usuario");}
   
} } catch (IOException ex) {            
        Logger.getLogger(descargar.class.getName()).log(Level.SEVERE, null, ex);}  } 
}   }
   
  
 