package LetsRide;

import java.sql.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.Vector;
import javax.swing.table.DefaultTableModel;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.sql.SQLException;
import javax.swing.table.JTableHeader;
class Homepage extends JFrame implements ActionListener{
        String current_user;
        int admin_or_not;
	String[] column={"Bus id","Departure","Arrival"};
	ResultSet rs;
	JScrollPane scro;
	JTable j;
	Container c;
	JButton b1,b2,b3,b4,b5,b6,b7;
	JPanel Header_Panel;
	JLabel Header_Label,im_ride,im_update,im_delete;
	DefaultTableModel model;
	Vector v;
	Homepage(String current_user,int admin_or_not){
                this.current_user=current_user;
                this.admin_or_not=admin_or_not;
                 
		c=getContentPane();
		b1=new JButton("Lets ride");
		b2=new JButton("Update account");
		b3=new JButton("Delete account");
		b4=new JButton("Logout");
		b5=new JButton("Records for Admin");
                b6=new JButton("My profile");
                b7=new JButton("My Wallet");
		c.setBackground(new Color(30, 39, 46));
		
                ImageIcon icon1=new ImageIcon("D:/letsridehome.png");
                im_ride=new JLabel(icon1);
                im_ride.setBounds(10,70,154,154);
                c.add(im_ride);
                
                
                
		b1.setBounds(40,210,100,25);
		b1.setFont(new Font("Times New Roman", Font.BOLD, 16));
		b1.setForeground(Color.white);
                b1.setBorderPainted(false);
                b1.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		b1.setBackground(new Color(68, 189, 50));
		
                ImageIcon icon2=new ImageIcon("D:/updatehome.png");
                im_update=new JLabel(icon2);
                im_update.setBounds(30,270,109,108);
                c.add(im_update);
     
		b2.setBounds(30,375,120,25);
		b2.setFont(new Font("Times New Roman", Font.BOLD,13));
		b2.setForeground(Color.white);
                b2.setBorderPainted(false);
                b2.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		b2.setBackground(new Color(68, 189, 50));
		
                ImageIcon icon3=new ImageIcon("D:/deletehome.png");
                im_delete=new JLabel(icon3);
                im_delete.setBounds(30,450,103,104);
                c.add(im_delete);
                
		b3.setBounds(30,545,120,25);
		b3.setFont(new Font("Times New Roman", Font.BOLD, 13));
		b3.setForeground(Color.white);
                b3.setBorderPainted(false);
                b3.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		b3.setBackground(new Color(68, 189, 50));
		
		b4.setBounds(480,23,100,25);
		b4.setFont(new Font("Times New Roman", Font.BOLD, 17));
		b4.setForeground(Color.white);
                b4.setBorderPainted(false);
                b4.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		b4.setBackground(new Color(194, 54, 22));
		
		b5.setBounds(430,90,150,25);
		b5.setFont(new Font("Times New Roman", Font.BOLD, 14));
		b5.setForeground(Color.white);
                b5.setBorderPainted(false);
                b5.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		b5.setBackground(new Color(61, 193, 211));
		
                b6.setBounds(430,90,150,25);
		b6.setFont(new Font("Times New Roman", Font.BOLD, 16));
		b6.setForeground(Color.white);
                b6.setBorderPainted(false);
                b6.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		b6.setBackground(new Color(61, 193, 211));
                
                b7.setBounds(430,130,150,25);
		b7.setFont(new Font("Times New Roman", Font.BOLD, 16));
		b7.setForeground(Color.white);
                b7.setBorderPainted(false);
                b7.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		b7.setBackground(new Color(68, 189, 50));
                b7.addActionListener(this);
                
                
                
                
                
                

		b1.addActionListener(this);
                b2.addActionListener(this);
                b3.addActionListener(this);
                b4.addActionListener(this);
                
		c.add(b1);
		c.add(b2);
		c.add(b3);
		c.add(b4);
                c.add(b7);
                if(admin_or_not==1){
                    c.add(b5);
                    b5.addActionListener(this);
                }
                if(admin_or_not==0)
                {
                    c.add(b6);
                    b6.addActionListener(this);
                }
		
		
		Header_Panel = new JPanel();
                Header_Panel.setBounds(0, 0, 600, 70);
                Header_Panel.setLayout(null);
                Header_Panel.setBackground(new Color(255, 168, 1));
                Header_Label = new JLabel("Route Radar");
                Header_Label.setBounds(20, 10, 300, 50);
                Header_Label.setFont(new Font("Bauhaus 93", Font.BOLD, 40));
                Header_Label.setForeground(new Color(255, 255, 255));
                c.add(Header_Label);
                c.add(Header_Panel);
    	
        //Table Model and Table declaration
		DefaultTableModel model= new DefaultTableModel()
		{
			public boolean isCellEditable(int row, int column) {
				return false;
				}
		};
		
		model.setColumnIdentifiers(column);
                
		j=new JTable();
                j.setRowHeight(50);
                j.setForeground(new Color(0,0,0));
                j.setFont(new Font("Times New Roman",Font.BOLD,15));
		JTableHeader head=j.getTableHeader();
                head.setBackground(new Color(30, 39, 46));
                head.setForeground(new Color(161, 214, 226));
                head.setFont(new Font("Times New Roman",Font.BOLD,20));
		j.setGridColor(Color.red);
		j.setBackground(new Color(154,205,50));
		j.getTableHeader().setReorderingAllowed(false); 
		j.setModel(model);
		j.setFillsViewportHeight(true);
		scro=new JScrollPane(j);
		scro.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);  
		scro.setBounds(180,220, 400,183);
		c.add(scro);
		
