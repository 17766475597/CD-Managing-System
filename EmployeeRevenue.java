package system;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRootPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

public class EmployeeRevenue extends JFrame implements ActionListener{
	String url = "jdbc:sqlserver://localhost:1433; DatabaseName=dpcz";  
    String user = "sa";  
    String password = "123";  
    static String driverName = "com.microsoft.sqlserver.jdbc.SQLServerDriver";  
    int flag=1;
    Connection conn;  
    Statement st;  
    // 1、加载驱动  
    static {  
        try {  
            Class.forName(driverName);  
        } catch (ClassNotFoundException e) {  
            System.out.println("驱动加载失败");  
        }  
    }  
    // 2、创建连接对象  
    public  Connection getConnection() throws SQLException{  
        conn=DriverManager.getConnection(url,user,password);  
        return conn;  
    }  
	
	private String[] columnname = {"碟片名称","职工姓名","会员姓名","租借日期","归还日期","租赁金额","租借标志"};
	private String[] month = {null,"01","02","03","04","05","06","07","08","09","10","11","12"};
	private String[] day = {null,"01","02","03","04","05","06","07","08","09","10","11","12","13","14","15","16","17","18","19","20","21","22","23","24","25","26","27","28","29","30","31"};
	private JLabel l,l1,l2,l3,l4,l5,l6,l7;
	private JTextField t1,t4,t5,t6,t7;
	private JComboBox c2,c3;
	private JButton b1;
	private JTable table;
	private String[][] r = {};
	private Object[][] obj = new Object[20][7]; 
	DefaultTableModel model=new DefaultTableModel(columnname,20);
	
	public EmployeeRevenue() {
		super("收益明细");
		Font f1 = new Font("楷体",Font.CENTER_BASELINE,24);
		Font f2 = new Font("楷体",Font.CENTER_BASELINE,20);
		
		l = new JLabel("职工业绩明细:");
		l.setFont(f1);
		l.setBounds(10, 20, 165, 23);
		l.setForeground(Color.BLUE);
		add(l);
		
		t1 = new JTextField();
		t1.setFont(f2);
		t1.setBounds(195, 20, 70, 23);
		add(t1);
		
		l1 = new JLabel("年");
		l1.setFont(f1);
		l1.setBounds(278, 20, 30, 23);
		add(l1);
		
		c2 = new JComboBox(month);
		c2.setFont(f2);
		c2.setBounds(320, 20, 65, 23);
		add(c2);
		
		l2 = new JLabel("月");
		l2.setFont(f1);
		l2.setBounds(388, 20, 30, 23);
		add(l2);
		
		c3 = new JComboBox(day);
		c3.setFont(f2);
		c3.setBounds(430, 20, 65, 23);
		add(c3);
		
		l3 = new JLabel("日");
		l3.setFont(f1);
		l3.setBounds(498, 20, 30, 23);
		add(l3);
		
		b1 = new JButton("查询");
		b1.setFont(f2);
		b1.setBounds(560, 55, 100, 23);
		add(b1);
		
		
		
		table = new JTable(r,columnname);
		table.setEnabled(false);
		table.setBounds(30,105,635,390);
		table.setRowHeight(23);
		table.setModel(model);
		JScrollPane scrollpane=new JScrollPane(table);
		scrollpane.setBounds(30,105,635,390);
		add(scrollpane);
		
		l4 = new JLabel("年业绩为:");
		l4.setFont(f2);
		l4.setBounds(20, 520, 100, 23);
		add(l4);
		
		t4 = new JTextField();
		t4.setFont(f2);
		t4.setEditable(false);
		t4.setBounds(123, 520, 90, 23);
		t4.setEditable(false);
		add(t4);
		
		l5 = new JLabel("月业绩为:");
		l5.setFont(f2);
		l5.setBounds(243, 520, 100, 23);
		add(l5);
		
		t5 = new JTextField();
		t5.setFont(f2);
		t5.setEditable(false);
		t5.setBounds(347, 520, 90, 23);
		t5.setEditable(false);
		add(t5);
		
		l6 = new JLabel("日业绩为:");
		l6.setFont(f2);
		l6.setBounds(476, 520, 100, 23);
		add(l6);
		
		t6 = new JTextField();
		t6.setFont(f2);
		t6.setEditable(false);
		t6.setBounds(579, 520, 90, 23);
		t6.setEditable(false);
		add(t6);
		
		l7 = new JLabel("营业员编号");
		l7.setFont(f2);
		l7.setBounds(150, 68, 120,23);
		add(l7);
		
		t7 = new JTextField();
		t7.setFont(f2);
		t7.setBounds(275, 68, 90, 23);
		add(t7);
		
		b1.addActionListener(this);
		
		ImageIcon icon = new ImageIcon("C:\\Users\\HP\\Desktop\\116.jpg"); 
	    Image img = icon.getImage().getScaledInstance(700, 600, Image.SCALE_FAST); // 图像缩放为适合Frame大小
	    JLabel jlabel = new JLabel(new ImageIcon(img));
	    jlabel.setBounds(0, 0,700,600);
	    getLayeredPane().add(jlabel, new Integer(Integer.MIN_VALUE));
	    JPanel jp = (JPanel) getContentPane();
	    JRootPane jp1 = (JRootPane) getRootPane();
	    jp.setOpaque(false);
	    jp1.setOpaque(false);
		
		setLayout(null);
		setResizable(false);
		setVisible(true);
		setBounds(300,100,700,600);
	}
	
