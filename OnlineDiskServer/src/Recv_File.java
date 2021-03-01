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
	String root = "D:\\���̴洢";
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
			socket = new Socket(Ip,Port);//���ӿͻ���
			DataOutputStream dout = new DataOutputStream(socket.getOutputStream());//�����ļ���Ϣ
			dout.writeUTF(data);
			File file = new File(Route);
			if (file.exists()) {
				System.out.println("����ͬ���ļ������ظ��ϴ���");
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
	
	// �����ļ�
	public boolean Recv() throws IOException
	{
		int length = 0;//���ͳ���
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
			// �����ļ�����ȡ�����
			file = new File(Route);
			fout = new FileOutputStream(file);
			// ������ȡ�Ļ�����
			byte[] RecvByte = new byte[BufLen];
			// ��ȡ������
			InputStream in = socket.getInputStream();
			while ((length = in.read(RecvByte, 0, RecvByte.length)) != -1)
			{
				fout.write(RecvByte, 0, length);
				fout.flush();
				totallen += length;
			}
			// �ر������
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
					System.out.println("�洢�������㣬"+Filename+"�ļ��ϴ�ʧ�ܣ�");
					return false;
				}
				System.out.println("�ѽ����ļ�:" + file.getName() + "��" + totallen + "�ֽ�");
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
			System.out.println("�ѽ����ļ�:" + file.getName() + "��" + totallen + "�ֽ�");
			return false;
		}
	}
	private boolean DeleteDir(File dir) 
    {
        if (dir.isDirectory()) 
        {
            String[] children = dir.list();
            //�ݹ�ɾ��Ŀ¼�е���Ŀ¼��
            for (int i=0; i<children.length; i++) 
            {
                boolean success = DeleteDir(new File(dir, children[i]));
                if (!success) 
                {
                    return false;
                }
            }
        }
        // Ŀ¼��ʱΪ�գ�����ɾ��
        return dir.delete();
    }
	
	
	private void UpdateDatabase()
	{
		//���Ȳ�ѯ��ʹ�õ���
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
		//��������ɵı���
		statement = "insert into log values('" + Username + "','" + Filename + "','" + FileSize +"');";
		Database.Send(statement);
		//����hash����
		statement = "insert into hash values('" + Username + "','" + Filename + "','" + Route + "','"
				+ FileHash + "');";
		System.out.println(statement);
		Database.Send(statement);
	}
}
