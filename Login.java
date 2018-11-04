package system;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.*;

public class Login extends JFrame implements ActionListener{
	private JButton b1,b2,b3,b4;
	private JMenuBar bar;
	private JMenu m;
	private JMenuItem mi;
	
	public Login() {
		super("登录界面");
		
		
		bar = new JMenuBar();
		setJMenuBar(bar);
		m = new JMenu("关于");		
		bar.add(m);
		mi = new JMenuItem("关于系统");
		m.add(mi);
				
		b1 = new JButton("会员");
		b2 = new JButton("顾客");
		b3 = new JButton("营业员");
		b4 = new JButton("管理员");
		
		b1.setBounds(30, 320, 85, 28);
		b2.setBounds(125, 320, 85, 28);
		b3.setBounds(220, 320, 85, 28);
		b4.setBounds(315, 320, 85, 28);
		
		add(b1);
		add(b2);
		add(b3);
		add(b4);
		
		b1.addActionListener(this);
		b2.addActionListener(this);
		b3.addActionListener(this);
		b4.addActionListener(this);
		mi.addActionListener(this);
		
        ImageIcon icon = new ImageIcon("C:\\Users\\HP\\Desktop\\112.jpg"); 
        Image img = icon.getImage().getScaledInstance(700, 550, Image.SCALE_FAST); // 图像缩放为适合Frame大小
        JLabel jlabel = new JLabel(new ImageIcon(img));
        jlabel.setBounds(0, 0,700, 550);
        getLayeredPane().add(jlabel, new Integer(Integer.MIN_VALUE));
        JPanel jp = (JPanel) getContentPane();
        JRootPane jp1 = (JRootPane) getRootPane();
        jp.setOpaque(false);
        jp1.setOpaque(false);
		
        this.setResizable(false);
		this.setLayout(null);
        this.setVisible(true);
        this.setBounds(300,100,700,550); 
	}
	
	public void actionPerformed(ActionEvent arg1) {
		if(arg1.getSource()==b1) {
			VipLogin v = new VipLogin();
			dispose();
		}
		if(arg1.getSource()==b2) {
			Customer c = new Customer();
			dispose();
		}
		if(arg1.getSource()==b3) {
			EmployeeLogin e = new EmployeeLogin();
			dispose();
		}
		if(arg1.getSource()==b4) {
			ManagerLogin m = new ManagerLogin();
			dispose();
		}
		if(arg1.getSource()==mi) {
			About a = new About();
		}
	}
}
