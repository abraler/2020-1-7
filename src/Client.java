import java.io.*;
import java.net.*;
import java.util.Scanner;

public class Client {
    public static void main(String[] args) throws IOException {
        Scanner in=new Scanner(System.in);
        Socket socket=new Socket();

        byte[]ip={(byte)127,(byte)0,(byte)0,(byte)1};
        InetAddress inetAddress=InetAddress.getByAddress(ip);

        SocketAddress socketAddress=new InetSocketAddress(inetAddress,8080);
        socket.connect(socketAddress);

            System.out.println("输入你要发送的消息");
            String request=in.nextLine();

            OutputStream os=socket.getOutputStream();
            PrintStream printStream=new PrintStream(os,true,"utf-8");
            printStream.println(((InetSocketAddress) socketAddress).getHostName()+"->"+request);

            printStream.flush();

            InputStream inputStream=socket.getInputStream();
            BufferedReader reader=new BufferedReader(new InputStreamReader(inputStream,"utf-8"));
            String str=reader.readLine();
            System.out.println(str);

    }
}
