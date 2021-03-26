package LetsRide;
import java.sql.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

class Admin extends JFrame implements ItemListener,ActionListener{
    Container c=getContentPane();
    String current_user,stat_acc;
    int admin_or_not;
    int zero=0;
    JLabel Header_label,corner,p1_user_name,present_name,present_phone,present_status,present_email,p2_user_name,p3_bus,present_gender;
    JTextField name,phone,email,status,gender;
    JButton back;
    JTabbedPane tp;
    JPanel p1,p2,p3;
    JTable user_travel_log,bus_log_table;
    DefaultTableModel model2,model3; 
    JScrollPane scroll,scroll3;
    JComboBox display_names,p2_display_names,bus_display,busdate,namedate;
    Vector<String> vector_name=new Vector<String>();
    Vector<String> date_all=new Vector<String>();
    String[] column_2ndtab={"Bus id","Date","Boarding Point","Destination Point", "Amount"};
    
    String[] thirdpanel_tablecolumn={"User Name","Date","Boarding Point","Destination Point", "Amount"};
    String[] bus_combo={"--select--","RR101","RR102","RR103"};
    JPanel Header_panel;
    Admin(String current_user,int admin_or_not){
        this.admin_or_not=admin_or_not;
        this.current_user=current_user;
        
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
        //corner label of app
        corner=new JLabel();
        corner.setText("Records for Admin");
        corner.setBounds(200,60,300,50);
        c.add(corner);
        corner.setFont(new Font("Times New Roman",Font.BOLD,25));
        corner.setForeground(new Color(255,215,0));
        
        //back button
        back=new JButton();
        back.addActionListener(this);
        back.setText("Back");
        back.setBounds(500,20,70,20);
        back.setFont(new Font("Times New Roman",Font.BOLD,15));
        back.setBackground(new Color(194, 54, 22));
        back.setBorderPainted(false);
        back.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        back.setForeground(new Color(255,255,255));
        Header_panel.add(back);
        
        tp=new JTabbedPane();
        p1=new JPanel();
        p2=new JPanel();
        p3=new JPanel();
        
        p1.setLayout(null);
        p2.setLayout(null);
        p3.setLayout(null);
        p1.setBackground(new Color(0,139,139));
        p2.setBackground(new Color(0,139,139));
        p3.setBackground(new Color(0,139,139));
        tp.setBounds(10,120,570,530);
        tp.add("User info",p1);
        tp.add("User travel log",p2);
        tp.add("Bus log",p3);
        tp.setBackground(new Color(0,139,139));
        tp.setFont(new Font("Times New Roman",Font.BOLD,20));
        tp.setForeground(Color.WHITE);
        c.add(tp);
        
        //panel 1 user info codes
        p1_user_name=new JLabel();
        p1_user_name.setText("Select User name");
        p1_user_name.setBounds(90,10,200,50);
        p1_user_name.setFont(new Font("Times New Roman",Font.BOLD,20));
        p1_user_name.setForeground(new Color(245,255,250));        
        p1.add(p1_user_name);
        


        //calling function to get the vector of 
        getArray_of_usernames();
        getArray_of_dates();
        
        //creating combo box for panel 1
        display_names=new JComboBox(vector_name);
        display_names.setBounds(280,22,140,30); 
        display_names.addItemListener(this);
        display_names.setBackground(new Color(255, 168, 1));
        display_names.setForeground(new Color(255, 255, 255));
        display_names.setFont(new Font("Times New Roman",Font.BOLD,16));
        p1.add(display_names);  
        
        
        
        present_name=new JLabel();
        present_name.setText("User Name");
        present_name.setBounds(40,80,200,50);
        p1.add(present_name);
        present_name.setFont(new Font("Times New Roman",Font.BOLD,20));
        present_name.setForeground(new Color(245,255,250));
        
        present_email=new JLabel();
        present_email.setText("User Email id");
        present_email.setBounds(40,130,200,50);
        present_email.setForeground(new Color(245,255,250));
        present_email.setFont(new Font("Times New Roman",Font.BOLD,20));
        p1.add(present_email);
        
        present_gender=new JLabel();
        present_gender.setText("User Gender");
        present_gender.setBounds(40,180,200,50);
        present_gender.setForeground(new Color(245,255,250));
        present_gender.setFont(new Font("Times New Roman",Font.BOLD,20));
        p1.add(present_gender);
        
        present_phone=new JLabel();
        present_phone.setText("User Phone Number");
        present_phone.setBounds(40,230,200,50);
        present_phone.setForeground(new Color(245,255,250));
        present_phone.setFont(new Font("Times New Roman",Font.BOLD,20));
        p1.add(present_phone);
        
        present_status=new JLabel();
        present_status.setText("Account Status");
        present_status.setBounds(40,280,200,50);
        present_status.setForeground(new Color(245,255,250));
        present_status.setFont(new Font("Times New Roman",Font.BOLD,20));
        p1.add(present_status);
        
        name=new JTextField();
        name.setBounds(240,90,180,30);
        name.setFont(new Font("Times New Roman",Font.BOLD,17));
        name.setForeground(new Color(0,0,0));
        name.setEditable(false);
        p1.add(name);
        
        email=new JTextField();
        email.setBounds(240,140,180,30);
        email.setFont(new Font("Times New Roman",Font.BOLD,17));
        email.setForeground(new Color(0,0,0));
        email.setEditable(false);
        p1.add(email);
        
        gender=new JTextField();
        gender.setBounds(240,190,180,30);
        gender.setFont(new Font("Times New Roman",Font.BOLD,17));
        gender.setForeground(new Color(0,0,0));
        gender.setEditable(false);
        p1.add(gender);
        
        phone=new JTextField();
        phone.setBounds(240,240,180,30);
        phone.setFont(new Font("Times New Roman",Font.BOLD,17));
        phone.setForeground(new Color(0,0,0));
        phone.setEditable(false);
        p1.add(phone);
        
        status=new JTextField();
        status.setBounds(240,290,180,30);
        status.setFont(new Font("Times New Roman",Font.BOLD,17));
        status.setForeground(new Color(0,0,0));
        status.setEditable(false);
        p1.add(status);
        
        panel2_arrangements();
        
       
        model2=new DefaultTableModel(){
            @Override
            public boolean isCellEditable(int row,int column){
                    return false;
            }
        };
        
        model2.setColumnIdentifiers(column_2ndtab);
        user_travel_log=new JTable();
        user_travel_log.setModel(model2);
        user_travel_log.setRowHeight(30);
        user_travel_log.getColumnModel().getColumn(0).setPreferredWidth(40);
        user_travel_log.getColumnModel().getColumn(1).setPreferredWidth(70);
        user_travel_log.getColumnModel().getColumn(2).setPreferredWidth(100);
        user_travel_log.getColumnModel().getColumn(3).setPreferredWidth(100);
        user_travel_log.getColumnModel().getColumn(4).setPreferredWidth(40);
        JTableHeader head1=user_travel_log.getTableHeader();
        head1.setBackground(new Color(30, 39, 46));
        user_travel_log.getTableHeader().setReorderingAllowed(false);
        user_travel_log.setRowSelectionAllowed(false);
        user_travel_log.setColumnSelectionAllowed(false);
        user_travel_log.setBackground(new Color(154,205,50));
        user_travel_log.setForeground(new Color(0,0,0));
        user_travel_log.setFont(new Font("Times New Roman",Font.BOLD,17));
        head1.setForeground(new Color(161, 214, 226));
        head1.setFont(new Font("Times New Roman",Font.BOLD,20));
        
        scroll=new JScrollPane(user_travel_log);
        scroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        user_travel_log.setGridColor(Color.RED);
        scroll.setBackground(new Color(30, 39, 46));
        scroll.setBounds(10,70,550,400);
                    
                    
        panel3_arrangements();
        model3=new DefaultTableModel(){
            @Override
            public boolean isCellEditable(int row,int column){
                return false;
            }
        };
        
        model3.setColumnIdentifiers(thirdpanel_tablecolumn);
        bus_log_table=new JTable();
        bus_log_table.setModel(model3);
        bus_log_table.setRowHeight(30);
        bus_log_table.setBackground(new Color(154,205,50));
        bus_log_table.setForeground(new Color(0,0,0));
        bus_log_table.setFont(new Font("Times New Roman",Font.BOLD,17));
        JTableHeader head=bus_log_table.getTableHeader();
        head.setBackground(new Color(30, 39, 46));
        bus_log_table.setRowSelectionAllowed(false);
        bus_log_table.setColumnSelectionAllowed(false);
        bus_log_table.getTableHeader().setReorderingAllowed(false);
        bus_log_table.getColumnModel().getColumn(0).setPreferredWidth(50);
        bus_log_table.getColumnModel().getColumn(1).setPreferredWidth(80);
        bus_log_table.getColumnModel().getColumn(2).setPreferredWidth(100);
        bus_log_table.getColumnModel().getColumn(3).setPreferredWidth(100);
        bus_log_table.getColumnModel().getColumn(4).setPreferredWidth(60);
        head.setForeground(new Color(161, 214, 226));
        head.setFont(new Font("Times New Roman",Font.BOLD,20));
        scroll3=new JScrollPane(bus_log_table);
        scroll3.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scroll3.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        bus_log_table.setGridColor(Color.RED);
        scroll3.setBounds(10,70,550,400);
    }
    
