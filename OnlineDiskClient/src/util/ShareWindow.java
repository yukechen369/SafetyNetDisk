package util;

import javax.swing.*;
import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.datatransfer.Transferable;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class ShareWindow extends JFrame {
    public String context = "";
    public ShareWindow(String link,String password){
        Image icon = Toolkit.getDefaultToolkit().getImage("D:\\360安全浏览器下载\\18c5838d58ab7d98bd1bd40ca886d6b1.png");
        setIconImage(icon);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setTitle("分享成功");    //设置显示窗口标题
        setSize(500,200);    //设置窗口显示尺寸

        JPanel panel = new JPanel();

        JLabel linkLabel=new JLabel("链接："+link);
        linkLabel.setFont(new Font("宋体", Font.PLAIN, 16));
        JLabel pwdLabel=new JLabel("密码："+password);
        pwdLabel.setFont(new Font("宋体", Font.PLAIN, 16));

        context = "链接：" + link + "\n" + "密码：" + password;

        panel.setLayout(null);
        this.setLayout(null);

        JButton copy = new JButton();
        copy.setBackground(new Color(100, 186, 243));
        copy.setBounds(160, 100, 80, 26);
        copy.setEnabled(true);
        copy.setBorder(null);
        copy.setText("复制");
        copy.requestFocusInWindow();
        copy.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                copyActionPerformed();
            }
        });


        panel.add(linkLabel);
        panel.add(pwdLabel);
        linkLabel.setBounds(20,30,400,30);
        pwdLabel.setBounds(20,60,400,30);
        panel.setBounds(0,0,500,500);
           //获取当前窗口的内容窗格
           //将标签组件添加到内容窗格上
        this.add(panel);
        this.add(copy);
        int windowWidth = this.getWidth(); //获得窗口宽
        int windowHeight = this.getHeight(); //获得窗口高
        Toolkit kit = Toolkit.getDefaultToolkit(); //定义工具包
        Dimension screenSize = kit.getScreenSize(); //获取屏幕的尺寸
        int screenWidth = screenSize.width; //获取屏幕的宽
        int screenHeight = screenSize.height; //获取屏幕的高
        this.setLocation(screenWidth/2-windowWidth/2, screenHeight/2-windowHeight/2);//设置窗口居中显示


        setVisible(true);    //设置窗口是否可见
    }
    protected void copyActionPerformed() {
        // TODO Auto-generated method stub
        Clipboard sysc=Toolkit.getDefaultToolkit().getSystemClipboard();
        Transferable tText=new StringSelection(context);
        sysc.setContents(tText, null);
        JOptionPane.showMessageDialog(null, "复制成功!","系统提示",JOptionPane.INFORMATION_MESSAGE);
    }
    public static void main(String[] agrs)
    {
        new ShareWindow("www.baidu.com","292329");    //创建一个实例化对象
    }
}
