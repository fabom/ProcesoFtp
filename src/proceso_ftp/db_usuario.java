//Hilo: De consulta, actualiza y nuevo en BD MYSQL: USUARIO 
//de tabla regusuario: cedusuario,correusuario,preg1,resp1  

package proceso_ftp;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

public class db_usuario implements Runnable { //interfaz runnable
 
Connection db = null;
Statement st = null;
ResultSet rs = null;
PreparedStatement pst=null;

StringBuilder clave;

String cedu,corre,clav;
String sql;
static int op;
static boolean a;

//Conectarse base dato
public void conectarseDB() throws SQLException {
    db=DriverManager.getConnection("jdbc:mysql://213.190.6.10:3306/u312507976_usuario","u312507976_usuario1","Usuario-1"); 
//ruta jbdc/basedatos,usuario,contraseña  base dato:XXX,usuario,contraseña
}

//Limpia entrada datos
static public void lim_entrada() {
 formu2.a1.setText(""); formu2.a2.setText("");formu2.a3.setText("");}

//Generar clave
public void genera_clave() throws NoSuchAlgorithmException {
SecureRandom cla = SecureRandom.getInstanceStrong();
byte[] valor = new byte[4]; // 4 bit
cla.nextBytes(valor);clave = new StringBuilder();
for (byte b : valor) {clave.append(String.format("%02x", b));} }

@Override  //sobreescribe metodo de clase padre   
public void run(){
    try {
    //Ejecuta proceso hilo

   conectarseDB();
     
   //Consulta 
   if (op==1) { formu2.jButton1.setEnabled(true);
     st = db.createStatement();cedu=formu2.a1.getText();formu2.a3.setEnabled(false);
     rs = st.executeQuery("select * from regusuario where cedusuario='"+cedu+"'");
     if (rs.next()) {a=true;
      formu2.a2.setText(rs.getString(2));formu2.a3.setText(rs.getString(3));}
      else {JOptionPane.showMessageDialog(null,"No Existe...");a=false;
     }
   }
    
   //Actualiza y nuevo
   if (op==2) {  formu2.jButton1.setEnabled(false);
   
   genera_clave();
   
    //Actualiza   
    if(a==true&&cedu!="") {cedu=formu2.a1.getText();
      corre=formu2.a2.getText();formu2.a3.setText(clave.toString());
      clav=formu2.a3.getText();formu2.a3.setEnabled(false);
      sql = "update regusuario set correusuario=?, clave=? where cedusuario='"+cedu+"'";
      pst = db.prepareStatement(sql);
      pst.setString(1,corre);pst.setString(2,clav);pst.executeUpdate();
      JOptionPane.showMessageDialog(null,"Se Actualizo...");
     } 
   
   //Nuevo 
   if (a==false&&cedu!="") {
     cedu=formu2.a1.getText();corre=formu2.a2.getText();
     formu2.a3.setText(clave.toString());clav=formu2.a3.getText();
     formu2.a3.setEnabled(false);
     sql = "insert into regusuario(cedusuario,correusuario,clave) values (?,?,?)";
     pst = db.prepareStatement(sql);
     pst.setString(1,cedu);pst.setString(2,corre);pst.setString(3,clav);
     pst.executeUpdate();
     JOptionPane.showMessageDialog(null,"Se Guardo...");} }
   
    } catch (SQLException | NoSuchAlgorithmException ex) {
       Logger.getLogger(db_usuario.class.getName()).log(Level.SEVERE, null, ex);}
} }  
 
  
   
  
 