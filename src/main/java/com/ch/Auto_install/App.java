package com.ch.Auto_install;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.net.UnknownHostException;
import java.sql.SQLException;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import com.ch.bean.Aa_config;
import com.ch.bean.Ap_config;
import com.ch.bean.Install;
import com.ch.bean.Jdbcbean;
import com.ch.bean.Mm_config;
import com.ch.bean.Pass_config;
import com.ch.kettle.AlterKettle;
import com.ch.sys.Files_config;
import com.ch.sys.Kettle_config;

/**
 * Hello world! 区域医疗PASS版简单安装部署工具
 *
 */
public class App extends JFrame implements ActionListener {
	private JFrame jframe;
	private Jdbcbean jdbcbean = new Jdbcbean();
	private Install install = new Install();
	private Pass_config pass_config = new Pass_config();
	private Kettle_config kettleconfig = new Kettle_config();

	private Aa_config aa_config = new Aa_config();
	private Mm_config mm_config = new Mm_config();
	private Ap_config ap_config = new Ap_config();
	private AlterKettle alterKettle = new AlterKettle();

	Files_config files_config = new Files_config();

	// 公共组件
	JLabel jLabel_1;
	JButton jbtn1_1;

	// 页面1使用的组件
	JPanel panel_1 = new JPanel(false);
	JTextField textField1;
	JTextField textField2;
	JTextField textField3;
	JTextField textField4;
	JTextField textField5;

	// 页面2使用的组件
	JPanel panel_2 = new JPanel(false);
	JTextField panel_2_textField1;
	JTextField panel_2_textField2;
	JTextField panel_2_textField3;
	JTextField panel_2_textField4;
	JTextField panel_2_textField5;
	JTextField panel_2_textField6;
	JTextField panel_2_textField7;
	JTextField panel_2_textField8;
	JTextField panel_2_textField9;
	JTextField panel_2_textField10;
	JTextField panel_2_textField11;
	JTextField panel_2_textField12;
	JTextField panel_2_textField13;
	JTextField panel_2_textField14;
	JTextField panel_2_textField15;
	JTextField panel_2_textField16;
	JTextField panel_2_textField17;
	JTextField panel_2_textField18;
	JTextField panel_2_textField19;
	JTextField panel_2_textField20;

	// 页面3使用的组件
	JPanel panel_3 = new JPanel(false);
	JTextField panel_3_textField1;
	JTextField panel_3_textField2;
	JTextField panel_3_textField3;
	JTextField panel_3_textField4;
	JTextField panel_3_textField5;
	JTextField panel_3_textField6;
	JTextField panel_3_textField7;
	JTextField panel_3_textField8;
	JTextField panel_3_textField9;
	JTextField panel_3_textField10;

