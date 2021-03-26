/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package LetsRide;

import java.sql.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
/**
 *
 * @author Sekar&Kowshik
 */
public class Wallet extends JFrame implements ActionListener{
    
    String current_user,Wallet_amount;
    int admin_or_not;
    JLabel Wallet_bal_label,Header_label,img,img2,Wallet_bal,Add_bal_label,Amount_label;
    JPanel Header_panel,bal_panel;
    JTextField Amount;
    JButton Back_button,Add_button;
    int update_amount,prev_bal;
    Container C= getContentPane();
    Wallet(String current_user,int admin_or_not)
    {
        this.current_user=current_user;
        this.admin_or_not=admin_or_not;
        C.setBackground(new Color(30, 39, 46));
        C.setLayout(null);
        Header_panel = new JPanel();
        Header_panel.setBounds(0, 0, 600, 60);
        Header_panel.setLayout(null);
        Header_panel.setBackground(new Color(255, 168, 1));
        Header_label = new JLabel("Route Radar");
        Header_label.setBounds(20, 10, 300, 50);
        Header_label.setFont(new Font("Bauhaus 93", Font.BOLD, 40));
        Header_label.setForeground(new Color(255, 255, 255));
        Header_panel.add(Header_label);
        C.add(Header_panel);
        
        Back_button=new JButton("Back");
        Back_button.setBounds(480,23,90,24);
        Back_button.setFont(new Font("Times New Roman",1,17));
        Back_button.setBackground(new Color(194, 54, 22));
        Back_button.setForeground(new Color(255,255,255));
        Back_button.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        Back_button.setBorderPainted(false);
        Back_button.addActionListener(this);
        Header_panel.add(Back_button);
        try{ 
                Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/bootcamp_project?zeroDateTimeBehavior=convertToNull", "root", "");
                Statement  st = con.createStatement();
                String walamount="select * from wallet where Username='"+current_user+"'";
                ResultSet rs= st.executeQuery(walamount);
                while(rs.next()){
                    Wallet_amount=rs.getString(2);
                }
           }
        catch(Exception ex)
            {
                 System.out.println("---------->  "+ex);
            }

        
        Wallet_bal_label=new JLabel();
        Wallet_bal_label.setText("My Wallet Balance:");
        Wallet_bal_label.setBounds(140,140,200,50);
        Wallet_bal_label.setFont(new Font("Times New Roman",Font.BOLD,22));
        Wallet_bal_label.setForeground(new Color(245,255,250));
        C.add(Wallet_bal_label);
        
        Wallet_bal=new JLabel();
        Wallet_bal.setText(Wallet_amount);
        Wallet_bal.setBounds(350,140,200,50);
        Wallet_bal.setFont(new Font("Times New Roman",Font.BOLD,22));
        Wallet_bal.setForeground(new Color(245,255,250));
        C.add(Wallet_bal);
        
        
        bal_panel=new JPanel();
        bal_panel.setBounds(50,240,500,200);
        bal_panel.setLayout(null);
        bal_panel.setBackground(new Color(30, 144, 255));
        C.add(bal_panel);
        
        Add_bal_label=new JLabel();
        Add_bal_label.setText("Add Wallet Balance:");
        Add_bal_label.setBounds(10,10,290,50);
        Add_bal_label.setFont(new Font("Showcard Gothic",Font.BOLD,25));
        Add_bal_label.setForeground(new Color(245,255,250));
        bal_panel.add(Add_bal_label);
        
        Amount_label=new JLabel();
        Amount_label.setText("Enter Amount:");
        Amount_label.setBounds(40,60,200,50);
        Amount_label.setFont(new Font("Times New Roman",Font.BOLD,22));
        Amount_label.setForeground(new Color(245,255,250));
        bal_panel.add(Amount_label);
        
        
        Amount = new JTextField();
        Amount.setBounds(190,75,100,25);
        Amount.setFont(new Font("Times New Roman",Font.BOLD,20));
        bal_panel.add(Amount);
        
        
        Add_button = new JButton("Add Amount");
        Add_button.setBackground(new Color(194, 54, 22));
        Add_button.setFont(new Font("Tahoma",Font.BOLD,16));
        Add_button.setForeground(new Color(255,255,255));
        Add_button.setBounds(170,120,140,25);
        Add_button.setBorderPainted(false);
        Add_button.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        bal_panel.add(Add_button);
        Add_button.addActionListener(this);
        
        ImageIcon pic=new ImageIcon("D:/Cartoon.png");
        img=new JLabel(pic);
        img.setBounds(310,490,300,200);
        C.add(img);
        ImageIcon pic2=new ImageIcon("D:/road.png");
        img2=new JLabel(pic2);
        img2.setBounds(0,470,600,200);
        C.add(img2);
   
    }
    public void actionPerformed(ActionEvent e) {

                            if(e.getSource()==Back_button)
                            {
                                this.dispose();
                                Homepage fr=new Homepage(current_user,admin_or_not);
                                Image icon = Toolkit.getDefaultToolkit().getImage("D:/busicon.png");    
                                fr.setIconImage(icon);
                                fr.setBounds(400,10,600, 700);
                                fr.setTitle("Route Radar");
                                fr.setResizable(false);
                                fr.setDefaultCloseOperation(fr.EXIT_ON_CLOSE);
                                fr.setLayout(null);
                                fr.setVisible(true);   
                            }
                            if(e.getSource()==Add_button)
                            {
                                String amount_field=Amount.getText();
                                if(amount_field!="")
                                {
                                int add_bal=Integer.parseInt(amount_field);
                                int prev_bal=Integer.parseInt(Wallet_amount);
                                int Update_bal=add_bal+prev_bal;
                                try{ 
                                        Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/bootcamp_project?zeroDateTimeBehavior=convertToNull", "root", "" );
                                        Statement st=con.createStatement();
                                        String qu="UPDATE Wallet SET Wallet_Amount='"+Update_bal+"' WHERE Username='"+current_user+"' ";
                                        st.executeUpdate(qu);
                                        JOptionPane.showMessageDialog(null,"Amount added sucessfully");
                                        Amount.setText("");
                                        Wallet_bal.setText(String.valueOf(Update_bal));
                                    }
                                catch(Exception ex)
                                    {
                                         System.out.println("---------->  "+ex);
                                    }
                                }
                                else{
                                        JOptionPane.showMessageDialog(null,"Enter Amount");
                                }
                                
                            }   
        }

}
