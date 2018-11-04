package system;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.*;

import javax.swing.*;

public class VipOrder extends JFrame implements ActionListener{
	static String srentdate,sreturndate,sCD_no,svno;
	
	private JLabel l,l1,l2,l3,l4;
	private JTextField t1,t2,t3,t4;
	private JButton b1,b2;
	
	public VipOrder() {
		super("会员订单生成");
		l = new JLabel("会员订单生成");
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
		
		t1 = new JTextField(17);
		t1.setFont(f1);
		t1.setBounds(230, 90, 130, 23);
		add(t1);
		
		l2 = new JLabel("会员编号:");
		l2.setFont(f1);
		l2.setBounds(100, 140, 120, 23);
		add(l2);
		
		t2 = new JTextField(17);
		t2.setFont(f1);
		t2.setBounds(230, 140, 130, 23);
		add(t2);
		
		l3 = new JLabel("出租日期:");
		l3.setFont(f1);
		l3.setBounds(100, 190, 120, 23);
		add(l3);
		
		t3 = new JTextField(17);
		t3.setFont(f1);
		t3.setBounds(230, 190, 130, 23);
		add(t3);
		
		l4 = new JLabel("归还日期:");
		l4.setFont(f1);
		l4.setBounds(100, 240, 120, 23);
		add(l4);
		
		t4 = new JTextField(10);
		t4.setFont(f1);
		t4.setBounds(230, 240, 130, 23);
		add(t4);
		
		b1 = new JButton("确认");
		b1.setFont(f1);
		b1.setBounds(120, 300, 110, 23);
		add(b1);
		
		b2 = new JButton("取消");
		b2.setFont(f1);
		b2.setBounds(260, 300, 110, 23);
		add(b2);
		
		b1.addActionListener(this);
		b2.addActionListener(this);
		
		ImageIcon icon = new ImageIcon("C:\\Users\\HP\\Desktop\\116.jpg"); 
	    Image img = icon.getImage().getScaledInstance(500, 400, Image.SCALE_FAST); // 图像缩放为适合Frame大小
	    JLabel jlabel = new JLabel(new ImageIcon(img));
	    jlabel.setBounds(0, 0,500,400);
	    getLayeredPane().add(jlabel, new Integer(Integer.MIN_VALUE));
	    JPanel jp = (JPanel) getContentPane();
	    JRootPane jp1 = (JRootPane) getRootPane();
	    jp.setOpaque(false);
	    jp1.setOpaque(false);
		
		setLayout(null);
		setResizable(false);
		setVisible(true);
		setBounds(300,100,500,400);
	}
	
	public void actionPerformed(ActionEvent arg1) {
		if(arg1.getSource()==b2) {
			dispose();
		}
		if(arg1.getSource()==b1) {
			sCD_no = t1.getText();
			svno = t2.getText();
			srentdate = t3.getText();
			sreturndate = t4.getText();
			VipRent vr = new VipRent();
			dispose();
		}
	}
}
