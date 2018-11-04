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

import javax.swing.*;

public class EmployeeManagement extends JFrame implements ActionListener{
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
	
	
	private String[] sex= {"","男","女"};
	private JLabel l,l1,l2,l3,l4,l5,l6;
	private JTextField t1,t2,t3,t4,t5;
	private JComboBox c6;
	private JButton b1,b2,b3,b4,b5,b6;
	public EmployeeManagement() {
		super("职工操作页面");
		l = new JLabel("职工基本信息");
		Font f = new Font("楷体",Font.CENTER_BASELINE,32);
		l.setFont(f);
		l.setBounds(180,20,240,30);
		l.setForeground(Color.BLUE);
		add(l);
		
		Font f1 = new Font("楷体",Font.CENTER_BASELINE,20);
		l1 = new JLabel("职工编号:");
		l1.setFont(f1);
		l1.setBounds(60, 73, 110, 23);
		add(l1);
			
		t1 = new JTextField(10);
		t1.setBounds(175, 73, 90, 23);
		t1.setFont(f1);
		add(t1);
		
		l2 = new JLabel("职工密码:");
		l2.setFont(f1);
		l2.setBounds(290, 73, 110, 23);
		add(l2);
		
		t2 = new JTextField(10);
		t2.setBounds(405, 73, 90, 23);
		t2.setFont(f1);
		add(t2);
		
		l3 = new JLabel("职工姓名:");
		l3.setFont(f1);
		l3.setBounds(60, 123, 110, 23);
		add(l3);
		
		t3 = new JTextField();
		t3.setFont(f1);
		t3.setBounds(175, 123, 90, 23);
		add(t3);
		
		l4 = new JLabel("职工年龄:");
		l4.setFont(f1);
		l4.setBounds(290,123,110,23);
		add(l4);
		
		t4 = new JTextField(10);
		t4.setBounds(405, 123, 90, 23);
		t4.setFont(f1);
		add(t4);
		
		l5 = new JLabel("职工电话:");
		l5.setFont(f1);
		l5.setBounds(100,173,110,23);
		add(l5);	
		
		t5 = new JTextField(10);
		t5.setBounds(220, 173, 120, 23);
		t5.setFont(f1);
		add(t5);
		
		l6 = new JLabel("职工性别:");
		l6.setFont(f1);
		l6.setBounds(100,223,110,23);
		add(l6);
		
		c6= new JComboBox(sex);
		c6.setMaximumRowCount(2);
		c6.setBounds(220, 223, 120, 23);
		c6.setSelectedItem(2);
		c6.setEditable(false);
		add(c6);
		
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
		
		b5 = new JButton("帮助");
		b1 = new JButton("添加");
		b2 = new JButton("修改");
		b3 = new JButton("删除");
		b4 = new JButton("取消");
		b6 = new JButton("查询");
		
		b5.setFont(f1);
		b4.setFont(f1);
		b3.setFont(f1);
		b2.setFont(f1);
		b1.setFont(f1);
		b6.setFont(f1);
		
		b5.setBounds(60, 280, 80, 27);
		b1.setBounds(150, 280, 80, 27);
		b2.setBounds(240, 280, 80, 27);
		b3.setBounds(330, 280, 80, 27);
		b4.setBounds(420, 280, 80, 27);
		b6.setBounds(400, 198, 80, 27);
		
		add(b5);
		add(b1);
		add(b2);
		add(b3);
		add(b4);
		add(b6);
		
		b1.addActionListener(this);
		b2.addActionListener(this);
		b3.addActionListener(this);
		b4.addActionListener(this);
		b5.addActionListener(this); 
		b6.addActionListener(this);
	}
	
	public void actionPerformed(ActionEvent arg1) {
		if(arg1.getSource()==b5) {
			new JOptionPane().showMessageDialog(null, "添加信息时不需要在意职工编号"+"\n"+"删除或修改信息时以信息编号为准");
		}
		
		if(arg1.getSource()==b4) {
			dispose();
		}
		
		if(arg1.getSource()==b1) {
			String s2,s3,s4,s5,s6;
			s2 = t2.getText();
			s3 = t3.getText();
			s4 = t4.getText();
			s5 = t5.getText();
			s6 = (String) c6.getSelectedItem();
			String sql1="insert into Employee(epassword,ename,eage,ephone,esex) values('"+s2+"','"+s3+"','"+s4+"','"+s5+"','"+s6+"')";
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
				 
			 }
			 if(flag==1) {
				 new JOptionPane().showMessageDialog(null, "添加成功");
			 }
				t1.setText("");
				t2.setText("");
				t3.setText("");
				t4.setText("");
				t5.setText("");
		}
		
		if(arg1.getSource()==b2) {
			String s1,s2,s3,s4,s5,s6;
			s1 = t1.getText().trim();
			s2 = t2.getText();
			s3 = t3.getText();
			s4 = t4.getText();
			s5 = t5.getText();
			s6 = (String) c6.getSelectedItem();
			String sql1="update Employee set epassword='"+s2+"' ,ename='"+s3+"' ,eage='"+s4+"' ,ephone='"+s5+"' ,esex='"+s6+"' where eno='"+s1+"'";
		
			System.out.println(sql1);
			
			try {
				 st=getConnection().createStatement();  
				 st.executeUpdate(sql1);
			 }catch(Exception ex) {
				 ex.printStackTrace();
				 flag=0;
				 new JOptionPane().showMessageDialog(null, "修改失败");
			 }
			 try {
				 st.close();  
			     getConnection().close();  
			 }catch(Exception e) {
				 
			 }
			 if(flag==1) {
				 new JOptionPane().showMessageDialog(null, "修改成功");
			 }
				t1.setText("");
				t2.setText("");
				t3.setText("");
				t4.setText("");
				t5.setText("");
		}
		
		if(arg1.getSource()==b3) {
			String s1;
			s1 = t1.getText().trim();
			
			String sql1="delete from CD where CD_no='"+s1+"'";
		
			System.out.println(sql1);
			
			try {
				 st=getConnection().createStatement();  
				 st.executeUpdate(sql1);
			 }catch(Exception ex) {
				 ex.printStackTrace();
				 flag=0;
				 new JOptionPane().showMessageDialog(null, "删除失败");
			 }
			 try {
				 st.close();  
			     getConnection().close();  
			 }catch(Exception e) {
				 
			 }
			 if(flag==1) {
				 new JOptionPane().showMessageDialog(null, "删除成功");
			 }
				t1.setText("");
				t2.setText("");
				t3.setText("");
				t4.setText("");
				t5.setText("");
		}
		
		if(arg1.getSource()==b6) {
			String s1;
			s1 = t1.getText().trim();
			
			String sql1 = "select * from Employee where eno='"+s1+"'";
			
			try {
				st = getConnection().createStatement();
				ResultSet rs = st.executeQuery(sql1);
				
				while(rs.next()) {
					t2.setText(rs.getString(2).trim());
					t3.setText(rs.getString(3).trim());
					t4.setText(rs.getString(4).trim());
					t5.setText(rs.getString(5).trim());
					c6.setSelectedItem(rs.getString(6).trim());
				}
					
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
	}
}