	App() {

		jframe = new JFrame();
		System.out.println("简易版安装工具");
		jframe.setLocation(300, 150);// 屏幕定位
		jframe.setTitle("简易版安装工具");
		jframe.setVisible(true);
		jframe.setLayout(null);

		jframe.setDefaultCloseOperation(jframe.DO_NOTHING_ON_CLOSE);
		jframe.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				// TODO Auto-generated method stub
				int n = JOptionPane.showConfirmDialog(null, "要退出该程序吗？", "友情提示", JOptionPane.YES_NO_OPTION,
						JOptionPane.QUESTION_MESSAGE);
				if (n == 0) {
					System.out.println("关闭");
					// System.exit (0);
					jframe.setDefaultCloseOperation(jframe.EXIT_ON_CLOSE);
				} else {
					System.out.println("停止关闭");
					return;
				}
			}
		});

		// JPanel面板
		JPanel jPanel = new JPanel();
		jPanel.setLayout(new GridLayout(1, 1));
		jPanel.setBackground(Color.decode("#A6FFFF"));

		// JTabbedPane选项卡
		JTabbedPane tabbedPane = new JTabbedPane();

		// 页面1
		tabbedPane.addTab("one", new JScrollPane(ComponentPanel()));// 增加滚动条
		tabbedPane.setSelectedIndex(0);// 默认显示标签

		// 页面2
		tabbedPane.addTab("two", new JScrollPane(ComponentPanel1()));// 增加滚动条

		// 页面3
		tabbedPane.addTab("three", new JScrollPane(ComponentPanel2()));

		// 将选项卡添加到panl中
		jPanel.add(tabbedPane);

		// jframe.add(jPanel);
		jframe.setContentPane(jPanel);
		jframe.pack();
		jframe.setSize(700, 600);
		// jframe.setResizable(false);
	}

	// 选项卡1
	protected Component ComponentPanel() {
		// 流式布局
		FlowLayout fl = new FlowLayout();
		fl.setAlignment(FlowLayout.LEFT);// 靠左对齐
		panel_1.setLayout(fl);
		panel_1.setBackground(Color.decode("#FFEC8B"));

		// 箱式布局
		Box boxvertical = Box.createVerticalBox();// 垂直布局;
		// Box.createVerticalStrut(10);//垂直组件间间隔
		// Box boxHorizontal = Box.createHorizontalBox();// 水平布局;
		// Box.createHorizontalStrut(10);//水平组件间间隔

		JPanel jjj = null;

		// jdk
		jjj = new JPanel(new FlowLayout(FlowLayout.LEFT));
		jjj.setBackground(Color.decode("#FFEC8B"));

		jbtn1_1 = new JButton("1.安装JDK");
		jbtn1_1.setPreferredSize(new Dimension(140, 30));
		jbtn1_1.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				jbtn1_1.setEnabled(false);
				try {
					install.jdk_install();
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
					JOptionPane.showMessageDialog(null, "出现异常，请检查操作结果");
				}

				jbtn1_1.setEnabled(true);
			}
		});
		jjj.add(jbtn1_1);

		// 增加间距
		jjj.add(Box.createHorizontalStrut(10));

		jLabel_1 = new JLabel("<html>请在同目录下放置JDK安装程序，路径为：\\unit\\xxx.exe！！！(名字中带jdk)</html>");
		// jLabel_1.setPreferredSize(new Dimension(500,
		// 30));//根据当前窗口来调整大小，如果用setSize()强制固定大小可能会设置失败

		jjj.add(jLabel_1);
		boxvertical.add(jjj);

		// jdk设置环境变量
		jjj = new JPanel(new FlowLayout(FlowLayout.LEFT));
		jjj.setBackground(Color.decode("#FFEC8B"));

		jbtn1_1 = new JButton("2.设置环境变量");
		jbtn1_1.setPreferredSize(new Dimension(140, 30));
		jbtn1_1.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				jbtn1_1.setEnabled(false);
				// JOptionPane.showMessageDialog(null, "该功能未开放,请先手动部署");

				try {
					if ("".equals(textField1.getText()) || textField1.getText() == null) {
						JOptionPane.showMessageDialog(null, "请输入JDK安装路径");
					} else {
						// int n=JOptionPane.showConfirmDialog(null,
						// "是否需要备份系统环境变量：path", "友情提示",
						// JOptionPane.YES_NO_OPTION,
						// JOptionPane.QUESTION_MESSAGE);
						// if(n==0){
						install.setjdk_install(textField1.getText(), 0);
						// }else{
						// install.setjdk_install(jTextArea_1.getText(),1);
						// }
						JOptionPane.showMessageDialog(null, "已备份原环境变量path内容到\\unit文件夹下,环境变量设置完成");
					}
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
					JOptionPane.showMessageDialog(null, "处理失败，请查看是否修改成功");
				}

				jbtn1_1.setEnabled(true);
			}
		});
		jjj.add(jbtn1_1);

		// 增加间距
		jjj.add(Box.createHorizontalStrut(10));

		textField1 = new JTextField();
		textField1.setPreferredSize(new Dimension(200, 25));
		jjj.add(textField1);

		// 增加间距
		jjj.add(Box.createHorizontalStrut(10));

		jLabel_1 = new JLabel("<html>请输入jdk安装路径（注意：关闭360安全卫士等安全类软件，或者提示时点击允许）</html>");
		jjj.add(jLabel_1);
		boxvertical.add(jjj);

		//
		// // TOMCAT
		jjj = new JPanel(new FlowLayout(FlowLayout.LEFT));
		jjj.setBackground(Color.decode("#FFEC8B"));

		jbtn1_1 = new JButton("3.安装TOMCAT");
		jbtn1_1.setPreferredSize(new Dimension(140, 30));
		jbtn1_1.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				jbtn1_1.setEnabled(false);
				try {
					install.tomcat_install();
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
					JOptionPane.showMessageDialog(null, "出现异常，请检查操作结果");
				}

				jbtn1_1.setEnabled(true);
			}
		});
		jjj.add(jbtn1_1);

		// 增加间距
		jjj.add(Box.createHorizontalStrut(10));

		jLabel_1 = new JLabel("<html>请在同目录下放置TOMCAT安装程序，路径为：\\unit\\xxx.exe！！！(名字中带tomcat)</html>");
		jjj.add(jLabel_1);
		boxvertical.add(jjj);

		// MYSQL
		jjj = new JPanel(new FlowLayout(FlowLayout.LEFT));
		jjj.setBackground(Color.decode("#FFEC8B"));

		jbtn1_1 = new JButton("4.安装MYSQL");
		jbtn1_1.setPreferredSize(new Dimension(140, 30));
		jbtn1_1.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				jbtn1_1.setEnabled(false);
				try {
					install.mysql_install();
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
					JOptionPane.showMessageDialog(null, "出现异常，请检查操作结果");
				}

				jbtn1_1.setEnabled(true);
			}
		});
		jjj.add(jbtn1_1);

		// 增加间距
		jjj.add(Box.createHorizontalStrut(10));

		jLabel_1 = new JLabel("<html>请在同目录下放置MYSQL安装程序，路径为：\\unit\\xxx.exe！！！(名字中带mysql-installer)</html>");
		jjj.add(jLabel_1);

		boxvertical.add(jjj);

		// 填写数据库连接地址
		jjj = new JPanel(new FlowLayout(FlowLayout.LEFT));
		jjj.setBackground(Color.decode("#FFEC8B"));

		jLabel_1 = new JLabel("<html>5.请先在mysql中创建数据库，然后再继续安装步骤</html>");
		jLabel_1.setPreferredSize(new Dimension(680, 30));
		jLabel_1.setOpaque(true);// 重置颜色开关
		jLabel_1.setBackground(Color.decode("#C6E2FF"));
		jjj.add(jLabel_1);
		boxvertical.add(jjj);

		jjj = new JPanel(new FlowLayout(FlowLayout.LEFT));
		jjj.setBackground(Color.decode("#FFEC8B"));
		jLabel_1 = new JLabel("数据库IP：");
		jLabel_1.setPreferredSize(new Dimension(80, 30));
		jjj.add(jLabel_1);

		// 增加间距
		jjj.add(Box.createHorizontalStrut(10));

		textField2 = new JTextField();
		textField2.setPreferredSize(new Dimension(200, 25));
		// textField2.setColumns(10);
		jjj.add(textField2);

		// 增加间距
		jjj.add(Box.createHorizontalStrut(10));

		jLabel_1 = new JLabel("数据库名称：");
		jLabel_1.setPreferredSize(new Dimension(80, 30));
		jjj.add(jLabel_1);

		// 增加间距
		jjj.add(Box.createHorizontalStrut(10));

		textField5 = new JTextField();
		textField5.setPreferredSize(new Dimension(200, 25));
		// textField5.setColumns(10);
		jjj.add(textField5);
		boxvertical.add(jjj);

		jjj = new JPanel(new FlowLayout(FlowLayout.LEFT));
		jjj.setBackground(Color.decode("#FFEC8B"));

		jLabel_1 = new JLabel("数据库账户：");
		jLabel_1.setPreferredSize(new Dimension(80, 30));
		jjj.add(jLabel_1);

		// 增加间距
		jjj.add(Box.createHorizontalStrut(10));

		textField3 = new JTextField();
		textField3.setPreferredSize(new Dimension(200, 25));
		jjj.add(textField3);

		// 增加间距
		jjj.add(Box.createHorizontalStrut(10));

		jLabel_1 = new JLabel("数据库密码：");
		jLabel_1.setPreferredSize(new Dimension(80, 30));
		jjj.add(jLabel_1);

		// 增加间距
		jjj.add(Box.createHorizontalStrut(10));

		textField4 = new JTextField();
		textField4.setPreferredSize(new Dimension(200, 25));
		jjj.add(textField4);
		boxvertical.add(jjj);

		// // 创建表
		jjj = new JPanel(new FlowLayout(FlowLayout.LEFT));
		jjj.setBackground(Color.decode("#FFEC8B"));

		jbtn1_1 = new JButton("6.开始创建表");
		jbtn1_1.setPreferredSize(new Dimension(140, 30));
		jbtn1_1.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				jbtn1_1.setEnabled(false);
				Jdbcbean jdbcbean = new Jdbcbean();
				try {
					if ("".equals(textField2.getText()) || "".equals(textField3.getText())
							|| "".equals(textField4.getText()) || "".equals(textField5.getText())) {
						JOptionPane.showMessageDialog(null, "请输入数据库信息");
					} else {
						jdbcbean.ceatetable(textField2.getText(), textField3.getText(), textField4.getText(),
								textField5.getText());
					}

				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
					JOptionPane.showMessageDialog(null, "出现异常，请检查操作结果");
				}

				jbtn1_1.setEnabled(true);
			}
		});
		jjj.add(jbtn1_1);

		// 增加间距
		jjj.add(Box.createHorizontalStrut(10));

		jLabel_1 = new JLabel("<html>请先建数据库，在同目录下放置数据表结构sql文件，路径为：\\unit\\mysql_table\\passpa.sql！！！</html>");
		jjj.add(jLabel_1);
		boxvertical.add(jjj);

		// 创建函数
		jjj = new JPanel(new FlowLayout(FlowLayout.LEFT));
		jjj.setBackground(Color.decode("#FFEC8B"));

		jbtn1_1 = new JButton("7.开始创建函数");
		jbtn1_1.setPreferredSize(new Dimension(140, 30));
		jbtn1_1.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				jbtn1_1.setEnabled(false);
				try {
					if ("".equals(textField2.getText()) || "".equals(textField3.getText())
							|| "".equals(textField4.getText()) || "".equals(textField5.getText())) {
						JOptionPane.showMessageDialog(null, "请输入数据库信息");
					} else {
						jdbcbean.ceatehanshu(textField2.getText(), textField3.getText(), textField4.getText(),
								textField5.getText());
					}
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
					JOptionPane.showMessageDialog(null, "出现异常，请检查操作结果");
				}

				jbtn1_1.setEnabled(true);
			}
		});
		jjj.add(jbtn1_1);

		// 增加间距
		jjj.add(Box.createHorizontalStrut(10));

		jLabel_1 = new JLabel("<html>请先建数据库，在同目录下放置函数结构sql文件，路径为：\\unit\\mysql_table\\hanshu.sql！！！</html>");
		jLabel_1.setPreferredSize(new Dimension(500, 30));
		jjj.add(jLabel_1);
		boxvertical.add(jjj);

		// 创建视图
		jjj = new JPanel(new FlowLayout(FlowLayout.LEFT));
		jjj.setBackground(Color.decode("#FFEC8B"));

		jbtn1_1 = new JButton("8.开始创建视图");
		jbtn1_1.setPreferredSize(new Dimension(140, 30));
		jbtn1_1.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				jbtn1_1.setEnabled(false);
				try {
					if ("".equals(textField2.getText()) || "".equals(textField3.getText())
							|| "".equals(textField4.getText()) || "".equals(textField5.getText())) {
						JOptionPane.showMessageDialog(null, "请输入数据库信息");
					} else {
						jdbcbean.ceateview1(textField2.getText(), textField3.getText(), textField4.getText(),
								textField5.getText());
					}

				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
					JOptionPane.showMessageDialog(null, "出现异常，请检查操作结果");
				}

				jbtn1_1.setEnabled(true);
			}
		});
		jjj.add(jbtn1_1);

		// 增加间距
		jjj.add(Box.createHorizontalStrut(10));

		jLabel_1 = new JLabel("<html>请先建数据库，在同目录下放置视图结构sql文件，路径为：\\unit\\mysql_table\\view.sql！！！</html>");
		jLabel_1.setPreferredSize(new Dimension(500, 30));
		jjj.add(jLabel_1);
		boxvertical.add(jjj);

		// 导入mysql数据库数据
		jjj = new JPanel(new FlowLayout(FlowLayout.LEFT));
		jjj.setBackground(Color.decode("#FFEC8B"));

		jLabel_1 = new JLabel("<html>9.请将出厂数据导入mysql数据库中</html>");
		jLabel_1.setPreferredSize(new Dimension(680, 30));
		jLabel_1.setOpaque(true);// 重置颜色开关
		jLabel_1.setBackground(Color.decode("#C6E2FF"));
		jjj.add(jLabel_1);
		boxvertical.add(jjj);

		// 安装redis数据库
		jjj = new JPanel(new FlowLayout(FlowLayout.LEFT));
		jjj.setBackground(Color.decode("#FFEC8B"));

		jbtn1_1 = new JButton("10.安装redis数据库");
		jbtn1_1.setPreferredSize(new Dimension(140, 30));
		jbtn1_1.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				jbtn1_1.setEnabled(false);
				JOptionPane.showMessageDialog(null, "该功能未开放,请先手动部署");

				// try {
				// jdbcbean.ceateview();
				// } catch (Exception e1) {
				// // TODO Auto-generated catch block
				// e1.printStackTrace();
				// }

				jbtn1_1.setEnabled(true);
			}
		});
		jjj.add(jbtn1_1);

		// 增加间距
		jjj.add(Box.createHorizontalStrut(10));

		jLabel_1 = new JLabel("<html>请在同目录下放置redis安装文件，路径为：\\unit\\redis！！！</html>");
		jjj.add(jLabel_1);
		boxvertical.add(jjj);

		panel_1.add(boxvertical);
		return panel_1;
	}

	// 选项卡2
	protected Component ComponentPanel1() {
		// 流式布局
		FlowLayout fl = new FlowLayout();
		fl.setAlignment(FlowLayout.LEFT);// 靠左对齐
		panel_2.setLayout(fl);
		panel_2.setBackground(Color.decode("#FFEC8B"));

		// 箱式布局
		Box boxvertical = Box.createVerticalBox();// 垂直布局;
		// Box.createVerticalStrut(10);//垂直组件间间隔
		// Box boxHorizontal = Box.createHorizontalBox();// 水平布局;
		// Box.createHorizontalStrut(10);//水平组件间间隔

		JPanel jjj = null;

		jjj = new JPanel(new FlowLayout(FlowLayout.LEFT));
		jjj.setBackground(Color.decode("#FFEC8B"));

		// 修改PASS工程包配置
		jbtn1_1 = new JButton("1.修改PASS配置");
		jbtn1_1.setPreferredSize(new Dimension(140, 30));
		jbtn1_1.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				jbtn1_1.setEnabled(false);
				try {
					passconfig();// 给参数对象赋值
					if (pass_config.config(files_config)) {
						JOptionPane.showMessageDialog(null, "PASS配置修改完成");
					} else {
						JOptionPane.showMessageDialog(null, "失败，可能未找到资源文件");
					}

				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
					JOptionPane.showMessageDialog(null, "出现异常，请检查操作结果");
				}

				jbtn1_1.setEnabled(true);
			}
		});
		jjj.add(jbtn1_1);
		jjj.add(Box.createHorizontalStrut(10));

		// 修改AA工程包配置
		jbtn1_1 = new JButton("2.修改AA配置");
		jbtn1_1.setPreferredSize(new Dimension(140, 30));
		jbtn1_1.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				jbtn1_1.setEnabled(false);
				try {
					passconfig();// 给参数对象赋值
					if (aa_config.config(files_config)) {
						JOptionPane.showMessageDialog(null, "AA配置修改完成");
					} else {
						JOptionPane.showMessageDialog(null, "失败，可能未找到资源文件");
					}
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
					JOptionPane.showMessageDialog(null, "出现异常，请检查操作结果");
				}

				jbtn1_1.setEnabled(true);
			}
		});
		jjj.add(jbtn1_1);
		jjj.add(Box.createHorizontalStrut(10));
		//
		// // 修改MM工程包配置
		jbtn1_1 = new JButton("3.修改MM配置");
		jbtn1_1.setPreferredSize(new Dimension(140, 30));
		jbtn1_1.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				jbtn1_1.setEnabled(false);
				try {
					passconfig();// 给参数对象赋值
					if (mm_config.config(files_config)) {
						JOptionPane.showMessageDialog(null, "MM配置修改完成");
					} else {
						JOptionPane.showMessageDialog(null, "失败，可能未找到资源文件");
					}
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
					JOptionPane.showMessageDialog(null, "出现异常，请检查操作结果");
				}

				jbtn1_1.setEnabled(true);
			}
		});
		jjj.add(jbtn1_1);
		jjj.add(Box.createHorizontalStrut(10));

		// // 修改AP工程包配置
		jbtn1_1 = new JButton("4.修改AP配置");
		jbtn1_1.setPreferredSize(new Dimension(140, 30));
		jbtn1_1.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				jbtn1_1.setEnabled(false);
				try {
					passconfig();// 给参数对象赋值
					if (ap_config.config(files_config)) {
						JOptionPane.showMessageDialog(null, "AP配置修改完成");
					} else {
						JOptionPane.showMessageDialog(null, "失败，可能未找到资源文件");
					}
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
					JOptionPane.showMessageDialog(null, "出现异常，请检查操作结果");
				}

				jbtn1_1.setEnabled(true);
			}
		});
		jjj.add(jbtn1_1);
		jjj.add(Box.createHorizontalStrut(10));
		// box.add(jjj);
		boxvertical.add(jjj);

		// =================================配置参数===========================
		jjj = new JPanel(new FlowLayout(FlowLayout.LEFT));
		jjj.setBackground(Color.decode("#FFEC8B"));

		jLabel_1 = new JLabel("<html>将WAR包放入到.\\unit\\install-war\\目录下，修改后的WAR包名字为new</html>");
		jLabel_1.setPreferredSize(new Dimension(600, 30));
