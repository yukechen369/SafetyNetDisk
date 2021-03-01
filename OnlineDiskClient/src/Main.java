import javax.swing.UIManager;

public class Main
{
	public static void main(String[] args) throws InterruptedException
	{
		
		try {
      for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
          if ("Nimbus".equals(info.getName())) {
              javax.swing.UIManager.setLookAndFeel(info.getClassName());
              break;
          }
      }
  }catch(Exception e) {
  	System.out.println(e);
  }

//		MainWindow w = new MainWindow(new String("s@qq.com"));
		Login_Window login = new Login_Window();
		
		new Thread(new Connect("127.0.0.1", 43211, login)).start();
		new Thread(new Recv_Connect()).start();
	}
}

