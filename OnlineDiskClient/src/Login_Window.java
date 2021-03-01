import util.ValidCode;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;

public class Login_Window extends JFrame implements KeyListener
{
	public static ValidCode vcode;

	Container container = null;
	JPanel UpPanel = null;
	JPanel DownPanel = null;
	JPanel RightPanel = null;
	JTextField UsernameText = null;
	JTextField ValidCodeText = null;

	JTextField PasswordText = null;
	JTextField PasswordTipText = null;
	JLabel Error = null;
	JCheckBox RemPassword = null;
	JCheckBox AutoLogin = null;
	JButton LoginButton = null;
	Signup signup = null;
	String Key = "2fh;mvok3;faojdjojf3n2";
	public String validCode;
	public Point origin = new Point();

	public Login_Window()
	{
		Init();
		MakeUpPanel();
		MakeRightPanel();
		this.setVisible(true);
	}
	
	public void Init()
	{
		this.setBounds(330, 150, 480, 620);
		this.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		this.setUndecorated(true);
		this.setTitle("BUPT网盘 - 登录");
		container = this.getContentPane();
		container.setLayout(null);
		UpPanel = new JPanel()
		{
			public void paintComponent(Graphics g)
			{
				super.paintComponent(g);
				g.setColor(Color.BLACK);
			}
		};
		UpPanel.setBounds(0, 0, 480, 88);
		UpPanel.setLayout(null);
		UpPanel.setBackground(new Color(245,245,220));
		container.add(UpPanel);

		DownPanel = new JPanel()
		{
			public void paintComponent(Graphics g)
			{
				super.paintComponent(g);
				g.setColor(Color.BLACK);
			}
		};
		DownPanel.setBounds(0, 88, 480, 592);
		DownPanel.setLayout(null);
		DownPanel.setBackground(new Color(245,245,220));
		container.add(DownPanel);

		RightPanel = new JPanel()
		{
			public void paintComponent(Graphics g)
			{
				super.paintComponent(g);
				g.setColor(new Color(138, 209, 244));
			}
		};
		RightPanel.setBounds(90, 40 , 480, 592);
		RightPanel.setLayout(null);
		RightPanel.setBackground(new Color(245,245,220));
		DownPanel.add(RightPanel);
	}

