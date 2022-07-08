/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ebilling;

import javax.swing.*;
import java.awt.*;
import java.sql.*;
import java.awt.event.*;

public class UpdateInformation extends JFrame implements ActionListener{
    JTextField name,id,add,city,phone;
    JLabel l11, l12;
    JButton b1, b2;
    int meter;
    ImageIcon logo = new ImageIcon(ClassLoader.getSystemResource("icon/muktai logo.jpg"));
    UpdateInformation(int meter){
        this.meter = meter;
        
        setBounds(500, 220, 1050, 450);
        setLayout(null);
        getContentPane().setBackground(Color.WHITE);
        setIconImage(logo.getImage());
        JLabel title = new JLabel("UPDATE CUSTOMER INFORMATION");
        title.setBounds(110, 0, 400, 30);
        title.setFont(new Font("Tahoma", Font.PLAIN, 20));
        add(title);
        
        
        
        JLabel l1 = new JLabel("Name");
        l1.setBounds(30, 70, 100, 20);
        add(l1);
        
        name = new JTextField();
       
       name.setBounds(230, 70, 200, 20);
        add(name);
        
        JLabel l2 = new JLabel("Cust No");
        l2.setBounds(30, 110, 100, 20);
        add(l2);
        
          id = new JTextField();
        id.setBounds(230, 110, 200, 20);
        add(id);
        
        JLabel l3 = new JLabel("Address");
        l3.setBounds(30, 150, 100, 20);
        add(l3);
        
        add = new JTextField();
        add.setBounds(230, 150, 200, 20);
        add(add);
        
        JLabel l4 = new JLabel("City");
        l4.setBounds(30, 190, 100, 20);
        add(l4);
        
        city = new JTextField();
        city.setBounds(230, 190, 200, 20);
        add(city);
        
        JLabel l5 = new JLabel("Phone");
        l5.setBounds(30, 230, 100, 20);
        add(l5);
        
        phone = new JTextField();
        phone.setBounds(230, 230, 200, 20);
        add(phone);
        
        
      
        
        b1 = new JButton("Update");
        b1.setBackground(Color.BLACK);
        b1.setForeground(Color.WHITE);
        b1.setBounds(70, 360, 100, 25);
        b1.addActionListener(this);
        add(b1);
        
        b2 = new JButton("Back");
        b2.setBackground(Color.BLACK);
        b2.setForeground(Color.WHITE);
        b2.setBounds(230, 360, 100, 25);
        b2.addActionListener(this);
        add(b2);
        
        try{
            Conn c = new Conn();
            ResultSet rs = c.Stmt.executeQuery("select * from customers where id = "+meter);
            while(rs.next()){
                name.setText(rs.getString(2));
                
                add.setText(rs.getString(3));
                city.setText(rs.getString(5));
                phone.setText(rs.getString(4));
               
                id.setText(rs.getString(1));
                
            }
        }catch(Exception e){}
        
        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icon/update.jpg"));
        Image i2  = i1.getImage().getScaledInstance(400, 300, Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel l8  = new JLabel(i3);
        l8.setBounds(550, 50, 400, 300);
        add(l8);
    }
    
    public void actionPerformed(ActionEvent ae){
        if(ae.getSource() == b1){
            String s1 =name.getText();
            
            String s3 = add.getText();
            String s4 = city.getText();
            String s5 = phone.getText();
           
            String s7 = id.getText();
            
            try{
                Conn c = new Conn();
                c.Stmt.executeUpdate("update customers set id = "+s7+", address = '"+s3+"', city = '"+s4+"', phone = '"+s5+"', name = '"+s1+"' where id = '"+meter+"'");
                JOptionPane.showMessageDialog(null, "Details Updated Successfully");
                this.setVisible(false);
                
            }catch(Exception e){}
            
        }else if(ae.getSource() == b2){
            this.setVisible(false);
        }
    }
    
    public static void main(String[] args){
        new UpdateInformation(0).setVisible(true);
        
    }
}