		//Database
        Connection con=null;
    	try {
    		
            con=DriverManager.getConnection("jdbc:mysql://localhost:3306/bootcamp_project?zeroDateTimeBehavior=convertToNull", "root", "");
            
    	    Statement  st = con.createStatement();
             String query="select * from bus";
             rs=st.executeQuery(query);
             while(rs.next())
             {
	        	 column[0]=rs.getString("Bus_id");
	        	 column[1]=rs.getString("Departure");
	        	 column[2]=rs.getString("Arrival");
	        	 model.addRow(column);
             }
             con.close();
    	}
    	catch (Exception e)	
    	{
    		System.out.println(e.getMessage());
    	}
    	
    }
	
	//Lets-Ride action event
	public void actionPerformed(ActionEvent e)
	{
                if(e.getSource()==b1)
                {
                    this.dispose();
                    letsrideframe f = new letsrideframe(current_user,admin_or_not);
                    Image icon = Toolkit.getDefaultToolkit().getImage("D:/busicon.png");    
                    f.setIconImage(icon);
                    f.setBounds(400, 10, 600, 700);
                    f.setTitle("Lets Ride");
                    f.setResizable(false);
                    f.setDefaultCloseOperation(3);
                    f.setVisible(true);
                }
		
                if(e.getSource()==b3){
                    try{
                Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/bootcamp_project?zeroDateTimeBehavior=convertToNull", "root", "" );
                Statement st=con.createStatement();
                String query="UPDATE register set active_or_not= '"+0+"' where User_Name= '"+current_user+"' ";
                st.executeUpdate(query);
                JOptionPane.showMessageDialog(null,"Deleted successfully!");
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
            catch(SQLException ex){
                   System.out.println("---> "+ex);
            } 
                }
                if(e.getSource()==b2){
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
                
             if(e.getSource()==b4){
                this.dispose();
                Login  obj = new Login();
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
             if(e.getSource()==b5)
             {
                this.dispose();
                Admin a=new Admin(current_user,admin_or_not);
                Image icon = Toolkit.getDefaultToolkit().getImage("D:/busicon.png");    
                a.setIconImage(icon);
                a.setBounds(400, 10, 600, 700);
                a.setLayout(null);       
                a.setTitle("Admin");
                a.setResizable(false); 
                a.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                a.setLocationRelativeTo(null);
                a.setVisible(true);
             }
             if(e.getSource()==b6)
             {
                 this.dispose();
                 Myprofile obj=new Myprofile(current_user,admin_or_not);
                 Image icon = Toolkit.getDefaultToolkit().getImage("D:/busicon.png");    
                 obj.setIconImage(icon);
                 obj.setBounds(400, 10, 600, 700);
                 obj.setLayout(null);       
                 obj.setTitle("My profile");
                 obj.setResizable(false); 
                 obj.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                 obj.setLocationRelativeTo(null);
                 obj.setVisible(true);
             }
             if(e.getSource()==b7)
             {
                 
                 Wallet objee=new Wallet(current_user,admin_or_not);
                 
                 Image icon = Toolkit.getDefaultToolkit().getImage("D:/busicon.png");    
                 objee.setIconImage(icon);
                 objee.setBounds(400, 10, 600, 700);
                 objee.setLayout(null);       
                 objee.setTitle("Wallet");
                 objee.setResizable(false); 
                 objee.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                 objee.setLocationRelativeTo(null);
                 objee.setVisible(true);   
                 this.dispose();
             }
	}
}


