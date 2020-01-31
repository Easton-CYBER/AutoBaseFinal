import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Point;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;

class Table {

	MysqlCon sql = new MysqlCon();

	DefaultTableModel dtm;

	public Connection con;
	public JTable tbl;

	private String[] index;
	private JFrame jf;
	private JFrame reptfr;

	private JLabel label1;
	private String fname;
	private String email;
	private String phone;
	private String home;
	
	public String lname;
	public String clname;
	public String IdVal;
	public String getEmail;

	private JMenuBar menubar;
	private JMenu menu;
	private JMenuItem md;
	private JMenuItem ms;
	private JMenuItem save;
	private JMenuItem re;
	private JMenuItem send;
	private String emailadd;
	//private JMenuItem finish;
	private JMenuItem details;
	private JMenuItem fini;

	//private JMenuItem car;
	//private JMenu tbls; 

	private String upname;
	private String uplname;
	private String upemail;
	private String upphone;
	private String uphome;
	private String search_string;

	int id;
	int row;
	int inparts;
	int inlabour;
	int inshop;
	int ingarbo;
	int partsint;
	int labourint;
	int shopint;
	int disposalint;
	int total;
	int numunit;

	protected JPanel panel;
	protected JPanel search_pan;
    protected JButton fsubmit;
    protected JTextField part;
    protected JTextField txtlb;
    protected JTextField txtshp;
    protected JTextField txtdisp;
    protected JTextField unit;
    protected JLabel lblparts;
    protected JLabel lbllb;
    protected JLabel lblshp;
    protected JLabel lbldisp;
    protected JLabel lblunit;
    
    protected JTextField search;
    
    Point location;

	public Table() 
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
	
