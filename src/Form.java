import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

//creates a form with multiple text inputs for the user to input contact info and car info.
//info is sent to the mysql server and inputed into the correct columns and tables when the user hits submit.

public class Form extends JFrame {
    private static final long serialVersionUID = 1L;

    //Declaring  varialbes and objects
    protected JPanel panel;
    protected JTextField fname;
    protected JTextField lname;
    protected JTextField email;
    protected JTextField phone;
    protected JTextField home;
    protected JButton submit;

    protected JLabel lblfname;
    protected JLabel lbllname;
    protected JLabel lblemail;
    protected JLabel lblphone;
    protected JLabel lblhome;
    protected JLabel lblteach;
    protected JLabel lblbool;

    public String name;
    public String lastname;
    public String inemail;
    public String inphone;
    public String inhome;

    protected JTextField year;
    protected JTextField make;
    protected JTextField model;
    protected JTextField vin;
    protected JTextField licence;
    protected JTextField odoread;
    protected JTextField teach;
    protected JCheckBox bool;

    protected JLabel lblyear;
    protected JLabel lblmake;
    protected JLabel lblmodel;
    protected JLabel lblvin;
    protected JLabel lbllicence;
    protected JLabel lblodoread;

    public String years;
    public String makes;
    public String models;
    public String vins;
    public String licences;
    public String odoreads;

    //creates the form, and defines variable for each text input
    public void Data() 
    {
    	try 
    	{
            //here you can put the selected theme class name in JTattoo
    		UIManager.setLookAndFeel("com.jtattoo.plaf.mint.MintLookAndFeel");

        }
    	catch (ClassNotFoundException ex) 
    	{
        	System.out.println("error");
        }
    	catch (InstantiationException ex)
    	{
        	System.out.println("error");
        }
    	catch (IllegalAccessException ex) 
    	{
        	System.out.println("error");
        } 
    	catch (javax.swing.UnsupportedLookAndFeelException ex) {
            System.out.println("error");
        }
    	
        Font font = new Font("serif", Font.PLAIN, 18);

        panel = new JPanel();
        fname = new JTextField();
        lname = new JTextField();
        email = new JTextField();
        phone = new JTextField();
        home = new JTextField();
        submit = new JButton("Submit");

        year = new JTextField();
        make = new JTextField();
        model = new JTextField();
        vin = new JTextField();
        licence = new JTextField();
        odoread = new JTextField();
        
        teach = new JTextField();
        bool = new JCheckBox();

        Submit sbmt = new Submit();
        odoread.addActionListener(sbmt);
        submit.addActionListener(sbmt);

        lblfname = new JLabel("First Name:");
        lbllname = new JLabel("Last Name:");
        lblemail = new JLabel("Email Address:");
        lblphone = new JLabel("Phone Number:");
        lblhome = new JLabel("Home Address:");

        lblyear = new JLabel("Car Year:");
        lblmake = new JLabel("Car make:");
        lblmodel = new JLabel("Car Model:");
        lblvin = new JLabel("VIN #:");
        lbllicence = new JLabel("Licence Plate:");
        lblodoread = new JLabel("Odometer Reading:");
        
        lblbool = new JLabel("Dispose of Parts?");
        lblteach = new JLabel("Teachers Name");

        setSize(600, 800);
        setLocation(500, 280);
        panel.setLayout(null);

        fname.setBounds(70, 40, 225, 30);
        lname.setBounds(310, 40, 225, 30);
        email.setBounds(70, 100, 465, 30);
        phone.setBounds(70, 160, 465, 30);
        home.setBounds(70, 220, 465, 30);

        year.setBounds(70, 280, 225, 30);
        vin.setBounds(310, 280, 225, 30);
        make.setBounds(70, 340, 465, 30);
        model.setBounds(70, 400, 465, 30);
        licence.setBounds(70, 460, 225, 30);
        odoread.setBounds(310, 460, 225, 30);
        
        teach.setBounds(70, 520, 225, 30);
        bool.setBounds(450, 520, 225, 30);

        submit.setBounds(200, 620, 200, 40);

        fname.setFont(font);
        lname.setFont(font);
        email.setFont(font);
        phone.setFont(font);
        home.setFont(font);

        year.setFont(font);
        make.setFont(font);
        model.setFont(font);
        vin.setFont(font);
        licence.setFont(font);
        odoread.setFont(font);
        
        teach.setFont(font);

        lblyear.setFont(font);
        lblmake.setFont(font);
        lblmodel.setFont(font);
        lblvin.setFont(font);
        lbllicence.setFont(font);
        lblodoread.setFont(font);

        lblbool.setFont(font);
        lblteach.setFont(font);
        
        lblfname.setBounds(70, 5, 200, 40);
        lbllname.setBounds(310, 5, 200, 40);
        lblemail.setBounds(70, 65, 200, 40);
        lblphone.setBounds(70, 125, 200, 40);
        lblhome.setBounds(70, 185, 200, 40);

        lblyear.setBounds(70, 245, 200, 40);
        lblmake.setBounds(70, 305, 200, 40);
        lblmodel.setBounds(70, 365, 200, 40);
        lblvin.setBounds(310, 245, 200, 40);
        lbllicence.setBounds(70, 425, 200, 40);
        lblodoread.setBounds(310, 425, 200, 40);
        
        lblteach.setBounds(70, 485, 200, 40);
        lblbool.setBounds(310, 518, 225, 30);

        lblfname.setFont(font);
        lbllname.setFont(font);
        lblemail.setFont(font);
        lblphone.setFont(font);
        lblhome.setFont(font);

        panel.add(bool);
        panel.add(teach);
        
        panel.add(fname);
        panel.add(lname);
        panel.add(email);
        panel.add(phone);
        panel.add(home);
        panel.add(submit);

        panel.add(year);
        panel.add(make);
        panel.add(model);
        panel.add(vin);
        panel.add(licence);
        panel.add(odoread);

        panel.add(lblteach);
        panel.add(lblbool);
        
        panel.add(lblyear);
        panel.add(lblmake);
        panel.add(lblmodel);
        panel.add(lblvin);
        panel.add(lbllicence);
        panel.add(lblodoread);

        panel.add(lblfname);
        panel.add(lbllname);
        panel.add(lblemail);
        panel.add(lblphone);
        panel.add(lblhome);

        getContentPane().add(panel);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setVisible(true);
    }

