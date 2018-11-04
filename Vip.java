package system;

import java.awt.FlowLayout;
import java.awt.Font;
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

public class Vip extends JFrame implements ActionListener{
	private JMenu m1;
	private JMenu m2;
	private JMenu m3;
	private JMenuBar mb;
	private JMenuItem mi1;
	private JMenuItem mi2;
	private JMenuItem mi3;
	private JMenuItem mi4;
	private JMenuItem mi5;
	
	public Vip() {
		super("会员界面——欢迎 "+VipLogin.svname.trim()+" 登录");
		Font f1 = new Font("宋体",Font.CENTER_BASELINE,14);
		mi1 = new JMenuItem("碟片查询");
		mi2 = new JMenuItem("租赁查询");
		mi5 = new JMenuItem("人气碟片");
		m1 = new JMenu("碟片信息");
		m2 = new JMenu("租赁信息");
		mb = new JMenuBar();
		m1.setFont(f1);
		m2.setFont(f1);
		mi1.setFont(f1);
		mi2.setFont(f1);
		mi5.setFont(f1);
		m1.add(mi1);
		m1.add(mi5);
		m2.add(mi2);
		
		mi3 = new JMenuItem("重新登录");
		mi4 = new JMenuItem("退出系统");
		m3 = new JMenu("系统操作");
		mi3.setFont(f1);
		mi4.setFont(f1);
		m3.setFont(f1);
		m3.add(mi3);
		m3.add(mi4);
				
		mb.add(m1);
		mb.add(m2);	
		mb.add(m3);
		setJMenuBar(mb);
		mi1.addActionListener(this);
		mi2.addActionListener(this);
		mi3.addActionListener(this);
		mi4.addActionListener(this);
		mi5.addActionListener(this);
		
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
			SearchVipRecord s2 = new SearchVipRecord();
		}
		if(arg1.getSource()==mi3) {
			dispose();
			Login l = new Login();
		}
		if(arg1.getSource()==mi4) {
			System.exit(0);
		}
		if(arg1.getSource()==mi5) {
			TopCD t = new TopCD();
		}
	}
}