    @Override
    public void itemStateChanged(ItemEvent e){
        if(e.getSource()==display_names){
            if(display_names.getSelectedItem()!="--select--"){
                try{
                    Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/bootcamp_project?zeroDateTimeBehavior=convertToNull", "root", ""  );
                    Statement st=con.createStatement();
                    String query="select * from register where User_Name='"+display_names.getSelectedItem()+"'";
                    ResultSet r=st.executeQuery(query);
                    if(r.next()){
                        name.setText(r.getString(1));
                        email.setText(r.getString(2));
                        phone.setText(r.getString(4));
                        gender.setText(r.getString(3));
                        if(r.getInt(7)==1){stat_acc="Active";}
                        if(r.getInt(7)==0){stat_acc="Deleted";}
                        status.setText(stat_acc);
                       
                    }
                     con.close();
                }
                catch(Exception exx){
                    System.out.println("-->"+exx);
                }
            }
            else{
                name.setText("");
                email.setText("");
                phone.setText("");
                gender.setText("");
                status.setText("");
            }
        }
        if(e.getSource()==p2_display_names || e.getSource()==namedate){
            if(p2_display_names.getSelectedItem().equals("--select--")){
                model2.setRowCount(0);
                
            }
            else{
                try{
                    model2.setRowCount(0);
                    p2.add(scroll);
                    String str;
                    Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/bootcamp_project?zeroDateTimeBehavior=convertToNull", "root", ""  );
                    if(namedate.getSelectedItem().equals("All")){
                        str="select * from travellog where Username='"+(String)p2_display_names.getSelectedItem()+"'";
                    }
                    else{
                        str="select * from travellog where Username='"+(String)p2_display_names.getSelectedItem()+"' and Date='"+namedate.getSelectedItem()+"'";
                    }
                    PreparedStatement ps=con.prepareStatement(str);
                    ResultSet rs=ps.executeQuery();
                    while(rs.next()){
                        column_2ndtab[0]=rs.getString(2);
                        column_2ndtab[1]=rs.getString(6);
                        column_2ndtab[2]=rs.getString(3);
                        column_2ndtab[3]=rs.getString(4);
                        column_2ndtab[4]=rs.getString(5);
                        model2.addRow(column_2ndtab);
                    }
                    con.close();
                }
             
                catch(Exception exp){
                    System.out.println("->"+exp);
                }
                
            }
        }
        
        if(e.getSource()==bus_display || e.getSource()==busdate){
            if(bus_display.getSelectedItem().equals("--select--")){
                model3.setRowCount(0);
                
            }
            else{
                try{
                    model3.setRowCount(0);
                    p3.add(scroll3);
                    String str3;
                    Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/bootcamp_project?zeroDateTimeBehavior=convertToNull", "root", ""  );
                    if(busdate.getSelectedItem().equals("All")){
                        str3="select * from travellog where BusId='"+(String)bus_display.getSelectedItem()+"'";
                    }
                    else{
                        str3="select * from travellog where BusId='"+(String)bus_display.getSelectedItem()+"' and Date='"+busdate.getSelectedItem()+"'";
                    }
                    PreparedStatement ps=con.prepareStatement(str3);
                    ResultSet r=ps.executeQuery();
                    while(r.next()){
                        thirdpanel_tablecolumn[0]=r.getString(1);
                        thirdpanel_tablecolumn[1]=r.getString(6);
                        thirdpanel_tablecolumn[2]=r.getString(3);
                        thirdpanel_tablecolumn[3]=r.getString(4);
                        thirdpanel_tablecolumn[4]=r.getString(5);
                        model3.addRow(thirdpanel_tablecolumn);
                    }
                    con.close();
                }
             
                catch(Exception exp){
                    System.out.println("->"+exp);
                }
                
            }
        }
    }
    
