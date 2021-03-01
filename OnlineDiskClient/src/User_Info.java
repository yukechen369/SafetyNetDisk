import java.util.HashMap;

public class User_Info implements Runnable
{
	String Nickname = null;
	String UsedAmount = null;
	String TotalAmount = null;
	String Percent = null;
	Main_Window mainwindow = null;
	public User_Info(Main_Window mainwindow,String data)
	{
		HashMap<String,String> map = Map_String.StringToMap(data);
		Nickname = map.get("Nickname");
		UsedAmount = map.get("UsedCapacity");
		TotalAmount = map.get("TotalCapacity");
		Percent = map.get("Percent");
		this.mainwindow = mainwindow;
	}
	public void run()
	{
		mainwindow.Nickname.setText(Nickname);
		mainwindow.SetCapacity(UsedAmount, TotalAmount, Integer.valueOf(Percent));
	}
}
