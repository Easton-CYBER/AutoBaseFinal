import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class Workflow {
	private MysqlCon sql = new MysqlCon();
	
	JButton complete;
	JButton inprog;
	JFrame frame;
	
	private void sqlCon() 
	{
		try
		{
			Connection con = DriverManager.getConnection(sql.url, sql.user, sql.password);
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
	
	public String set_status(String status)
	{
		return status;
	}
	
	public void draw(String stat)
	{
		style();
		
		Click click = new Click();
		
		frame = new JFrame();
		JPanel panel = new JPanel();
		complete = new JButton("Complete");
		inprog = new JButton("In-Progress");
		JTextArea txt = new JTextArea(set_status(stat));
		
		complete.addActionListener(click);
		
		panel.add(txt);
		panel.add(complete);
		panel.add(inprog);
		
		frame.add(panel);
		frame.setSize(600, 600);
		frame.setLocation(500, 280);
		frame.setVisible(true);
	}
	
	private class Click implements ActionListener
	{
		public void actionPerformed(ActionEvent e)
		{	
			if(e.getSource() == complete)
			{
				frame.dispose();
				draw("complete");
			}
		}
	}
}
