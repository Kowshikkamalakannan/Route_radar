/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package LetsRide;
import javax.swing.*;  
import java.awt.*;  
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.sql.*;
import java.util.Vector;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
/**
 *
 * @author Sekar&Kowshik
 */
public class Travellogframe extends JFrame implements ItemListener,ActionListener {
    JLabel Header_label,title_image;
    JPanel Header_panel,Body_panel;
    JButton Back_button; 
    //JTextArea Travel_logs;
    Container C = getContentPane();
    JTable TravellogsTable;
    JScrollPane scroll;
    String[] ColumnNames={"BusId","Boarding Location","Destination","Amount"};
    String[] Table_data={"BusId","Date","Boarding_Loc","Destination","Amount"};
    JComboBox date_selector;
    DefaultTableModel Tabmodel;
    //JScrollPane Travel_logScroll;
    String current_user;
    int admin_or_not;
    Vector<String> vector_dates=new Vector<String>();
    JTableHeader head;
    Travellogframe(String current_user,int admin_or_not)
    {
        this.current_user=current_user;
        this.admin_or_not=admin_or_not;
        C.setBackground(new Color(30, 39, 46));
        C.setLayout(null);
        
        
        Header_panel=new JPanel();
        Header_panel.setBounds(0,0,600,60);
        Header_panel.setLayout(null);
        Header_panel.setBackground(new Color(255, 168, 1));
        Header_label=new JLabel("Route Radar");
        Header_label.setBounds(20,10,300,50);
        Header_label.setFont(new Font("Bauhaus 93",Font.BOLD,40));  
        Header_label.setForeground(new Color(255,255,255));
        
        Body_panel=new JPanel();
        Body_panel.setBounds(50,200,500,300);
        Body_panel.setLayout(null);
        Body_panel.setBackground(new Color(255,255,255));

        Back_button=new JButton("Back");
        Back_button.setBounds(480,23,100,25);
        Back_button.setFont(new Font("Times New Roman", Font.BOLD, 17));
        Back_button.setBackground(new Color(194, 54, 22));
        Back_button.setForeground(new Color(255,255,255));
        Back_button.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        Back_button.setBorderPainted(false);
        Back_button.addActionListener(this);
        
        
        
        vector_dates.add("--select--");
        vector_dates.add("All");
        try{
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/bootcamp_project?zeroDateTimeBehavior=convertToNull", "root", "");
            Statement  st = con.createStatement();
            String q1="select * from date";
            ResultSet rs= st.executeQuery(q1);
                while(rs.next()){
                  String d=rs.getString(1);
                  vector_dates.add(d);
                }
        }
        catch(Exception ex2){}
        date_selector=new JComboBox(vector_dates);
        date_selector.setBounds(250,180,140,30); 
        date_selector.addItemListener(this);
        date_selector.setBackground(new Color(255, 168, 1));
        date_selector.setForeground(new Color(255, 255, 255));
        date_selector.setFont(new Font("Times New Roman",Font.BOLD,16));
        C.add(date_selector);
       
        Tabmodel=new DefaultTableModel(){
            @Override
            public boolean isCellEditable(int row,int column){
                    return false;
            }
        };
        ImageIcon i1=new ImageIcon("D:/Travel.jpeg");
        title_image=new JLabel(i1);
        title_image.setBounds(180,90,203,46); 
         
        Tabmodel.setColumnIdentifiers(Table_data);
        TravellogsTable=new JTable();
        TravellogsTable.setBackground(new Color(154,205,50));
        TravellogsTable.setGridColor(Color.RED);
        TravellogsTable.setRowSelectionAllowed(false);
        TravellogsTable.setColumnSelectionAllowed(false);
        TravellogsTable.setModel(Tabmodel);
        TravellogsTable.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
        TravellogsTable.setFillsViewportHeight(true);
        TravellogsTable.setRowHeight(30);
        TravellogsTable.setForeground(new Color(0,0,0));
        TravellogsTable.setFont(new Font("Times New Roman",Font.BOLD,17));
        TravellogsTable.getColumnModel().getColumn(0).setPreferredWidth(60);
        TravellogsTable.getColumnModel().getColumn(1).setPreferredWidth(60);
        TravellogsTable.getColumnModel().getColumn(2).setPreferredWidth(120);
        TravellogsTable.getColumnModel().getColumn(3).setPreferredWidth(120);
        TravellogsTable.getColumnModel().getColumn(4).setPreferredWidth(40);
        head=TravellogsTable.getTableHeader();
        head.setBackground(new Color(30, 39, 46));
        head.setForeground(new Color(255,165,0));
        head.setFont(new Font("Times New Roman",Font.BOLD,20));
        scroll=new JScrollPane(TravellogsTable);
        scroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scroll.setBounds(20,250,560,150);        
        

        Header_panel.add(Header_label);
        C.add(Header_panel);
        Header_panel.add(Back_button);
        C.add(scroll);
        C.add(title_image);

    }
    
    @Override
    public void actionPerformed(ActionEvent e){ 
        if(e.getSource().equals(Back_button))
        {
        this.dispose();
        letsrideframe f=new letsrideframe(current_user,admin_or_not);
        Image icon = Toolkit.getDefaultToolkit().getImage("D:/busicon.png");    
        f.setIconImage(icon);
        f.setBounds(400,10,600,700);
        f.setTitle("RouteRadar");
        f.setResizable(false);
        f.setDefaultCloseOperation(3);
        f.setVisible(true); 
        }
    }
    
    
    @Override
    public void itemStateChanged(ItemEvent e){
        if(e.getSource()==date_selector){
            if(date_selector.getSelectedItem().equals("--select--")){
                Tabmodel.setRowCount(0);
            }
            else{
                try{
                    String Travellogs;
                Tabmodel.setRowCount(0);    
                Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/bootcamp_project?zeroDateTimeBehavior=convertToNull", "root", "");
                Statement  st = con.createStatement();
                if(date_selector.getSelectedItem().equals("All")){
                    Travellogs="select * from travellog where Username='"+current_user+"'";
                }
                else{
                    Travellogs="select * from travellog where Username='"+current_user+"' and Date='"+date_selector.getSelectedItem()+"'";
                }
                
                ResultSet rs= st.executeQuery(Travellogs);
                while(rs.next()){
                Table_data[0]=rs.getString("BusID");
                Table_data[1]=rs.getString("Date");
                Table_data[2]=rs.getString("Boarding_Loc");
                Table_data[3]=rs.getString("Destination");
                Table_data[4]=rs.getString("Amount");
                Tabmodel.addRow(Table_data);
                }
              }
               catch(Exception ex)
            {
                 System.out.println("---------->  "+ex);
            }
            }
            
        }
    }

}
