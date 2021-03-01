import java.net.Socket;
import java.util.HashMap;

public class Add_File_List implements Runnable
{
	Socket socket = null;
	Main_Window mainwindow = null;
	HashMap<String,String> map = null;
	public Add_File_List(Main_Window mainwindow,Socket socket,String data)
	{
		this.mainwindow = mainwindow;
		this.socket = socket;
		map = Map_String.StringToMap(data);
	}

	public void run()
	{
		map.remove("Mark");//去掉Mark字段
		int FileNum = map.size();
		String [] Filename = new String[FileNum];
		String [] FileSize = new String[FileNum];
		String [] ModTime = new String[FileNum];
		//遍历map，找到每个文件的信息
		int i = 0;
		for(String str : map.keySet())
		{
			HashMap<String,String> temp = Map_String.StringToMapX(map.get(str));
			Filename[i] = str;	
			FileSize[i] = temp.get("FileSize");
			ModTime[i] = temp.get("ModTime");
			i++;
		}
		if(Filename != null && FileSize != null && ModTime != null && mainwindow != null)
			mainwindow.AddMulTableData(Filename, FileSize, ModTime, FileNum);
	}

}
