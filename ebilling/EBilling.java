/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ebilling;

import java.awt.Color;
import javax.swing.*;
import java.awt.event.*;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

/**
 *
 * @author CORECOMP
 */
public class EBilling extends JFrame implements ActionListener ,ListSelectionListener{
    JFrame j;
    JLabel Name,id;
    JList List1;
    JTextField nameBox,idBox,addressBox;
    String Text;
    JButton search,nc,go,fr;
    JScrollPane jsp;
    ResultSet s1;
    final DefaultListModel<String> l1 = new DefaultListModel<>();
    
    EBilling(){
        
        j = new JFrame("Search");
        j.setLayout(null);
        j.setLocation(400,100);
        
        j.getContentPane().setBackground(Color.WHITE);
        
        Name = new JLabel("Name");
        Name.setBounds(20,0,100,100);
        Name.setFont(Name.getFont().deriveFont(18.0f));
        j.add(Name);
        
        id = new JLabel("Cust ID");
        id.setBounds(400,00,100,100);
        id.setFont(id.getFont().deriveFont(18.0f));
        j.add(id);
        
        nameBox = new JTextField();
        nameBox.setBounds(130,30,250,30);
        j.add(nameBox);
        
        idBox = new JTextField();
        idBox.setBounds(480,30,250,30);
        j.add(idBox);
        
        search = new JButton("Search");
        search.setBounds(200,100,100,30);
        search.addActionListener(this);
        search.setBackground(Color.BLACK);
        search.setForeground(Color.WHITE);
        j.add(search);
        
        nc = new JButton("Add Cust");
        nc.setBounds(350,100,100,30);
        nc.addActionListener(this);
        nc.setBackground(Color.BLUE);
        nc.setForeground(Color.WHITE);
        j.add(nc);
        
        fr = new JButton("Records");
        fr.setBounds(500,100,100,30);
        fr.addActionListener(this);
        fr.setBackground(Color.BLUE);
        fr.setForeground(Color.WHITE);
        j.add(fr);
        
        
  
        l1.addElement("");
        List1 = new JList(l1);
//        List1.setBounds(20,170,200,550);
        List1.setFont(List1.getFont().deriveFont(18.0f));
//        j.add(List1);

        
         jsp = new JScrollPane(List1);
         jsp.setBounds(10,200,750,400);
        j.add(jsp);
        
        List1.addListSelectionListener(this);
        
      
       
        
        j.setDefaultCloseOperation(j.EXIT_ON_CLOSE);
        j.setSize(800,700);
        j.setVisible(true);
    }
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        new EBilling();
        
    }

    @Override
    public void actionPerformed(ActionEvent e) {
       
        l1.clear();
        
        int count=0;
        if(e.getSource()==search){
        String a = nameBox.getText();
       String b ;
        b= idBox.getText();
        
        
        String s = "select * from customers where "+ "  id= "+b;
        String s2 = "select * from customers where" +" Name like  '%"+a+"%'";
        
        try{
           Conn c1 = new Conn();
           if(a.equals("")){
               if(b.equals("")){
           s1=c1.Stmt.executeQuery("Select * from customers");
            
        }
               else
            s1 = c1.Stmt.executeQuery(s);
           }
           else{
              s1 = c1.Stmt.executeQuery(s2);
           }
           
            while(s1.next()){
                count++;
              String user = s1.getString("Name");
            l1.addElement(user);
       
//        j.add(List1);
            }
            if(count==0){
                l1.addElement("No Records Found!!!");
       
//        j.add(List1);
            }
           
        
    }
        catch(Exception ex){
            System.out.print(ex);
        }

    }
    
        else if(e.getSource()== nc){
            this.setVisible(false);
            new NewCustomer().setVisible(true);
    
}
        else if(e.getSource()== fr){
            this.setVisible(false);
            Records r = new Records();
    
}
    }

    @Override
    public void valueChanged(ListSelectionEvent e) {
         
     String aa =(String) List1.getSelectedValue();
       String Query = "Select * from customers where name ='"+aa+"'";
        
        try {
            Conn con = new Conn();
            s1 =con.Stmt.executeQuery(Query);
            while(s1.next()){
                
                    
                    int id = s1.getInt("id");
                    
                    ViewInformation v = new ViewInformation(id);
                    v.setVisible(true);
               
                
            }
        } catch (SQLException ex) {            
            Logger.getLogger(EBilling.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}