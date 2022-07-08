/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ebilling;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author CORECOMP
 */
public class NewClass {
    
    public static void main(String args[]) throws SQLException{
        Conn c = new Conn();
        
        String s = "select distinct payments.id,customers.name,customers.phone,Customers.Address,payments.balance from customers right join payments on payments.id = customers.id where payments.balance>0 and customers.address = 'abc' and customers.id = 123  order by payments.tid desc limit 1;";
        
        ResultSet rs = c.Stmt.executeQuery(s);
        
        while(rs.next()){
            System.out.println(" null");
            if(rs.getString("id") != null){
                
                
                System.out.println(rs.getString("id"));
                System.out.println(rs.getString("balance"));
            } 
            else
                System.out.println("It is null");
            
           
        }
        System.out.println("It is null 2.0");
         System.out.println("You can go");
    }
    
}
