package NewDemo;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

class Client{
    public static void main(String[] args) throws UnknownHostException, IOException{
        JFrame jFrame=new JFrame("群聊");
        Toolkit toolkit=Toolkit.getDefaultToolkit();//获取一个与系统相关工具类对象
        Dimension d=toolkit.getScreenSize();//获取屏幕的分辨率
        jFrame.setBounds((int)d.getWidth()/4, (int)d.getHeight()/4, (int)d.getWidth()/2, (int)d.getHeight()/2);//设置坐标和大小

        jFrame.setLayout(null);
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//设置窗体关闭事件，窗口关闭即关闭jav


        JPanel jPanel=new JPanel();             //创建面板
        jPanel.setLayout(new BorderLayout());  //布局管理
        jPanel.setBounds(jFrame.getX()-jFrame.getWidth()/2, jFrame.getY()-jFrame.getHeight()/2, (int)d.getWidth()/2, (int)d.getHeight()/4-30);//获取窗口大小设置面板大小和位置
        jPanel.setVisible(true);

        JPanel jPane2=new JPanel();             //创建面板
        jPane2.setLayout(new BorderLayout());  //布局管理
        jPane2.setBounds(jFrame.getX()-jFrame.getWidth()/2, jFrame.getY(), (int)d.getWidth()/2, (int)d.getHeight()/4-10);
        jPane2.setVisible(true);


        JTextArea field1=new JTextArea(); //文本框
        field1.setEditable(false);   //设置为不可被编辑
        JTextArea field2=new JTextArea();

        JScrollPane jsp1=new JScrollPane();//滚动条
        jsp1.setViewportView(field1);   //添加文本框
        JScrollPane jsp2=new JScrollPane();
        jsp2.setViewportView(field2);

        jPanel.add(jsp1);
        jPane2.add(jsp2);
        jFrame.add(jPanel);
        jFrame.add(jPane2);
        field1.setVisible(true);
        field2.setVisible(true);
        jFrame.setVisible(true);//设置可见性


        String name=JOptionPane.showInputDialog("请为自己起个昵称");



        //客户端
        Socket socket = new Socket("127.0.0.1",9090);//建立tcp的客户端服务
        BufferedWriter outputStream=new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
        BufferedReader socketReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));//获取socket的输入流对象
        //设置监听器监听第二个文本框  向服务器发送该信息
        field2.addKeyListener(new KeyAdapter() {
            public void keyTyped(KeyEvent e) {
                if((char)e.getKeyChar()==KeyEvent.VK_ENTER) {
                    try {
                        outputStream.write(name+"@"+field2.getText());
                        outputStream.flush();
                    } catch (IOException e1) {
                        // TODO 自动生成的 catch 块
                        e1.printStackTrace();
                    }
                    field2.setText(null);
                }
            }
        });
        String line=null;
        while((line=socketReader.readLine())!=null){
            //读取客户端回送的数据
            field1.append(line+"\n\r");
            field1.setCaretPosition(field1.getText().length());
        }
        //关闭资源
        //socket.close();


    }
}