    //"listens" for an action such as the submit button being clicked or the user hitting enter
    //send information to mysql when an action is performed.
    private class Submit implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
        {
            MysqlCon sql = new MysqlCon();

            Connection con;

            name = fname.getText();
            lastname = lname.getText();
            inemail = email.getText();
            inphone = phone.getText();
            inhome = home.getText();

            years = year.getText();
            makes = make.getText();
            models = model.getText();
            vins = vin.getText();
            licences = licence.getText();
            odoreads = odoread.getText();

            try 
            {
            	Boolean bol = bool.isSelected();
                String teacher = teach.getText();
                
                String customersql = "INSERT INTO `info_db`.`info` (`First Name`, `Last Name`, `Email Address`, `Phone Number`, `Home Address`) VALUES (?, ?, ?, ?, ?)";
                String carsql = "INSERT INTO `info_db`.`car-info` (`Last Name`, `Year`, `Make`, `Model`, `VIN #`, `License Plate`, `odometer reading on intake`) VALUES (?, ?, ?, ?, ?, ?, ?);";
                String detailsql =  "INSERT INTO `info_db`.`details` (`Last Name`, `Return Parts`, `Teachers Name`, `Status`, `Progress`) VALUES (?, ?, ?, ?, ?);";

                con = DriverManager.getConnection(sql.url, sql.user, sql.password);
                PreparedStatement pstmt = con.prepareStatement(customersql);
                pstmt.setString(1, name);
                pstmt.setString(2, lastname);
                pstmt.setString(3, inemail);
                pstmt.setString(4, inphone);
                pstmt.setString(5, inhome);
                pstmt.executeUpdate();

                PreparedStatement prestat = con.prepareStatement(carsql);
                prestat.setString(1, lastname);
                prestat.setString(2, years);
                prestat.setString(3, makes);
                prestat.setString(4, models);
                prestat.setString(5, vins);
                prestat.setString(6, licences);
                prestat.setString(7, odoreads);
                prestat.executeUpdate();

                PreparedStatement prest = con.prepareStatement(detailsql);
                prest.setString(1, lastname);
                prest.setBoolean(2, bol);//set bool from checkbox
                prest.setString(3, teacher);
                prest.setBoolean(4, false);
                prest.setBoolean(5, false);
                prest.executeUpdate();

                con.close();
                setVisible(false);
                dispose();
            } 
            catch (SQLException e1) 
            {
                e1.printStackTrace();
            }
        }
    }
    
    //main method
    public static void main(String[] args) 
    {
        Form frameTable = new Form();
        frameTable.Data();
    }

}