package system;

import java.awt.FlowLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JRootPane;

public class Manager extends JFrame implements ActionListener{
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
	private JMenuItem mi14;
	
	public Manager() {
		super("管理员界面——欢迎 "+ManagerLogin.smname.trim()+" 登录");
		mi1 = new JMenuItem("账目查询");
		mi13 = new JMenuItem("收益查询");
		mi14 =  new JMenuItem("职工业绩");
		mi2 = new JMenuItem("碟片查询");
		mi3 = new JMenuItem("碟片管理");
		mi12 = new JMenuItem("人气碟片");
		mi4 = new JMenuItem("会员查询");
		mi5 = new JMenuItem("会员管理");
		mi6 = new JMenuItem("会员注册");
		mi7 = new JMenuItem("营业员查询");
		mi8 = new JMenuItem("营业员管理");
		mi9 = new JMenuItem("管理员管理");
		mi10 = new JMenuItem("重新登录");
		mi11 = new JMenuItem("退出系统");
		
		m1 = new JMenu("账目信息");
		m2 = new JMenu("碟片信息");
		m3 = new JMenu("会员信息");
		m4 = new JMenu("营业员信息");
		m5 = new JMenu("自身信息");
		m6 = new JMenu("系统操作");
		mb = new JMenuBar();
		
		m1.add(mi1);
		m1.add(mi13);
		m1.add(mi14);
		mb.add(m1);
		
		m2.add(mi2);
		m2.add(mi3);
		m2.add(mi12);
		mb.add(m2);
		
		m3.add(mi4);
		m3.add(mi5);
		m3.add(mi6);
		mb.add(m3);
		
		m4.add(mi7);
		m4.add(mi8);
		mb.add(m4);
		
		m5.add(mi9);
		mb.add(m5);
		
		m6.add(mi10);
		m6.add(mi11);
		mb.add(m6);
		
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
		mi14.addActionListener(this);
				
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
			SearchRentRecord sr = new SearchRentRecord();
		}
		if(arg1.getSource()==mi2) {
			SearchCD s1 = new SearchCD();
		}
		if(arg1.getSource()==mi3) {
			CDManagement c = new CDManagement();
		}
		if(arg1.getSource()==mi4) {
			SearchVip v = new SearchVip();		
		}
		if(arg1.getSource()==mi5) {
			VipManagement v = new VipManagement();
		}
		if(arg1.getSource()==mi6) {
			VipAdd vd = new VipAdd();		
		}
		if(arg1.getSource()==mi7) {
			SearchEmployee s1 = new SearchEmployee();
		}
		if(arg1.getSource()==mi8) {
			EmployeeManagement e = new EmployeeManagement();		
		}
		if(arg1.getSource()==mi9) {
			ManagerManagement m = new ManagerManagement();
		}
		if(arg1.getSource()==mi10) {
			dispose();
			Login l = new Login();
		}
		if(arg1.getSource()==mi11) {
			System.exit(0);
		}
		if(arg1.getSource()==mi12) {
			TopCD t = new TopCD();
		}
		if(arg1.getSource()==mi13) {
			Profit p = new Profit();
		}
		if(arg1.getSource()==mi14) {
			EmployeeRevenue er = new EmployeeRevenue();
		}
	}
}
