package NewDemo;

//群聊     无法发送出去

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.List;
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
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;


class Server extends Thread{
    static ArrayList<BufferedWriter>sockets=new ArrayList<>();
    Socket socket;
    BufferedWriter l;
    public Server(Socket socket) throws IOException{
        this.socket = socket;
        l=new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
        sockets.add(new BufferedWriter(new OutputStreamWriter(socket.getOutputStream())));
    }
    public void run(){
        //服务器
        try {
            BufferedReader socketReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));//获取socket的输入流对象
            //传输数据
            String line=null;
            while((line=socketReader.readLine())!=null){
                //向其他链接的用户传输该信息
                for(BufferedWriter p:sockets) {
                    if(!p.equals(l)) {
                        p.write(line.split("@")[0]+":"+line.split("@")[1]+"\n");
                        p.flush();
                    }
                }
            }
        }catch (Exception e) {
        }
        //关闭资源
        try {
            socket.close();
        } catch (IOException e) {
            // TODO 自动生成的 catch 块
            e.printStackTrace();
        }
    }



    //服务器




    public static void main(String[] args) throws UnknownHostException, IOException{
        System.out.println();
        //建立tcp的服务端
        ServerSocket serverSocket = new ServerSocket(9090);
        //不断的接受客户端的连接
        while(true){
            Socket socket = serverSocket.accept();
            new Server(socket).start();

        }






    }
}