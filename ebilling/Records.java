/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ebilling;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;

/**
 *
 * @author CORECOMP
 */
public class Records extends JFrame implements ActionListener,ItemListener {

    JFrame JF;
    JTable JT;
    JButton print, back;
    JComboBox area;
    String x[] = {"ID", "Name","Address", "Phone", "Balance"};
    String y[][] = new String[400][5];
    int id, balance, count = 0, bala;
    int ids[] = new int[500];
    String b,name,Address,Phone;
    Conn c = new Conn();

    public Records() {

        try {

            String s1 = "select id from payments group by id ";
            ResultSet rs = c.Stmt.executeQuery(s1);

            while (rs.next()) {
                id = Integer.parseInt(rs.getString("id"));

                ids[count] = id;

                count++;

            }

        } catch (Exception es) {
            System.out.println("Exception is:");
            es.printStackTrace();

        }


        JF = new JFrame("Records");
        JF.setLayout(null);
        
        
        
        back = new JButton("Back");
        back.setBounds(200,650,100,30);
        back.addActionListener(this);
        back.setBackground(Color.BLACK);
        back.setForeground(Color.WHITE);
        JF.add(back);
        
        print = new JButton("Print");
        print.setBounds(350,650,100,30);
        print.addActionListener(this);
        print.setBackground(Color.BLACK);
        print.setForeground(Color.WHITE);
        JF.add(print);
        
        String[] areas={"AREA 1","ARTEA 2","AREA 3"};
        
        area = new JComboBox(areas); 
        
        area.addItemListener(this);
        area.setBounds(500,650,200,40);
        JF.add(area);
        
        JF.setLocation(300, 100);
        JF.setSize(800, 800);
        JF.setVisible(true);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==print){
           try{
            JT.print();
        }catch(Exception e1){}
       }
        else{
            JF.setVisible(false);
        }
    }
    
     public void itemStateChanged(ItemEvent e)
    {
        
        // if the state combobox is changed
        if (e.getSource() == area) {
            
            for(int a = 0 ; a<400;a++){
                for(int b = 0;b<5;b++){
                    y[a][b] = "";
                }
            }
            
            Conn c = new Conn();
            int counter = 0;
            String arena = (String) area.getSelectedItem();
            
            for(int i=0;i<ids.length;i++)
            {
                id = ids[i];
        
        String s = "select distinct payments.id,customers.name,customers.phone,Customers.Address,payments.balance from customers right join payments on payments.id = customers.id where payments.balance>0 and customers.address = '"+arena+"' and customers.id = "+id+" order by payments.tid desc limit 1;";
        
            try {
                ResultSet rs = c.Stmt.executeQuery(s);
                while(rs.next()){
                    
                    if(Integer.parseInt(rs.getString("Balance")) >0){
                    y[counter][0] = rs.getString("id");
                    y[counter][1] = rs.getString("Name");
                    y[counter][2] = rs.getString("Address");
                    y[counter][3] = rs.getString("Phone");
                    y[counter][4] = rs.getString("Balance");

                    counter = counter+1;
                    }
                
            }
            } catch (SQLException ex) {
                Logger.getLogger(Records.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            
                        }
            JF.setVisible(false);
        
            JT = new JTable(y, x);
            JT.getTableHeader().setFont(new Font("SansSerif", Font.ITALIC, 20));
            JT.setRowHeight(30);
            JT.setFont(JT.getFont().deriveFont(20.0f));
//            JT.setBounds(10, 0, 800, 600);
            JScrollPane sp = new JScrollPane(JT);
            sp.setLocation(0, 0);
            sp.setSize( 800, 600);
            JF.add(sp);
            
            counter = 0;
            
            JF.setVisible(true);
            
        }
    }
     
     

}
