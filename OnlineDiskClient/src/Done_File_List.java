import java.util.HashMap;

public class Done_File_List implements Runnable
{
	Main_Window mainwindow = null;
	String data = null;
	public Done_File_List(Main_Window mainwindow,String data)
	{
		this.mainwindow = mainwindow;
		this.data = data;
	}
	public void run()
	{
		HashMap<String,String> map = Map_String.StringToMap(data);
		map.remove("Mark");
		for(String Filename:map.keySet())
		{
			mainwindow.AddOneDoneFile(Filename, map.get(Filename));
		}
	}
}
