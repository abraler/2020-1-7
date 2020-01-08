import java.io.*;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Scanner;

public class Task implements Runnable {
    private final Socket socket;
    Scanner in=new Scanner(System.in);
    public Task(Socket socket) {
        this.socket=socket;
    }

    @Override
    public void run() {

        try {
            InputStream is=socket.getInputStream();

            InputStreamReader inputStreamReader=new InputStreamReader(is,"utf-8");

            BufferedReader bufferedReader=new BufferedReader(inputStreamReader);

            OutputStream os=socket.getOutputStream();
            PrintStream out=new PrintStream(os,true,"utf-8");

            String line=bufferedReader.readLine();
            System.out.println(line);

            out.println(line);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
