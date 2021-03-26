/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package LetsRide;
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.text.SimpleDateFormat;  
import java.util.Date;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.sql.*;
import java.util.Hashtable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

public class letsrideframe extends JFrame implements ActionListener{

    JLabel Header_label, Boarding_Label, Destination_Label, BusId_Label, Amount_Label, Checkwait_Label,Tab1BusId,Tab2BusId;
    JComboBox combo_board, combo_Destination, combo_BusId, combo_Tab1BusId,combo_Tab2BusId;
    JButton Pay_button, TravelLog_button, Fare_Button,Check_Button,Check_ButtonTab2,HomeButton;
    JTextField Amount_textField,Tab1Text;
    JTabbedPane FooterPanelPane;
    JPanel Header_panel, Footer_panel, Tab1, Tab2;
    String Boarding_stop1[] = {"Gandhipuram", "Gp","Ganapathy","CMS","Bharathi Nagar","Ramakrishna Mill","Prozone Mall","Srp Mill","AmmanKovil","Kalapatti pirivu","Saravanampatti"};
    String Destination_stop1[] = {"Gandhipuram", "Gp","Ganapathy","CMS","Bharathi Nagar","Prozone Mall","Srp Mill","AmmanKovil","Kalapatti pirivu","Saravanampatti"};
    String Boarding_stop2[]= {"Gandhipuram","Marakadai","Townhall","Ukkadam","Kuniyamuthur","Aparana","Vijayalakshmi mill","BK pudhur","Kovaipudhur pirivu","Madukkarai"};
    String Destination_stop2[]= {"Gandhipuram","Marakadai","Townhall","Ukkadam","Kuniyamuthur","Aparana","Vijayalakshmi mill","BK pudhur","Kovaipudhur pirivu","Madukkarai"};
    String Boarding_stop3[]= {"Gandhipuram","Women's Polytechnic","Mani school","Lakshmi Mills","Nava India","S.O Bunk","Krishnammal College","Fun Mall","Hopes","Peelamedu"};
    String Destination_stop3[]= {"Gandhipuram","Women's Polytechnic","Mani school","Lakshmi Mills","Nava India","S.O Bunk","Krishnammal College","Fun Mall","Hopes","Peelamedu"};
    String BusIds[] = {"----Select-----","RR101", "RR102", "RR103"};
    JTable Tab2Table1,Tab2Table2,Tab2Table3;
    JScrollPane scrolltab2_RR01,scrolltab2_RR02,scrolltab2_RR03;
    String[] columntab2={"Timing","Stop Timings"};
    Hashtable<String, String[]> subItems = new Hashtable<String, String[]>();
    String Current_user;
    int admin_or_not;
    int curren_bal;
    Container C = getContentPane();
    JTableHeader head;
    letsrideframe(String Current_user,int admin_or_not) {
        this.Current_user=Current_user;
        this.admin_or_not=admin_or_not;
        C.setBackground(new Color(30, 39, 46));
        C.setLayout(null); 
        //header Panel
        Header_panel = new JPanel();
        Header_panel.setBounds(0, 0, 600, 60);
        Header_panel.setLayout(null);
        Header_panel.setBackground(new Color(255, 168, 1));
        Header_label = new JLabel("Route Radar");
        Header_label.setBounds(20, 10, 300, 50);
        Header_label.setFont(new Font("Bauhaus 93", Font.BOLD, 40));
        Header_label.setForeground(new Color(255, 255, 255));

        //TravelLog
        TravelLog_button = new JButton("My Travel Log");
        TravelLog_button.setBounds(435, 65, 150, 25);
        TravelLog_button.setFont(new Font("Times New Roman", 1, 16));
        TravelLog_button.setBackground(new Color(194, 54, 22));
        TravelLog_button.setForeground(new Color(255, 255, 255));
        TravelLog_button.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        TravelLog_button.addActionListener(this);
        TravelLog_button.setBorderPainted(false);
        
        //HomeButton
        HomeButton =new JButton("Home");
        HomeButton.setBounds(480,23,100,25);
        HomeButton.setFont(new Font("Times New Roman", Font.BOLD, 17));
        HomeButton.setBackground(new Color(61, 193, 211));
        HomeButton.setForeground(new Color(255, 255, 255));
        HomeButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        HomeButton.addActionListener(this);
        HomeButton.setBorderPainted(false);
        
        //Boarding
        Boarding_Label = new JLabel("Boarding Location:");
        Boarding_Label.setBounds(40, 125, 170, 30);
        Boarding_Label.setFont(new Font("Times New Roman", Font.BOLD, 20));
        Boarding_Label.setForeground(new Color(255, 255, 255));
        String combo_startBoard[]={"---------Select------"};
        combo_board = new JComboBox(combo_startBoard);
        combo_board.setBounds(55, 155, 130, 20);
        combo_board.setBackground(new Color(255, 168, 1));
        combo_board.setForeground(new Color(255, 255, 255));
        
        //Destination
        Destination_Label = new JLabel("Destination:");
        Destination_Label.setBounds(400, 125, 110, 30);
        Destination_Label.setFont(new Font("Times New Roman", Font.BOLD, 20));
        Destination_Label.setForeground(new Color(255, 255, 255));
        String combo_startDesti[]={"---------Select-------"};
        combo_Destination = new JComboBox(combo_startDesti);
        combo_Destination.setBounds(395, 155, 130, 20);
        combo_Destination.setBackground(new Color(255, 168, 1));
        combo_Destination.setForeground(new Color(255, 255, 255));
        
        //Combo Board Destination Based on BUsID
        subItems.put(BusIds[1],Boarding_stop1);
        subItems.put(BusIds[2],Boarding_stop2);
        subItems.put(BusIds[3],Boarding_stop3);

        //BusId
        BusId_Label = new JLabel("Bus Id:");
        BusId_Label.setBounds(165, 80, 105, 20);
        BusId_Label.setFont(new Font("Times New Roman", Font.BOLD, 24));
        BusId_Label.setForeground(new Color(255, 255, 255));
        combo_BusId = new JComboBox(BusIds);
        combo_BusId.setBounds(260, 80, 100, 20);
        combo_BusId.setBackground(new Color(255, 168, 1));
        combo_BusId.setForeground(new Color(255, 255, 255));
        combo_BusId.addActionListener(this);
        
        //Calculate Fare Button
        Fare_Button = new JButton("Calculate Fare");
        Fare_Button.setBounds(223, 180, 135, 20);
        Fare_Button.setFont(new Font("Tahoma", 1, 14));
        Fare_Button.setBackground(new Color(68, 189, 50));
        Fare_Button.setForeground(new Color(255, 255, 255));
        Fare_Button.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        Fare_Button.addActionListener(this);
        Fare_Button.setBorderPainted(false);
        //Amount
        Amount_Label = new JLabel("Amount:");
        Amount_Label.setBounds(190, 235, 120, 20);
        Amount_Label.setFont(new Font("Times New Roman", Font.BOLD, 24));
        Amount_Label.setForeground(new Color(255, 255, 255));
        Amount_textField = new JTextField();
        Amount_textField.setBounds(290, 235, 100, 20);
        Amount_textField.setForeground(new Color(0,0,0));
        Amount_textField.setFont(new Font("Times New Roman",Font.BOLD,20));
        Amount_textField.setEditable(false);
        
        //pay
        Pay_button = new JButton("Pay");
        Pay_button.setBounds(190, 280, 200, 25);
        Pay_button.setFont(new Font("Tahoma", 1, 14));
        Pay_button.setBackground(new Color(68, 189, 50));
        Pay_button.setForeground(new Color(255, 255, 255));
        Pay_button.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        Pay_button.addActionListener(this);
        Pay_button.setBorderPainted(false);
        //Footer Pannel
        Footer_panel = new JPanel();
        Footer_panel.setBounds(20, 320, 550, 330);
        Footer_panel.setLayout(null);
        Footer_panel.setBackground(new Color(30, 144, 255));

        //WaitTime
        Checkwait_Label = new JLabel("Info Box:");
        Checkwait_Label.setBounds(20, 1, 400, 50);
        Checkwait_Label.setFont(new Font("Broadway", Font.BOLD, 28));
        Checkwait_Label.setForeground(new Color(255, 255, 255));

        //Tabbed Pane On Fotter Panel
        FooterPanelPane = new JTabbedPane();
        Tab1 = new JPanel();
        Tab1.setBackground(new Color(255,255,255));
        Tab2 = new JPanel();
        Tab1.setLayout(null);
        Tab2.setLayout(null);
        FooterPanelPane.setBounds(30, 50, 500, 270);
        FooterPanelPane.add("Bus Timings", Tab1);
        FooterPanelPane.add("Travel Timing", Tab2);
        FooterPanelPane.setForeground(new Color(30, 39, 46));
        Footer_panel.add(FooterPanelPane);
        
        //Tab1 
        Tab1BusId = new JLabel("BusID:");
        Tab1BusId.setBounds(10, 10, 180, 20);
        Tab1BusId.setFont(new Font("Times New Roman", Font.BOLD, 20));
        Tab1BusId.setForeground(new Color(30, 39, 46));
        combo_Tab1BusId = new JComboBox(BusIds);
        combo_Tab1BusId.setBounds(100, 10, 100, 20);
        combo_Tab1BusId.setBackground(new Color(255, 168, 1));
        combo_Tab1BusId.setForeground(new Color(255, 255, 255));
        Check_Button=new JButton("Check");
        Check_Button.setBounds(350,10,100,20);
        Check_Button.setFont(new Font("Tahoma", 1, 14));
        Check_Button.setBackground(new Color(194, 54, 22));
        Check_Button.setForeground(new Color(255,255,255));
        Check_Button.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        Check_Button.setBorderPainted(false);
        Tab1.add(Check_Button);
        Check_Button.addActionListener(this);
        
        //Tab1Text
        Tab1Text=new JTextField();
        Tab1Text.setBounds(80,100,350,40);
        Tab1Text.setEditable(false);
        Tab1Text.setBackground(new Color(255,228,196));
        Tab1Text.setFont(new Font("Tahoma",Font.BOLD,16));
        Tab1Text.setForeground(Color.red);
        combo_Tab1BusId.addActionListener(this);
        Tab1Text.setText("------------Check Your Timings------------");
        Tab1.add(Tab1Text);
        Tab1.add(Tab1BusId);
        Tab1.add(combo_Tab1BusId);
        
        //Tab2
        Tab2BusId = new JLabel("BusID:");
        Tab2BusId.setBounds(10, 10, 180, 20);
        Tab2BusId.setFont(new Font("Times New Roman", Font.BOLD, 20));
        Tab2BusId.setForeground(new Color(30, 39, 46));
        combo_Tab2BusId= new JComboBox(BusIds);
        combo_Tab2BusId.setBounds(100, 10, 100, 20);
        combo_Tab2BusId.setBackground(new Color(255, 168, 1));
        combo_Tab2BusId.setForeground(new Color(255, 255, 255));
        Tab2.add(Tab2BusId);
        Tab2.add(combo_Tab2BusId);
        //Tab2Text
        Check_ButtonTab2=new JButton("Check");
        Check_ButtonTab2.setBounds(350,10,100,20);
        Check_ButtonTab2.setFont(new Font("Tahoma", 1, 14));
        Check_ButtonTab2.setBackground(new Color(194, 54, 22));
        Check_ButtonTab2.setForeground(new Color(255,255,255));
        Check_ButtonTab2.setBorderPainted(false);
        Check_ButtonTab2.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        Tab2.add(Check_ButtonTab2);
        Check_ButtonTab2.addActionListener(this);
        
        //Tab2Table1(RR101)
        String[][] Data101={{"5.00","|Gandhipuram:5.00|---|Gp:5.15|---|Ganapathy:5.25|----|CMS:5.40|----|Bharathi Nagar:6.00|----|Ramakrishna Mill:6.05|----|Prozone Mall:6.15|-----|Srp Mill:6.25|----|AmmanKovil:6.35|----|Kalapatti pirivu:6.50|----|Saravanampatti:7.00|"},{"9.00","|Gandhipuram:9.00|---|Gp:9.15|---|Ganapathy:9.25|----|CMS:9.40|----|Bharathi Nagar:10.00|----|Ramakrishna Mill:10.05|----|Prozone Mall:10.15|-----|Srp Mill:10.25|----|AmmanKovil:10.35|----|Kalapatti pirivu:10.50|----|Saravanampatti:11.00|"},{"1.00","|Gandhipuram:1.00|---|Gp:1.15|---|Ganapathy:1.25|----|CMS:1.40|----|Bharathi Nagar:2.00|----|Ramakrishna Mill:2.05|----|Prozone Mall:2.15|-----|Srp Mill:2.25|----|AmmanKovil:2.35|----|Kalapatti pirivu:2.50|----|Saravanampatti:3.00|"},{"5.00","|Gandhipuram:5.00|---|Gp:5.15|---|Ganapathy:5.25|----|CMS:5.40|----|Bharathi Nagar:6.00|----|Ramakrishna Mill:6.05|----|Prozone Mall:6.15|-----|Srp Mill:6.25|----|AmmanKovil:6.35|----|Kalapatti pirivu:6.50|----|Saravanampatti:7.00|"}};
        Tab2Table1=new JTable(Data101,columntab2);
        Tab2Table1.setFont(new Font("Times New Roman",Font.BOLD,15));
        Tab2Table1.setForeground(new Color(0,0,0));
        Tab2Table1.setBackground(new Color(154,205,50));
        Tab2Table1.setRowHeight(30);
        Tab2Table1.getTableHeader().setReorderingAllowed(false);
        Tab2Table1.setDefaultEditor(Object.class, null);
        Tab2Table1.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        Tab2Table1.getColumnModel().getColumn(0).setPreferredWidth(65);
        Tab2Table1.getColumnModel().getColumn(1).setPreferredWidth(1600);
        head=Tab2Table1.getTableHeader();
        head.setBackground(new Color(30, 39, 46));
        head.setForeground(new Color(255,165,0));
        head.setFont(new Font("Times New Roman",Font.BOLD,20));
        scrolltab2_RR01=new JScrollPane(Tab2Table1);
        scrolltab2_RR01.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        scrolltab2_RR01.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scrolltab2_RR01.setBounds(20,50,450,170);
        
        //Tab2Tabele2(RR102)
        String[][] Data102={{"5.20","|Gandhipuram:5.20|---|Marakadai:5.35|---|Townhall:5.50|----|Ukkadam:6.00|----|Kuniyamuthur:6.05|----|Aparana:6.15|-----|Vijayalakshmi mill:6.30|----|BK pudhur:6.35|----|Kovaipudhur pirivu:6.55|----|Madukkarai:7.20|"},{"9.20","|Gandhipuram:9.20|---|Marakadai:9.35|---|Townhall:9.50|----|Ukkadam:9.00|----|Kuniyamuthur:10.05|----|Aparana:10.15|-----|Vijayalakshmi mill:10.30|----|BK pudhur:10.35|----|Kovaipudhur pirivu:10.55|----|Madukkarai:11.20|"},{"1.20","|Gandhipuram:1.20|---|Marakadai:1.35|---|Townhall:1.50|----|Ukkadam:2.00|----|Kuniyamuthur:2.05|----|Aparana:2.15|-----|Vijayalakshmi mill:2.30|----|BK pudhur:2.35|----|Kovaipudhur pirivu:2.55|----|Madukkarai:3.20|"},{"5.20","|Gandhipuram:5.20|---|Marakadai:5.35|---|Townhall:5.50|----|Ukkadam:6.00|----|Kuniyamuthur:6.05|----|Aparana:6.15|-----|Vijayalakshmi mill:6.30|----|BK pudhur:6.35|----|Kovaipudhur pirivu:6.55|----|Madukkarai:7.20|"}};
        Tab2Table2=new JTable(Data102,columntab2);
        Tab2Table2.setFont(new Font("Times New Roman",Font.BOLD,15));
        Tab2Table2.setForeground(new Color(0,0,0));
        Tab2Table2.setBackground(new Color(154,205,50));
        Tab2Table2.setRowHeight(30);
        Tab2Table2.getTableHeader().setReorderingAllowed(false);
        Tab2Table2.setDefaultEditor(Object.class, null);
        Tab2Table2.getColumnModel().getColumn(0).setPreferredWidth(65);
        Tab2Table2.getColumnModel().getColumn(1).setPreferredWidth(1600);
        Tab2Table2.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        head=Tab2Table2.getTableHeader();
        head.setBackground(new Color(30, 39, 46));
        head.setForeground(new Color(255,165,0));
        head.setFont(new Font("Times New Roman",Font.BOLD,20));
        scrolltab2_RR02=new JScrollPane(Tab2Table2);
        scrolltab2_RR02.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        scrolltab2_RR02.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scrolltab2_RR02.setBounds(20,50,450,170);
        
        //Tab2Table2(RR103)
        String[][] Data103={{"5.50","|Gandhipuram:5.50|---|Women's Polytechnic:6.00|---|Mani school:6.15|----|Lakshmi Mills:6.35|----|Nava India:6.40|----|S.O Bunk:6.55|-----|Krishnammal College:7.10|----|Fun Mall:7.25|----|Hopes:7.35|----|Peelamedu:7.50|"},{"9.50","|Gandhipuram:9.50|---|Women's Polytechnic:10.00|---|Mani school:10.15|----|Lakshmi Mills:10.35|----|Nava India:10.40|----|S.O Bunk:10.55|-----|Krishnammal College:11.10|----|Fun Mall:11.25|----|Hopes:11.35|----|Peelamedu:11.50|"},{"1.50","|Gandhipuram:1.50|---|Women's Polytechnic:2.00|---|Mani school:2.15|----|Lakshmi Mills:2.35|----|Nava India:2.40|----|S.O Bunk:2.55|-----|Krishnammal College:3.10|----|Fun Mall:3.25|----|Hopes:3.35|----|Peelamedu:3.50|"},{"5.20","|Gandhipuram:5.50|---|Women's Polytechnic:6.00|---|Mani school:6.15|----|Lakshmi Mills:6.35|----|Nava India:6.40|----|S.O Bunk:6.55|-----|Krishnammal College:7.10|----|Fun Mall:7.25|----|Hopes:7.35|----|Peelamedu:7.50|"}};
        Tab2Table3=new JTable(Data103,columntab2);
        Tab2Table3.setFont(new Font("Times New Roman",Font.BOLD,15));
        Tab2Table3.setForeground(new Color(0,0,0));
        Tab2Table3.setBackground(new Color(154,205,50));
        Tab2Table3.setRowHeight(30);
        Tab2Table3.setDefaultEditor(Object.class, null);
        Tab2Table3.getTableHeader().setReorderingAllowed(false);
        Tab2Table3.getColumnModel().getColumn(0).setPreferredWidth(65);
        Tab2Table3.getColumnModel().getColumn(1).setPreferredWidth(1600);
        Tab2Table3.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        head=Tab2Table3.getTableHeader();
        head.setBackground(new Color(30, 39, 46));
        head.setForeground(new Color(255,165,0));
        head.setFont(new Font("Times New Roman",Font.BOLD,20));
        scrolltab2_RR03=new JScrollPane(Tab2Table3);
        scrolltab2_RR03.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        scrolltab2_RR03.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scrolltab2_RR03.setBounds(20,50,450,170);
        
        
        
        //HeaderPanel Elements
        Header_panel.add(Header_label);
        Header_panel.add(HomeButton);
        
        //Container Elements
        C.add(Header_panel);
        C.add(TravelLog_button);
        C.add(Boarding_Label);
        C.add(combo_board);
        C.add(Destination_Label);
        C.add(combo_Destination);
        C.add(BusId_Label);
        C.add(combo_BusId);
        C.add(Fare_Button);
        C.add(Amount_Label);
        C.add(Amount_textField);
        C.add(Pay_button);

        //FooterPanel elements
        Footer_panel.add(Checkwait_Label);
        C.add(Footer_panel);
    }
    int click = 0;
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==HomeButton)
        {
            this.dispose();
            Homepage obj=new Homepage(Current_user,admin_or_not);
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
        if (e.getSource().equals(Fare_Button)) {
            
            int Amount_value = 0;
            if (combo_board.getSelectedIndex() == combo_Destination.getSelectedIndex()) {
                JOptionPane.showMessageDialog(this,"Invalid Location");
            } 
            else 
            {
                if (combo_board.getSelectedIndex() < combo_Destination.getSelectedIndex()) {
                    Amount_value = combo_Destination.getSelectedIndex() - combo_board.getSelectedIndex();
                } else {
                    //Amount_value = combo_board.getSelectedIndex() - combo_Destination.getSelectedIndex();
                    JOptionPane.showMessageDialog(this, "Invalid Location Provided");
                }
            }
            Amount_textField.setText(String.valueOf(Amount_value * 5));
            click = 1;
        }
        if (e.getSource().equals(Pay_button)) {
            int success = 0;
            if (click == 0 || Amount_textField.getText().isEmpty()) {
                JOptionPane.showMessageDialog(this, "Invalid Please Calculate Fare");
            }
            if (!(Amount_textField.getText().equals('0')) && click == 1) {
                success = 1;
                
                try{
                    Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/bootcamp_project?zeroDateTimeBehavior=convertToNull", "root", "");
                    String sel="select * from wallet where Username='"+Current_user+"'";
                    Statement st1=con.createStatement();
                    ResultSet rs=st1.executeQuery(sel);
                    if(rs.next()){
                       curren_bal=Integer.parseInt(rs.getString(2));
                    }
                    if(curren_bal>=Integer.parseInt(Amount_textField.getText())){
                        try {
                
                            Statement st = con.createStatement();
                            String Username=Current_user;
                            String Bus_Ids = combo_BusId.getSelectedItem().toString();
                            String Boarding_loc = combo_board.getSelectedItem().toString();
                            String Destination_loc = combo_Destination.getSelectedItem().toString();
                            String Amount_val = Amount_textField.getText();
                            String query = "insert into travellog values(?,?,?,?,?,?)";
                            //to get current date
                            SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");  
                            Date date = new Date();
                            String curr_date=formatter.format(date);
                            System.out.println(curr_date);
                            PreparedStatement pstmt = con.prepareStatement(query);
                            pstmt.setString(1, Username);
                            pstmt.setString(2, Bus_Ids);
                            pstmt.setString(3, Boarding_loc);
                            pstmt.setString(4, Destination_loc);
                            pstmt.setString(5, Amount_val);
                            pstmt.setString(6, curr_date);
                            pstmt.executeUpdate();
                            String check="select * from date where Dates='"+curr_date+"'";
                            Statement st2=con.createStatement();
                            ResultSet d=st2.executeQuery(check);
                            if(d.next()){
                                System.out.println("yes");
                            }
                            else{
                                System.out.println("no");
                                String in="insert into date values('"+curr_date+"')";
                                Statement st3=con.createStatement();
                                st3.executeUpdate(in);
                            }
                            int up=curren_bal-Integer.parseInt(Amount_val);
                            String query_up="UPDATE wallet set Wallet_Amount= '"+up+"' where Username= '"+Current_user+"' ";
                            st1.executeUpdate(query_up);
                            con.close();
                            JOptionPane.showMessageDialog(this, "Payment Successfull");
                            Amount_textField.setText("");
                        } catch (Exception ex) {
                            System.out.println("---------->  " + ex);
                        }
                        
                          click = 0;
                    }
                    else{
                        JOptionPane.showMessageDialog(null,"Your balance is low recharge and proceed further!");
                        Amount_textField.setText("");
                    }
                }
                catch(Exception exp){}
                
            }
            if (Amount_textField.getText().equals('0') ) {
                JOptionPane.showMessageDialog(this, "Please Provide Correct Location");
            }
        }
        if (e.getSource().equals(TravelLog_button)) {
            this.dispose();
            Travellogframe t = new Travellogframe(Current_user,admin_or_not);
            Image icon = Toolkit.getDefaultToolkit().getImage("D:/busicon.png");    
            t.setIconImage(icon);
            t.setBounds(400, 10, 600, 700);
            t.setTitle("TravelLog");
            t.setResizable(false);
            t.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            t.setVisible(true);
        }
        if(e.getSource().equals(Check_Button))
        {
        if(combo_Tab1BusId.getSelectedItem().equals("RR101"))
          {
              Tab1Text.setText("                 5.00|9.00|1.00|5.00");
          }
        if(combo_Tab1BusId.getSelectedItem()=="RR102")
          {
              Tab1Text.setText("                 5.20|9.20|1.20|5.20");
          } 
        if(combo_Tab1BusId.getSelectedItem()=="RR103")
          {
              Tab1Text.setText("                 5.50|9.50|1.50|5.50");
          }
        }
        if(e.getSource().equals(Check_ButtonTab2))
        {
            if(combo_Tab2BusId.getSelectedItem()=="RR101")
            {
                Tab2.remove(scrolltab2_RR02);
                Tab2.remove(scrolltab2_RR03);
                Tab2Table1.setGridColor(new Color(128,0,0));
                Tab2.add(scrolltab2_RR01);
            }
            if(combo_Tab2BusId.getSelectedItem()=="RR102")
            {
                Tab2.remove(scrolltab2_RR01);
                Tab2.remove(scrolltab2_RR03);
                Tab2Table2.setGridColor(new Color(128,0,0));
                Tab2.add(scrolltab2_RR02);
            }
            if(combo_Tab2BusId.getSelectedItem()=="RR103")
            {
                Tab2.remove(scrolltab2_RR01);
                Tab2.remove(scrolltab2_RR02);
                Tab2Table3.setGridColor(new Color(128,0,0));
                Tab2.add(scrolltab2_RR03);
            }
        }
        if(e.getSource()==combo_BusId){
            String item = (String)combo_BusId.getSelectedItem();
            Object o = subItems.get( item );

            if (o == null)
            {
                combo_board.setModel( new DefaultComboBoxModel() );
                combo_Destination.setModel( new DefaultComboBoxModel() );
            }
            else
            {
               combo_board.setModel( new DefaultComboBoxModel( (String[])o ) );
               combo_Destination.setModel( new DefaultComboBoxModel( (String[])o ) );
            }
        }
    }

}
