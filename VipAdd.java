package system;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.*;

public class VipAdd extends JFrame implements ActionListener{
	String url = "jdbc:sqlserver://localhost:1433; DatabaseName=dpcz";  
    String user = "sa";  
    String password = "123";  
    static String driverName = "com.microsoft.sqlserver.jdbc.SQLServerDriver";  
    int flag=1,flag1=1;
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
	
	
	private String[] sex= {"","男","女"};
	private JLabel l,l1,l2,l3,l4,l5,l6,l7;
	private JTextField t1,t2,t3,t4,t6,t7;
	private JComboBox c5;
	private JButton b1,b2,b3,b4,b5;
	public VipAdd() {
		super("会员注册页面");
		l = new JLabel("会员注册");
		Font f = new Font("楷体",Font.CENTER_BASELINE,32);
		l.setFont(f);
		l.setBounds(180,20,240,30);
		l.setForeground(Color.BLUE);
		add(l);
		
		Font f1 = new Font("楷体",Font.CENTER_BASELINE,20);
		l1 = new JLabel("原始顾客编号:");
		l1.setFont(f1);
		l1.setBounds(40, 73, 150, 23);
		add(l1);
			
		t1 = new JTextField(10);
		t1.setBounds(195, 73, 70, 23);
		t1.setFont(f1);
		add(t1);
		
		l2 = new JLabel("会员密码:");
		l2.setFont(f1);
		l2.setBounds(290, 73, 110, 23);
		add(l2);
		
		t2 = new JTextField(10);
		t2.setBounds(405, 73, 90, 23);
		t2.setFont(f1);
		add(t2);
		
		l3 = new JLabel("会员姓名:");
		l3.setFont(f1);
		l3.setBounds(60, 123, 110, 23);
		add(l3);
		
		t3 = new JTextField();
		t3.setFont(f1);
		t3.setBounds(175, 123, 90, 23);
		add(t3);
		
		l4 = new JLabel("会员电话:");
		l4.setFont(f1);
		l4.setBounds(290,123,110,23);
		add(l4);
		
		t4 = new JTextField(10);
		t4.setBounds(405, 123, 90, 23);
		add(t4);
		
		l5 = new JLabel("会员性别:");
		l5.setFont(f1);
		l5.setBounds(60,173,110,23);
		add(l5);	
		
		c5 = new JComboBox(sex);
		c5.setBounds(175, 173, 90, 23);
		c5.setFont(f1);
		add(c5);
		
		l6 = new JLabel("折扣率:");
		l6.setFont(f1);
		l6.setBounds(290,173,110,23);
		add(l6);
		
		t6 = new JTextField(10);
		t6.setBounds(405, 173, 90, 23);
		t6.setFont(f1);
		add(t6);
		
		l7 = new JLabel("首充金额:");
		l7.setFont(f1);
		l7.setBounds(90, 223, 140, 23);
		add(l7);
		
		t7 = new JTextField();
		t7.setFont(f1);
		t7.setBounds(235, 223, 90,23);
		add(t7);
		
		ImageIcon icon = new ImageIcon("C:\\Users\\HP\\Desktop\\116.jpg"); 
	    Image img = icon.getImage().getScaledInstance(600, 400, Image.SCALE_FAST); // 图像缩放为适合Frame大小
	    JLabel jlabel = new JLabel(new ImageIcon(img));
	    jlabel.setBounds(0, 0,600,400);
	    getLayeredPane().add(jlabel, new Integer(Integer.MIN_VALUE));
	    JPanel jp = (JPanel) getContentPane();
	    JRootPane jp1 = (JRootPane) getRootPane();
	    jp.setOpaque(false);
	    jp1.setOpaque(false);
		
		setLayout(null);
		setResizable(false);
		setVisible(true);
		setBounds(300,100,600,400);
		
		b1 = new JButton("提交");
		b2 = new JButton("取消");	
		b3 = new JButton("重置");
		b4 = new JButton("查询");
		b5 = new JButton("折扣标准");
		b1.setFont(f1);
		b2.setFont(f1);	
		b3.setFont(f1);
		b4.setFont(f1);
		b5.setFont(f1);
		b1.setBounds(90,275,80,23);
		b2.setBounds(290,275,80,23);
		b3.setBounds(185,275,80,23);
		b4.setBounds(370, 223, 80, 23);
		b5.setBounds(385, 275, 120, 23);
		add(b1);
		add(b2);
		add(b3);
		add(b4);
		add(b5);
				
		b1.addActionListener(this);
		b2.addActionListener(this);
		b3.addActionListener(this);
		b4.addActionListener(this);
		b5.addActionListener(this);
	}
	
