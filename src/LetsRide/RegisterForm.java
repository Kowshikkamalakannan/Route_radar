package LetsRide;


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.image.ImageObserver;
import java.awt.image.ImageProducer;
import java.sql.*;
import java.util.regex.*;
import javax.swing.*;
public class RegisterForm extends JFrame
{
    JLabel Header_label,frameTitle,nameLabel,emailIdLabel,genderLabel,phoneLabel,passwordLabel,confirmLabel;
    JTextField nameText,emailIdText,phoneText;
    JPasswordField passText,confirmText;
    JButton register,back;
    JRadioButton Male,Female;
    ButtonGroup selectOne;
    int flag=1,check=1;
    String name,emailId,gender,phoneNumber,password;
    Container C=getContentPane();
    JPanel Header_panel;
    JLabel img,img2;
    RegisterForm()
    {
        C.setBackground(new Color(30, 39, 46));
        C.setLayout(null);
        //FooterImage
        ImageIcon pic=new ImageIcon("D:/Cartoon.png");
        img=new JLabel(pic);
        img.setBounds(310,490,300,200);
        C.add(img);
        ImageIcon pic2=new ImageIcon("D:/road.png");
        img2=new JLabel(pic2);
        img2.setBounds(0,470,600,200);
        C.add(img2);
        
        
        //Header Panel
        Header_panel = new JPanel();
        Header_panel.setBounds(0, 0, 600, 60);
        Header_panel.setLayout(null);
        Header_panel.setBackground(new Color(255, 168, 1));
        Header_label = new JLabel("Route Radar");
        Header_label.setBounds(20, 10, 300, 50);
        Header_label.setFont(new Font("Bauhaus 93", Font.BOLD, 40));
        Header_label.setForeground(new Color(255, 255, 255));
        
        frameTitle = new JLabel("User Registration");
        frameTitle.setBounds(180,70,280,40);
        frameTitle.setFont(new Font("Times New Roman",Font.BOLD,32));
        frameTitle.setForeground(new Color(64,224,208));
        
        
        nameLabel = new JLabel("Username :");
        nameLabel.setBounds(150,160,130,30);
        nameLabel.setFont(new Font("Times New Roman",Font.BOLD,22));
        nameLabel.setForeground(new Color(255, 250, 101));
        nameText = new JTextField();
        nameText.setBounds(280,165,150,25);
        
        emailIdLabel = new JLabel("E-mail id :");
        emailIdLabel.setFont(new Font("Times New Roman",Font.BOLD,22));
        emailIdLabel.setBounds(158,200,100,30);
        emailIdLabel.setForeground(new Color(255, 250, 101));
        emailIdText = new JTextField();
        emailIdText.setBounds(280,205,150,25);
        
        genderLabel = new JLabel("Gender :");
        genderLabel.setFont(new Font("Times New Roman",Font.BOLD,22));
        genderLabel.setForeground(new Color(255, 250, 101));
        genderLabel.setBounds(173,240,120,30);
        
        Male = new JRadioButton("Male");
        Male.setBounds(280,240,70,30);
        Male.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        Female = new JRadioButton("Female");
        Male.setBackground(new Color(30, 39, 46));
        Male.setForeground(new Color(255,255,255));
        Female.setBackground(new Color(30, 39, 46));
        Female.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        Female.setForeground(new Color(255,255,255));
        Female.setBounds(380,240,70,30);
        
        selectOne = new ButtonGroup();
        selectOne.add(Male);
        selectOne.add(Female);
        
        phoneLabel = new JLabel("Phone :");
        phoneLabel.setFont(new Font("Times New Roman",Font.BOLD,22));
        phoneLabel.setForeground(new Color(255, 250, 101));
        phoneLabel.setBounds(183,280,130,30);
        
        phoneText = new JTextField();
        phoneText.setBounds(280,285,150,25);
        
        passwordLabel = new JLabel("Password :");
        passwordLabel.setFont(new Font("Times New Roman",Font.BOLD,22));
        passwordLabel.setForeground(new Color(255, 250, 101));
        passwordLabel.setBounds(150,320,130,30);
        
        passText = new JPasswordField();
        passText.setBounds(280,325,150,25);
        passText.setToolTipText("<html>Password should contain atleast<br>one Uppercase(A-Z)<br>one lowercase(a-z)<br>one special character( !\"#$%&'()*+,-./:;<=>?@[\\]^_`{|}~)<br>one number(0-9)</html>");
        
        confirmLabel = new JLabel("Confirm Password :");
        confirmLabel.setFont(new Font("Times New Roman",Font.BOLD,22));
        confirmLabel.setForeground(new Color(255, 250, 101));
        confirmLabel.setBounds(65,365,200,30);
        
        confirmText = new JPasswordField();
        confirmText.setBounds(280,370,150,25);
        
        register = new JButton("Register");
        register.setBackground(new Color(61, 193, 211));
        register.setFont(new Font("Tahoma",Font.BOLD,16));
        register.setForeground(new Color(255,255,255));
        register.setBounds(290,430,130,25);
        register.setBorderPainted(false);
        register.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        
        back = new JButton("Back");
        back.setBounds(500,30,80,20);
        back.setBackground(new Color(194, 54, 22));
        back.setForeground(new Color(255,255,255));
        back.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        back.setBorderPainted(false);
        register.addActionListener((ActionEvent ae) -> 
                                {
                                    flag=1;
                                    if(nameText.getText().isEmpty())
                                    {
                                        JOptionPane.showMessageDialog(null,"Name Field cannot be Empty");
                                        flag=0;
                                    }
                                    else if(!Pattern.matches("[^ 0-9`!@#$%^&*()=?/;:| ]{0,50}",nameText.getText()))
                                    {
                                        JOptionPane.showMessageDialog(null,"Registration Unsuccessful - Invalid Name Format ");
                                        flag=0;
                                    }
                                    else if(emailIdText.getText().isEmpty())
                                    {
                                        JOptionPane.showMessageDialog(null,"Email ID is required");
                                        flag=0;
                                    }
                                    else if(!Pattern.matches("[^ `!@#$%^&*()=?/;:| ]{0,64}@[^ `!@#$%^&*()=?/;:| ]{0,253}.[^ `!@#$%^&*()=?/;:| ]{2,3}",emailIdText.getText()))
                                    {
                                        JOptionPane.showMessageDialog(null,"Registration Unsuccessful - Invalid E-mail id");
                                        flag=0;
                                    }
                                    else if(!(Male.isSelected() || Female.isSelected()))
                                    {
                                        JOptionPane.showMessageDialog(null,"Please Select Gender");
                                        flag=0;
                                    }
                                    else if(phoneText.getText().isEmpty())
                                    {
                                        JOptionPane.showMessageDialog(null,"Phone number is required");
                                        flag=0;
                                    }
                                    else if(!Pattern.matches("[0-9]{10}",phoneText.getText()))
                                    {
                                        JOptionPane.showMessageDialog(null,"Registration Unsuccessful - Invalid Phone Number");
                                        flag=0;
                                    }
                                    else if(passText.getText().isEmpty())
                                    {
                                        JOptionPane.showMessageDialog(null,"Password Field cannot be Empty");
                                        flag=0;
                                    }
                                    else if(confirmText.getText().isEmpty())
                                    {
                                        JOptionPane.showMessageDialog(null,"Confirm password Field cannot be Empty");
                                        flag=0;
                                    }
                                    else if(!String.valueOf(passText.getPassword()).equals(String.valueOf(confirmText.getPassword())))
                                    {
                                       JOptionPane.showMessageDialog(null,"Registration Unsuccessful - Passwords does not Match");
                                        flag=0;
                                    }
                                    else if(!Pattern.matches("(?=^.{8,}$)(?=.*\\d)(?=.*[!@#$%^&*]+)(?![.\\n])(?=.*[A-Z])(?=.*[a-z]).*$",String.valueOf(passText.getPassword())))
                                    {
                                        JOptionPane.showMessageDialog(null,"Please Set a Strong Password !!");
                                        flag=0;
                                    }
                                    
                                    if(flag==1)
                                    {
                                        name = nameText.getText();
                                        emailId = emailIdText.getText();
                                        if(Male.isSelected())
                                        {
                                            gender = "Male";
                                        }
                                        else
                                        {
                                            gender = "Female";
                                        }
                                        phoneNumber = phoneText.getText();
                                        password = String.valueOf(passText.getPassword());
                                        
                                        try
                                        {

                                            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/bootcamp_project?zeroDateTimeBehavior=convertToNull", "root", "" );

                                            Statement  st = con.createStatement();
                                            
                                            String query = "select * from  register";
                                        
                                            ResultSet rs = st.executeQuery(query);
                                            
                                            while(rs.next())
                                            {
                                                if(name.equals(rs.getString("User_Name")))
                                                {
                                                    check=0;
                                                }
                                            }
                                        
                                            if(check==1)
                                            {
                                                query = "insert into register values('"+name+"','"+emailId+"','"+gender+"','"+phoneNumber+"','"+password+"',0,1);";
                                                int result = st.executeUpdate(query);
                                                JOptionPane.showMessageDialog(null,"Registration Successful");
                                                //Wallet Amount
                                                try {
                                                        Connection newcon=DriverManager.getConnection("jdbc:mysql://localhost:3306/bootcamp_project?zeroDateTimeBehavior=convertToNull", "root", "" );
                                                        Statement sta=newcon.createStatement();
                                                        String usname=name;
                                                        String Amount="100";
                                                        String query1="insert into wallet values(?,?)";
                                                        PreparedStatement pstmt = con.prepareStatement(query1);
                                                        pstmt.setString(1,name);
                                                        pstmt.setString(2,Amount);
                                                        pstmt.executeUpdate();
                                                        con.close();
                                                     }
                                                catch(Exception exx)
                                                    {
                                                        System.out.println("-->"+exx);
                                                    }
                                                this.dispose();
                                                Login  obje = new Login();
                                                Image icon = Toolkit.getDefaultToolkit().getImage("D:/busicon.png");    
                                                obje.setIconImage(icon);
                                                obje.setBounds(400,10,600,700);
                                                obje.setLayout(null);       
                                                obje.setTitle("Login");
                                                obje.setResizable(false); 
                                                obje.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                                                obje.setLocationRelativeTo(null);
                                                obje.setVisible(true);
                                                
                                            }
                                            else
                                            {
                                                JOptionPane.showMessageDialog(null,"User Name Already Exists");
                                            }
            
                                            con.close();
                                            
                                        }
                                        catch(SQLException e)
                                        {
                                            System.out.println("---------->  "+e);
                                        }
                                        
                                    }
                                });
        
        back.addActionListener((ActionEvent ae) ->
                                {
                                    this.dispose();
                                    Login obje=new Login();
                                    Image icon = Toolkit.getDefaultToolkit().getImage("D:/busicon.png");    
                                    obje.setIconImage(icon);
                                    obje.setBounds(400,10,600,700);
                                    obje.setLayout(null);       
                                    obje.setTitle("Login");
                                    obje.setResizable(false); 
                                    obje.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                                    obje.setLocationRelativeTo(null);
                                    obje.setVisible(true);
                                });
        C.add(Header_panel);
        Header_panel.add(back);
        Header_panel.add(Header_label);
        C.add(frameTitle);
        C.add(nameLabel);
        C.add(nameText);
        C.add(emailIdLabel);
        C.add(emailIdText);
        C.add(genderLabel);
        C.add(Male);
        C.add(Female);
        C.add(phoneLabel);
        C.add(phoneText);
        C.add(passwordLabel);
        C.add(passText);
        C.add(confirmLabel);
        C.add(confirmText);
        C.add(register);
        
        
    }
    /*public static void main(String args[])
    {
        RegisterForm f=new RegisterForm();
        f.setLayout(null);       
        f.setBounds(400, 10, 600, 700);
        f.setTitle("Register Form");
        f.setResizable(false); 
        //f.setUndecorated(true);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setLocationRelativeTo(null);
        f.setVisible(true); 
        ImageIcon ic=new ImageIcon("D:/BusIcon.png");
        f.setIconImage(ic.getImage());
        
    }*/
}