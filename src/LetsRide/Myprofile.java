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
import java.util.regex.*;
import java.sql.*;
import javax.swing.*;
/**
 *
 * @author Sekar&Kowshik
 */
public class Myprofile extends JFrame implements ActionListener{
    
    String current_user;
    int admin_or_not;
    JLabel present_name,present_email,present_phone,Header_label,img,img2;
    JTextField name,email,phone;
    JPanel Header_panel;
    JButton Back_button,update_button;
    Container C= getContentPane();
    Myprofile(String current_user,int admin_or_not)
    {
        C.setBackground(new Color(30, 39, 46));
        C.setLayout(null);
        this.current_user=current_user;
        this.admin_or_not=admin_or_not;
 
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
        
        present_name=new JLabel();
        present_name.setText("Username :");
        present_name.setBounds(140,140,200,50);
        C.add(present_name);
        present_name.setFont(new Font("Times New Roman",Font.BOLD,22));
        present_name.setForeground(new Color(245,255,250));
        
        present_email=new JLabel();
        present_email.setText("Email id :");
        present_email.setBounds(155,210,200,50);
        present_email.setForeground(new Color(245,255,250));
        present_email.setFont(new Font("Times New Roman",Font.BOLD,22));
        C.add(present_email);

        present_phone=new JLabel();
        present_phone.setText("Phone Number :");
        present_phone.setBounds(90,280,200,50);
        present_phone.setForeground(new Color(245,255,250));
        present_phone.setFont(new Font("Times New Roman",Font.BOLD,22));
        C.add(present_phone);
        
        name=new JTextField();
        name.setBounds(280,150,180,30);
        name.setFont(new Font("Times New Roman",Font.BOLD,17));
        name.setForeground(new Color(0,0,0));
        name.setEditable(false);
        C.add(name);
        
        email=new JTextField();
        email.setBounds(280,220,180,30);
        email.setFont(new Font("Times New Roman",Font.BOLD,17));
        email.setForeground(new Color(0,0,0));
        email.setEditable(false);
        C.add(email);
           
        phone=new JTextField();
        phone.setBounds(280,290,180,30);
        phone.setFont(new Font("Times New Roman",Font.BOLD,17));
        phone.setForeground(new Color(0,0,0));
        phone.setEditable(false);
        C.add(phone);
        
        Back_button=new JButton("Back");
        Back_button.setBounds(480,23,90,24);
        Back_button.setFont(new Font("Times New Roman",1,17));
        Back_button.setBackground(new Color(194, 54, 22));
        Back_button.setForeground(new Color(255,255,255));
        Back_button.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        Back_button.setBorderPainted(false);
        Back_button.addActionListener(this);
        Header_panel.add(Back_button);
        
        update_button=new JButton("Update Account");
        update_button.setBounds(295,360,150,25);
        update_button.setBackground(new Color(61, 193, 211));
        update_button.setForeground(new Color(255,255,255));
        update_button.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        update_button.setBorderPainted(false);
        update_button.addActionListener(this);
        C.add(update_button);
        
        ImageIcon pic=new ImageIcon("D:/Cartoon.png");
        img=new JLabel(pic);
        img.setBounds(310,490,300,200);
        C.add(img);
        ImageIcon pic2=new ImageIcon("D:/road.png");
        img2=new JLabel(pic2);
        img2.setBounds(0,470,600,200);
        C.add(img2);
        
        try {
                Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/bootcamp_project?zeroDateTimeBehavior=convertToNull", "root", "");
                Statement st=con.createStatement();
                String query="select * from register where User_name='"+current_user+"'";
                ResultSet r=st.executeQuery(query);
                if(r.next()){
                        name.setText(r.getString(1));
                        email.setText(r.getString(2));
                        phone.setText(r.getString(4));
       
                    }
                     con.close();
            }
            catch(Exception exx)
            {
                    System.out.println("-->"+exx);
            }
        
        
         
    }
    public void actionPerformed(ActionEvent e) {
                            if(e.getSource()==update_button)
                            {
                                this.dispose();
                                update frame = new update(current_user,admin_or_not);
                                Image icon = Toolkit.getDefaultToolkit().getImage("D:/busicon.png");    
                                frame.setIconImage(icon);
                                frame.setTitle("Update Form");
                                frame.setBounds(400, 10, 600, 700);
                                frame.getContentPane().setBackground(new Color(30, 39, 46));
                                frame.setLayout(null);
                                frame.setVisible(true);
                                frame.setResizable(false);
                                frame.setVisible(true);	
                                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);                                

                            }
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
 
        } 

}
