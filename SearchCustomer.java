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

public class SearchCustomer extends JFrame implements ActionListener{
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
	
	private JTable table;
	private JLabel l1;
	private String[] name = {"","顾客姓名","顾客性别","顾客电话","全部顾客"};
	private String[] columnname = {"顾客编号","顾客姓名","顾客性别","顾客电话"};
	private String [][] r = {};
	private JTextField t1;
	private JComboBox c1;
	private JButton b1;
	private Object[][] obj = new Object[20][4]; 
	DefaultTableModel model=new DefaultTableModel(columnname,20);
	
	public SearchCustomer() {
		super("顾客查询");
		
		l1 = new JLabel("查询内容：");
		t1 = new JTextField(10);
		b1 = new JButton("查询");
		c1 = new JComboBox(name);
		c1.setMaximumRowCount(4);
		Container container = getContentPane();
		container.setLayout(null);
		
		l1.setBounds(30, 20, 130, 20);
		Font font1 = new Font("楷体",Font.BOLD,21);
		l1.setFont(font1);
		add(l1);
		t1.setBounds(155, 18, 120, 24);
		Font font2 = new Font("宋体",Font.PLAIN,18);
		t1.setFont(font2);
		add(t1);
		
		c1.setBounds(300, 17, 90, 26);
		add(c1);
		
		Font font3 = new Font("楷体",Font.BOLD,18);
		b1.setFont(font3);
		b1.setBounds(410, 18, 80, 24);
		add(b1);

		
		table = new JTable(r,columnname);
		table.setBounds(40,80,600,400);
		table.setRowHeight(23);
		table.setModel(model);
		table.setEnabled(false);
		JScrollPane scrollpane=new JScrollPane(table);
		scrollpane.setBounds(40,80,600,400);
		add(scrollpane);
		
		b1.addActionListener(this);
		
		ImageIcon icon = new ImageIcon("C:\\Users\\HP\\Desktop\\116.jpg"); 
	    Image img = icon.getImage().getScaledInstance(700, 550, Image.SCALE_FAST); // 图像缩放为适合Frame大小
	    JLabel jlabel = new JLabel(new ImageIcon(img));
	    jlabel.setBounds(0, 0,700,550);
	    getLayeredPane().add(jlabel, new Integer(Integer.MIN_VALUE));
	    JPanel jp = (JPanel) getContentPane();
	    JRootPane jp1 = (JRootPane) getRootPane();
	    jp.setOpaque(false);
	    jp1.setOpaque(false);
		
		this.setLayout(null);
        this.setVisible(true);
        this.setBounds(300,100,700,550);
	}
	
	public void actionPerformed(ActionEvent arg1) {
	
		if(arg1.getSource()==b1) {
				for(int i=0;i<20;i++) {
					for(int j=0;j<4;j++) {
						model.setValueAt(null,i,j);
					}
				}
			
			int flag = 0;
			String sitem = (String) c1.getSelectedItem();
			String stext = t1.getText().trim();
			
			if(sitem.equals("顾客姓名")) {
				sitem = "cname";
			}
			else if(sitem.equals("顾客性别")) {
				sitem = "csex";
			}else if(sitem.equals("顾客电话")){
				sitem = "cphone";
			}else if(sitem.equals("全部顾客")){
				flag = 1;
			}else {
				new JOptionPane().showMessageDialog(null, "请选择查询类别！");
			}
			
			String sql = "";
			
			if(flag==1) {
				sql = "select * from Customer;";
				System.out.println(sql);
			}			
			else {
				sql = "select * from Customer where "+sitem+"='"+stext+"';";
				System.out.println(sql);
			}
			
			try {
				st = getConnection().createStatement();
				ResultSet rs = st.executeQuery(sql);
				
				int k=0;
				while(rs.next()) {
					for(int i=0;i<4;i++) {
						obj[k][i]=rs.getString(i+1).trim();	
						model.setValueAt(rs.getString(i+1).trim(), k, i);
					}
					k++;
				}
				
				if(obj[0][0]==null) {
					new JOptionPane().showMessageDialog(null, "查询结果为空！");
				}
				
				/*System.out.println(obj[0][0]);
				System.out.println(obj[0][1]);
				System.out.println(obj[0][2]);
				System.out.println(obj[0][3]);*/

				DefaultTableCellRenderer r = new DefaultTableCellRenderer();   
				r.setHorizontalAlignment(JLabel.CENTER);   
				table.setDefaultRenderer(Object.class, r);
				table.setEnabled(false);
				table.setModel(model);
				table.setRowHeight(23);
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
	}
}
