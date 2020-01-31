import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.UIManager;


public class Workflow {
	private MysqlCon sql = new MysqlCon();
	
	Connection con;
	JButton complete;
	JButton inprog;
	JButton bt;
	JButton bt2;
	JFrame frame;
	GridBagLayout gridbag;
	GridBagConstraints c;
	String idv;
	JTextArea txt;
	
	String teacher_name;
	String name;
	String name2;
	String phone;
	String address;
	String email;
	String date;
	String year;
	String vin;
	String make;
	String model;
	String license;
	String odometerin;
	String odometerout;
	String Lname;
	
	Boolean prog;
	Boolean return_parts;
	Boolean status;
	
	private Point location;
	
	public Workflow(String idval, Point loca)
	{
		idv = idval;
		
		txt = new JTextArea(30,37);
		
		teacher_name = null;
		name = null;
		name2 = null;
		phone = null;
		address = null;
		email = null;
		date = null;
		year = null;
		vin = null;
		make = null;
		model = null;
		license = null;
		odometerin = null;
		odometerout = null;
		return_parts = null;
		status = null;
		Lname = null;
		
		location = loca;
	}
	
	public String get_lname() 
	{
		try
		{
			sqlCon();
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery("SELECT * FROM `info_db`.info WHERE id = " + idv + ";");
			
			while(rs.next())
			{
				Lname = rs.getString(3);
			}
		}
		catch(SQLException el)
		{
			el.printStackTrace();
		}
		return Lname;
	}
	
	private void sqlCon() 
	{
		try
		{
			con = DriverManager.getConnection(sql.url, sql.user, sql.password);
		}
		catch (SQLException el)
		{
			System.out.println("Could not connect to database");
		}
	}
	
