import java.net.Socket;
import java.util.HashMap;

public class Ask_File_List implements Runnable
{
	String Username = null;
	String Route = null;
	public Ask_File_List(String Username,String Route)
	{
		this.Username = Username;
		this.Route = Route;
	}
	public void run()
	{
		HashMap<String,String> map = new HashMap<>();
		map.put("Mark", "FileListAsk");
		map.put("Username", Username);
		map.put("Route", Route);
		Send.send(Map_String.MapToString(map));
	}
}
