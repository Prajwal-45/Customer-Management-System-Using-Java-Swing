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

public class ViewInformation extends JFrame implements ActionListener{
    JButton b1,payments,update;
    int meter=0;
    
    ImageIcon logo = new ImageIcon(ClassLoader.getSystemResource("icon/muktai logo.jpg"));
    ViewInformation(int meter){
        this.meter=meter;
        setBounds(600,250, 850, 650);
        getContentPane().setBackground(Color.WHITE);
        setLayout(null);
        setIconImage(logo.getImage());
        
        JLabel title = new JLabel("VIEW CUSTOMER INFORMATION");
        title.setBounds(250, 0, 500, 40);
        title.setFont(new Font("Tahoma", Font.PLAIN, 20));
        add(title);
        
        JLabel l1 = new JLabel("Name");
        l1.setBounds(70, 80, 200, 20);
        add(l1);
        
        JLabel l11 = new JLabel();
        l11.setBounds(250, 80, 100, 20);
        add(l11);
        
        JLabel l2 = new JLabel("Customer No");
        l2.setBounds(70, 140, 100, 20);
        add(l2);
        
        JLabel l12 = new JLabel();
        l12.setBounds(250, 140, 100, 20);
        add(l12);
        
        JLabel l3 = new JLabel("Address");
        l3.setBounds(70, 200, 100, 20);
        add(l3);
        
        JLabel l13 = new JLabel();
        l13.setBounds(250, 200, 100, 20);
        add(l13);
        
        JLabel l4 = new JLabel("Phone");
        l4.setBounds(70, 260, 100, 20);
        add(l4);
        
        JLabel l14 = new JLabel();
        l14.setBounds(250, 260, 100, 20);
        add(l14);
        
        
        
        
        
        try{
            Conn c = new Conn();
            ResultSet rs = c.Stmt.executeQuery("select * from customers where id = "+meter+"");
            while(rs.next()){
                l11.setText(rs.getString(2));
                l12.setText(rs.getString(1));
                l13.setText(rs.getString(3));
                l14.setText(rs.getString(4));
                
               
                
            }
        }catch(Exception e){}
        
        b1 = new JButton("Back");
        b1.setBackground(Color.BLACK);
        b1.setForeground(Color.WHITE);
        b1.setBounds(250, 340, 100, 25);
        b1.addActionListener(this);
        add(b1);
        
         payments = new JButton("payments");
        payments.setBackground(Color.BLACK);
        payments.setForeground(Color.WHITE);
        payments.setBounds(400, 340, 100, 25);
        payments.addActionListener(this);
        add(payments);
        
         update = new JButton("update");
        update.setBackground(Color.BLACK);
        update.setForeground(Color.WHITE);
        update.setBounds(550, 340, 100, 25);
        update.addActionListener(this);
        add(update);
        
        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icon/viewcustomer.jpg"));
        Image i2 = i1.getImage().getScaledInstance(600, 300, Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel l8  = new JLabel(i3);
        l8.setBounds(20, 350, 600, 300);
        add(l8);
        
        setVisible(true);
    }
    
    public void actionPerformed(ActionEvent ae){
        if(ae.getSource()==b1){
        this.setVisible(false);}
        else  if(ae.getSource()==payments){
            new Payments(meter).setVisible(true);
        }
        else if(ae.getSource()==update){
        this.setVisible(false);
        new UpdateInformation(meter).setVisible(true);
        }
    }
    
   
}
