package handlethread;

import java.net.Socket;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;

public class Signup implements Runnable
{
	HashMap<String,String> map = null;
	Socket socket = null;
	public Signup(Socket socket,String data)
	{
		this.socket = socket;
		map = MapString.StringToMap(data);
	}
	public void run()
	{
		//���Ȳ�ѯ�����Ƿ��ظ�
		String statement = "select * from user where mail =" + "'" + map.get("Username") + "'";
		ResultSet result = Database.Send(statement);
		try
		{
			//result.last();
			int rowCount = 0; 
			while(result.next()) { 
				rowCount++; 
			}
			//System.out.println(result.getRow());
			System.out.println(rowCount);
			
			if(result.getRow() > 0)//����Ѵ���
			{
				HashMap<String,String> map = new HashMap<>();
				map.put("Mark", "Sign Up Failed");
				Send.send(socket, MapString.MapToString(map));
				return;
			}
			
		} catch (SQLException e)
		{
			e.printStackTrace();
		}
		//ȷ��û��ע����������
		//�����User
		String Nickname = "'" + map.get("Nickname") + "'";
		String mail ="'" + map.get("Username") + "'";
		String password ="'" + map.get("Password") + "'";
		statement = "insert into user values (" 
				+ Nickname + "," + mail + "," + password + "," + "0" + "," + "48240000" + ")" + ";";
		Database.Send(statement);
		
/*		//�����capacity
		statement = "insert into capacity values("
				+ mail + "," + "0" + "," + "482400" + ")" + ";";
		Database.Send(statement);*/
		
		//����ע��ɹ�����Ϣ
		HashMap<String,String> map = new HashMap<>();
		map.put("Mark", "Sign Up Ok");
		Send.send(socket, MapString.MapToString(map));
	}
}
