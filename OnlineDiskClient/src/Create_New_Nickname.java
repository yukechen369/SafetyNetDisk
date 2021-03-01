import java.util.HashMap;

public class Create_New_Nickname implements Runnable
{
	String Username = null;
	String NewNickName = null;
	
	public Create_New_Nickname(String Username,String NewNickName)
	{
		this.Username = Username;
		this.NewNickName = NewNickName;
	}
	public void run()
	{
		HashMap<String,String> map = new HashMap<>();
		map.put("Mark", "NewNickname");
		map.put("Username", Username);
		map.put("Nickname", NewNickName);
		Send.send(Map_String.MapToString(map));
		Main_Window.mainwindow.Nickname.setText(NewNickName);
	}
}