	public void actionPerformed(ActionEvent arg1) {
		if(arg1.getSource()==b1) {
			for(int i=0;i<20;i++) {
				for(int j=0;j<7;j++) {
					model.setValueAt(null,i,j);
				}
			}
			
			String s1 = new String("");
			String s2 = new String("");
			String s3 = new String("");
			String s4 = new String("");
			s1 = t1.getText();
			s2 = (String) c2.getSelectedItem();
			s3 = (String) c3.getSelectedItem();
			s4 = t7.getText();
			
			//System.out.println(s1+"111"+s2+"222"+s3);
			
			if(t1.getText().length()==0&&c2.getSelectedItem()==null&&c3.getSelectedItem()==null&&t4.getText().length()!=0) {
				new JOptionPane().showMessageDialog(null, "请输入查询时间！");
			}
			else if(t1.getText().length()!=0&&c2.getSelectedItem()==null&&c3.getSelectedItem()==null&&t7.getText().length()!=0) {
				t5.setText("");
				t6.setText("");
				 double totalyear=0;
				String sql1 = "select CD_name,ename,vname,rentdate,returndate,rmoney,rflag from CD,Employee,Vip,Rent where Rent.rentdate like '"+s1+"%' and Rent.eno='"+s4+"' and Rent.vno=Vip.vno and Rent.CD_no=CD.CD_no and Rent.eno=Employee.eno;";
				
				try {
					st = getConnection().createStatement();
					ResultSet rs2 = st.executeQuery(sql1);
					
				
					 int k=0;
					 while(rs2.next()) {
						for(int i=0;i<7;i++) {
							obj[k][i]=rs2.getString(i+1).trim();	
							model.setValueAt(rs2.getString(i+1).trim(), k, i);
							
						}
						totalyear += Double.parseDouble(rs2.getString(6));
						k++;
					}
					 
					if(obj[0][0]==null) {
						new JOptionPane().showMessageDialog(null, "查询结果为空！");
					}
					 
					 DefaultTableCellRenderer r = new DefaultTableCellRenderer();   
						r.setHorizontalAlignment(JLabel.CENTER);   
						table.setDefaultRenderer(Object.class, r);
						table.setEnabled(false);
						table.setModel(model);
						table.setRowHeight(23);
				}catch(Exception e) {
					e.printStackTrace();
				}
				t4.setText(Double.toString(totalyear));
			}
			
			else if(t1.getText().length()!=0&&c2.getSelectedItem()!=null&&c3.getSelectedItem()==null&&t7.getText().length()!=0) {
				t6.setText("");
				double totalyear=0,totalmonth=0;
				String sql1 = "select CD_name,ename,vname,rentdate,returndate,rmoney,rflag from CD,Employee,Vip,Rent where Rent.rentdate like '"+s1+"-"+s2+"%' and Rent.eno='"+s4+"' and Rent.vno=Vip.vno and Rent.CD_no=CD.CD_no and Rent.eno=Employee.eno;";
				
				try {
					st = getConnection().createStatement();
					ResultSet rs2 = st.executeQuery(sql1);
					
					 obj[0][0] = null;
					 int k=0;
					 while(rs2.next()) {
						for(int i=0;i<7;i++) {
							obj[k][i]=rs2.getString(i+1).trim();	
							model.setValueAt(rs2.getString(i+1).trim(), k, i);
							
						}
						totalmonth += Double.parseDouble(rs2.getString(6));
						k++;
					}
					 
					if(obj[0][0]==null) {
						new JOptionPane().showMessageDialog(null, "查询结果为空！");
					}
					 
					 DefaultTableCellRenderer r = new DefaultTableCellRenderer();   
						r.setHorizontalAlignment(JLabel.CENTER);   
						table.setDefaultRenderer(Object.class, r);
						table.setEnabled(false);
						table.setModel(model);
						table.setRowHeight(23);					
				}catch(Exception e) {
					e.printStackTrace();
				}
				t5.setText(Double.toString(totalmonth));
				
				String sql2 = "select rmoney from Rent where rentdate like '"+s1+"%' and eno='"+s4+"';";
				System.out.println(sql2);
				try {
					st = getConnection().createStatement();
					ResultSet rs2 = st.executeQuery(sql2);
					while(rs2.next()) {
						totalyear += Double.parseDouble(rs2.getString(1));
					}
				}catch(Exception e) {
					e.printStackTrace();
				}
				t4.setText(Double.toString(totalyear));
			}
			
			else if(t1.getText().length()!=0&&c2.getSelectedItem()!=null&&c3.getSelectedItem()!=null&&t7.getText().length()!=0) {
				double totalyear=0,totalmonth=0,totalday=0;
				String sql1 = "select CD_name,ename,vname,rentdate,returndate,rmoney,rflag from CD,Employee,Vip,Rent where Rent.rentdate like '"+s1+"-"+s2+"-"+s3+"%' and Rent.eno='"+s4+"' and Rent.vno=Vip.vno and Rent.CD_no=CD.CD_no and Rent.eno=Employee.eno;";
				
				try {
					st = getConnection().createStatement();
					ResultSet rs2 = st.executeQuery(sql1);
					
					 obj[0][0] = null;
					 DefaultTableModel model=new DefaultTableModel(columnname,20);
					 int k=0;
					 while(rs2.next()) {
						for(int i=0;i<7;i++) {
							obj[k][i]=rs2.getString(i+1).trim();	
							model.setValueAt(rs2.getString(i+1).trim(), k, i);
							
						}
						totalday += Double.parseDouble(rs2.getString(6));
						k++;
					}
					 
					if(obj[0][0]==null) {
						new JOptionPane().showMessageDialog(null, "查询结果为空！");
					}
					 
					 DefaultTableCellRenderer r = new DefaultTableCellRenderer();   
						r.setHorizontalAlignment(JLabel.CENTER);   
						table.setDefaultRenderer(Object.class, r);
						table.setEnabled(false);
						table.setModel(model);
						table.setRowHeight(23);
				}catch(Exception e) {
					e.printStackTrace();
				}
				t6.setText(Double.toString(totalday));
				
				String sql2 = "select rmoney from Rent where rentdate like '"+s1+"-"+s2+"%' and eno='"+s4+"';";
				System.out.println(sql2);
				try {
					st = getConnection().createStatement();
					ResultSet rs2 = st.executeQuery(sql2);
					while(rs2.next()) {
						totalmonth += Double.parseDouble(rs2.getString(1));
					}
				}catch(Exception e) {
					e.printStackTrace();
				}
				t5.setText(Double.toString(totalmonth));
				
				String sql3 = "select rmoney from Rent where rentdate like '"+s1+"%' and eno='"+s4+"';";
				System.out.println(sql3);
				try {
					st = getConnection().createStatement();
					ResultSet rs3 = st.executeQuery(sql3);
					while(rs3.next()) {
						totalyear += Double.parseDouble(rs3.getString(1));
					}
				}catch(Exception e) {
					e.printStackTrace();
				}
				t4.setText(Double.toString(totalyear));
			}
			else if(t7.getText().length()==0) {
				new JOptionPane().showMessageDialog(null, "请输入职工编号！");
			}
		}
	}
}
