import java.io.*;
import java.net.*;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;

import handlethread.Database;
import handlethread.DeleteFile;
import handlethread.DoneFileList;
import handlethread.MapString;
import handlethread.Send;
import handlethread.UserInfo;

public class Recv_File implements Runnable
{
	static int BufLen = 1024;
	Socket socket = null;
	String Route = null;
	String root = "D:\\网盘存储";
	String Username = null;
	String Filename = null;
	String FileSize = null;
	String RemoteRoute = null;
	String Ip = null;
	int Port = 0;
	String FileHash = null;
	String data = null;
	HashMap<String,String> map = null;
	
	public Recv_File(Socket ClientSocket,String data,String Ip,int Port)
	{
		this.data = data;
		this.Ip = Ip;
		this.Port = Port;
		map = MapString.StringToMap(data);
		Username = map.get("Username");
		Route = map.get("Route");
		Filename = map.get("Filename");
		FileSize = map.get("FileSize");
		System.out.println(FileSize);
		RemoteRoute = map.get("RemoteRoute");
		FileHash = map.get("FileHash");
		Route = root + "\\" + Username + RemoteRoute;
	}
	public void run()
	{
		try
		{
			socket = new Socket(Ip,Port);//连接客户端
			DataOutputStream dout = new DataOutputStream(socket.getOutputStream());//发送文件信息
			dout.writeUTF(data);
			File file = new File(Route);
			if (file.exists()) {
				System.out.println("已有同名文件，勿重复上传！");
			}
			else {
				
				String statement = "select Used,Total from user where mail = '" + Username + "';";
				ResultSet result = Database.Send(statement);
				long  Used = 0;
				try{
					result.next();
					Used = Long.valueOf(result.getString("Used"));
					String newFile = FileSize.substring(0,FileSize.indexOf(" "));
					Used = Used + (long)(Double.parseDouble(newFile));
					if(Used <= Long.valueOf(result.getString("Total")))
						if(Recv())
							UpdateDatabase();
				}catch (Exception e1)
				{
					e1.printStackTrace();
					
				}
				
//				if(Recv()) 
//					UpdateDatabase();
			}
		} catch (Exception e1)
		{
			e1.printStackTrace();
			
		}
	}
	
	// 接收文件
	public boolean Recv() throws IOException
	{
		int length = 0;//发送长度
		int totallen = 0;
		File file = null;
		FileOutputStream fout = null;
		if(FileSize.equals("-"))
		{
			System.out.println("wzpz");
			Route =Route+".zip";
			Filename=Filename+".zip";
		}
		
		try
		{
			// 创建文件，获取输出流
			file = new File(Route);
			fout = new FileOutputStream(file);
			// 创建读取的缓冲区
			byte[] RecvByte = new byte[BufLen];
			// 获取输入流
			InputStream in = socket.getInputStream();
			while ((length = in.read(RecvByte, 0, RecvByte.length)) != -1)
			{
				fout.write(RecvByte, 0, length);
				fout.flush();
				totallen += length;
			}
			// 关闭输出流
			fout.close();
			
			String statement = "select Used,Total from user where mail = '" + Username + "';";
			ResultSet result = Database.Send(statement);
			long  Used = 0;
			try{
				result.next();
				Used = Long.valueOf(result.getString("Used"));
//				String newFile = FileSize.substring(0,FileSize.indexOf(" "));
//				Used = Used + (long)(Double.parseDouble(newFile));
				Used = Used + totallen;
				System.out.println('\n');
				System.out.println('\n'+Used+'\n'+Long.valueOf(result.getString("Total")));
				System.out.println('\n');
				if(Used > Long.valueOf(result.getString("Total")))
				{
					DeleteDir(file);
					System.out.println("存储容量不足，"+Filename+"文件上传失败！");
					return false;
				}
				System.out.println("已接收文件:" + file.getName() + "共" + totallen + "字节");
				return true;
			}catch (Exception e)
			{
				e.printStackTrace();
				return false;
			}	
			
		} catch (Exception e)
		{
			e.printStackTrace();
			if(fout != null)
				fout.close();
			System.out.println("已接收文件:" + file.getName() + "共" + totallen + "字节");
			return false;
		}
	}
	private boolean DeleteDir(File dir) 
    {
        if (dir.isDirectory()) 
        {
            String[] children = dir.list();
            //递归删除目录中的子目录下
            for (int i=0; i<children.length; i++) 
            {
                boolean success = DeleteDir(new File(dir, children[i]));
                if (!success) 
                {
                    return false;
                }
            }
        }
        // 目录此时为空，可以删除
        return dir.delete();
    }
	
	
	private void UpdateDatabase()
	{
		//首先查询已使用的量
//		String statement = "select Used,Total from capacity where mail = '" + Username + "';";
		String statement = "select Used,Total from user where mail = '" + Username + "';";
		ResultSet result = Database.Send(statement);
		long Used = 0;
		try
		{
			result.next();
			Used = Long.valueOf(result.getString("Used"));
			Used = Used + new File(Route).length();
//			statement = "update capacity set used = '" + String.valueOf(Used) + "' where mail = '" +
//						Username + "';";
			statement = "update user set used = '" + String.valueOf(Used) + "' where mail = '" +
					Username + "';";
			Database.Send(statement);
		} catch (SQLException e)
		{
			e.printStackTrace();
		}
		//插入已完成的表里
		statement = "insert into log values('" + Username + "','" + Filename + "','" + FileSize +"');";
		Database.Send(statement);
		//插入hash表里
		statement = "insert into hash values('" + Username + "','" + Filename + "','" + Route + "','"
				+ FileHash + "');";
		System.out.println(statement);
		Database.Send(statement);
	}
}