//		jLabel_1.setOpaque(true);// 重置颜色开关
//		jLabel_1.setBackground(Color.decode("#C6E2FF"));
		jjj.add(jLabel_1);
		boxvertical.add(jjj);
		
		// =================公共配置=================
		jjj = new JPanel(new FlowLayout(FlowLayout.LEFT));
		jjj.setBackground(Color.decode("#FFEC8B"));

		jLabel_1 = new JLabel("<html>公共配置：</html>");
		jLabel_1.setPreferredSize(new Dimension(600, 30));
		jLabel_1.setOpaque(true);// 重置颜色开关
		jLabel_1.setBackground(Color.decode("#C6E2FF"));
		jjj.add(jLabel_1);
		boxvertical.add(jjj);

		jjj = new JPanel(new FlowLayout(FlowLayout.LEFT));
		jjj.setBackground(Color.decode("#FFEC8B"));
		jLabel_1 = new JLabel("数据库URL：");
		jLabel_1.setPreferredSize(new Dimension(100, 30));
		jjj.add(jLabel_1);

		panel_2_textField1 = new JTextField();
		panel_2_textField1.setText(
				"jdbc:mysql://172.18.7.160:3306/passpa2db_1609_rh_v5?useUnicode=true&characterEncoding=UTF-8&autoReconnect=true");
		panel_2_textField1.setPreferredSize(new Dimension(500, 30));
		jjj.add(panel_2_textField1);
		boxvertical.add(jjj);

		// jjj = new JPanel();
		// jjj.setLayout(fl);
		// jLabel_1 = new JLabel("<html>数据库名称：</html>");
		// jLabel_1.setPreferredSize(new Dimension(80,30));
		// jjj.add(jLabel_1);
		//
		// panel_2_textField2 = new JTextField();
		// panel_2_textField2.setText("passpa2db_1609_rh_v5");
		// panel_2_textField2.setPreferredSize(new Dimension(250,30));
		// jjj.add(panel_2_textField2);
		// boxvertical.add(jjj);

		jjj = new JPanel(new FlowLayout(FlowLayout.LEFT));
		jjj.setBackground(Color.decode("#FFEC8B"));

		jLabel_1 = new JLabel("<html>数据库账户：</html>");
		jLabel_1.setPreferredSize(new Dimension(100, 30));
		jjj.add(jLabel_1);

		panel_2_textField3 = new JTextField();
		panel_2_textField3.setText("root");
		panel_2_textField3.setPreferredSize(new Dimension(200, 30));
		jjj.add(panel_2_textField3);

		jLabel_1 = new JLabel("<html>数据库密码：</html>");
		jLabel_1.setPreferredSize(new Dimension(100, 30));
		jjj.add(jLabel_1);

		panel_2_textField4 = new JTextField();
		panel_2_textField4.setText("zfmxz");
		panel_2_textField4.setPreferredSize(new Dimension(200, 30));
		jjj.add(panel_2_textField4);
		boxvertical.add(jjj);

		jjj = new JPanel(new FlowLayout(FlowLayout.LEFT));
		jjj.setBackground(Color.decode("#FFEC8B"));

		jLabel_1 = new JLabel("<html>redis-IP：</html>");
		jLabel_1.setPreferredSize(new Dimension(100, 30));
		jjj.add(jLabel_1);

		panel_2_textField7 = new JTextField();
		panel_2_textField7.setText("172.18.7.160");
		panel_2_textField7.setPreferredSize(new Dimension(200, 30));
		jjj.add(panel_2_textField7);

		jLabel_1 = new JLabel("<html>redis-密码：</html>");
		jLabel_1.setPreferredSize(new Dimension(100, 30));
		jjj.add(jLabel_1);

		panel_2_textField8 = new JTextField();
		panel_2_textField8.setText("123");
		panel_2_textField8.setPreferredSize(new Dimension(200, 30));
		jjj.add(panel_2_textField8);
		boxvertical.add(jjj);

		jjj = new JPanel(new FlowLayout(FlowLayout.LEFT));
		jjj.setBackground(Color.decode("#FFEC8B"));

		jLabel_1 = new JLabel("<html>redis-库编号：</html>");
		jLabel_1.setPreferredSize(new Dimension(100, 30));
		jjj.add(jLabel_1);

		panel_2_textField9 = new JTextField();
		panel_2_textField9.setText("0");
		panel_2_textField9.setPreferredSize(new Dimension(200, 30));
		jjj.add(panel_2_textField9);
		boxvertical.add(jjj);

		// =================PASS配置=================
		jjj = new JPanel(new FlowLayout(FlowLayout.LEFT));
		jjj.setBackground(Color.decode("#FFEC8B"));

		jLabel_1 = new JLabel("<html>PASS CORE配置：</html>");
		jLabel_1.setPreferredSize(new Dimension(600, 30));
		jLabel_1.setOpaque(true);// 重置颜色开关
		jLabel_1.setBackground(Color.decode("#C6E2FF"));
		jjj.add(jLabel_1);
		boxvertical.add(jjj);

		jjj = new JPanel(new FlowLayout(FlowLayout.LEFT));
		jjj.setBackground(Color.decode("#FFEC8B"));

		jLabel_1 = new JLabel("<html>ecache源路径：</html>");
		jLabel_1.setPreferredSize(new Dimension(100, 30));
		jjj.add(jLabel_1);

		panel_2_textField5 = new JTextField();
		panel_2_textField5.setText("D:/cache/data/");
		panel_2_textField5.setPreferredSize(new Dimension(200, 30));
		jjj.add(panel_2_textField5);

		jLabel_1 = new JLabel("<html>ecache目标路径：</html>");
		jLabel_1.setPreferredSize(new Dimension(100, 30));
		jjj.add(jLabel_1);

		panel_2_textField6 = new JTextField();
		panel_2_textField6.setText("D:/cache/datarun/");
		panel_2_textField6.setPreferredSize(new Dimension(200, 30));
		jjj.add(panel_2_textField6);
		boxvertical.add(jjj);

		jjj = new JPanel(new FlowLayout(FlowLayout.LEFT));
		jjj.setBackground(Color.decode("#FFEC8B"));

		jLabel_1 = new JLabel("<html>webservice-访问地址：</html>");
		jLabel_1.setPreferredSize(new Dimension(100, 30));
		jjj.add(jLabel_1);

		panel_2_textField10 = new JTextField();
		panel_2_textField10.setText("http://172.18.7.160:8081/pass/ws");
		panel_2_textField10.setPreferredSize(new Dimension(500, 30));
		jjj.add(panel_2_textField10);
		boxvertical.add(jjj);

		// =================AA配置=================
		jjj = new JPanel(new FlowLayout(FlowLayout.LEFT));
		jjj.setBackground(Color.decode("#FFEC8B"));

		jLabel_1 = new JLabel("<html>PASS AA配置：</html>");
		jLabel_1.setPreferredSize(new Dimension(600, 30));
		jLabel_1.setOpaque(true);// 重置颜色开关
		jLabel_1.setBackground(Color.decode("#C6E2FF"));
		jjj.add(jLabel_1);
		boxvertical.add(jjj);

		jjj = new JPanel(new FlowLayout(FlowLayout.LEFT));
		jjj.setBackground(Color.decode("#FFEC8B"));

		jLabel_1 = new JLabel("<html>AP工程路径：</html>");
		jLabel_1.setPreferredSize(new Dimension(100, 30));
		jjj.add(jLabel_1);

		panel_2_textField11 = new JTextField();
		panel_2_textField11.setText("http://172.18.7.160:8088/passrhap");
		panel_2_textField11.setPreferredSize(new Dimension(200, 30));
		jjj.add(panel_2_textField11);

		jLabel_1 = new JLabel("<html>PASSCORE工程路径：</html>");
		jLabel_1.setPreferredSize(new Dimension(100, 30));
		jjj.add(jLabel_1);

		panel_2_textField12 = new JTextField();
		panel_2_textField12.setText("http://172.18.7.160:8081/pass");
		panel_2_textField12.setPreferredSize(new Dimension(200, 30));
		jjj.add(panel_2_textField12);
		boxvertical.add(jjj);

		jjj = new JPanel(new FlowLayout(FlowLayout.LEFT));
		jjj.setBackground(Color.decode("#FFEC8B"));

		jLabel_1 = new JLabel("<html>报表文件夹路径：</html>");
		jLabel_1.setPreferredSize(new Dimension(100, 30));
		jjj.add(jLabel_1);

		panel_2_textField13 = new JTextField();
		panel_2_textField13.setText("D:\\reportDoc");
		panel_2_textField13.setPreferredSize(new Dimension(200, 30));
		jjj.add(panel_2_textField13);

		jjj = new JPanel(new FlowLayout(FlowLayout.LEFT));
		jjj.setBackground(Color.decode("#FFEC8B"));

		jLabel_1 = new JLabel("<html>AA工程端口：</html>");
		jLabel_1.setPreferredSize(new Dimension(100, 30));
		jjj.add(jLabel_1);

		panel_2_textField14 = new JTextField();
		panel_2_textField14.setText("9090");
		panel_2_textField14.setPreferredSize(new Dimension(200, 30));
		jjj.add(panel_2_textField14);
		boxvertical.add(jjj);

		// =================MM配置=================
		jjj = new JPanel(new FlowLayout(FlowLayout.LEFT));
		jjj.setBackground(Color.decode("#FFEC8B"));

		jLabel_1 = new JLabel("<html>PASS MM配置：</html>");
		jLabel_1.setPreferredSize(new Dimension(600, 30));
		jLabel_1.setOpaque(true);// 重置颜色开关
		jLabel_1.setBackground(Color.decode("#C6E2FF"));
		jjj.add(jLabel_1);
		boxvertical.add(jjj);

		jjj = new JPanel(new FlowLayout(FlowLayout.LEFT));
		jjj.setBackground(Color.decode("#FFEC8B"));

		jLabel_1 = new JLabel("<html>AA工程路径：</html>");
		jLabel_1.setPreferredSize(new Dimension(100, 30));
		jjj.add(jLabel_1);

		panel_2_textField15 = new JTextField();
		panel_2_textField15.setText("http://172.18.7.160:8088/passrhaa");
		panel_2_textField15.setPreferredSize(new Dimension(500, 30));
		jjj.add(panel_2_textField15);
		boxvertical.add(jjj);

		// jjj = new JPanel(new FlowLayout(FlowLayout.LEFT));
		// jjj.setBackground(Color.decode("#FFEC8B"));
		//
		// jLabel_1 = new JLabel("<html>中医药查询工程路径：</html>");
		// jLabel_1.setPreferredSize(new Dimension(80,30));
		// jjj.add(jLabel_1);
		//
		// panel_2_textField16 = new JTextField();
		// panel_2_textField16.setText("http://127.0.0.1:8099/passtcm/");
		// panel_2_textField16.setPreferredSize(new Dimension(550,30));
		// jjj.add(panel_2_textField16);
		// boxvertical.add(jjj);

		// =================AP配置=================
		jjj = new JPanel(new FlowLayout(FlowLayout.LEFT));
		jjj.setBackground(Color.decode("#FFEC8B"));

		jLabel_1 = new JLabel("<html>PASS AP配置：</html>");
		jLabel_1.setPreferredSize(new Dimension(600, 30));
		jLabel_1.setOpaque(true);// 重置颜色开关
		jLabel_1.setBackground(Color.decode("#C6E2FF"));
		jjj.add(jLabel_1);
		boxvertical.add(jjj);

		jjj = new JPanel(new FlowLayout(FlowLayout.LEFT));
		jjj.setBackground(Color.decode("#FFEC8B"));

		jLabel_1 = new JLabel("<html>oracle地址：</html>");
		jLabel_1.setPreferredSize(new Dimension(100, 30));
		jjj.add(jLabel_1);

		panel_2_textField17 = new JTextField();
		panel_2_textField17.setText("jdbc:oracle:thin:@172.18.7.154:1521:orcl");
		panel_2_textField17.setPreferredSize(new Dimension(500, 30));
		jjj.add(panel_2_textField17);
		boxvertical.add(jjj);

		jjj = new JPanel(new FlowLayout(FlowLayout.LEFT));
		jjj.setBackground(Color.decode("#FFEC8B"));

		jLabel_1 = new JLabel("<html>oracle账户：</html>");
		jLabel_1.setPreferredSize(new Dimension(100, 30));
		jjj.add(jLabel_1);

		panel_2_textField18 = new JTextField();
		panel_2_textField18.setText("passpa");
		panel_2_textField18.setPreferredSize(new Dimension(200, 30));
		jjj.add(panel_2_textField18);

		jLabel_1 = new JLabel("<html>oracle密码：</html>");
		jLabel_1.setPreferredSize(new Dimension(100, 30));
		jjj.add(jLabel_1);

		panel_2_textField19 = new JTextField();
		panel_2_textField19.setText("123456");
		panel_2_textField19.setPreferredSize(new Dimension(200, 30));
		jjj.add(panel_2_textField19);
		boxvertical.add(jjj);

		jjj = new JPanel(new FlowLayout(FlowLayout.LEFT));
		jjj.setBackground(Color.decode("#FFEC8B"));
		jLabel_1 = new JLabel("<html>PASSRHMM地址：</html>");
		jLabel_1.setPreferredSize(new Dimension(100, 30));
		jjj.add(jLabel_1);

		panel_2_textField20 = new JTextField();
		panel_2_textField20.setText("http://172.18.7.118:8095/passrhmm");
		panel_2_textField20.setPreferredSize(new Dimension(500, 30));
		jjj.add(panel_2_textField20);
		boxvertical.add(jjj);

		panel_2.add(boxvertical);

		return panel_2;
	}

	// 选项卡3
	protected Component ComponentPanel2() {
		// 流式布局
		FlowLayout fl = new FlowLayout();
		fl.setAlignment(FlowLayout.LEFT);// 靠左对齐
		panel_3.setLayout(fl);
		panel_3.setBackground(Color.decode("#FFEC8B"));

		// 箱式布局
		Box boxvertical = Box.createVerticalBox();// 垂直布局;

		JPanel jjj = null;

		// 修改kettle配置
		jjj = new JPanel(new FlowLayout(FlowLayout.LEFT));
		jjj.setBackground(Color.decode("#FFEC8B"));

		jbtn1_1 = new JButton("修改kettle文件配置");
		jbtn1_1.setPreferredSize(new Dimension(150, 30));
		jbtn1_1.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				jbtn1_1.setEnabled(false);
				try {
					kettleconfig();// 给参数对象赋值
					alterKettle.kettlerun(kettleconfig,panel_3_textField9.getText(),panel_3_textField10.getText());
					JOptionPane.showMessageDialog(null, "kettle配置修改完成");
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

				jbtn1_1.setEnabled(true);
			}
		});
		jjj.add(jbtn1_1);
		boxvertical.add(jjj);

		//说明
		jjj = new JPanel(new FlowLayout(FlowLayout.LEFT));
		jjj.setBackground(Color.decode("#FFEC8B"));
		
		jLabel_1 = new JLabel("将直接修改原文件，并自动备份文件到指定目录");
		jLabel_1.setPreferredSize(new Dimension(600, 30));
		jjj.add(jLabel_1);
		
		boxvertical.add(jjj);
		
		//文件路径
		jjj = new JPanel(new FlowLayout(FlowLayout.LEFT));
		jjj.setBackground(Color.decode("#FFEC8B"));
		
		jLabel_1 = new JLabel("<html>待修改kettle文件路径：</html>");
		jLabel_1.setPreferredSize(new Dimension(100, 30));
		jjj.add(jLabel_1);
		
		
		panel_3_textField9 = new JTextField(".\\unit\\kettle\\kettle_original\\");
		panel_3_textField9.setPreferredSize(new Dimension(200, 25));
		// textField2.setColumns(10);
		jjj.add(panel_3_textField9);
		
		jLabel_1 = new JLabel("<html>备份kettle文件路径：</html>");
		jLabel_1.setPreferredSize(new Dimension(100, 30));
		jjj.add(jLabel_1);
		
		panel_3_textField10 = new JTextField(".\\unit\\kettle\\kettle_bak\\");
		panel_3_textField10.setPreferredSize(new Dimension(200, 25));
		// textField2.setColumns(10);
		jjj.add(panel_3_textField10);
		boxvertical.add(jjj);
		
		// mysql配置
		jjj = new JPanel(new FlowLayout(FlowLayout.LEFT));
		jjj.setBackground(Color.decode("#FFEC8B"));

		jLabel_1 = new JLabel("<html>修改MYSQL配置：</html>");
		jLabel_1.setPreferredSize(new Dimension(600, 30));
		jLabel_1.setOpaque(true);// 重置颜色开关
		jLabel_1.setBackground(Color.decode("#C6E2FF"));
		jjj.add(jLabel_1);
		boxvertical.add(jjj);

		jjj = new JPanel(new FlowLayout(FlowLayout.LEFT));
		jjj.setBackground(Color.decode("#FFEC8B"));

		jLabel_1 = new JLabel("数据库IP：");
		jLabel_1.setPreferredSize(new Dimension(100, 30));
		jjj.add(jLabel_1);

		// 增加间距
		// jjj.add(Box.createHorizontalStrut(10));

		panel_3_textField1 = new JTextField("172.18.7.160");
		panel_3_textField1.setPreferredSize(new Dimension(200, 25));
		// textField2.setColumns(10);
		jjj.add(panel_3_textField1);

		// 增加间距
		// jjj.add(Box.createHorizontalStrut(10));

		jLabel_1 = new JLabel("数据库名称：");
		jLabel_1.setPreferredSize(new Dimension(100, 30));
		jjj.add(jLabel_1);

		// 增加间距
		// jjj.add(Box.createHorizontalStrut(10));

		panel_3_textField2 = new JTextField("pass_rh_v5");
		panel_3_textField2.setPreferredSize(new Dimension(200, 25));
		// textField5.setColumns(10);
		jjj.add(panel_3_textField2);
		boxvertical.add(jjj);

		jjj = new JPanel(new FlowLayout(FlowLayout.LEFT));
		jjj.setBackground(Color.decode("#FFEC8B"));

		jLabel_1 = new JLabel("数据库账户：");
		jLabel_1.setPreferredSize(new Dimension(100, 30));
		jjj.add(jLabel_1);

		// 增加间距
		// jjj.add(Box.createHorizontalStrut(10));

		panel_3_textField3 = new JTextField("root");
		panel_3_textField3.setPreferredSize(new Dimension(200, 25));
		jjj.add(panel_3_textField3);

		// 增加间距
		// jjj.add(Box.createHorizontalStrut(10));

		jLabel_1 = new JLabel("数据库密码：");
		jLabel_1.setPreferredSize(new Dimension(100, 30));
		jjj.add(jLabel_1);

		// 增加间距
		// jjj.add(Box.createHorizontalStrut(10));

		panel_3_textField4 = new JTextField("zfxmz");
		panel_3_textField4.setPreferredSize(new Dimension(200, 25));
		jjj.add(panel_3_textField4);
		boxvertical.add(jjj);

		// oracle配置
		jjj = new JPanel(new FlowLayout(FlowLayout.LEFT));
		jjj.setBackground(Color.decode("#FFEC8B"));

		jLabel_1 = new JLabel("<html>修改ORACLE配置：</html>");
		jLabel_1.setPreferredSize(new Dimension(600, 30));
		jLabel_1.setOpaque(true);// 重置颜色开关
		jLabel_1.setBackground(Color.decode("#C6E2FF"));
		jjj.add(jLabel_1);
		boxvertical.add(jjj);

		jjj = new JPanel(new FlowLayout(FlowLayout.LEFT));
		jjj.setBackground(Color.decode("#FFEC8B"));

		jLabel_1 = new JLabel("数据库IP：");
		jLabel_1.setPreferredSize(new Dimension(100, 30));
		jjj.add(jLabel_1);

		// 增加间距
		// jjj.add(Box.createHorizontalStrut(10));

		panel_3_textField5 = new JTextField("172.18.7.154");
		panel_3_textField5.setPreferredSize(new Dimension(200, 25));
		// textField2.setColumns(10);
		jjj.add(panel_3_textField5);

		// 增加间距
		// jjj.add(Box.createHorizontalStrut(10));

		jLabel_1 = new JLabel("数据库名称：");
		jLabel_1.setPreferredSize(new Dimension(100, 30));
		jjj.add(jLabel_1);

		// 增加间距
		// jjj.add(Box.createHorizontalStrut(10));

		panel_3_textField6 = new JTextField("orcl");
		panel_3_textField6.setPreferredSize(new Dimension(200, 25));
		// textField5.setColumns(10);
		jjj.add(panel_3_textField6);
		boxvertical.add(jjj);

		jjj = new JPanel(new FlowLayout(FlowLayout.LEFT));
		jjj.setBackground(Color.decode("#FFEC8B"));

		jLabel_1 = new JLabel("数据库账户：");
		jLabel_1.setPreferredSize(new Dimension(100, 30));
		jjj.add(jLabel_1);

		// 增加间距
		// jjj.add(Box.createHorizontalStrut(10));

		panel_3_textField7 = new JTextField("passpa");
		panel_3_textField7.setPreferredSize(new Dimension(200, 25));
		jjj.add(panel_3_textField7);

		// 增加间距
		// jjj.add(Box.createHorizontalStrut(10));

		jLabel_1 = new JLabel("数据库密码：");
		jLabel_1.setPreferredSize(new Dimension(100, 30));
		jjj.add(jLabel_1);

		// 增加间距
		// jjj.add(Box.createHorizontalStrut(10));

		panel_3_textField8 = new JTextField("123456");
		panel_3_textField8.setPreferredSize(new Dimension(200, 25));
		jjj.add(panel_3_textField8);
		boxvertical.add(jjj);

		panel_3.add(boxvertical);
		return panel_3;
	}

	// 按钮按键事件，貌似这个方法必须存在，不能删除
	public void actionPerformed(ActionEvent e) {
		if (e.getActionCommand() != null) {
			System.out.println("1111");
		}

		// if(e.getActionCommand().equals("测试")){
		// }
		// TODO Auto-generated method stub
		// if(e.getSource()==jbtn1){
		// myTableModel.getColumnCount();
		// System.out.println("aaaa"+myTableModel.getValueAt(1,2));
		// }
	}

	public void passconfig() {
		files_config.setJdbc_url(panel_2_textField1.getText());
		files_config.setJdbc_username(panel_2_textField3.getText());
		files_config.setJdbc_password(panel_2_textField4.getText());
		files_config.setSeccache_dir(panel_2_textField5.getText());
		files_config.setCache_dir(panel_2_textField6.getText());
		files_config.setRedis_host(panel_2_textField7.getText());
		files_config.setRedis_pass(panel_2_textField8.getText());
		files_config.setRedis_default_db(panel_2_textField9.getText());
		files_config.setThis_PassWebServiceUrl(panel_2_textField10.getText());
		files_config.setPassrhap_path(panel_2_textField11.getText());
		files_config.setPasscore_path(panel_2_textField12.getText());
		files_config.setBirt_savepath(panel_2_textField13.getText());
		files_config.setAA_port(panel_2_textField14.getText());
		files_config.setPassaa_path(panel_2_textField15.getText());
		// files_config.setPasstcm_path(panel_2_textField16.getText());
		files_config.setJdbc_url_oracle(panel_2_textField17.getText());
		files_config.setJdbc_username_oracle(panel_2_textField18.getText());
		files_config.setJdbc_password_oracle(panel_2_textField19.getText());
		files_config.setPassrhmm_url(panel_2_textField20.getText());

	}

	public void kettleconfig() {
		kettleconfig.setMysqlserver(panel_3_textField1.getText());
		kettleconfig.setMysqldatabase(panel_3_textField2.getText());
		kettleconfig.setMysqlusername(panel_3_textField3.getText());
		kettleconfig.setMysqlpassword(panel_3_textField4.getText());
		kettleconfig.setOrclserver(panel_3_textField5.getText());
		kettleconfig.setOrcldatabase(panel_3_textField6.getText());
		kettleconfig.setOrclusername(panel_3_textField7.getText());
		kettleconfig.setOrclpassword(panel_3_textField8.getText());

	}

	public static void main(String[] args) throws UnknownHostException, IOException {
		new App();
	}
}