	public void actionPerformed(ActionEvent arg1) {
		if(arg1.getSource()==b2) {
			dispose();
		}
		if(arg1.getSource()==b3) {
			t1.setText("");
			t2.setText("");
			t3.setText("");
			t4.setText("");
			t6.setText("");
			t7.setText("");
		}
		if(arg1.getSource()==b1) {
			String s1,s2,s3,s4,s5,s6,s7,judge=null;
			s1 = t1.getText();
			s2 = t2.getText();
			s3 = t3.getText();
			s4 = t4.getText();
			s5 = (String) c5.getSelectedItem();
			s6 = t6.getText();
			s7 = t7.getText();
			
			int flag = 1;
			String sql = "select * from Upgrade where cno='"+s1+"';";
			try {
				st=getConnection().createStatement();
				ResultSet rs1 = st.executeQuery(sql);
				while(rs1.next()) {
					judge = rs1.getString(1).trim();
				}
				if(judge!=null) {
					new JOptionPane().showMessageDialog(null, "该顾客已经是会员！");
					flag=0;
				}
			}catch(Exception e) {
				e.printStackTrace();
			}
			
			if(flag==1) {
				String sql1="insert into Vip(vpassword,vname,vphone,vsex,vdiscount,vmoney) values('"+s2+"','"+s3+"','"+s4+"','"+s5+"','"+s6+"','"+s7+"')";
				try {
					st=getConnection().createStatement();
					st.executeUpdate(sql1);
				}catch(Exception ex) {
					 flag=0;
					 new JOptionPane().showMessageDialog(null, "添加失败");
				 }
				try {
					 st.close();  
				     getConnection().close();  
				 }catch(Exception e) {
					 e.printStackTrace();
				 }
				 if(flag==1) {
					 new JOptionPane().showMessageDialog(null, "添加成功");
				 }
				 
				 
				 
				 String sql2 = "select vno from Vip where vphone='"+s4+"';";
				 String vvno="12345";
				 try {
					 st=getConnection().createStatement();
					 ResultSet rs = st.executeQuery(sql2);
					 while(rs.next()) {
						 vvno = rs.getString(1);
					 }
				 }catch(Exception e) {
					 e.printStackTrace();
				 }
				 System.out.println(vvno);
				 
				 
				 String sql3 = "insert into Upgrade values('"+s1+"','"+vvno+"');";
				 try {
					 st=getConnection().createStatement();
					 st.executeUpdate(sql3);
				 }catch(Exception e){
					 flag1=0;
					 new JOptionPane().showMessageDialog(null, "添加失败");
				 }
				 
				 try {
					 st.close();  
				     getConnection().close();  
				 }catch(Exception e) {
					 e.printStackTrace();
				 }
				 if(flag1==1) {
					
				 }
			}
		}
		
		if(arg1.getSource()==b4) {
			String s1;
			s1 = t1.getText();
			
			String sql1 = "select cname,cphone,csex from Customer where cno='"+s1+"';";
			
			try {
				st = getConnection().createStatement();
				ResultSet rs = st.executeQuery(sql1);
				
				while(rs.next()) {
					t3.setText(rs.getString(1).trim());
					t4.setText(rs.getString(2).trim());
					c5.setSelectedItem(rs.getString(3).trim());
				}
				
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
		if(arg1.getSource()==b5) {
			VipInfo vi = new VipInfo();
		}
	}
}