		location = new Point();
	}

	
	public void sqlCon() 
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

	public void FetchTable() 
	{
		try 
		{
			sqlCon();

			index = new String[] { "ID", "First Name", "Last Name", "Email Address", "Phone Number", "Home Address" };
			label1 = new JLabel("Customer Information");
			jf = new JFrame("Autobase: Information");

			Clicklistener click = new Clicklistener();

			menubar = new JMenuBar();
			menu = new JMenu("Edit");
			//tbls = new JMenu("Tables");
			menubar.add(menu);
			//menubar.add(tbls);

			md = new JMenuItem("Delete");
			md.addActionListener(click);
			menu.add(md);

			ms = new JMenuItem("Add Entry");
			ms.addActionListener(click);
			menu.add(ms);

			save = new JMenuItem("Save Changes");
			save.addActionListener(click);
			menu.add(save);
			
			fini = new JMenuItem("Repair Finished");
			fini.addActionListener(click);
			menu.add(fini);

			re = new JMenuItem("Refresh");
			re.addActionListener(click);
			menu.add(re);

			send = new JMenuItem("Send Receipt");
			send.addActionListener(click);
			menu.add(send);
			
			details = new JMenuItem("Status");
			details.addActionListener(click);
			menu.add(details);
			
			//car = new JMenuItem("Car Info");
			//car.addActionListener(click);
			//tbls.add(car);

			//finish = new JMenuItem("Finished");
			//finish.addActionListener(click);
			//tbls.add(finish);

			Statement stmnt = con.createStatement();
			ResultSet rs = stmnt.executeQuery("select * from info;");

			tbl = new JTable();
			dtm = new DefaultTableModel(0, 0);
			dtm.setColumnIdentifiers(index);
			tbl.setModel(dtm);

			TableColumnModel colmod = tbl.getColumnModel();
			colmod.getColumn(0).setPreferredWidth(40);

			while (rs.next()) 
			{
				id = rs.getInt(1);
				fname = rs.getString(2);
				lname = rs.getString(3);
				email = rs.getString(4);
				phone = rs.getString(5);
				home = rs.getString(6);

				dtm.insertRow(tbl.getRowCount(), new Object[] { id, fname, lname, email, phone, home });
			}

			tbl.setBounds(30, 40, 300, 400);
			tbl.getTableHeader().setReorderingAllowed(false);
			tbl.getTableHeader().setResizingAllowed(false);
			
			JScrollPane scrollP = new JScrollPane(tbl);
			scrollP.getVerticalScrollBar().setPreferredSize(new Dimension(20, 0));
			scrollP.getHorizontalScrollBar().setPreferredSize(new Dimension(0, 20));
			
			DefaultTableCellRenderer tableRenderer = new DefaultTableCellRenderer();
			tableRenderer.setHorizontalAlignment(JLabel.LEFT);
			tbl.setDefaultRenderer(Object.class, tableRenderer);

			scrollP.setBorder(BorderFactory.createEmptyBorder());
			scrollP.setPreferredSize(new Dimension(1020, 600));
			
			label1.setFont(new Font("serif", Font.PLAIN, 20));
			
			search_pan = new JPanel();
			search = new JTextField(15);
			search.setText("search last name");
			search.addActionListener(click);
			search_pan.add(search);	
			
			jf.setLayout(new GridBagLayout());
			GridBagConstraints gbc = new GridBagConstraints();
			
			gbc.gridwidth = GridBagConstraints.REMAINDER;
			
			gbc.weightx = 0;
			gbc.gridy= 0;
			gbc.gridx= 0;
			gbc.anchor = GridBagConstraints.PAGE_START;
			jf.add(label1, gbc);
			
			gbc.anchor = GridBagConstraints.FIRST_LINE_END;
			jf.add(search_pan, gbc);
			
			//gbc.fill = GridBagConstraints.BOTH;
			//gbc.gridwidth = GridBagConstraints.REMAINDER;
			
			jf.add(scrollP);
			jf.setJMenuBar(menubar);
			jf.setResizable(true);
			jf.setLocation(location);
			jf.setSize(1035, 720);
			jf.setVisible(true);
			jf.setResizable(false);

			clname = lname;
			con.close();
		} 
		catch (SQLException el) 
		{
			System.out.println("could not print table");
		}
	}
	
	private void search_table(String text)
	{
		jf.setVisible(false);
		jf.dispose();
		String sqlq = "SELECT * FROM info_db.info WHERE `Last Name` LIKE  ?;";
			
		try 
		{
			sqlCon();

			index = new String[] { "ID", "First Name", "Last Name", "Email Address", "Phone Number", "Home Address" };
			label1 = new JLabel("Customer Information");
			jf = new JFrame("Autobase: Information");

			Clicklistener click = new Clicklistener();

			menubar = new JMenuBar();
			menu = new JMenu("Edit");
			//tbls = new JMenu("Tables");
			menubar.add(menu);
			//menubar.add(tbls);

			md = new JMenuItem("Delete");
			md.addActionListener(click);
			menu.add(md);

			ms = new JMenuItem("Add Entry");
			ms.addActionListener(click);
			menu.add(ms);

			save = new JMenuItem("Save Changes");
			save.addActionListener(click);
			menu.add(save);
			
			fini = new JMenuItem("Repair Finished");
			fini.addActionListener(click);
			menu.add(fini);

			re = new JMenuItem("Refresh");
			re.addActionListener(click);
			menu.add(re);

			send = new JMenuItem("Send Receipt");
			send.addActionListener(click);
			menu.add(send);
			
			details = new JMenuItem("Status");
			details.addActionListener(click);
			menu.add(details);

			//car = new JMenuItem("Car Info");
			//car.addActionListener(click);
			//tbls.add(car);

			//finish = new JMenuItem("Finished");
			//finish.addActionListener(click);
			//tbls.add(finish);

			PreparedStatement prestat = con.prepareStatement(sqlq);
			prestat.setString(1, "%" + text + "%");
					
			ResultSet rs = prestat.executeQuery();

			tbl = new JTable();
			dtm = new DefaultTableModel(0, 0);
			dtm.setColumnIdentifiers(index);
			tbl.setModel(dtm);

			TableColumnModel colmod = tbl.getColumnModel();
			colmod.getColumn(0).setPreferredWidth(40);

			while (rs.next()) 
			{
				id = rs.getInt(1);
				fname = rs.getString(2);
				lname = rs.getString(3);
				email = rs.getString(4);
				phone = rs.getString(5);
				home = rs.getString(6);

				dtm.insertRow(tbl.getRowCount(), new Object[] { id, fname, lname, email, phone, home });
			}

			tbl.setBounds(30, 40, 300, 400);
			tbl.getTableHeader().setReorderingAllowed(false);
			tbl.getTableHeader().setResizingAllowed(false);
				
			JScrollPane scrollP = new JScrollPane(tbl);
			scrollP.getVerticalScrollBar().setPreferredSize(new Dimension(20, 0));
			scrollP.getHorizontalScrollBar().setPreferredSize(new Dimension(0, 20));
				
			DefaultTableCellRenderer tableRenderer = new DefaultTableCellRenderer();
			tableRenderer.setHorizontalAlignment(JLabel.LEFT);
			tbl.setDefaultRenderer(Object.class, tableRenderer);

			scrollP.setBorder(BorderFactory.createEmptyBorder());
			scrollP.setPreferredSize(new Dimension(1020, 600));
				
			label1.setFont(new Font("serif", Font.PLAIN, 20));
				
			search_pan = new JPanel();
			search = new JTextField(15);
			search.setText(text);
			search.addActionListener(click);
			search_pan.add(search);	
				
			jf.setLayout(new GridBagLayout());
			GridBagConstraints gbc = new GridBagConstraints();
				
			gbc.gridwidth = GridBagConstraints.REMAINDER;
			
			gbc.weightx = 0;
			gbc.gridy= 0;
			gbc.gridx= 0;
			gbc.anchor = GridBagConstraints.PAGE_START;
			jf.add(label1, gbc);
				
			gbc.anchor = GridBagConstraints.FIRST_LINE_END;
			jf.add(search_pan, gbc);
			
			//gbc.fill = GridBagConstraints.BOTH;
			//gbc.gridwidth = GridBagConstraints.REMAINDER;
				
			jf.add(scrollP);
			jf.setJMenuBar(menubar);
			jf.setResizable(true);
			jf.setLocation(location);
			jf.setSize(1035, 720);
			jf.setVisible(true);
			jf.setResizable(false);

			clname = lname;

			con.close();
		} 
		catch (SQLException el) 
		{
			System.out.println("could not print table");
		}
	}



	String IdVal()
	{
		row = tbl.getSelectedRow();
		IdVal = tbl.getModel().getValueAt(row, 0).toString(); 

		return IdVal;
	}

	public void Wait()
	{
		try 
		{
			TimeUnit.MILLISECONDS.sleep(200);
			Refresh();
		} 
		catch (InterruptedException e) 
		{
			e.printStackTrace();
			System.out.println("could not refresh table");
		}
	}

	public void Del()
	{
		try 
		{
			sqlCon();
			IdVal();

			PreparedStatement pstm = con.prepareStatement("DELETE FROM info_db.info WHERE id = ?;");
			pstm.setString(1, IdVal);
			pstm.executeUpdate();
		
			PreparedStatement ptm = con.prepareStatement("DELETE FROM info_db.`car-info` WHERE `id-car-info` = ?;");
			ptm.setString(1, IdVal);
			ptm.executeUpdate();
			
			PreparedStatement pstmt = con.prepareStatement("DELETE FROM info_db.`details` WHERE `id` = ?;");
			pstmt.setString(1, IdVal);
			pstmt.executeUpdate();
			
			System.out.println("Deleted row " + IdVal);
			con.close();
		}
		catch(SQLException el)
		{
			System.out.println("an error occured: could not delete row " + IdVal);
		}
	}

	public void Refresh()
	{
		jf.setVisible(false);
		jf.dispose();

		FetchTable();
	}

	public void Add()
	{
		Form form = new Form();
		form.Data();
		Refresh();
	}

	public void Save()
	{
		try 
		{
			sqlCon();
			IdVal();

			upname = tbl.getModel().getValueAt(row, 1).toString();
			uplname = tbl.getModel().getValueAt(row, 2).toString();
			upemail = tbl.getModel().getValueAt(row, 3).toString();
			upphone = tbl.getModel().getValueAt(row, 4).toString();
			uphome = tbl.getModel().getValueAt(row, 5).toString();

			PreparedStatement pstmt = con.prepareStatement("UPDATE info_db.info SET `First Name` = ?, `Last Name` = ?, `Email Address` = ?, `Phone Number` = ?, `Home Address` = ? WHERE id = ?;");
			pstmt.setString(1, upname);
			pstmt.setString(2, uplname);
			pstmt.setString(3, upemail);
			pstmt.setString(4, upphone);
			pstmt.setString(5, uphome);
			pstmt.setString(6, IdVal);
			pstmt.executeUpdate();

			System.out.println("updated row " + IdVal);
			con.close();

			try
			{
				Wait();
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
		}
		catch(SQLException el)
		{
			System.out.println("could not save changes to row " + IdVal);
		}
	}

	public void Rept()
	{
		reptfr = new JFrame();
        Font font = new Font("serif", Font.PLAIN, 18);

        panel = new JPanel();
        fsubmit = new JButton("Submit");
        fsubmit.setFont(font);

        part = new JTextField();
        lblparts = new JLabel("Cost of Parts: ");
        part.setFont(font);
        lblparts.setFont(font);

        txtlb = new JTextField();
        lbllb = new JLabel("Cost of Labour: ");
        txtlb.setFont(font);
        lbllb.setFont(font);

        txtshp = new JTextField();
        lblshp = new JLabel("Cost of Shop Supplies: ");
        txtshp.setFont(font);
        lblshp.setFont(font);

        txtdisp = new JTextField();
        lbldisp = new JLabel("Cost of Waste Disposal: ");
        txtdisp.setFont(font);
        lbldisp.setFont(font);

        unit = new JTextField();
        lblunit = new JLabel("How many units were used? ");
        unit.setFont(font);
        lblunit.setFont(font);

        Clicklistener click  = new Clicklistener();
        fsubmit.addActionListener(click);
        unit.addActionListener(click);

        reptfr.setSize(600, 600);
        reptfr.setLocation(500, 280);
        panel.setLayout(null);

        part.setBounds(75, 40, 225, 30);
        txtlb.setBounds(300, 40, 225, 30);
        txtshp.setBounds(75, 100, 450, 30);
        txtdisp.setBounds(75, 160, 450, 30);
        unit.setBounds(75, 220, 450, 30);

        lblparts.setBounds(75, 5, 200, 40);
        lbllb.setBounds(300, 5, 200, 40);
        lblshp.setBounds(75, 65, 200, 40);
        lbldisp.setBounds(75, 125, 200, 40);
        lblunit.setBounds(75, 185, 200, 40);

        fsubmit.setBounds(200, 520, 200, 40);

        panel.add(part);
        panel.add(txtlb);
        panel.add(txtshp);
        panel.add(txtdisp);
        panel.add(unit);
        panel.add(lblparts);
        panel.add(lbllb);
        panel.add(lblshp);
        panel.add(lbldisp);
        panel.add(lblunit);
        panel.add(fsubmit);

        reptfr.getContentPane().add(panel);
        reptfr.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        reptfr.setVisible(true);
	}
	
	public String getEmail()
	{
		try
		{
            sqlCon();
			IdVal();

            Statement tmt = con.createStatement();
            ResultSet rs = tmt.executeQuery("select * from info WHERE id = " + IdVal + ";");
            while (rs.next())
            {
				emailadd = rs.getString(4);
			}
			System.out.println("connected to database");
        } 
		catch (SQLException e)
		{
        	System.out.println("could not pull from database");
		};
		getEmail = emailadd;
		return getEmail;
	}

	public void SendMail()
	{
        final String username = "nyazawa99@gmail.com";
        final String password = "niconiconii";

        Properties prop = new Properties();
		prop.put("mail.smtp.host", "smtp.gmail.com");
        prop.put("mail.smtp.port", "587");
        prop.put("mail.smtp.auth", "true");
        prop.put("mail.smtp.starttls.enable", "true");
        
		try 
		{
            String p = part.getText();
            String l = txtlb.getText();
            String s = txtshp.getText();
            String d = txtdisp.getText();
            String n = unit.getText();
            
            partsint = Integer.parseInt(p);
            labourint = Integer.parseInt(l);
            shopint = Integer.parseInt(s);
            disposalint = Integer.parseInt(d);
            numunit = Integer.parseInt(n);
        }
		catch(NumberFormatException e)
		{
            partsint = 0;
            labourint = 0;
            shopint = 0;
            disposalint = 0;
        }

		int inparts = partsint;
		int inlabour = labourint;
		int inshop = shopint;
		int ingarbo = disposalint;

		int intparts = numunit * partsint;
		int intlabour = numunit * labourint;
		int intshop = numunit * shopint;
		int intgarbo = numunit * disposalint;
		int total = intparts + intlabour + intshop + intgarbo;
		
		String teacher_name = null;
		String name = null;
		String name2 = null;
		String phone = null;
		String address = null;
		String email = null;
		String date = null;
		
		String id = IdVal();
		String year = null;
		String vin = null;
		String make = null;
		String model = null;
		String license = null;
		String odometerin = null;
		String odometerout = null;
		
		Boolean return_parts = null;
		Boolean parts_opp = true;
		
		try
		{
			sqlCon();
			String customer = "SELECT * FROM `info_db`.`info` WHERE id = " + IdVal + ";";
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
			}
			
			if(return_parts == true)
			{
				parts_opp = false;
			}
			
		}
		catch(SQLException el)
		{
			el.printStackTrace();
		}
        
        Session session = Session.getInstance(prop,
                new javax.mail.Authenticator()
        		{
                    protected PasswordAuthentication getPasswordAuthentication() 
                    {
                        return new PasswordAuthentication(username, password);
                    }
                });

        try 
        {
        	StringBuilder msg = new StringBuilder();
        	msg.append("<!DOCTYPE html>\r\n" + 
        			"<html>\r\n" + 
        			"    <body>\r\n" + 
        			"        <h><b>Customer Information:</b></h>\r\n" + 
        			"        <table>\r\n" + 
        			"            <tr>\r\n" + 
        			"                <td>Name(print): " + name + " " + name2 + "</td>\r\n" + 
        			"                <td>Phone: " + phone + "</td>\r\n" + 
        			"            </tr>\r\n" + 
        			"            <tr>\r\n" + 
        			"                <td>Address: " + address + "</td>\r\n" + 
        			"                <td></td>\r\n" + 
        			"            </tr>\r\n" + 
        			"            <tr>\r\n" + 
        			"                <td>Email: " + email + "</td>\r\n" + 
        			"                <td></td>\r\n" + 
        			"            </tr>\r\n" + 
        			"            <tr>\r\n" + 
        			"                <td>Invoice #: " + id + "</td>\r\n" + 
        			"                <td>Date of invoice: " + date + "</td>\r\n" + 
        			"            </tr>\r\n" + 
        			"        </table>\r\n" + 
        			"\r\n" + 
        			"        \r\n" + 
        			"        <h><b>Automobile repaired:</b></h>\r\n" + 
        			"        <table>\r\n" + 
        			"            <tr>\r\n" + 
        			"                <td>Year: " + year + "</td>\r\n" + 
        			"                <td>VIN #: " + vin + "</td>\r\n" + 
        			"            </tr>\r\n" + 
        			"            <tr>\r\n" + 
        			"                <td>Make: " + make + "</td>\r\n" + 
        			"                <td>License Plate: " + license + "</td>\r\n" + 
        			"            </tr>\r\n" + 
        			"            <tr>\r\n" + 
        			"                <td>Model: " + model + "</td>\r\n" + 
        			"                <td>Odometer reading on intake: " + odometerin + "</td>\r\n" + 
        			"            </tr>\r\n" + 
        			"            <tr>\r\n" + 
        			"                <td></td>\r\n" + 
        			"                <td>Odometer reading on return: "+ odometerout +"</td>\r\n" + 
        			"            </tr>\r\n" + 
        			"        </table>\r\n" + 
        			"\r\n" + 
        			"        <p>Detailed description of work performed, parts (including whether each part is a new part provided by the\r\n" + 
        			"            original equipment manufacturer, a new part not provided by the original equipment manufacturer, a used\r\n" + 
        			"            part or a reconditioned part) shop materials, environmental related, fees, disposal/recycling fees, etc.:\r\n" + 
        			"            <br></p>\r\n" + 
        			"\r\n" + 
        			"        <p style=\"display: inline;\">Date of authorization of work: </p>\r\n" + 
        			"        <p style=\"display: inline;\">Date of completion of work:</p>\r\n" + 
        			"        <p>Date of return of vehicle: TBD<br>\r\n" + 
        			"            Any parts removed in the course of work on or repairs to the automobile shall be (select one):<br>\r\n" + 
        			"            (A) returned to the undersigned: " + return_parts + "; or (B) disposed of by the Board: " + parts_opp + "<br>\r\n" + 
        			"            </p>\r\n" + 
        			"\r\n" + 
        			"            <table style = \"border-collapse: collapse; border: 1px solid black;\">\r\n" + 
        			"                <tr style = \"border: 1px solid black;\">\r\n" + 
        			"                    <td style = \"border: 1px solid black;\"><b></b></td>\r\n" + 
        			"                    <td style = \"border: 1px solid black;\"><b>Price Per Unit</b></td>\r\n" + 
        			"                    <td style = \"border: 1px solid black;\"><b>Line Total</b></td>\r\n" + 
        			"                </tr>\r\n" + 
        			"                <tr style = \"border: 1px solid black;\">\r\n" + 
        			"                    <td style = \"border: 1px solid black;\">Parts:</td>\r\n" + 
        			"                    <td style = \"border: 1px solid black;\">" + inparts + "</td>\r\n" + 
        			"                    <td style = \"border: 1px solid black;\">" + intparts + "</td>\r\n" + 
        			"                </tr>\r\n" + 
        			"                <tr style = \"border: 1px solid black;\">\r\n" + 
        			"                    <td style = \"border: 1px solid black;\">Labour:</td>\r\n" + 
        			"                    <td style = \"border: 1px solid black;\">" + inlabour + "</td>\r\n" + 
        			"                    <td style = \"border: 1px solid black;\">" + intlabour + "</td>\r\n" + 
        			"                </tr>\r\n" + 
        			"                <tr style = \"border: 1px solid black;\">\r\n" + 
        			"                    <td style = \"border: 1px solid black;\">Shop Supplies:</td>\r\n" + 
        			"                    <td style = \"border: 1px solid black;\">" + inshop + "</td>\r\n" + 
        			"                    <td style = \"border: 1px solid black;\">" + intshop + "</td>\r\n" + 
        			"                </tr>\r\n" + 
        			"                <tr style = \"border: 1px solid black;\">\r\n" + 
        			"                    <td style = \"border: 1px solid black;\">Recycling/Disposal Fee:</td>\r\n" + 
        			"                    <td style = \"border: 1px solid black;\">" + ingarbo + "</td>\r\n" + 
        			"                    <td style = \"border: 1px solid black;\">" + intgarbo + "</td>\r\n" + 
        			"                </tr>\r\n" + 
        			"                <tr style = \"border: 1px solid black;\">\r\n" + 
        			"                    <td style = \"border: 1px solid black;\">Total Estimated Cost:</td>\r\n" + 
        			"                    <td style = \"border: 1px solid black;\"></td>\r\n" + 
        			"                    <td style = \"border: 1px solid black;\">" + total + "</td>\r\n" + 
        			"                </tr>\r\n" + 
        			"            </table>\r\n" + 
        			"\r\n" + 
        			"        <p>If this Invoice is not paid, and/or if the automobile is not claimed within THIRTY (30) days after notice of completion\r\n" + 
        			"            of work, it and all property therein or thereon will be deemed abandoned and disposed of as considered appropriate\r\n" + 
        			"            in the sole discretion of the Board.<br></p>\r\n" + 
        			"        <p><b>WARRANTY<br></b></p>\r\n" + 
        			"        <p>Notwithstanding the STUDENT AUTOMOTIVE SERVICES RELEASE AND WAIVER OF LIABILITY AGREEMENT, for each new or\r\n" + 
        			"            reconditioned part or the labour required to install it:\r\n" + 
        			"            <ol>\r\n" + 
        			"                <li>the Board warrants the part and/or labour for a minimum of 90 days or 5,000 kilometers, whichever comes first,</li>\r\n" + 
        			"                <li>the warranty set out in subparagraph i. is provided under the Consumer Protection Act, 2002 (Ontario) and may\r\n" + 
        			"                    not be waived by the consumer, and</li>\r\n" + 
        			"                <li>the warranty set out in subparagraph i. does not apply to,\r\n" + 
        			"                    <ol>\r\n" + 
        			"                        <li>fluids, filters, lights, tires or batteries, or</li>\r\n" + 
        			"                        <li>a part that was not warranted by the manufacturer of the vehicle when the vehicle was sold as new.</li>\r\n" + 
        			"                    </ol>\r\n" + 
        			"                </li>\r\n" + 
        			"            </ol>    \r\n" + 
        			"        </p>\r\n" + 
        			"        <p><b>CONSUMER PROTECTION ACT, 2002<br></b></p>\r\n" + 
        			"        <p>The Consumer Protection Act, 2002 (Ontario) provides you with rights in relation to having a motor vehicle repaired.\r\n" + 
        			"            Among other things, you have a right to a written estimate. A repairer may not charge an amount that is more than ten\r\n" + 
        			"            (10) per cent above that estimate. If you waived your right to an estimate, the repairer must have your authorization of\r\n" + 
        			"            the maximum amount that you will pay for the repairs. The repairer may not charge more than the maximum amount\r\n" + 
        			"            you authorized. In either case, the repairer may not charge for any work you did not authorize.\r\n" + 
        			"            <br>\r\n" + 
        			"            If you have concerns about the work or repairs performed by the repairer or about your rights or duties under the\r\n" + 
        			"            Consumer Protection Act, 2002, (Ontario) you should contact the Ministry of Consumer and Business Services.\r\n" + 
        			"        </p>\r\n" + 
        			"\r\n" + 
        			"        <p><b>PEEL DISTRICT SCHOOL BOARD</b></p>\r\n" + 
        			"        <br>\r\n" + 
        			"        <p><b>TeacherÅfs Name: " + teacher_name + "</b>\r\n" + 
        			"            <br>I have authority to bind the Board.\r\n" + 
        			"            <br>E. & O. Excepted \r\n" + 
        			"        </p>\r\n" + 
        			"        <p><b>ACKNOWLEDGEMENT</b></p><br>\r\n" + 
        			"        <p>The foregoing is acknowledged and accepted by the undersigned.<br></p>\r\n" + 
        			"        <p>Signature of Registered Owner: _________________________________________<br></p>\r\n" + 
        			"        <p>Name (Print): " + name + " " + name2 + "<br></p>\r\n" + 
        			"    </body>\r\n" + 
        			"</html>");
			getEmail();
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress("nyazawa99@gmail.com"));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(emailadd));
            message.setSubject("Car Repair Receipt");
            message.setContent(msg.toString(), "text/html");
			
            Transport.send(message);

            System.out.println("Receipt Sent");

        } 
        catch (MessagingException e) 
        {
            System.out.println("could not send receipt");
        }
	}

	public class Clicklistener implements ActionListener 
	{
		public void actionPerformed(ActionEvent e)
		{
			location = jf.getLocationOnScreen();
			
			if(e.getSource() ==  md)
			{
				Del();
			}
			if(e.getSource() == ms)
			{
				Add();
			}
			if(e.getSource() == save)
			{
				Save();
			}
			if(e.getSource() == re)
			{
				Refresh();
			}
			if(e.getSource() == send)
			{
				Rept();
			}
			if(e.getSource() == unit)
			{
				SendMail();
				reptfr.dispose();
			}
			if(e.getSource() == fsubmit)
			{
				SendMail();
				reptfr.dispose();
				
			}
			if(e.getSource() == fini)
			{
				CarTable crtbl = new CarTable();
				crtbl.Form(IdVal());
			}
			if(e.getSource() == search)
			{

				search_string = search.getText();
				search_table(search_string);
			}
			if(e.getSource() == details)
			{
				Workflow stat = new Workflow(IdVal(), location);
				stat.draw();
			}
			/*if(e.getSource() == car)
			{
				jf.dispose();
				CarTable crtbl = new CarTable();
				crtbl.FetchTable();
			}
			if(e.getSource() == finish)
			{
				jf.dispose();
				FinTable finir = new FinTable();
				finir.FetchTable();
			}*/
		}
	}
}