	public void MakeUpPanel()
	{
		
		ImageIcon ico = new ImageIcon("左上角(登录界面).jpg");
		Image temp = ico.getImage().getScaledInstance(80, 60, ico.getImage().SCALE_DEFAULT);
		ico = new ImageIcon(temp);
		JLabel Image = new JLabel(ico);
		Image.setBounds(13, 10, 80, 60);
		//UpPanel.add(Image);
		// 标题
		JLabel Title = new JLabel();
		Font f = new Font("黑体", Font.BOLD, 27);
		Title.setForeground(new Color(238, 245, 255));
		Title.setFont(f);
		Title.setText("网盘");
		Title.setBounds(100, 16, 100, 50);
		//UpPanel.add(Title);
		// 最小化按钮
		JButton MinButton = new JButton();
		MinButton.setBounds(430, 3, 20, 20);
		MinButton.setLayout(null);
		MinButton.setBackground(new Color(245,245,220));
		MinButton.setBorderPainted(false);
		Login_Window this_temp = this;
		MinButton.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent arg0)
			{
				this_temp.setExtendedState(JFrame.ICONIFIED);
			}
		});

		ico = new ImageIcon("最小化.jpg");
		temp = ico.getImage().getScaledInstance(12, 12, ico.getImage().SCALE_DEFAULT);
		ico = new ImageIcon(temp);
		Image = new JLabel(ico);
		Image.setBounds(0, 0, 12, 12);
		
		JLabel zuixiaohua = new JLabel();
		zuixiaohua.setText("—");
		zuixiaohua.setBounds(4, 5, 12, 12);
		MinButton.add(zuixiaohua);
		UpPanel.add(MinButton);
		// 退出按钮
		JButton ExitButton = new JButton();
		ExitButton.setBounds(452, 3, 20, 20);
		ExitButton.setLayout(null);
		ExitButton.setBackground(new Color(	245,245,220));
		ExitButton.setBorderPainted(false);
		ExitButton.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent arg0)
			{
				System.exit(0);
			}
		});

		ico = new ImageIcon("退出.jpg");
		temp = ico.getImage().getScaledInstance(12, 12, ico.getImage().SCALE_DEFAULT);
		ico = new ImageIcon(temp);
		Image = new JLabel(ico);
		Image.setBounds(5, 5, 12, 12);
		
		JLabel tuichu = new JLabel();
		tuichu.setText("X");
		tuichu.setBounds(6, 5, 12, 12);
		ExitButton.add(tuichu);
		UpPanel.add(ExitButton);
		UpPanel.addMouseListener(new MouseAdapter()
		{
			public void mousePressed(MouseEvent e)
			{
				origin.x = e.getX();
				origin.y = e.getY();
			}
		});
		UpPanel.addMouseMotionListener(new MouseMotionAdapter()
		{
			public void mouseDragged(MouseEvent e)
			{
				Point p = this_temp.getLocation();
				this_temp.setLocation(p.x + e.getX() - origin.x, p.y + e.getY() - origin.y);
			}
		});
	}

	

	private void AddGirlImage()
	{
		ImageIcon ico = new ImageIcon("buptlogo.png");
		JLabel Image = new JLabel(ico);
		Image.setBounds(70, 20, 176, 43);
		RightPanel.add(Image);
	}

	private void AddTitle()
	{
		
	}

	private void AddErrorLabel()
	{
		// 错误标签
		Error = new JLabel();
		Error.setBounds(125, 245, 200, 30);
		Error.setForeground(new Color(252, 109, 123));
		Font f = new Font("黑体", Font.PLAIN, 13);
		Error.setFont(f);
		Error.setText("");
		RightPanel.add(Error);
	}

	private void AddUsername()
	{
		// 用户名
		UsernameText = new JTextField("请输入用户名");
		UsernameText.setBounds(40, 95, 240, 27);
		UsernameText.setForeground(Color.GRAY);
		UsernameText.addKeyListener(this);
		RightPanel.add(UsernameText);
		UsernameText.addFocusListener(new FocusAdapter()
		{
			@Override
			public void focusGained(FocusEvent e)
			{
				if (UsernameText.getText().equals("请输入用户名"))
				{
					UsernameText.setText("");
					UsernameText.setForeground(Color.BLACK);
				}
			}

			@Override
			public void focusLost(FocusEvent e)
			{
				if (UsernameText.getText().equals(""))
				{
					UsernameText.setForeground(Color.GRAY);
					UsernameText.setText("请输入用户名");
				}
			}
		});
		// 用户名前面的图片
		ImageIcon ico = new ImageIcon("user.png");
		Image temp = ico.getImage().getScaledInstance(20, 20, ico.getImage().SCALE_DEFAULT);
		ico = new ImageIcon(temp);
		JLabel Image = new JLabel(ico);
		Image.setBounds(17, 95, 20, 20);
		RightPanel.add(Image);
	}

	private void AddPassword()
	{
		// 密码
		PasswordText = new JPasswordField();
		PasswordText.setBounds(40, 140, 240, 27);
		PasswordText.setText("");
		PasswordText.setForeground(Color.GRAY);
		PasswordText.setVisible(false);
		PasswordText.addKeyListener(this);
		RightPanel.add(PasswordText);

		PasswordTipText = new JTextField();
		PasswordTipText.setBounds(40, 140, 240, 27);
		PasswordTipText.setText("请输入密码");
		PasswordTipText.setForeground(Color.GRAY);
		RightPanel.add(PasswordTipText);
		PasswordTipText.addFocusListener(new FocusAdapter()
		{
			@Override
			public void focusGained(FocusEvent e)
			{
				PasswordTipText.setVisible(false);
				PasswordText.setVisible(true);
				PasswordText.requestFocusInWindow();
			}
		});
		PasswordText.addFocusListener(new FocusAdapter()
		{
			@Override
			public void focusGained(FocusEvent e)
			{
				if (PasswordText.getText().equals("请输入密码"))
				{
					PasswordText.setText("");
					PasswordText.setForeground(Color.BLACK);
				}
			}

			@Override
			public void focusLost(FocusEvent e)
			{
				if (PasswordText.getText().equals(""))
				{
					PasswordTipText.setText("请输入密码");
					PasswordTipText.setVisible(true);
					PasswordText.setVisible(false);
				}
			}
		});
		// 密码前面的图片
		ImageIcon ico = new ImageIcon("password.png");
		JLabel Image = new JLabel(ico);
		Image.setBounds(16, 140, 20, 20);
		RightPanel.add(Image);
	}
	private void AddValidCode(){
//		// 验证码输入框
		ValidCodeText = new JTextField("请输入验证码");
		ValidCodeText.setBounds(40, 185, 140, 27);
		ValidCodeText.setForeground(Color.GRAY);
		ValidCodeText.addKeyListener(this);
		RightPanel.add(ValidCodeText);
		ValidCodeText.addFocusListener(new FocusAdapter()
		{
			@Override
			public void focusGained(FocusEvent e)
			{
				if (ValidCodeText.getText().equals("请输入验证码"))
				{
					ValidCodeText.setText("");
					ValidCodeText.setForeground(Color.BLACK);
				}
			}

			@Override
			public void focusLost(FocusEvent e)
			{
				if (ValidCodeText.getText().equals(""))
				{
					ValidCodeText.setForeground(Color.GRAY);
					ValidCodeText.setText("请输入验证码");
				}
			}
		});

		



		vcode = new ValidCode();
		vcode.setBounds(185,185,100,20);
		RightPanel.add(Box.createGlue());
		RightPanel.add(vcode);
	}
	
	private void AddCode(){

	}
	private void AddRemPassword()
	{
		// 记住密码前面的复选框
		RemPassword = new JCheckBox();
		RemPassword.setBounds(56, 223, 20, 20);
		RemPassword.setBackground(new Color(245,245,220));
		RemPassword.addKeyListener(this);
		RemPassword.addMouseListener(new MouseAdapter()
				{
					public void mouseClicked(MouseEvent e)
					{
						if(!RemPassword.isSelected())
							AutoLogin.setSelected(false);
					}
				});
		RightPanel.add(RemPassword);
		// 记住密码标签
		JLabel RemPassLabel = new JLabel();
		RemPassLabel.setBounds(76, 217, 56, 30);
		RemPassLabel.setForeground(new Color(102, 102, 102));
		Font f = new Font("黑体", Font.PLAIN, 13);
		RemPassLabel.setFont(f);
		RemPassLabel.setText("记住密码");
		RemPassLabel.addMouseListener(new MouseAdapter()
		{
			public void mouseClicked(MouseEvent e)
			{
				RemPassword.setSelected(!RemPassword.isSelected());
				if(!RemPassword.isSelected())
					AutoLogin.setSelected(false);
			}
		});
		RightPanel.add(RemPassLabel);
		//如果有存储的密码，优先使用存储的
		try
		{
			File file = new File("Password.txt");
			FileReader filereader = new FileReader(file);
			BufferedReader br = new BufferedReader(filereader);
			String Username = null;
			Username = br.readLine();
			if(Username == null || Username.isEmpty())
			{
				br.close();
				return;
			}
			String Password = Triple_DES.decrypt(br.readLine(), Key);
			br.close();
			UsernameText.setText(Username);
			PasswordText.setText(Password);
			PasswordTipText.setVisible(false);
			RemPassword.setSelected(true);
			PasswordText.setVisible(true);
		} catch (Exception e1)
		{
		}
	}

	private void AddAuto()
	{
		// 自动登录前面的复选框
		AutoLogin = new JCheckBox();
		AutoLogin.setBounds(170, 223, 20, 20);
		AutoLogin.setBackground(new Color(245,245,220));
		AutoLogin.addKeyListener(this);
		AutoLogin.addMouseListener(new MouseAdapter()
				{
					public void mouseClicked(MouseEvent e)
					{
						if(AutoLogin.isSelected())
							RemPassword.setSelected(true);
					}
				});
		RightPanel.add(AutoLogin);
		// 自动登录标签
		JLabel AutoLabel = new JLabel();
		AutoLabel.setBounds(190, 217, 56, 30);
		AutoLabel.setForeground(new Color(102, 102, 102));
		Font f = new Font("黑体", Font.PLAIN, 13);
		AutoLabel.setFont(f);
		AutoLabel.setText("自动登录");
		AutoLabel.addMouseListener(new MouseAdapter()
		{
			public void mouseClicked(MouseEvent e)
			{
				AutoLogin.setSelected(!AutoLogin.isSelected());
				if(AutoLogin.isSelected())
					RemPassword.setSelected(true);
			}
		});
		RightPanel.add(AutoLabel);
		AutoLogin.addChangeListener(new ChangeListener()
				{
					public void stateChanged(ChangeEvent arg0)
					{
						if((!AutoLogin.isSelected()) && Error.getText().equals("正在自动登录..."))
							Error.setText("");
					}
				});
	}

	private void AddLoginButton()
	{
		
		
		
		LoginButton = new JButton();
		LoginButton.setForeground(new Color(236, 247, 255));
		LoginButton.setBounds(40, 280, 100, 34);
		LoginButton.setBorder(null);
		LoginButton.setBackground(new Color(0,160,233));
		Font f = new Font("黑体", Font.PLAIN, 18);
		LoginButton.setFont(f);
		LoginButton.setText("登录");
		
		LoginButton.setEnabled(false);
		LoginButton.requestFocusInWindow();
		Login_Window this_temp = this;
		LoginButton.addKeyListener(this);
		LoginButton.addMouseListener(new MouseAdapter()
		{

			public void mouseClicked(MouseEvent e)
			{
				if(LoginButton.isEnabled() == false)
					return;

				if(UsernameText.getText().isEmpty()){//没输入用户名
					Error.setText("请输入用户");
					return;
				}
				if(PasswordText.getText().isEmpty()) {//没输入密码
					Error.setText("请输入密码");
					return;
				}
				if(ValidCodeText.getText().isEmpty()||ValidCodeText.getText().equals("")
				||ValidCodeText.getText().equals("请输入验证码")) {//没输入密码
					Error.setText("请输入验证码");
					return;
				}else if (!ValidCodeText.getText().toLowerCase().equals(Login_Window.vcode.code.toLowerCase())){
					Error.setText("验证码错误");
					ValidCodeText.setText("");
					Login_Window.vcode.nextCode();
					return;
				}
				HashMap<String,String> map = new HashMap<>();
				map.put("Mark", "Login");
				map.put("Username", UsernameText.getText());
				try {
					map.put("Password", MD5.encrypt(PasswordText.getText()));
				} catch (Exception e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}
				Send.send(Map_String.MapToString(map));
				//如果选了记住密码，把密码保存到本地
				if(RemPassword.isSelected())
				{
					File file = new File("Password.txt");
					try
					{
						FileWriter filewriter = new FileWriter(file); 
						BufferedWriter bw = new BufferedWriter(filewriter);
						bw.write(UsernameText.getText() + "\r\n");
						bw.write(Triple_DES.encrypt(PasswordText.getText(), Key));
						bw.close();
						filewriter.close();
					} catch (Exception e1)
					{
						e1.printStackTrace();
					}
				}
				else
				{
					File file = new File("Password.txt");
					file.delete();
				}
				//是否自动登录，记录到文件中
				File file = new File("AutoLogin.txt");
				if(AutoLogin.isSelected())
				{
					try
					{
						file.createNewFile();
					} catch (IOException e1)
					{
					}
				}
				else
				{
					file.delete();
				}
			}
		});
		RightPanel.add(LoginButton);
	}

	private void Signup()
	{
		
		JLabel NoAccount = new JLabel();
		NoAccount.setBounds(30, 300, 100, 30);
		NoAccount.setForeground(Color.GRAY);
		Font f = new Font("黑体", Font.PLAIN, 13);
		NoAccount.setFont(f);
		// 注册按钮
		JButton SignupButton = new JButton();
		SignupButton = new JButton();
		SignupButton.setForeground(new Color(0,160,233));
		SignupButton.setBounds(170, 280, 110, 34);
		SignupButton.setBackground(new Color(236, 247, 255));
		f = new Font("黑体", Font.PLAIN, 18);
		SignupButton.setFont(f);
		SignupButton.setText("注册");
		SignupButton.setFocusable(false);
		SignupButton.addMouseListener(new MouseAdapter()
		{
			public void mouseEntered(MouseEvent e)
			{
				setCursor(new Cursor(Cursor.HAND_CURSOR));
			}

			public void mouseExited(MouseEvent e)
			{
				setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			}
			public void mouseClicked(MouseEvent e)
			{
				signup = new Signup();
			}
		});
		RightPanel.add(SignupButton);
	}

	public void MakeRightPanel()
	{
		AddGirlImage();
		AddTitle();
		AddErrorLabel();
		AddUsername();
		AddPassword();
		AddValidCode();
		AddRemPassword();
		AddAuto();
		AddLoginButton();
		Signup();
	}

	public void AutoLogin()
	{
		
		File file1 = new File("AutoLogin.txt");
		File file2 = new File("Password.txt");
		if(file1.exists())
			AutoLogin.setSelected(true);
		else
			AutoLogin.setSelected(false);
		
		if(file1.exists() && file2.exists() && AutoLogin.isSelected())
		{
			Error.setText("正在自动登录...");
			try
			{
				Thread.sleep(2000);
			} catch (InterruptedException e)
			{
			}
			if(!AutoLogin.isSelected())
				return;
			if(LoginButton.isEnabled() == false)
				return;
			if(UsernameText.getText().isEmpty())//没输入用户名
				Error.setText("请输入用户名");
			if(PasswordText.getText().isEmpty())//没输入密码
				Error.setText("请输入密码");
			HashMap<String,String> map = new HashMap<>();
			map.put("Mark", "Login");
			map.put("Username", UsernameText.getText());
			try {
				map.put("Password", MD5.encrypt(PasswordText.getText()));
			} catch (Exception e2) {
				// TODO Auto-generated catch block
				e2.printStackTrace();
			}
			Send.send(Map_String.MapToString(map));
			
			//如果选了记住密码，把密码保存到本地
			if(RemPassword.isSelected())
			{
				try
				{
					FileWriter filewriter = new FileWriter(file2); 
					BufferedWriter bw = new BufferedWriter(filewriter);
					bw.write(UsernameText.getText() + "\r\n");
					bw.write(Triple_DES.encrypt(PasswordText.getText(), Key));
					bw.close();
					filewriter.close();
				} catch (Exception e1)
				{
					e1.printStackTrace();
				}
			}
			else
			{
				file2.delete();
			}
			//是否自动登录，记录到文件中
			if(AutoLogin.isSelected())
			{
				try
				{
					file1.createNewFile();
				} catch (IOException e1)
				{
				}
			}
			else
			{
				file1.delete();
			}
		}
	}
	
	@Override
	public void keyPressed(KeyEvent e)
	{
		
		
	}

	@Override
	public void keyReleased(KeyEvent e)
	{
		
		
	}

	@Override
	public void keyTyped(KeyEvent e)
	{
		if(e.getKeyChar() == '\n')
		{
			if(UsernameText.getText().isEmpty())//没输入用户名
				Error.setText("请输入用户名");
			if(PasswordText.getText().isEmpty())//没输入密码
				Error.setText("请输入密码");
			if(ValidCodeText.getText().isEmpty())//没输入密码
				Error.setText("请输入验证码");

			HashMap<String,String> map = new HashMap<>();
			map.put("Mark", "Login");
			map.put("Username", UsernameText.getText());
			try {
				map.put("Password", MD5.encrypt(PasswordText.getText()));
			} catch (Exception e2) {
				// TODO Auto-generated catch block
				e2.printStackTrace();
			}
			Send.send(Map_String.MapToString(map));
			
			//如果选了记住密码，把密码保存到本地
			if(RemPassword.isSelected())
			{
				File file = new File("Password.txt");
				try
				{
					FileWriter filewriter = new FileWriter(file); 
					BufferedWriter bw = new BufferedWriter(filewriter);
					bw.write(UsernameText.getText() + "\r\n");
					bw.write(Triple_DES.encrypt(PasswordText.getText(), Key));
					bw.close();
					filewriter.close();
				} catch (Exception e1)
				{
					e1.printStackTrace();
				}
			}
			else
			{
				File file = new File("Password.txt");
				file.delete();
			}
			//是否自动登录，记录到文件中
			File file = new File("AutoLogin.txt");
			if(AutoLogin.isSelected())
			{
				try
				{
					file.createNewFile();
				} catch (IOException e1)
				{
				}
			}
			else
			{
				file.delete();
			}
		}
	}
	
	
}
