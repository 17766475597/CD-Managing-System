package system;

import java.awt.Color;
import java.awt.Container;
import java.awt.GridLayout;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRootPane;

public class About extends JFrame{
	JLabel label = new JLabel("运行环境：Windows 10旗舰版");//创建标签并实例化
	JLabel labe2 = new JLabel("开发语言：JAVA");//创建标签并实例化
	JLabel labe3 = new JLabel("数据库类型：Sql Server 2017");//创建标签并实例化
	JLabel labe4 = new JLabel("开发人员：物联网工程学院计科1604张雨轩");//创建标签并实例化
	public About(){//构造方法
        setTitle("关于");//设置标题
 	    Container con=getContentPane();
        con.setLayout(new GridLayout(4,1));//设置网格布局
        con.add(label);//添加标签
        con.add(labe2);//添加标签
        con.add(labe3);//添加标签
        con.add(labe4);//添加标签
        
		ImageIcon icon = new ImageIcon("C:\\Users\\HP\\Desktop\\116.jpg"); 
	    Image img = icon.getImage().getScaledInstance(400, 300, Image.SCALE_FAST); // 图像缩放为适合Frame大小
	    JLabel jlabel = new JLabel(new ImageIcon(img));
	    jlabel.setBounds(0, 0,400,300);
	    getLayeredPane().add(jlabel, new Integer(Integer.MIN_VALUE));
	    JPanel jp = (JPanel) getContentPane();
	    JRootPane jp1 = (JRootPane) getRootPane();
	    jp.setOpaque(false);
	    jp1.setOpaque(false);

 	    setResizable(false);//不可更改大小
 	    setBounds(300,100,400,300);//设置大小
 	    setVisible(true);//可见
	}
}
