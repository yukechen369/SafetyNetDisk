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
        Image icon = Toolkit.getDefaultToolkit().getImage("D:\\360��ȫ���������\\18c5838d58ab7d98bd1bd40ca886d6b1.png");
        setIconImage(icon);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setTitle("����ɹ�");    //������ʾ���ڱ���
        setSize(500,200);    //���ô�����ʾ�ߴ�

        JPanel panel = new JPanel();

        JLabel linkLabel=new JLabel("���ӣ�"+link);
        linkLabel.setFont(new Font("����", Font.PLAIN, 16));
        JLabel pwdLabel=new JLabel("���룺"+password);
        pwdLabel.setFont(new Font("����", Font.PLAIN, 16));

        context = "���ӣ�" + link + "\n" + "���룺" + password;

        panel.setLayout(null);
        this.setLayout(null);

        JButton copy = new JButton();
        copy.setBackground(new Color(100, 186, 243));
        copy.setBounds(160, 100, 80, 26);
        copy.setEnabled(true);
        copy.setBorder(null);
        copy.setText("����");
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
           //��ȡ��ǰ���ڵ����ݴ���
           //����ǩ�����ӵ����ݴ�����
        this.add(panel);
        this.add(copy);
        int windowWidth = this.getWidth(); //��ô��ڿ�
        int windowHeight = this.getHeight(); //��ô��ڸ�
        Toolkit kit = Toolkit.getDefaultToolkit(); //���幤�߰�
        Dimension screenSize = kit.getScreenSize(); //��ȡ��Ļ�ĳߴ�
        int screenWidth = screenSize.width; //��ȡ��Ļ�Ŀ�
        int screenHeight = screenSize.height; //��ȡ��Ļ�ĸ�
        this.setLocation(screenWidth/2-windowWidth/2, screenHeight/2-windowHeight/2);//���ô��ھ�����ʾ


        setVisible(true);    //���ô����Ƿ�ɼ�
    }
    protected void copyActionPerformed() {
        // TODO Auto-generated method stub
        Clipboard sysc=Toolkit.getDefaultToolkit().getSystemClipboard();
        Transferable tText=new StringSelection(context);
        sysc.setContents(tText, null);
        JOptionPane.showMessageDialog(null, "���Ƴɹ�!","ϵͳ��ʾ",JOptionPane.INFORMATION_MESSAGE);
    }
    public static void main(String[] agrs)
    {
        new ShareWindow("www.baidu.com","292329");    //����һ��ʵ��������
    }
}
