import handlethread.Database;

public class Main
{
	public static void main(String[] args)
	{
		System.out.println("��������������");
		Database.Init();//�������ݿ�
		new Recv_Connect().run();//��ʼ��������
	}
}