    @Override
    public void actionPerformed(ActionEvent e){
        if(e.getSource()==back){
            this.dispose();
            Homepage obj=new Homepage(current_user,admin_or_not);
            Image icon = Toolkit.getDefaultToolkit().getImage("D:/busicon.png");    
            obj.setIconImage(icon);
            obj.setLayout(null);
            obj.setBounds(400, 10, 600, 700);
            obj.setTitle("HomePage");
            obj.setResizable(false);
            obj.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            obj.setLocationRelativeTo(null);
            obj.setVisible(true);
        }
        
       
    }
    
    void getArray_of_usernames(){
        try{
            Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/bootcamp_project?zeroDateTimeBehavior=convertToNull", "root", ""  );
            Statement st=con.createStatement();
            String query="select User_Name from register where admin_or_not='"+zero+"'";
            ResultSet r=st.executeQuery(query);
            vector_name.add("--select--");
            while(r.next()){
                vector_name.add(r.getString(1));
            }
            
            con.close();
        }
        catch(Exception ex){
            System.out.println(ex);
        }
    }
    
    
    void getArray_of_dates(){
        try{
            Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/bootcamp_project?zeroDateTimeBehavior=convertToNull", "root", "" );
            Statement st=con.createStatement();
            String query="select * from date";
            ResultSet r=st.executeQuery(query);
            
            date_all.add("All");
            while(r.next()){
                date_all.add(r.getString(1));
            }
            
            con.close();
        }
        catch(Exception ex){
            System.out.println(ex);
        }
    }
    