	public void style()
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
		catch (javax.swing.UnsupportedLookAndFeelException ex) 
		{
	        System.out.println("error");
	    }
	}
	
	public String set_status(Boolean status, Boolean progress)
	{
		if(status == true)
		{
			return "Complete";
		}
		else if(progress == true)
		{
			return "In-Progress";
		}
		else
		{
			return "Incomplete";
		}
	}
	
	public String set_return(Boolean parts)
	{
		if(parts == false)
		{
			return "No";
		}
		else
		{
			return "Yes";
		}
	}
	
	public void update_complete(Boolean status)
	{	
		try
		{
			sqlCon();
			PreparedStatement pre = con.prepareStatement("UPDATE `info_db`.details SET `Status` = ? WHERE `Last Name` = ?;"); 
			pre.setBoolean(1, status);
			pre.setString(2, get_lname());
			pre.executeUpdate();
		}
		catch(SQLException e)
		{
			System.out.println("Could not connect to database - 2");
		}
	}
	
	public void update_progress(Boolean status)
	{	
		try
		{
			sqlCon();
			PreparedStatement pre = con.prepareStatement("UPDATE `info_db`.details SET `Progress` = ? WHERE `Last Name` = ?;"); 
			pre.setBoolean(1, status);
			pre.setString(2, get_lname());
			pre.executeUpdate();
		}
		catch(SQLException e)
		{
			System.out.println("Could not connect to database - 2");
		}
	}
	
	private void insert_data()
	{
		try
		{
			sqlCon();
			String customer = "SELECT * FROM `info_db`.`info` WHERE id = " + idv + ";";
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery(customer);
			
			//info table
			while(rs.next())
			{
				name = rs.getString(2);
				name2= rs.getString(3);
				phone = rs.getString(5);
				address = rs.getString(6);
				email = rs.getString(4);
				date = rs.getString(7);
			}
			
			String car = "SELECT * FROM `info_db`.`car-info` WHERE `Last Name` = ?;";
			String details = "SELECT * FROM `info_db`.`details` WHERE `Last Name` = ?;";

			PreparedStatement psts = con.prepareStatement(car);
			psts.setString(1, name2);
			ResultSet res = psts.executeQuery();
			//car-info table
			while(res.next())
			{
				year = res.getString(3);
				vin = res.getString(6);
				make = res.getString(4);
				model = res.getString(5);
				license = res.getString(7);
				odometerin = res.getString(8);
				odometerout = res.getString(9);
			}
			
			psts = con.prepareStatement(details);
			psts.setString(1, name2);
			ResultSet resu = psts.executeQuery();
			//details table
			while(resu.next())
			{
				teacher_name = resu.getString(4);
				return_parts = resu.getBoolean(3);
				status = resu.getBoolean(5);
				prog = resu.getBoolean(6);
			}
		}
		catch(SQLException el)
		{
			el.printStackTrace();
		}
	}
	
	public void draw()
	{
		style();

		Click click = new Click();
		
		frame = new JFrame();
		
		JPanel panel = new JPanel(new GridBagLayout());
		JPanel panel2 = new JPanel(new GridBagLayout());
	
		GridBagConstraints c = new GridBagConstraints();
		Font font = new Font("serif", Font.BOLD, 18);
		
		JLabel title = new JLabel("DETAILS: " + get_lname());
		c.anchor = GridBagConstraints.FIRST_LINE_START;
        c.fill = GridBagConstraints.BOTH;
        c.gridx = 0;
	    c.gridy = 0;
	    title.setFont(font);
		panel.add(title,c);
		
		JTextArea txt = new JTextArea(30,35);
		c.anchor = GridBagConstraints.FIRST_LINE_START;
		c.gridx = 0;
		c.gridy = 1;
		panel.add(txt,c);
		
		complete = new JButton("Complete");
		complete.setPreferredSize(new Dimension(150, 50));
		c.gridx = 5;
	    c.gridy = 0;
	    c.insets = new Insets (0,0,65,10);
		complete.addActionListener(click);
	    panel2.add(complete,c);
		
		bt = new JButton("In-Progress");
		c.fill = GridBagConstraints.BOTH;
		bt.setPreferredSize(new Dimension(150, 50));
		c.gridx = 5;
	    c.gridy = 1;
	    bt.addActionListener(click);
	 	panel2.add(bt,c);
				
		bt2 = new JButton("In-Complete");
		bt2.setPreferredSize(new Dimension(150, 50));
		c.gridx = 5;
	    c.gridy = 2;
	    bt2.addActionListener(click);
		panel2.add(bt2,c);
		
		inprog = new JButton("Close");
		inprog.setPreferredSize(new Dimension(150, 50));
		c.gridx = 5;
	    c.gridy = 3;
	    inprog.addActionListener(click);
	    panel2.add(inprog,c);
	
	    insert_data();
	    txt.append("Id: " + idv + "\n");
		txt.append("First Name: " + name + "\n");
		txt.append("Last Name: " + name2 + "\n");
		txt.append("Phone Number: " + phone + "\n");
		txt.append("Home Address: " + address + "\n");
		txt.append("Email Address: " + email + "\n");
		txt.append("Car Year: " + year + "\n");
		txt.append("Car Make: " + make + "\n");
		txt.append("Car Model: " + model + "\n");
		txt.append("VIN Number: " + vin + "\n");
		txt.append("License Plate: " + license + "\n");
		txt.append("Odometer Reading on Intake: " + odometerin + "\n");
		txt.append("Odometer Reading on Return: " + odometerout + "\n");
		txt.append("Date Brought In: " + date + "\n");
		txt.append("Teachers Name: " + teacher_name + "\n");
		txt.append("Return Parts?: " + set_return(return_parts) + "\n");
		txt.append("Status: " + set_status(status, prog) + "\n");
		
		txt.setEditable(false);
	
		frame.add(panel);
		frame.add(panel2,BorderLayout.LINE_END);
		frame.setSize(600, 600);
		frame.setLocation(location);
		frame.setResizable(false);
		frame.setVisible(true);
	}

	private class Click implements ActionListener
	{
		public void actionPerformed(ActionEvent e)
		{	
			location = frame.getLocationOnScreen();
			if(e.getSource() == complete)
			{
				frame.dispose();
				update_complete(true);
				update_progress(false);
				draw();
			}
			if(e.getSource() == bt)
			{
				frame.dispose();
				update_complete(false);
				update_progress(true);
				draw();
			}
			if(e.getSource() == bt2)
			{
				frame.dispose();
				update_complete(false);
				update_progress(false);
				draw();
			}
			if(e.getSource() == inprog)
			{
				frame.dispose();
			}
		}
	}
}
