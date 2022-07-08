/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ebilling;

import java.sql.*;

/**
 *
 * @author CORECOMP
 */
public class Conn {
    
    Connection con;
    Statement Stmt;
    PreparedStatement search,insert,delete;
    
   public Conn(){  
        try{  
            Class.forName("com.mysql.jdbc.Driver");  
            con =DriverManager.getConnection("jdbc:mysql:///ebill","root","Pass@123");    
            Stmt =con.createStatement();  
            
           
           
        }catch(Exception e){ 
            System.out.println(e);
        }  
    }  
}
