package system;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

public class VipReturn extends JFrame implements ActionListener{
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
	
	private JLabel l,l1,l2;
	private JTextField t1,t2;
	private JButton b1,b2;
	private JTable table;
	private String[] columnname = {"碟片编号","碟片名称","职工姓名","会员姓名","租借日期","归还日期","租赁金额","租借标志"};
	private String[][] r = {};
	private Object[][] obj = new Object[20][8]; 
	
	public VipReturn(){
		super("会员归还");
		Font f = new Font("楷体",Font.CENTER_BASELINE,32);
		Font f1 = new Font("楷体",Font.CENTER_BASELINE,20);
		
		l = new JLabel("会员碟片归还");
		l.setFont(f);
		l.setBounds(180,20,240,30);
		l.setForeground(Color.BLUE);
		add(l);
		
		l1 = new JLabel("会员账号:");
		l1.setFont(f1);
		l1.setBounds(40, 70,100, 23);
		add(l1);
		
		t1 = new JTextField();
		t1.setFont(f1);
		t1.setBounds(145, 70, 80, 23);
		add(t1);
		
		l2 = new JLabel("还碟编号:");
		l2.setFont(f1);
		l2.setBounds(245, 70, 100, 23);
		add(l2);
		
		t2 = new JTextField();
		t2.setFont(f1);
		t2.setBounds(350, 70, 80, 23);
		add(t2);
		
		b1 = new JButton("查询");
		b1.setFont(f1);
		b1.setBounds(450, 70, 80, 23);
		add(b1);
		
		b2 = new JButton("归还");
		b2.setFont(f1);
		b2.setBounds(540, 70, 80, 23);
		add(b2);

		DefaultTableModel model=new DefaultTableModel(columnname,20);
		table = new JTable(r,columnname);
		table.setBounds(40,120,600,400);
		table.setRowHeight(23);
		table.setModel(model);
		table.setEnabled(false);
		JScrollPane scrollpane=new JScrollPane(table);
		scrollpane.setBounds(40,120,600,400);
		add(scrollpane);
		
		b1.addActionListener(this);
		b2.addActionListener(this);
		
		ImageIcon icon = new ImageIcon("C:\\Users\\HP\\Desktop\\116.jpg"); 
	    Image img = icon.getImage().getScaledInstance(700, 630, Image.SCALE_FAST); // 图像缩放为适合Frame大小
	    JLabel jlabel = new JLabel(new ImageIcon(img));
	    jlabel.setBounds(0, 0,700,630);
	    getLayeredPane().add(jlabel, new Integer(Integer.MIN_VALUE));
	    JPanel jp = (JPanel) getContentPane();
	    JRootPane jp1 = (JRootPane) getRootPane();
	    jp.setOpaque(false);
	    jp1.setOpaque(false);
		
		this.setLayout(null);
        this.setVisible(true);
        this.setBounds(300,100,700,630);
	}
	
	public void actionPerformed(ActionEvent arg1) {
		String s1 = t1.getText().trim();
		String s2 = t2.getText().trim();
		String s3 = EmployeeLogin.seno;
		if(arg1.getSource()==b1) {
			
			try {
				st=getConnection().createStatement();
				
					 String sql = "select CD.CD_no,CD_name,ename,vname,rentdate,returndate,rmoney,rflag from CD,Employee,Vip,Rent where Rent.vno='"+s1+"' and Rent.vno=Vip.vno and Rent.CD_no=CD.CD_no and Rent.eno=Employee.eno;";                
					System.out.println(sql);
					 ResultSet rs2 = st.executeQuery(sql);
					 
					 
					 DefaultTableModel model=new DefaultTableModel(columnname,20);
					 int k=0;
					 while(rs2.next()) {
						for(int i=0;i<8;i++) {
							obj[k][i]=rs2.getString(i+1).trim();	
							model.setValueAt(rs2.getString(i+1).trim(), k, i);
						}
						k++;
					}
					 table = new JTable(obj,columnname);
					 DefaultTableCellRenderer r = new DefaultTableCellRenderer();   
						r.setHorizontalAlignment(JLabel.CENTER);   
						table.setDefaultRenderer(Object.class, r);
						table.setEnabled(false);
						add(table);
						table.setBounds(40,120,600,400);
						table.setModel(model);
						table.setRowHeight(23);
						JScrollPane scrollpane1=new JScrollPane(table);
						scrollpane1.setBounds(40,120,600,400);
						add(scrollpane1);
				 
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
		
		if(arg1.getSource()==b2) {
			int flag=1;
			String sql1 = "update Rent set rflag='已还' where CD_no='"+s2+"' and vno='"+s1+"' and eno ='"+s3+"'";
			String sql2 = "update CD set CD_flag='可借' where CD_no='"+s2+"'";
			
			try {
				st = getConnection().createStatement();
				st.executeUpdate(sql1);
				System.out.println(sql1);
				st.executeUpdate(sql2);
				System.out.println(sql2);
			}catch(Exception e) {
				new JOptionPane().showMessageDialog(null, "碟片编号输入错误 归还失败！");
				 flag=0;
				e.printStackTrace();
			}
			if(flag==1) {
				new JOptionPane().showMessageDialog(null, "归还成功！");
			}
		}
	}
}
