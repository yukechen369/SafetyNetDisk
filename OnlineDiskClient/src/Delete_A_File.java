import java.util.HashMap;

public class Delete_A_File implements Runnable
{
	String Filename = null;
	String Username = null;
	public Delete_A_File(String Username,String Filename)
	{
		this.Username = Username;
		this.Filename = Filename;
	}
	public void run()
	{
		HashMap<String,String> map = new HashMap<>();
		map.put("Mark", "RemoveOneDoneFile");
		map.put("Username", Username);
		map.put("Filename", Filename);
		Send.send(Map_String.MapToString(map));
	}
}
