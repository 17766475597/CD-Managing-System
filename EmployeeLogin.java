package system;

import java.awt.Container;
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
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JRootPane;
import javax.swing.JTextField;

public class EmployeeLogin extends JFrame implements ActionListener{
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
	
	static String seno = new String("");
	static String sename = new String("");
    
	JTextField textName=new JTextField("");
	JPasswordField textPassword=new JPasswordField("");
	JLabel label = new JLabel("营业员登录");
	JLabel labelName=new JLabel("用户名：");
	JLabel labelPassword=new JLabel("密码：");

	JButton buttonEnter=new JButton("登录");
	JButton buttonCancel=new JButton("取消");
	public EmployeeLogin() {
	    super("营业员登录");
	    Font f = new Font("楷体",Font.CENTER_BASELINE,25);
		Container container = getContentPane();
		container.setLayout(null);
		label.setBounds(120,20,200,30);
		label.setFont(f);
		container.add(label);
		
		labelName.setBounds(80, 68, 60, 40);
		container.add(labelName);
		textName.setBounds(150, 75, 120, 23);
		container.add(textName);
		
		labelPassword.setBounds(80, 118, 60 , 40);
		container.add(labelPassword);
		textPassword.setBounds(150, 125, 120, 23);
		container.add(textPassword);
		
		buttonEnter.setBounds(100, 178, 60, 20);
		buttonCancel.setBounds(200, 178, 60, 20);
		add(buttonEnter);
		add(buttonCancel);
		
		buttonEnter.addActionListener(this);
		buttonCancel.addActionListener(this);
		
		ImageIcon icon = new ImageIcon("C:\\Users\\HP\\Desktop\\114.jpg"); 
	    Image img = icon.getImage().getScaledInstance(400, 280, Image.SCALE_FAST); // 图像缩放为适合Frame大小
	    JLabel jlabel = new JLabel(new ImageIcon(img));
	    jlabel.setBounds(0, 0,400,280);
	    getLayeredPane().add(jlabel, new Integer(Integer.MIN_VALUE));
	    JPanel jp = (JPanel) getContentPane();
	    JRootPane jp1 = (JRootPane) getRootPane();
	    jp.setOpaque(false);
	    jp1.setOpaque(false);
		
		setResizable(false);
		setVisible(true);
		setBounds(300,100,400,280);
	}

	public void actionPerformed(ActionEvent arg1){ 
		if(arg1.getSource()==buttonEnter) {
			 String sql = "select * from Employee where eno="+textName.getText().trim()+";";   //要执行的SQL
			 
			 try {
				 st=getConnection().createStatement();  
				
				 ResultSet rs = st.executeQuery(sql);//创建数据对象
				 String s = new String(textPassword.getPassword()).trim();
				 int flag=0;    //记录是否查到用户名
				 while (rs.next()){
					 if(s.equals(rs.getString(2).trim())) {
						 new JOptionPane().showMessageDialog(null, "登录成功");
						 seno = textName.getText();
						 sename = rs.getString(3);
						 flag=1;
						 Employee m = new Employee();
						 dispose();
					 }
					 else {
						 new JOptionPane().showMessageDialog(null, "密码错误，登录失败！");
						 flag=1;
					 }
				 }
				 if(flag==0) {
					 new JOptionPane().showMessageDialog(null, "账号错误，登录失败！");
				 }
				 rs.close();
	             st.close();
	             conn.close();
			 }catch(Exception e) {
				 e.printStackTrace();
			 }
		}
		
		if(arg1.getSource()==buttonCancel) {
			dispose();
		}
	}
}
