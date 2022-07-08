/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ebilling;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.sql.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;


/**
 *
 * @author CORECOMP
 */
public class Payments extends JFrame implements ActionListener {
    
    DefaultTableModel m = new DefaultTableModel();
    
   
    
    ImageIcon logo = new ImageIcon(ClassLoader.getSystemResource("icon/muktai logo.jpg"));
    JFrame j;
    JScrollPane pane;
    JTextField amt,date,bal;
    JLabel l1,l2;
    JButton submit,clear,back,print,undo;
    JTable t1;
    int id,j1=0,i=0, bala,tid;
    String x[] = {"Date","Amount","Paid","Balance"};
    String y[][] = new String[40][4];
    String b;
    public Payments(int id){
        this.id = id;
        j = new JFrame("Payments");
        j.setLayout(null);
        j.getContentPane().setBackground(Color.WHITE);
        j.setLocation(300, 100);
        j.setIconImage(logo.getImage());
        
        Conn c1  = new Conn();
        
         t1 = new JTable(m);
              m.addColumn("Date");
        m.addColumn("Date");
         m.addColumn("Date");
        m.addColumn("Date");
        
        try{
            
            String s1 = "select * from payments where id='"+id+"' order by dt ASC";
            ResultSet rs  = c1.Stmt.executeQuery(s1);
            
            while(rs.next()){
                
                  m.insertRow(0, new Object[] { rs.getString("dt"),rs.getString("amount"),rs.getString("Paid"),rs.getString("balance")});
                y[i][j1++]=rs.getString("dt");
                y[i][j1++]=rs.getString("amount");
                y[i][j1++]=rs.getString("Paid");
                y[i][j1++]=rs.getString("balance");
                 b = rs.getString("balance");
                bala = Integer.parseInt(b);
                if(b.equals("")){
                    bala=0;
                }
               
                i++;
                j1=0;
                
            }
            
            
             try{
            
             s1 = "select * from payments where id='"+id+"'";
          ResultSet  rsa  = c1.Stmt.executeQuery(s1);
            
            while(rsa.next()){
            bala = Integer.parseInt(rsa.getString("balance"));
            b = rsa.getString("balance");
            
            }
            y[++i][0] = "Total";
            y[i][1] = b ;
            
           

             }
           
             catch(Exception exe){
                         exe.printStackTrace();
             }
            
             
            
            
                 }catch(Exception e){
            e.printStackTrace();
        }
        
        
        try{
            String a = "select tid from payments order by tid desc limit 1";
            ResultSet res = c1.Stmt.executeQuery(a);
            
            while(res.next()){
                tid = Integer.parseInt(res.getString("tid"));
            }
        }
        catch(Exception exe){
            
        }
        
        
        
        l1 = new JLabel("Rcg amt");
        l1.setBounds(20,20,100,30);
        l1.setFont(l1.getFont().deriveFont(18.0f));
        j.add(l1);
        
        amt = new JTextField();
        amt.setBounds(20,70,100,30);
        j.add(amt);
        
        l2 = new JLabel("Paid Amt");
        l2.setBounds(20,110,100,30);
        l2.setFont(l1.getFont().deriveFont(18.0f));
        j.add(l2);
        
        bal = new JTextField();
        bal.setBounds(20,150,100,30);
        j.add(bal);
        
        l2 = new JLabel("Date");
        l2.setBounds(20,190,100,30);
        l2.setFont(l1.getFont().deriveFont(18.0f));
        j.add(l2);
        
        date = new JTextField();
        date.setBounds(20,230,100,30);
        j.add(date);
        
        pane = new JScrollPane(t1);
        pane.setBounds(130,0,650,750);
        j.add(pane);
        
        submit = new JButton("Submit");
        submit.setBounds(20,280,100,30);
        submit.addActionListener(this);
        submit.setBackground(Color.BLACK);
        submit.setForeground(Color.WHITE);
        j.add(submit);
        
        clear = new JButton("Clear 5");
        clear.setBounds(20,330,100,30);
        clear.addActionListener(this);
        clear.setBackground(Color.BLACK);
        clear.setForeground(Color.WHITE);
        j.add(clear);
        
        undo = new JButton("Undo");
        undo.setBounds(20,380,100,30);
        undo.addActionListener(this);
        undo.setBackground(Color.BLACK);
        undo.setForeground(Color.WHITE);
        j.add(undo);
            
        print = new JButton("Print");
        print.setBounds(20,430,100,30);
        print.addActionListener(this);
        print.setBackground(Color.BLACK);
        print.setForeground(Color.WHITE);
        j.add(print);
        
        back = new JButton("Back");
        back.setBounds(20,480,100,30);
        back.addActionListener(this);
        back.setBackground(Color.BLACK);
        back.setForeground(Color.WHITE);
        j.add(back);
        
        j.setSize(800,800);
        j.setVisible(true);
    }
    
    public static void main(String[] args){
        Payments p= new Payments(8788);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
       if(e.getSource()==submit){
                   tid = tid+1;

           String amount = amt.getText();
           String paid = bal.getText();
           
           int balance = (bala + Integer.parseInt(amount)) - Integer.parseInt(paid);
           
           
           if(amount.equals("") || paid.equals("")){
               JOptionPane.showMessageDialog(this, "Required!!!");
           }
          if(bala>0){
               
              if(Integer.parseInt(paid)<=0){
                  paid = "0";
              }
          }
          if(amount.equals("")){
              amount="0";
          }
           String id1 = Integer.toString(id);
           String date1 = date.getText();
            try{
            Conn c  = new Conn();
//             String sql = "insert into payments value("+id+","+amount+","+balance+",sysdate());";
//            c.insert = c.con.prepareStatement(sql);
//            c.insert.setInt(1, id);
//            c.insert.setInt(2, amount);
//            c.insert.setInt(3, balance);
//            System.out.println(sql);
String sql;
            if(date1.equals("")){
                sql= "insert into payments value( "+id+","+amount+","+paid+","+balance+","+"sysdate(),"+tid+");";
                  m.insertRow(0, new Object[] { java.time.LocalDate.now(),amount,paid,balance});
                
            }
            else{
          sql= "insert into payments value( "+id+","+amount+","+paid+","+balance+",'"+date1+"',"+tid+");";
          
            }
            c.Stmt.executeUpdate(sql);
            
           
            JOptionPane.showMessageDialog(this, "Submitted SuccessFully!!!");
            

           
            }
            catch(Exception EXE){
                System.out.print(EXE);
            }
            
//            new Payments(id).setVisible(true);
//                          j.setVisible(false);
       }
       else if(e.getSource()==clear){
           try{
            Conn c1  = new Conn();
            String sql = "delete  from payments where id ='"+id+"' limit 5";
            c1.Stmt.executeUpdate(sql);
            JOptionPane.showMessageDialog(this, "Deleted SuccessFully!!!");
            }
            catch(Exception EXE){
                
            }
       }
       
       else if(e.getSource()==print){
           try{
            t1.print();
        }catch(Exception e1){}
       }
       
       else if(e.getSource()==undo){
           try{
            Conn c1  = new Conn();
            String sql = "delete  from payments where id ='"+id+"'order by dt desc limit 1";
            c1.Stmt.executeUpdate(sql);
            JOptionPane.showMessageDialog(this, "Deleted SuccessFully!!!");
            }
            catch(Exception EXE){
                System.out.println(EXE);
            }
       }
       else {
           j.setVisible(false);
           
       }
       
    }
    
}
