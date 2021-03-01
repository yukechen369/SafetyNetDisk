import util.ShowShareInfo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;


public class Recv implements Runnable
{
	Login_Window login = null;
	Main_Window mainwindow = null;
	String Username = null;
	public Recv(Login_Window login)
	{
		this.login = login;
	}
	public void run()
	{
		try
		{
			InputStream in = Connect.socket.getInputStream();
			BufferedReader br = new BufferedReader(new InputStreamReader(in));
			while(true)
			{
				String data = br.readLine();
				String Mark = Map_String.StringToMap(data).get("Mark");
				switch(Mark)
				{
				case "Sign Up Ok":login.signup.dispose();break;
				case "Sign Up Failed":login.signup.MailError.setText("邮箱已注册");break;
				case "Login Ok":login.dispose();
							Username = Map_String.StringToMap(data).get("Username");
							mainwindow = new Main_Window(Username);break;
				case "Login Failed":login.Error.setText("用户名或密码错误");break;
				case "FileList":new Thread(new Add_File_List(mainwindow,Connect.socket,data)).start();
								break;
				case "UserInfo":new Thread(new User_Info(mainwindow,data)).start();break;
				case "DoneFileList":new Thread(new Done_File_List(mainwindow,data)).start();break;
				case "Share":new Thread(new ShowShareInfo(data)).start();break;

				}
			}
		} catch (IOException e)
		{
			e.printStackTrace();
		}
	}
}
