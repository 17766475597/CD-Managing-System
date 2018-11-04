package system;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.*;
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
import javax.swing.JRootPane;
import javax.swing.JTextField;

public class VipRent extends JFrame implements ActionListener{
	/*需要的SQL操作
	 * 1.CD表中的标志设为0
	 * 2.Vip表中的余额减少
	 * 3.租借信息Rent登记
	 * 4.会员消费记录Record登记
	 * 5.Settlement修改
	 */
	
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
	
	private JLabel l,l1,l2,l3,l4,l5,l6,l7;
	private JTextField t1,t2,t3,t4,t5,t6,t7;
	private JButton b1,b2,b3,b4;
	
	public VipRent(){
		super("会员租碟");
		l = new JLabel("会员租赁信息");
		Font f = new Font("楷体",Font.CENTER_BASELINE,30);
		l.setFont(f);
		l.setBounds(140,40,240,30);
		l.setForeground(Color.BLUE);
		add(l);
		
		Font f1 = new Font("楷体",Font.CENTER_BASELINE,20);
		
		l1 = new JLabel("碟片编号:");
		l1.setFont(f1);
		l1.setBounds(100, 90, 120, 23);
		add(l1);
		
		t1 = new JTextField(VipOrder.sCD_no);
		t1.setFont(f1);
		t1.setBounds(230, 90, 130, 23);
		t1.setEditable(false);
		add(t1);
		
		l2 = new JLabel("会员编号:");
		l2.setFont(f1);
		l2.setBounds(100, 140, 120, 23);
		add(l2);
		
		t2 = new JTextField(VipOrder.svno);
		t2.setFont(f1);
		t2.setBounds(230, 140, 130, 23);
		t2.setEditable(false);
		add(t2);
		
		l3 = new JLabel("职工编号:");
		l3.setFont(f1);
		l3.setBounds(100, 190, 120, 23);
		add(l3);
		
		t3 = new JTextField(EmployeeLogin.seno);
		t3.setFont(f1);
		t3.setBounds(230, 190, 130, 23);
		t3.setEditable(false);
		add(t3);
		
		l4 = new JLabel("出租日期:");
		l4.setFont(f1);
		l4.setBounds(100, 240, 120, 23);
		add(l4);
		
		t4 = new JTextField(VipOrder.srentdate);
		t4.setFont(f1);
		t4.setBounds(230, 240, 130, 23);
		t4.setEditable(false);
		add(t4);
		
		l5 = new JLabel("归还日期:");
		l5.setFont(f1);
		l5.setBounds(100, 290, 120, 23);
		add(l5);
		
		t5 = new JTextField(VipOrder.sreturndate);
		t5.setFont(f1);
		t5.setBounds(230, 290, 130, 23);
		t5.setEditable(false);
		add(t5);
		
		double real = 0;
		String s6="";
		String sql1 = "select CD_money from CD where CD_no='"+t1.getText()+"'";
		System.out.println(sql1);
		try {
			st = getConnection().createStatement();
			ResultSet rs = st.executeQuery(sql1);
			while(rs.next()) {
				s6 = rs.getString(1);
			}
			
			if(flag==1) {
				
				String sqlvip = "select * from Vip where vno='"+t2.getText()+"';";
				System.out.println(sqlvip);
				String money="",discount="";
				ResultSet rs2 = st.executeQuery(sqlvip);
				while(rs2.next()) {
					discount = rs2.getString("vdiscount");
					money = rs2.getString("vmoney");
				}
				rs2.close();
				
				double money1 = Double.parseDouble(s6);
				double discount1 =  Double.parseDouble(discount);
				real = money1 * discount1;
				
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
			
		String sr6 = Double.toString(real);
		l6 = new JLabel("碟片租金:");
		l6.setFont(f1);
		l6.setBounds(100, 340, 120, 23);
		add(l6);
		
		t6 = new JTextField(sr6);
		t6.setFont(f1);
		t6.setBounds(230, 340, 130, 23);
		t6.setEditable(false);
		add(t6);
		
		l7 = new JLabel("租赁标志:");
		l7.setFont(f1);
		l7.setBounds(100,390,120,23);
		add(l7);
		
		t7 = new JTextField("未还");
		t7.setFont(f1);
		t7.setBounds(230, 390, 130, 23);
		t7.setEditable(false);
		add(t7);
		
		b1 = new JButton("帮助");
		b1.setBounds(40, 450, 100, 26);
		b1.setFont(f1);
		add(b1);
		
		b2 = new JButton("提交");
		b2.setBounds(150, 450, 100, 26);
		b2.setFont(f1);
		add(b2);
		
		b3 = new JButton("重置");
		b3.setBounds(260, 450, 100, 26);
		b3.setFont(f1);
		add(b3);
		
		b4 = new JButton("取消");
		b4.setBounds(370, 450, 100, 26);
		b4.setFont(f1);
		add(b4);
		
		b1.addActionListener(this);
		b2.addActionListener(this);
		b3.addActionListener(this);
		b4.addActionListener(this);
		
		ImageIcon icon = new ImageIcon("C:\\Users\\HP\\Desktop\\116.jpg"); 
	    Image img = icon.getImage().getScaledInstance(500, 600, Image.SCALE_FAST); // 图像缩放为适合Frame大小
	    JLabel jlabel = new JLabel(new ImageIcon(img));
	    jlabel.setBounds(0, 0,500,600);
	    getLayeredPane().add(jlabel, new Integer(Integer.MIN_VALUE));
	    JPanel jp = (JPanel) getContentPane();
	    JRootPane jp1 = (JRootPane) getRootPane();
	    jp.setOpaque(false);
	    jp1.setOpaque(false);
		
		setLayout(null);
		setResizable(false);
		setVisible(true);
		setBounds(300,100,500,600);
	}
	
	public void actionPerformed(ActionEvent arg1) {
		if(arg1.getSource()==b1) {
			new JOptionPane().showMessageDialog(null, "日期格式为yyyy-mm-dd\n租金由碟片编号确定");
		}
		if(arg1.getSource()==b3) {
			t1.setText("");
			t3.setText("");
			t4.setText("");
			t5.setText("");
			t6.setText("");
		}
		if(arg1.getSource()==b4) {
			dispose();
		}
		if(arg1.getSource()==b2) {
			int flag=1;
			String s1,s2,s3,s4,s5,s6,s7;
			s1 = t1.getText();
			s2 = t2.getText();
			s3 = t3.getText();
			s4 = t4.getText();
			s5 = t5.getText();
			s6 = t6.getText();
			s7 = t7.getText();
			
			String time = "";
			
			String sql1 = "select CD_flag,CD_money,CD_time from CD where CD_no='"+s1+"'";
			System.out.println(sql1);
			try {
				st = getConnection().createStatement();
				ResultSet rs = st.executeQuery(sql1);
				while(rs.next()) {
					s6 = rs.getString(2);
					time = rs.getString(3);
					if(rs.getString(1).trim().equals("借出")) {
						new JOptionPane().showMessageDialog(null, "该碟片已借出!");
						flag=0;
					}
				}
				
				if(flag==1) {
					rs.close();
					
					
					String sqlvip = "select * from Vip where vno='"+s2+"';";
					System.out.println(sqlvip);
					String money="",discount="";
					ResultSet rs2 = st.executeQuery(sqlvip);
					while(rs2.next()) {
						discount = rs2.getString("vdiscount");
						money = rs2.getString("vmoney");
					}
					rs2.close();
					
					double money1 = Double.parseDouble(s6);
					double discount1 =  Double.parseDouble(discount);
					double real = money1 * discount1;
					String sr6 = Double.toString(real);
					
					if(Double.parseDouble(money)>=real){					
						String sql3="insert into Rent values('"+s1+"','"+s2+"','"+s3+"','"+s4+"','"+s5+"','"+sr6+"','"+s7+"');";
						System.out.println(sql3);
						st.executeUpdate(sql3);
						
						int time1 = Integer.parseInt(time);
						time1++;
						time = Integer.toString(time1);
						
						String sql4="update CD set CD_flag='借出',CD_time='"+time+"' where CD_no='"+s1+"';";
						System.out.println(sql4);
						st.executeUpdate(sql4);
						
						double left = Double.parseDouble(money) - real;
						String left1 = Double.toString(left);
						String sql5 = "update Vip set vmoney='"+left1+"' where vno='"+s2+"';";
						System.out.println(sql5);
						st.executeUpdate(sql5);
	
						String sql6 = "insert into Record values('"+s4+"','"+s1+"','"+s2+"','"+sr6+"');";
						System.out.println(sql6);
						st.executeUpdate(sql6);
								
						String rno1 = "";
						String sql7 = "select rno from Record where CD_no='"+s1+"' and vno ='"+s2+"' and rdate='"+s4+"';";
						ResultSet rs3 = st.executeQuery(sql7);
						System.out.println(sql7);
						while(rs3.next()) {
							rno1 = rs3.getString(1);
						}
						
						String sql8 = "insert into Settlement(rno,vno) values('"+rno1+"','"+s2+"');";
						System.out.println(sql8);
						st.executeUpdate(sql8);
						
						new JOptionPane().showMessageDialog(null, "租借成功!");
						
						dispose();
						//"upadate Vip set vmoney=vmoney-xxx"
					}
					else {
						new JOptionPane().showMessageDialog(null, "余额不足!");
						dispose();
					}
				}
			}catch(Exception e) {
				e.printStackTrace();
			}			
		}
	}		
		
}
