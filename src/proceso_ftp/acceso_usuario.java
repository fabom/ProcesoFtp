//Hilo: Acceso como usuario valido a procesos FTP

package proceso_ftp;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

public class acceso_usuario implements Runnable { //interfaz runnable
 
Connection db = null;
Statement st = null;
ResultSet rs = null;
PreparedStatement pst=null;

String cedu,clav;
String sql;

//Conectarse base dato
public void conectarseDB() throws SQLException {
    db=DriverManager.getConnection("jdbc:mysql://213.190.6.10:3306/u312507976_usuario","u312507976_usuario1","Usuario-1"); 
//ruta jbdc/basedatos,usuario,contraseña  base dato:XXX,usuario,contraseña
}

@Override  //sobreescribe metodo de clase padre   
public void run(){
    try {
    //Ejecuta proceso hilo
    conectarseDB();
    st = db.createStatement();cedu=formu3.a1.getText();clav=formu3.a2.getText();
     rs = st.executeQuery("select * from regusuario where cedusuario='"+cedu+"'"+"and clave='"+clav+"'");
     if (rs.next()) {
        formu1 form2 = new formu1();form2.setVisible(true);}
      else {JOptionPane.showMessageDialog(null,"No existe...");}
   }
     catch (SQLException ex) {
       Logger.getLogger(acceso_usuario.class.getName()).log(Level.SEVERE, null, ex);}
} }  
   

 