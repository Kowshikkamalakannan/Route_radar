package LetsRide;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


import java.util.regex.*;
import java.sql.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;


import java.awt.*;


public class update extends JFrame {
    
    String current_user;
    int admin_or_not;
    public update(String current_user,int admin_or_not) {
        
        this.current_user=current_user;
        this.admin_or_not=admin_or_not;
        setBounds(400, 10, 600, 700);
        //JFrame J=new JFrame(); /// J frame
	//J.getContentPane().setBackground(new Color(30, 39, 46));
        Container J=getContentPane();
        J.setBackground(new Color(30, 39, 46));
        //header panel
        JPanel Header_panel = new JPanel();
        Header_panel.setBounds(0, 0, 600, 60);
        Header_panel.setLayout(null);
        Header_panel.setBackground(new Color(255, 168, 1));
        JLabel Header_label = new JLabel("Route Radar");
        
        Header_label.setBounds(20, 10, 300, 50);
        Header_label.setFont(new Font("Bauhaus 93", Font.BOLD, 40));
        Header_label.setForeground(new Color(255, 255, 255));
        Header_panel.add(Header_label);
        
        JButton HomeBtn=new JButton("Back");  // Back Button
	HomeBtn.setBounds(500,20,80,25);	       
	HomeBtn.setFont(new Font("Times New Roman",Font.BOLD,18));
	HomeBtn.setBackground(new Color(194, 54, 22));
	HomeBtn.setForeground(Color.white);
        HomeBtn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        Header_panel.add(HomeBtn);
        J.add(Header_panel);
        
        
	
	JLabel labelUpdFrm =new JLabel("Update Your Account");
        labelUpdFrm.setBounds(40,60,280,65);
        labelUpdFrm.setFont(new Font("Times New Roman",Font.BOLD,25));
        labelUpdFrm.setForeground(Color.white);
        J.add(labelUpdFrm);
        
        ImageIcon icon=new ImageIcon("D:/update.png");
        JLabel im_up=new JLabel(icon);
        im_up.setBounds(360,40,230,192);
        J.add(im_up);
        
        JLabel Account_user=new JLabel("Account User- "+current_user);
        Account_user.setBounds(40,150,280,65);
        Account_user.setFont(new Font("Times New Roman",Font.BOLD,20));
        Account_user.setForeground(new Color(161, 214, 226));
        J.add(Account_user);
  
        JLabel labelPH =new JLabel("Change your phone no.: ");
        labelPH.setBounds(40,210,280,40);
        labelPH.setFont(new Font("Times New Roman",Font.BOLD,20));
        labelPH.setForeground(new Color(161, 214, 226));
        J.add(labelPH);
        
        
        JLabel labelEmailID =new JLabel("Change your Email Id: ");
        labelEmailID.setBounds(40,310,280,40);
        labelEmailID.setFont(new Font("Times New Roman",Font.BOLD,20));
        labelEmailID.setForeground(new Color(161, 214, 226));
        J.add(labelEmailID);
        
	
        
	JLabel labelPWD =new JLabel("Change your Password: ");
        labelPWD.setBounds(40,410,280,40);
        labelPWD.setFont(new Font("Times New Roman",Font.BOLD,20));
        labelPWD.setForeground(new Color(161, 214, 226)); 
        J.add(labelPWD);
        
	JLabel labelREPWD =new JLabel("Re-Enter Password : ");
        labelREPWD.setBounds(40,460,280,40);
        labelREPWD.setFont(new Font("Times New Roman",Font.BOLD,20));
        labelREPWD.setForeground(new Color(161, 214, 226));
        J.add(labelREPWD);
        
	JLabel statusLabel =new JLabel();
        statusLabel.setBounds(6,200,200,170);
        J.add(statusLabel);
        
        JLabel statusLabe2 =new JLabel();
	statusLabe2.setBounds(200,200,300,170);
        J.add(statusLabe2);
	
        //text field starts
        JTextField TxtPh =new JTextField();
        TxtPh.setBounds(260,210,160,30);
        TxtPh.setCursor(Cursor.getPredefinedCursor(Cursor.TEXT_CURSOR));
        TxtPh.setForeground(new Color(115, 96, 91));
        TxtPh.setFont(new Font("Times New Roman",Font.BOLD,15));
        J.add(TxtPh);
        
	JTextField TxtEmailID =new JTextField();
        TxtEmailID.setBounds(260,310,160,30);
        TxtEmailID.setCursor(Cursor.getPredefinedCursor(Cursor.TEXT_CURSOR));
        TxtEmailID.setForeground(new Color(115, 96, 91));
        TxtEmailID.setFont(new Font("Times New Roman",Font.BOLD,15));
        J.add(TxtEmailID);
        
	
	JPasswordField TxtPWD =new JPasswordField();
        TxtPWD.setBounds(260,410,160,30);
        TxtPWD.setForeground(new Color(115, 96, 91));
        TxtPWD.setFont(new Font("Times New Roman",Font.BOLD,15));
        J.add(TxtPWD);
        
	JPasswordField TxtREPWD =new JPasswordField();
        TxtREPWD.setBounds(260,460,160,30);
        TxtREPWD.setForeground(new Color(115, 96, 91));
	TxtREPWD.setFont(new Font("Times New Roman",Font.BOLD,15));
	J.add(TxtREPWD);
	   
        //button
        JButton UpdatePhnBtn=new JButton("Update Phone No");
        UpdatePhnBtn.setBounds(180,260,160,25);
        UpdatePhnBtn.setFont(new Font("Times New Roman",Font.BOLD,16));
	UpdatePhnBtn.setBackground(new Color(68, 189, 50));
	UpdatePhnBtn.setForeground(Color.white);
        UpdatePhnBtn.setBorderPainted(false);
        UpdatePhnBtn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        J.add(UpdatePhnBtn);
        
        
        JButton UpdateEmailBtn=new JButton("Update Email ID");
        UpdateEmailBtn.setBounds(180,360,160,25);
        UpdateEmailBtn.setFont(new Font("Times New Roman",Font.BOLD,16));
	UpdateEmailBtn.setBackground(new Color(68, 189,50));
	UpdateEmailBtn.setForeground(Color.white);
        UpdateEmailBtn.setBorderPainted(false);
        UpdateEmailBtn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        J.add(UpdateEmailBtn);
        
        
	
        
	JButton UpdatePwdBtn=new JButton("Update Password");  // Button
        UpdatePwdBtn.setBounds(180,510,160,25);
        UpdatePwdBtn.setFont(new Font("Times New Roman",Font.BOLD,16));
	UpdatePwdBtn.setBackground(new Color(68, 189,50));
	UpdatePwdBtn.setForeground(Color.white);
        UpdatePwdBtn.setBorderPainted(false);
        UpdatePwdBtn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        J.add(UpdatePwdBtn);
        
	
	    
	
		    
        UpdatePhnBtn.addActionListener(new ActionListener() {
	    public void actionPerformed(ActionEvent e) {
		String phonenumber = TxtPh.getText().toString();
		String number = TxtPh.getText();
		number = number.replace(" ", ""); // Remove spaces, sometimes people seperate different
	        // parts of the number with them
		boolean valid = number.matches("[0-9]{10,10}");
		if(!(phonenumber).equals("")&& valid==true){
                    try {

                        Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/bootcamp_project?zeroDateTimeBehavior=convertToNull", "root", "" );
                        Statement st=con.createStatement();
                        String q="UPDATE register set Phone_no= '"+TxtPh.getText()+"' where User_Name= '"+current_user+"' ";
                         st.executeUpdate(q);
                         
			JOptionPane.showMessageDialog(J,"Updated Phone Number Successfully");
                        TxtPh.setText("");
	             } 
                    catch (SQLException e1) {
			e1.printStackTrace();
			JOptionPane.showMessageDialog(J,"Error while Updating");
		    }
		}
		else {
                    JOptionPane.showMessageDialog(J,"Enter the valid Phone Number");
		}	
	    }
	});
		    
	UpdateEmailBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
		String varemailId = TxtEmailID.getText();
		String pattern = "[^ `!@#$%^&*()=?/;:| ]{0,64}@[^ `!@#$%^&*()=?/;:| ]{0,253}.[^ `!@#$%^&*()=?/;:| ]{2,3}";
		//boolean m= pattern.matches(var); 
		if (varemailId.matches(pattern)==true) { 
                    try {
                      
                        Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/bootcamp_project?zeroDateTimeBehavior=convertToNull", "root", "" );
                        Statement st=con.createStatement();
                        String q="UPDATE register set Email_id= '"+TxtEmailID.getText()+"' where User_Name= '"+current_user+"' ";
                        st.executeUpdate(q);
                 
			JOptionPane.showMessageDialog(J,"Updated Email Id Successfully");
                               TxtEmailID.setText("");
            	    } 
                    catch (SQLException e1) {
                        e1.printStackTrace();
			JOptionPane.showMessageDialog(J,"Error while Updating");
		    }		
		}
                else {
                    JOptionPane.showMessageDialog(J,"Email Id is in Incorrect format");
		}
	    }
	});
        
       UpdatePwdBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String var = TxtPWD.getText();
		String Password = TxtPWD.getText().toString();
		String RePassword = TxtREPWD.getText().toString();
		String phonenumber = TxtPh.getText().toString();
		String pattern = "(?=^.{8,}$)(?=.*\\d)(?=.*[!@#$%^&*]+)(?![.\\n])(?=.*[A-Z])(?=.*[a-z]).*$";
                boolean m= pattern.matches(var); 
		
		    if (var.matches(pattern)==true)  {			           
			if(Password.equals(RePassword)) {
			    try {
				
                                Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/bootcamp_project?zeroDateTimeBehavior=convertToNull", "root", "");
                                Statement st=con.createStatement();
                                String q="UPDATE register set Password= '"+TxtPWD.getText()+"' where User_Name= '"+current_user+"' ";
                                st.executeUpdate(q);
                                
				JOptionPane.showMessageDialog(J,"Updated Password Successfully");
                                TxtPWD.setText("");
                                TxtREPWD.setText("");
			    }
                            catch (SQLException e1) {
                                e1.printStackTrace();
				JOptionPane.showMessageDialog(J,"Error while Updating");
			    }		
			}
			else {
                            JOptionPane.showMessageDialog(J,"Passwords do not Match");
			    statusLabel.setText("");
			}
	       	    }
                    else {            	
			JOptionPane.showMessageDialog(J,"Incorrect Password Format");
			statusLabe2.setText("");
		    }
		             
            }
	}); 
        
       HomeBtn.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent e) {
                update.this.dispose();
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
	});

    }
}


