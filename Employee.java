package system;

import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.*;

import javax.swing.*;

public class Employee extends JFrame implements ActionListener{
	private JMenu m1;
	private JMenu m2;
	private JMenu m3;
	private JMenu m4;
	private JMenu m5;
	private JMenu m6;
	private JMenuBar mb;
	private JMenuItem mi1;
	private JMenuItem mi2;
	private JMenuItem mi3;
	private JMenuItem mi4;
	private JMenuItem mi5;
	private JMenuItem mi6;
	private JMenuItem mi7;
	private JMenuItem mi8;
	private JMenuItem mi9;
	private JMenuItem mi10;
	private JMenuItem mi11;
	private JMenuItem mi12;
	private JMenuItem mi13;
	
	public Employee() {
		super("营业员界面——欢迎 "+EmployeeLogin.sename.trim()+" 登录");
		mi1 = new JMenuItem("碟片查询");
		mi13 = new JMenuItem("人气碟片");
		mi2 = new JMenuItem("会员查询");
		mi3 = new JMenuItem("会员管理");
		mi4 = new JMenuItem("会员注册");
		mi5 = new JMenuItem("顾客查询");
		mi6 = new JMenuItem("顾客管理");
		mi7 = new JMenuItem("会员租赁");
		mi8 = new JMenuItem("顾客租赁");
		mi9 = new JMenuItem("重新登录");
		mi10 = new JMenuItem("退出系统");
		mi11 = new JMenuItem("会员归还");
		mi12 = new JMenuItem("顾客归还");
		
		m1 = new JMenu("碟片信息");
		m2 = new JMenu("会员信息");
		m3 = new JMenu("顾客信息");
		m4 = new JMenu("租赁操作");
		m5 = new JMenu("系统操作");
		m6 = new JMenu("归还操作");
		mb = new JMenuBar();
		
		m1.add(mi1);
		m1.add(mi13);
		mb.add(m1);
		
		m2.add(mi2);
		m2.add(mi3);
		m2.add(mi4);
		mb.add(m2);
		
		m3.add(mi5);
		m3.add(mi6);
		mb.add(m3);
		
		m4.add(mi7);
		m4.add(mi8);
		mb.add(m4);
		
		m6.add(mi11);
		m6.add(mi12);
		mb.add(m6);
		
		m5.add(mi9);
		m5.add(mi10);
		mb.add(m5);
		
		Font f1 = new Font("宋体",Font.CENTER_BASELINE,14);
		m1.setFont(f1);
		m2.setFont(f1);
		m3.setFont(f1);
		m4.setFont(f1);
		m5.setFont(f1);
		m6.setFont(f1);
		mi1.setFont(f1);
		mi2.setFont(f1);
		mi3.setFont(f1);
		mi4.setFont(f1);
		mi5.setFont(f1);
		mi6.setFont(f1);
		mi7.setFont(f1);
		mi8.setFont(f1);
		mi9.setFont(f1);
		mi10.setFont(f1);
		mi11.setFont(f1);
		mi12.setFont(f1);
		mi13.setFont(f1);
		
		setJMenuBar(mb);
		mi1.addActionListener(this);
		mi2.addActionListener(this);
		mi3.addActionListener(this);
		mi4.addActionListener(this);
		mi5.addActionListener(this);
		mi6.addActionListener(this);
		mi7.addActionListener(this);
		mi8.addActionListener(this);
		mi9.addActionListener(this);
		mi10.addActionListener(this);
		mi11.addActionListener(this);
		mi12.addActionListener(this);
		mi13.addActionListener(this);
				
		ImageIcon icon = new ImageIcon("C:\\Users\\HP\\Desktop\\115.jpg"); 
	    Image img = icon.getImage().getScaledInstance(1200, 800, Image.SCALE_FAST); // 图像缩放为适合Frame大小
	    JLabel jlabel = new JLabel(new ImageIcon(img));
	    jlabel.setBounds(0, 0,1200,800);
	    getLayeredPane().add(jlabel, new Integer(Integer.MIN_VALUE));
	    JPanel jp = (JPanel) getContentPane();
	    JRootPane jp1 = (JRootPane) getRootPane();
	    jp.setOpaque(false);
	    jp1.setOpaque(false);
		
	    this.setResizable(false);
		this.setLayout(null);
        this.setVisible(true);
        this.setBounds(300,100,1200,800);  
	}
	
	public void actionPerformed(ActionEvent arg1){  
		if(arg1.getSource()==mi1) {
			SearchCD s1 = new SearchCD();
		}
		if(arg1.getSource()==mi2) {
			SearchVip s2 = new SearchVip();
		}
		if(arg1.getSource()==mi3) {
			VipManagement e = new VipManagement();		
		}
		if(arg1.getSource()==mi4) {
			VipAdd r2 = new VipAdd();
		}
		if(arg1.getSource()==mi5) {
			SearchCustomer s3 = new SearchCustomer();		
		}
		if(arg1.getSource()==mi6) {
			CustomerManagement v = new CustomerManagement();	
		}
		if(arg1.getSource()==mi7) {
			VipOrder r1 = new VipOrder();		
		}
		if(arg1.getSource()==mi8) {
			CustomerOrder r2 = new CustomerOrder();
		}
		if(arg1.getSource()==mi9) {
			dispose();
			Login l = new Login();
		}
		if(arg1.getSource()==mi10) {
			System.exit(0);
		}
		if(arg1.getSource()==mi11) {
			VipReturn cr = new VipReturn();
		}
		if(arg1.getSource()==mi12) {
			CustomerReturn cr = new CustomerReturn();
		}
		if(arg1.getSource()==mi13) {
			TopCD cr = new TopCD();
		}
	}
}

