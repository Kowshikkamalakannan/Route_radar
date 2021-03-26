package LetsRide;
        
import java.awt.Font;
import java.awt.event.*;
import java.sql.*;
import javax.swing.*;
import java.awt.*;

public class Login extends JFrame
{
    JLabel l1,l2,l3,l4,l5;
    JTextField t1;
    JPasswordField p1;
    JButton b1,b2;
    int flag=0;
    int admin_or_not;
    String userName;
    JPanel Header_panel;
    JLabel Header_label,im;
    JCheckBox pass_check;
    Login()
    {
        Container c=getContentPane();
        c.setBackground(new Color(30, 39, 46));
        c.setLayout(null);
        Header_panel = new JPanel();
        Header_panel.setBounds(0, 0, 600, 60);
        Header_panel.setLayout(null);
        Header_panel.setBackground(new Color(255, 168, 1));
        Header_label = new JLabel("Route Radar");
        Header_label.setBounds(20, 10, 300, 50);
        Header_label.setFont(new Font("Bauhaus 93", Font.BOLD, 40));
        Header_label.setForeground(new Color(255, 255, 255));
        Header_panel.add(Header_label);
        c.add(Header_panel);
        
        //welcome label
        l1 = new JLabel("Welcome to Route Radar");
        l1.setBounds(130,70,350,50);
        l1.setFont(new Font("Times New Roman",Font.BOLD,32));
        l1.setForeground(new Color(64,224,208));
        
        ImageIcon icon=new ImageIcon("D:/Cartoon.png");
        im= new JLabel(icon);
        im.setBounds(160,80,280,280);
        c.add(im);
        
        
        l2 = new JLabel("Login");
        l2.setBounds(280,330,200,40);
        l2.setFont(new Font("Times New Roman",Font.BOLD,25));
        l2.setForeground(Color.white); 
        
        l3 = new JLabel("User Name :");
        l3.setBounds(100,380,200,30);
        l3.setFont(new Font("Times New Roman",Font.BOLD,23));
        l3.setForeground(new Color(255, 250, 101)); 
        
        t1 = new JTextField();
        t1.setBounds(240,380,200,25);
        //t1.setBackground(new Color(255, 168, 1));
        //t1.setForeground(Color.white);
        t1.setFont(new Font("Times New Roman",Font.BOLD,20));
        
        l4 = new JLabel("Password  :");
        l4.setBounds(100,430,200,30);
        l4.setFont(new Font("Times New Roman",Font.BOLD,23));
        l4.setForeground(new Color(255, 250, 101));
        
        p1 = new JPasswordField();
        p1.setBounds(240,430,200,25);
        p1.setFont(new Font("Times New Roman",Font.BOLD,20));
        p1.setEchoChar('*');
        
        //show Password
        pass_check=new JCheckBox("show password");
        pass_check.setFont(new Font("Times New Roman",Font.PLAIN,16));
        pass_check.setBounds(240,460,200,25);
        pass_check.setBackground(new Color(30, 39, 46));
        pass_check.setForeground(Color.WHITE);
        c.add(pass_check);
        
        b1 = new JButton("Login");
        b1.setBounds(360,500,120,25);
        b1.setBorderPainted(false);
        b1.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        b1.setFont(new Font("Times New Roman",Font.BOLD,20));
        b1.setBackground(new Color(61, 193, 211));
	b1.setForeground(Color.white);	

        pass_check.addActionListener((ActionEvent ae)->
        {
            if(pass_check.isSelected()==true)
            {
                p1.setEchoChar('\u0000');
            }
            else if(pass_check.isSelected()==false)
            {
                p1.setFont(new Font("Times New Roman",Font.BOLD,20));
                p1.setEchoChar('*');
            }
        
        });
        
        b1.addActionListener((ActionEvent ae) ->
                                {
                                    if(t1.getText().isEmpty())
                                    {
                                        JOptionPane.showMessageDialog(null,"User Name cannot be Empty");
                                    }
                                    else if(p1.getText().isEmpty())
                                    {
                                        JOptionPane.showMessageDialog(null,"Password Field cannot be Empty");
                                    }
                                    else
                                    {
                                        try
                                        {
                                            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/bootcamp_project?zeroDateTimeBehavior=convertToNull", "root", "");

                                            Statement  st = con.createStatement();
                                        
                                            String query = "select * from  register";
                                        
                                            ResultSet rs = st.executeQuery(query);
                                        
                                            while(rs.next())
                                            {
                                                if(t1.getText().equals(rs.getString(1)))
                                                {
                                                    if(String.valueOf(p1.getPassword()).equals(rs.getString("Password")))
                                                    {
                                                        if(rs.getInt(7)==1)
                                                        {
                                                            userName = rs.getString(1);
                                                            admin_or_not = rs.getInt(6);
                                                            JOptionPane.showMessageDialog(null,"Logged in Successfully");
                                                            flag=1;
                                                            this.dispose();
                                                            Homepage obj=new Homepage(userName,admin_or_not);
                                                            Image icon1 = Toolkit.getDefaultToolkit().getImage("D:/busicon.png");    
                                                            obj.setIconImage(icon1);
                                                            obj.setLayout(null);
                                                            obj.setBounds(400, 10, 600, 700);
                                                            obj.setTitle("HomePage");
                                                            obj.setResizable(false);
                                                            obj.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                                                            obj.setLocationRelativeTo(null);
                                                            obj.setVisible(true);
                                                            
                                                            break;
                                                        }
                                                    }
                                                }
                                            }
                            
                                        if(flag==0)
                                        {
                                            JOptionPane.showMessageDialog(null,"Invalid User Credentials");
                                        }
                                    }
                                     catch(SQLException e)
                                     {
                                            System.out.println("---------->  "+e);
                                     } 
                                  }
                                });
        
        
        
        b2 = new JButton("New User");
        b2.setBounds(220,500,120,25);
        b2.setFont(new Font("Times New Roman",Font.BOLD,20));
        b2.setForeground(Color.white); 
        b2.setBackground(new Color(194, 54, 22));
        b2.setBorderPainted(false);
        b2.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        b2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                RegisterForm f=new RegisterForm();
                Image icon = Toolkit.getDefaultToolkit().getImage("D:/busicon.png");    
                f.setIconImage(icon);
                f.setLayout(null);
                f.setBounds(400, 10, 600, 700);
                f.setTitle("Register Form");
                f.setResizable(false);
                f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                f.setLocationRelativeTo(null);
                f.setVisible(true);
                Login.this.dispose();
            }
        });
        
        //c.add(l1);
        c.add(l2);
        c.add(l3);
        c.add(t1);
        c.add(l4);
        c.add(p1);
        c.add(b1);
        c.add(b2);
        
        //Toolkit.getDefaultToolkit().getScreenSize()
       
    }
    public static void main(String[] args) {
        Login obj=new Login();
        Image icon = Toolkit.getDefaultToolkit().getImage("D:/busicon.png");    
        obj.setIconImage(icon);
        obj.setBounds(400, 10, 600, 700);
        obj.setLayout(null);       
        obj.setTitle("Login");
        obj.setResizable(false); 
        obj.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        obj.setLocationRelativeTo(null);
        obj.setVisible(true);
    }
}
