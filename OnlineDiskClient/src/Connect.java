import java.io.*;
import java.net.*;


public class Connect implements Runnable
{
	//服务器参数
	static Socket socket = null;
	String ServerIp = "127.0.0.1";
	Login_Window login = null;
	int ServerPort = 43211;
	
	public Connect(String ip,int Port,Login_Window login)
	{
		ServerIp = ip;
		ServerPort = Port;
		this.login = login;
	}
	
	public void run()
	{
		try
		{
			socket = new Socket(ServerIp,ServerPort);//连接
			login.LoginButton.setEnabled(true);
			login.AutoLogin();
			new Thread(new Recv(login)).start();
		}
		catch (IOException e)
		{
			System.out.println("连接服务器失败");
		}
	}
}