    public void panel2_arrangements(){
        //panel 1 user info codes
        p2_user_name=new JLabel();
        p2_user_name.setText("Select User name");
        p2_user_name.setBounds(50,0,200,50);
        p2_user_name.setFont(new Font("Times New Roman",Font.BOLD,20));
        p2_user_name.setForeground(new Color(241, 241, 242));
        p2.add(p2_user_name);
        
        namedate=new JComboBox(date_all);
        namedate.setBounds(360,12,130,30); 
        namedate.addItemListener(this);
        namedate.setBackground(new Color(255, 168, 1));
        namedate.setForeground(new Color(255, 255, 255));
        namedate.setFont(new Font("Times New Roman",Font.BOLD,16));
        p2.add(namedate);
        
        //creating combo box for panel 1
        p2_display_names=new JComboBox(vector_name);
        p2_display_names.setBounds(210,12,140,30); 
        p2_display_names.setFont(new Font("Times New Roman",Font.BOLD,16));
        p2_display_names.setBackground(new Color(255, 168, 1));
        p2_display_names.setForeground(new Color(255, 255, 255));
        p2_display_names.addItemListener(this);
        p2.add(p2_display_names);  
        
        
        
        
    }
    public void panel3_arrangements(){
        //panel 3 user info codes
        p3_bus=new JLabel();
        p3_bus.setText("Select Bus id");
        p3_bus.setBounds(50,0,200,50);
        p3_bus.setFont(new Font("Times New Roman",Font.BOLD,20));
        p3_bus.setForeground(new Color(241, 241, 242));
        p3.add(p3_bus);
        
        busdate=new JComboBox(date_all);
        busdate.setBounds(360,12,130,30); 
        busdate.addItemListener(this);
        busdate.setBackground(new Color(255, 168, 1));
        busdate.setForeground(new Color(255, 255, 255));
        busdate.setFont(new Font("Times New Roman",Font.BOLD,16));
        p3.add(busdate);
        
        
        //creating combo box for panel 3
        bus_display=new JComboBox(bus_combo);
        bus_display.setFont(new Font("Times New Roman",Font.BOLD,16));
        bus_display.setBounds(210,12,140,30);
        bus_display.setBackground(new Color(255, 168, 1));
        bus_display.setForeground(new Color(255, 255, 255));
        bus_display.addItemListener(this);
        p3.add(bus_display);  
        
        
        
        
    }
}



