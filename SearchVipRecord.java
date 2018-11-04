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
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

public class SearchVipRecord extends JFrame implements ActionListener{
	
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
	private JTextField t1;
	private JButton b1;
	private JPasswordField p2;
	private JTable table;
	private String[] columnname = {"碟片名称","职工姓名","会员姓名","租借日期","归还日期","租赁金额","租借标志"};
	private String[][] r = {};
	private Object[][] obj = new Object[20][7]; 
	DefaultTableModel model=new DefaultTableModel(columnname,20);
	
	public SearchVipRecord(){
		super("会员记录查询");
		Font f = new Font("楷体",Font.CENTER_BASELINE,32);
		Font f1 = new Font("楷体",Font.CENTER_BASELINE,20);
		
		l = new JLabel("会员记录查询");
		l.setFont(f);
		l.setBounds(180,20,240,30);
		l.setForeground(Color.BLUE);
		add(l);
		
		l1 = new JLabel("会员账号:");
		l1.setFont(f1);
		l1.setBounds(40, 70,100, 23);
		add(l1);
		
		t1 = new JTextField(VipLogin.svno.trim());
		t1.setFont(f1);
		t1.setBounds(145, 70, 80, 23);
		t1.setEditable(false);
		add(t1);
		
		l2 = new JLabel("会员密码:");
		l2.setFont(f1);
		l2.setBounds(245, 70, 100, 23);
		add(l2);
		
		p2 = new JPasswordField();
		p2.setFont(f1);
		p2.setBounds(350, 70, 80, 23);
		add(p2);
		
		b1 = new JButton("查询");
		b1.setFont(f1);
		b1.setBounds(450, 70, 80, 23);
		add(b1);

		
		table = new JTable(r,columnname);
		table.setBounds(40,120,600,400);
		table.setRowHeight(23);
		table.setModel(model);
		table.setEnabled(false);
		JScrollPane scrollpane=new JScrollPane(table);
		scrollpane.setBounds(40,120,600,400);
		add(scrollpane);
		
		b1.addActionListener(this);
		
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
		if(arg1.getSource()==b1) {
			
				for(int i=0;i<20;i++) {
					for(int j=0;j<7;j++) {
						model.setValueAt(null,i,j);
					}
				}
			String s1 = t1.getText().trim();
			String s2 = new String(p2.getPassword()).trim();
			try {
				String sql1 = "select vpassword from Vip where vno='"+s1+"'";
				st=getConnection().createStatement();
				ResultSet rs = st.executeQuery(sql1);
				
				int flag=0;    //记录是否查到用户名
				 while (rs.next()){
					 if(s2.equals(rs.getString(1).trim())) {
						 flag=1;
					 }
					 else {
						 new JOptionPane().showMessageDialog(null, "密码错误！");
					 }
				 }
				 rs.close();
				 
				 if(flag==1) {
					 String sql2 = "select CD_name,ename,vname,rentdate,returndate,rmoney,rflag from CD,Employee,Vip,Rent where Rent.vno='"+s1+"' and Rent.vno=Vip.vno and Rent.CD_no=CD.CD_no and Rent.eno=Employee.eno;";                
					System.out.println(sql2);
					 ResultSet rs2 = st.executeQuery(sql2);
					 
					 
					 
					 int k=0;
					 while(rs2.next()) {
						for(int i=0;i<7;i++) {
							obj[k][i]=rs2.getString(i+1).trim();	
							model.setValueAt(rs2.getString(i+1).trim(), k, i);
						}
						k++;
					}
					 DefaultTableCellRenderer r = new DefaultTableCellRenderer();   
						r.setHorizontalAlignment(JLabel.CENTER);   
						table.setDefaultRenderer(Object.class, r);
						table.setEnabled(false);
						table.setModel(model);
						table.setRowHeight(23);
				 }
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
	}
}
