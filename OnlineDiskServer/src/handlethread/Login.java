package handlethread;

import java.net.Socket;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;

public class Login 
{
	HashMap<String,String> map = null;
	Socket socket = null;
	public boolean IsLoginOk = false;
	
	public Login(Socket socket,String data)
	{
		this.socket = socket;
		map = MapString.StringToMap(data);
		String statement = "select password from user where mail =" + "'" + map.get("Username") + "'";
		ResultSet result = Database.Send(statement);
		String password = null;
		try
		{
			System.out.println(result);

			int rowCount = 0; 
			while(result.next()) { 
				rowCount++; 
				password = result.getString("password");
			}
			//result.last();
			
//			if(result.getRow() == 0)//����û���������
			if(rowCount == 0)
			{
				HashMap<String,String> map = new HashMap<>();
				map.put("Mark", "Login Failed");
				Send.send(socket, MapString.MapToString(map));
				return;
			}
			
			System.out.println('\n');
			System.out.println(result);
			
			if(password.equals(map.get("Password")))//������ȷ
			{
				HashMap<String,String> map = new HashMap<>();
				map.put("Mark", "Login Ok");
				map.put("Username", this.map.get("Username"));
				Send.send(socket, MapString.MapToString(map));
				IsLoginOk = true;
				return;
			}
			else
			{
				HashMap<String,String> map = new HashMap<>();
				map.put("Mark", "Login Failed");
				Send.send(socket, MapString.MapToString(map));
				return;
			}
			
		} catch (SQLException e)
		{
			e.printStackTrace();
		}
	}
}
